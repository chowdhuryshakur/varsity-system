/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrolmentsystem;

import static com.sun.deploy.config.JREInfo.clear;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EntryDataController implements Initializable {
private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<Course> courseDropBox;
    private ObservableList<Course>courseLists;
    @FXML
    private ListView<TakenCourse> courseListView;
    private ObservableList<TakenCourse>coursess;
    private ObservableList<TakenCourse>c;
    @FXML
    private TextField semesterField;
    @FXML
    private Label entryDataLabel;
    @FXML
    private AnchorPane entryData;

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
 private void insertStudentRecord(Student student){
        int sid = student.getSid();
        String sname = student.getSname();
        String email = student.getEmail();
        String query = "insert into student values('"+sid+"','"+sname+"','"+email+"');";
        try{
            Connection connection = DriverManager.getConnection 
                     (DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);}
        catch(SQLException sqle){
            System.err.println(sqle);
        }
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
  private ArrayList<TakenCourse>getTakenCourseList(){
            ArrayList<TakenCourse>codeList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                int t=Integer.parseInt(idField.getText()); String y=semesterField.getText();
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
    private boolean handleSaveButtonAction(ActionEvent event) {
        int studentId= Integer.parseInt(idField.getText());
        String studentName=nameField.getText();
        String email = emailField.getText();
         ArrayList<Student>studentList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from student;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int sid = resultSet.getInt("sid");
                   Student s = new Student(sid);
                   studentList.add(s);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
        
        
        for(int i=0; i<studentList.size();i++)
        {   if(studentId==studentList.get(i).getSid())
                   {entryDataLabel.setText("ID is already exist in system");
                  return false;}
            
          
        }
        
       Student student = new Student(studentId,studentName,email);
                   insertStudentRecord(student);
                   entryDataLabel.setText("Successfully saved");
       idField.clear();
       nameField.clear();
       emailField.clear();
       semesterField.clear();
       courseListView.setItems(c);
    return false;
      }
   
       
    @FXML
    private boolean handleAddButtonAction(ActionEvent event) {
       
        int studentId = Integer.parseInt(idField.getText());
        Course code = courseDropBox.getValue();
        int courseCode = code.getCcode();
        String semesterId = (semesterField.getText());
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
       entryDataLabel.setText(" ");
         
        
    return false;}

    @FXML
    private void handleHomeActionButton(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("teacherPanel.fxml"));
                         entryData.getChildren().setAll(pane);
    }
    
}
