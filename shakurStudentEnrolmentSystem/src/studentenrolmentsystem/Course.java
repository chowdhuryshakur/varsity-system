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
public class Course {
    private int ccode;
    private String cname;
    private int credit;

    public Course(int ccode, String name, int credit) {
        this.ccode = ccode;
        this.cname = name;
        this.credit = credit;
    }

    public int getCcode() {
        return ccode;
    }

    public String getName() {
        return cname;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "Course code: "+ccode+"\n"+"Course Title: "+cname; 
    }

    public Course(String cname,int ccode) {
        this.ccode = ccode;
        this.cname = cname;
    }

    

   
    
    
}
