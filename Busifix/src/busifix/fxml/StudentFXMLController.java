/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class StudentFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    //Financial business overview labels
    @FXML
    private Label balance_lbl;
    @FXML
    private Label expenses_lbl;
    @FXML
    private Label expected_sales_lbl;
    @FXML
    private Label net_lbl;
    @FXML
    private Label expected_balance_lbl;
    
    //Transitions to the teacher interface
    public void toTeacherMode() {
        
        AnchorPane pane = new AnchorPane();
        
        try {
            
            //Load teacher mode
            pane = FXMLLoader.load(getClass().getResource("teacherFXML.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
