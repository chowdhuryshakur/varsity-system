/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrolmentsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SEntryCourseController implements Initializable {
    private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;

    @FXML
    private ComboBox<Course> courseDropBox;
    private ObservableList<Course>courseLists;
    @FXML
    private ListView<TakenCourse> courseListView;
     private ObservableList<TakenCourse>coursess;
    private ObservableList<TakenCourse>c;
    @FXML
    private Label entryDataLabel;
    @FXML
    private TextField semesterField;
    @FXML
    private AnchorPane takenCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        courseLists=FXCollections.observableArrayList();
        courseLists.addAll(getCourseList());
        courseDropBox.setItems(courseLists);
        c=FXCollections.observableArrayList();
    }    
private void insertCourse(TakenCourse course){
        int sid = course.getSid();
        int ccode = course.getCcode();
        String semid = course.getSemid();
        String query = "insert into takencourse values("+sid+","+ccode+",'"+semid+"');";
        try{
            Connection connection = DriverManager.getConnection 
                     (DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);}
        catch(SQLException sqle){
            System.err.println(sqle);
        }
    }
  private ArrayList<Course>getCourseList(){
            ArrayList<Course>courseList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from cource;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int courseCode = resultSet.getInt("ccode");
                    String courseName = resultSet.getString("cname");
                    Course course = new Course(courseName,courseCode);
                    courseList.add(course);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            return courseList;
        }
   
  ArrayList<spass>passList = new ArrayList<>();
        
                
  private ArrayList<TakenCourse>getTakenCourseList(){
            ArrayList<TakenCourse>codeList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                int t=passList.get(0).getSid(); String y=semesterField.getText();
                String query = "select * from takencourse where sid ="+t+" and semid='"+y+"';";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int courseCode = resultSet.getInt("ccode");
                    TakenCourse code = new TakenCourse(courseCode);
                    codeList.add(code);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            return codeList;
        }
    
    @FXML
    private boolean handleAddButtonAction(ActionEvent event) {
        try{
                String query = "select * from tempspass;";
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int sid = resultSet.getInt("sid");
                    String pass = resultSet.getString("pass");
                    spass s = new spass(sid,pass);
                    passList.add(s);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
        int studentId = passList.get(0).getSid();
        Course code = courseDropBox.getValue();
        int courseCode = code.getCcode();
        String semesterId = semesterField.getText();
          ArrayList<TakenCourse>codeList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
               
                String query = "select * from takencourse;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int cCode = resultSet.getInt("ccode");
                    int sid = resultSet.getInt("sid");
                    String semId = resultSet.getString("semid");
                    TakenCourse tc = new TakenCourse(sid,cCode,semId);
                    codeList.add(tc);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
             for(int i=0; i<codeList.size();i++)
                {   if(studentId==codeList.get(i).getSid()&&courseCode==codeList.get(i).getCcode()&&semesterId.equals(codeList.get(i).getSemid()))
                   {entryDataLabel.setText("Course is already exist in course list");
                   return false;}
                 }
        
        
        
        
        TakenCourse courses = new TakenCourse(studentId,courseCode,semesterId);
        insertCourse(courses);
        
        
        coursess=FXCollections.observableArrayList();
        coursess.addAll(getTakenCourseList());
       courseListView.setItems(coursess);
       entryDataLabel.setText("Successfully saved");
    return false;}

    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {
        String query = "delete from tempspass;";
        try{
            Connection connection = DriverManager.getConnection 
                     (DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);}
        catch(SQLException sqle){
            System.err.println(sqle);
        }
          AnchorPane pane =FXMLLoader.load(getClass().getResource("loginPanel.fxml"));
                        takenCourse.getChildren().setAll(pane);
    }

    @FXML
    private void handleHomeButtonAction(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("stdentPanel.fxml"));
                         takenCourse.getChildren().setAll(pane);
    }
    
}
