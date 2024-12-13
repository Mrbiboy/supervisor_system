package com.example.supervisor_sys;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddUser {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add User");

        // Create UI elements
        Label l1 = new Label("Ajouter un nouveau membre ");
        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();
        Button insertButton = new Button("Inserer");

        // Set button action
        insertButton.setOnAction(e -> {
            if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                    emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                System.out.println("Remplir tous les champs");
            } else {
                MemberDaoImpl md = new MemberDaoImpl();
                Member member = new Member(nomField.getText(), prenomField.getText(), emailField.getText(), phoneField.getText());
                try {
                    md.insrer(member);
                } catch (SQLException sqlerror) {
                    sqlerror.printStackTrace();
                }
            }
        });

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(l1, 0, 0, 2, 1);
        gridPane.add(new Label("Nom:"), 0, 1);
        gridPane.add(nomField, 1, 1);
        gridPane.add(new Label("Prenom:"), 0, 2);
        gridPane.add(prenomField, 1, 2);
        gridPane.add(new Label("Email:"), 0, 3);
        gridPane.add(emailField, 1, 3);
        gridPane.add(new Label("Phone:"), 0, 4);
        gridPane.add(phoneField, 1, 4);
        gridPane.add(insertButton, 1, 5);

        // Scene
        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
