package com.example.supervisor_sys;

import lombok.Data;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Set;
@Data
public class Incident {
    private String Ref;
    private Time Time ;
    private String Status ;
    private String MemberId;

    public boolean equals(Object obj) {
        if (obj instanceof Incident) {
            if (obj == this) return true;
            if (obj == null) return false;
            Incident member = (Incident) obj;
            if (member.Ref.equals(this.Ref)){
                return true;
            }
            return false;
        }
        return false;
    }
    public int hashCode(){
        return Ref.hashCode();
    }

}
interface IncidentDao{
    void inserer(Incident i) throws SQLException;

    void inser(Set<Incident> is) throws SQLException;
}