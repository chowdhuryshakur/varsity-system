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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EntryGradeController implements Initializable {
    private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;

    @FXML
    private TextField idField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField semIdField;
    @FXML
    private TextField gpaField;
    @FXML
    private Label entryResultLabel;
    @FXML
    private AnchorPane entryGrade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
private void insertGrade(Result result){
        int sid = result.getSid();
        int ccode = result.getCcode();
        String semID = result.getSemid();
        double gpa = result.getResult();
        String query = "insert into result values('"+sid+"','"+ccode+"','"+semID+"',"+gpa+");";
        try{
            Connection connection = DriverManager.getConnection 
                     (DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);}
        catch(SQLException sqle){
            System.err.println(sqle);
        }
    }
    @FXML
    private void handleSaveButtonAction(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        int ccode = Integer.parseInt(codeField.getText());
        String semId = semIdField.getText();
        double gpa = Double.parseDouble(gpaField.getText());
        
        Result r = new Result(id,ccode,semId,gpa);
        insertGrade(r);
       entryResultLabel.setText("Successfully saved");
       codeField.clear();
       gpaField.clear();
    }

    @FXML
    private void handleHomeActionButton(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("teacherPanel.fxml"));
                         entryGrade.getChildren().setAll(pane);
    }
    
}
