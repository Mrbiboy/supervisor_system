package com.example.supervisor_sys;

import lombok.Data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Data
public class Member {
    private String id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String phone;
    private List<Incident> incidents ;

    public Member( String nom, String prenom, String email, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.id=generateRandomId(10);
    }
    private String generateRandomId(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            idBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return idBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            if (obj == this) return true;
            if (obj == null) return false;
            Member member = (Member) obj;
            if (member.id==this.id){
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
interface MemberDao{
    void insrer(Member m)throws SQLException;
    List<Incident> chargeListIncidents() throws SQLException;
    Set<Member> chargeListMembers(String file ) throws IOException;

}
