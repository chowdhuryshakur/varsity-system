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
public class TakenCourse {
    private int sid;
    private int ccode;
    private String semid;

    public TakenCourse(int sid, int ccode, String semid) {
        this.sid = sid;
        this.ccode = ccode;
        this.semid = semid;
        
    }

    public int getSid() {
        return sid;
    }

    public int getCcode() {
        return ccode;
    }

    public String getSemid() {
        return semid;
    }

    public TakenCourse(int ccode) {
        this.ccode = ccode;
    }

    @Override
    public String toString() {
        return "Course Code: " + ccode ;
    }
    
    
}

