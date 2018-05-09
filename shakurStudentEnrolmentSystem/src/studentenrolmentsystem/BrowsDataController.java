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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BrowsDataController implements Initializable {
   private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;
    @FXML
    private ComboBox<Semester> semesterBox;
    private ObservableList<Semester>semList;
    @FXML
    private TableView<Result> resultTable;
    private ObservableList<Result>resultList;
    @FXML
    private TableColumn<Result, Number> codeColumn;
    @FXML
    private TableColumn<Result, Number> gpaColumn;
    @FXML
    private TextField idsField;
    @FXML
    private AnchorPane browsData;

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
    private ArrayList<Result>getGpaList(){
            ArrayList<Result>gpaList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
              int t=Integer.parseInt(idsField.getText()); Semester s = semesterBox.getValue(); 
               String se = s.getSemId();
                String query = "select * from result where sid="+t+" and semid='"+se+"';";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int ccode = resultSet.getInt("ccode");
                    Double result = resultSet.getDouble("result");
                    Result results = new Result(ccode,result);
                    gpaList.add(results);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            return gpaList;
    }
    @FXML
    private void handlShowButtonAction(ActionEvent event) {
        resultList=FXCollections.observableArrayList();
        resultList.addAll(getGpaList());
        resultTable.setItems(resultList);
        codeColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCcode()));
        gpaColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getResult()));
        
    }

    @FXML
    private void handleHomeActionButton(ActionEvent event) throws IOException {
         AnchorPane pane =FXMLLoader.load(getClass().getResource("teacherPanel.fxml"));
                         browsData.getChildren().setAll(pane);
    }
    
    
}
