package com.example.supervisor_sys;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Set;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Interface Add User
        AddUser addUserView = new AddUser();
        addUserView.start(primaryStage);

        MemberDaoImpl membreDao = new MemberDaoImpl();
        IncidentImpl incidentDao = new IncidentImpl();

        // Path to the CSV file
        String csvFilePath = "C:\\Users\\User\\OneDrive\\Bureau\\supervisor_sys\\src\\main\\resources\\membres.csv";

        try {
            // Load members from the CSV file
            Set<Member> membres = membreDao.chargeListMembers(csvFilePath);

            // Print out loaded members
            System.out.println("Loaded members from CSV:");
            for (Member membre : membres) {
                System.out.println("ID: " + membre.getId() +
                        ", Name: " + membre.getNom() +
                        ", First Name: " + membre.getPrenom() +
                        ", Email: " + membre.getEmail() +
                        ", Phone: " + membre.getPhone());
            }

            // Add members and their incidents to the database
            System.out.println("\nSaving members and their incidents to the database...");
            for (Member member : membres) {
                try {
                    // Insert member into the database
                    membreDao.insrer(member);

                    // Create and insert an incident associated with the member
                    Incident incident = new Incident();
                    incident.setRef("INC_" + member.getId()); // Example unique reference
                    incident.setTime(Time.valueOf("10:15:00")); // Example fixed time
                    incident.setStatus("Open");
                    incident.setMemberId(member.getId()); // Associate incident with the member

                    // Insert incident into the database
                    incidentDao.inserer(incident);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("All members and their incidents saved to the database successfully!");

        } catch (IOException e) {
            System.err.println("Failed to load members from CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}