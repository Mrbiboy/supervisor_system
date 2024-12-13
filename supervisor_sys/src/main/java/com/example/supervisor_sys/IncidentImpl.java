package com.example.supervisor_sys;

import java.sql.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class IncidentImpl implements IncidentDao {
    private final static String DB_URL= "jdbc:mysql://localhost:3306/supervisor";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";
    @Override
    public void inserer(Incident i) throws SQLException {
        String query = "INSERT INTO incident(Ref, Time, Status, member_id) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, i.getRef());
            ps.setTime(2, i.getTime());
            ps.setString(3, i.getStatus());
            ps.setString(4, i.getMemberId()); // Utilisez le member_id dynamique
            if (ps.executeUpdate() == 1) {
                System.out.println("Incident inserted successfully");
            }
        }
    }



    @Override
    public void inser(Set<Incident> is) throws SQLException {
        for (Incident x : is) {
            inserer(x);
        }
    }
}