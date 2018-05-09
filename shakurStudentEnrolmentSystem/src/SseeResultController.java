/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SseeResultController implements Initializable {

    @FXML
    private TableColumn<?, ?> gpaColumn;
    @FXML
    private ComboBox<?> courseDropBox;
    @FXML
    private TableView<?> gpaTable;
    @FXML
    private TableColumn<?, ?> cColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleShowButtonAction(ActionEvent event) {
    }
    
}
