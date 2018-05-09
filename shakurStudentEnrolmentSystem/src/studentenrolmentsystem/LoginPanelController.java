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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginPanelController implements Initializable {
    private final String DB_USERNAME = "shakur999";
   private final String DB_PASSWORD = "1234";
   private final String DB_HOSTNAME = "localhost";
   private final String DB_DBNAME = "Studentsystem";
   private final String DB_URL = "jdbc:mysql://"+DB_HOSTNAME+"/"+DB_DBNAME;
   
   

    @FXML
    private Tab teacherTab;
    @FXML
    private TextField tIdField;
    @FXML
    private TextField tPasswordField;
    @FXML
    private Tab studentTab;
    @FXML
    private TextField sIdField;
    @FXML
    private TextField sPasswordField;
    @FXML
    private Label snotification;
    @FXML
    private Label tnotification;
    @FXML
    private AnchorPane loginPan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginPan.setVisible(true);
    }    

    @FXML
    private void handleTLoginButtonAction(ActionEvent event) throws IOException {
        ArrayList<tpass>tpassList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from tpass;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    String tin = resultSet.getString("teacherinitial");
                    String password = resultSet.getString("pass");
                    tpass t = new tpass(tin,password);
                    tpassList.add(t);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            
            
            String tin=tIdField.getText();
            String tp=tPasswordField.getText();
            
            for(int i=0;i<tpassList.size();i++)
            { if(tin.equals(tpassList.get(i).getTinitial()) && tp.equals(tpassList.get(i).getPass())) 
                      { {AnchorPane pane =FXMLLoader.load(getClass().getResource("teacherPanel.fxml"));
                          loginPan.getChildren().setAll(pane);}
                      tnotification.setText("  ");
                      } 
            if(!tin.equals(tpassList.get(i).getTinitial()) || !tp.equals(tpassList.get(i).getPass()))
                  {tnotification.setText("Your teacher's initial or password is incorrect");}
            } 
     tIdField.clear();
     tPasswordField.clear();
    }
private void insertpassRecord(spass pass){
        int sid = pass.getSid();
        String p = pass.getPass();
        String query = "insert into tempspass values('"+sid+"','"+p+"');";
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
    private void handleSLogInButtonActionn(ActionEvent event) throws IOException {
       
        ArrayList<spass>passList = new ArrayList<>();
            try{
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                Statement statement = connection.createStatement();
                String query = "select * from spass;";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    int sId = resultSet.getInt("sid");
                    String password = resultSet.getString("pass");
                    spass s = new spass(sId,password);
                    passList.add(s);
                    }
                }catch(SQLException sqle){
                System.err.println("sqle");}
            
            
            int sd=Integer.parseInt(sIdField.getText());
            String ss=sIdField.getText();
            String sp=sPasswordField.getText();
            spass p = new spass(sd,sp);
            insertpassRecord(p);
            for(int i=0;i<passList.size();i++)
            {  
                   String r= String.valueOf(passList.get(i).getSid());
                if(ss.equals(r)&&sp.equals(passList.get(i).getPass())){ 
                       {{AnchorPane pane =FXMLLoader.load(getClass().getResource("stdentPanel.fxml"));
                          loginPan.getChildren().setAll(pane);}}
                      snotification.setText("  ");
                      break;
                       }
           if(!ss.equals(r)||!sp.equals(passList.get(i).getPass()))
                  {snotification.setText("Your ID or password is incorrect");}
            } 
            
              sIdField.clear();
              sPasswordField.clear(); 
              
    }

} 
    

