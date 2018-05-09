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
public class Result {
    private int sid;
    private int ccode;
    private String semid;
    private double result;

    public int getSid() {
        return sid;
    }

    public int getCcode() {
        return ccode;
    }

    public String getSemid() {
        return semid;
    }

    public double getResult() {
        return result;
    }

    public Result( int ccode, double result) {
       this.ccode = ccode;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" + "ccode=" + ccode + ", result=" + result + '}';
    }

    public Result(int sid, int ccode, String semid, double result) {
        this.sid = sid;
        this.ccode = ccode;
        this.semid = semid;
        this.result = result;
    }
    
    
}
