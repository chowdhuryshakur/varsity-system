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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ScurrentCourseController implements Initializable {
     private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;

    @FXML
    private ComboBox<Semester> semesterBox;
    private ObservableList<Semester>semList;
    @FXML
    private ListView<TakenCourse> cListView;
     private ObservableList<TakenCourse>cList;
    @FXML
    private AnchorPane sCurrentCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         semList=FXCollections.observableArrayList();
        semList.addAll(getCodeList());
        semesterBox.setItems(semList);
    }    
private ArrayList<Semester>getCodeList(){
            ArrayList<Semester>semsList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from semester;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String semid = resultSet.getString("semid");
                    Semester sem = new Semester(semid);
                    semsList.add(sem);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            return semsList;
    }
  
    @FXML
    private void handleShowActionButton(ActionEvent event) {
          ArrayList<spass>passList = new ArrayList<>();
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
       
        
        ArrayList<TakenCourse>CcodeList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
              int t=passList.get(0).getSid();; Semester s = semesterBox.getValue(); 
               String se = s.getSemId();
                String query = "select * from takencourse where sid="+t+" and semid='"+se+"';";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int ccode = resultSet.getInt("ccode");
                   TakenCourse tt = new TakenCourse(ccode);
                   CcodeList.add(tt);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            cList=FXCollections.observableArrayList();
        cList.addAll(CcodeList);
        cListView.setItems(cList);
    }

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
                        sCurrentCourse.getChildren().setAll(pane);
    }

    @FXML
    private void handleHomeButtonAction(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("stdentPanel.fxml"));
                         sCurrentCourse.getChildren().setAll(pane);
    }
    
}
