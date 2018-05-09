/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrolmentsystem;

/**
 *
 * @author User
 */
public class Semester {
    private String semId;
    private String semName;
    private int year;

    public Semester(String semId) {
        this.semId = semId;
    }

    public String getSemId() {
        return semId;
    }

    public String getSemName() {
        return semName;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "" + semId  ;
    }
    
    
    
}
