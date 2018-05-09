/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrolmentsystem;

import java.io.IOException;
import java.net.URL;
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
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane teacherPanel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEntryDataAction(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("entryData.fxml"));
                         teacherPanel.getChildren().setAll(pane);
    }

    @FXML
    private void handlseBrowseDataAction(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("browsData.fxml"));
                         teacherPanel.getChildren().setAll(pane);
    }

    @FXML
    private void handleCurrentCourseDataAction(ActionEvent event) throws IOException {
        AnchorPane pane =FXMLLoader.load(getClass().getResource("tSearchCurrentCourse.fxml"));
                         teacherPanel.getChildren().setAll(pane);
    }

    @FXML
    private void handlseEntryGradeButtonAction(ActionEvent event) throws IOException {
       AnchorPane pane =FXMLLoader.load(getClass().getResource("entryGrade.fxml"));
                         teacherPanel.getChildren().setAll(pane);
    }

    @FXML
    private void handlseLogoutButtonAction(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("loginPanel.fxml"));
                         teacherPanel.getChildren().setAll(pane);
        
    }
    
}
