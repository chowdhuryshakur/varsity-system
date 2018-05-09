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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StdentPanelController implements Initializable {
     private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;
    @FXML
    private AnchorPane studentPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEntryDataAction(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("sEntryCourse.fxml"));
                        studentPanel.getChildren().setAll(pane);
        
        
    }

    @FXML
    private void handlseBrowseDataAction(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("SseeResult.fxml"));
                        studentPanel.getChildren().setAll(pane);
    }

    @FXML
    private void handleCurrentCourseButtonDataAction(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("ScurrentCourse.fxml"));
                        studentPanel.getChildren().setAll(pane);
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
                        studentPanel.getChildren().setAll(pane);
    }
    
}
