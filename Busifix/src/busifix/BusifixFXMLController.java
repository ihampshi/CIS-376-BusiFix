package busifix;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;



public class BusifixFXMLController implements Initializable{

    @FXML
    private Button TeacherBtn;
    
    @FXML
    private Button StudentBtn;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    void buttonAction(ActionEvent event) throws IOException {
        AnchorPane pane = new AnchorPane();
        String buttonChoice = ((Button)event.getSource()).getId();
        if ( "TeacherBtn".equals(buttonChoice)) {
             pane = FXMLLoader.load(getClass().getResource("teacherFXML.fxml"));
            
        }else {
             pane = FXMLLoader.load(getClass().getResource("studentFXML.fxml"));
       }
        rootPane.getChildren().setAll(pane);
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
}

