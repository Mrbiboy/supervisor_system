package com.example.supervisor_sys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemberDaoImpl implements MemberDao{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/supervisor";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    @Override
    public void insrer(Member m) throws SQLException{
        String query = "INSERT INTO membre VALUES(?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1,m.getId());
            ps.setString(2, m.getNom());
            ps.setString(3, m.getPrenom());
            ps.setString(4, m.getEmail());
            ps.setString(5, m.getPhone());
            if (ps.executeUpdate() == 1){
                System.out.println("Membre inserted successfully");
            }
        }

    }
    @Override
    public List<Incident> chargeListIncidents() throws SQLException{
        String query = "SELECT * FROM incident";
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            List<Incident> incidents = new ArrayList<>();
            while(rs.next()){
                Incident i = new Incident();
                i.setRef(rs.getString("Ref"));
                i.setTime(rs.getTime("Time"));
                i.setStatus(rs.getString("Status"));
                incidents.add(i);
            }
            return incidents;
        }

    }
    public Set<Member> chargeListMembers(String file ) throws IOException {
        Set<Member> set = new HashSet();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] element = line.split(",");
            set.add(new Member(element[0], element[1], element[2], element[3]));
        }
        br.close();
        return set;
    }
}

