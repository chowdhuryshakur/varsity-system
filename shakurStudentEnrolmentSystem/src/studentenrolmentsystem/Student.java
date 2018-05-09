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
public class Student {
    private int sid;
    private String sname;
    private String email;

    public Student(int sid, String sname, String email) {
        this.sid = sid;
        this.sname = sname;
        this.email = email;
    }
    public Student(int sid)
   {this.sid = sid;}

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "student{" + "sid=" + sid + ", sname=" + sname + ", email=" + email + '}';
    }
    
    
 }


