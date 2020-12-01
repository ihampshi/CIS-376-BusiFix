/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import busifix.BusifixAppData;
import busifix.BusifixAppProgress;
import busifix.io.SimLoader;
import busifix.simdatatypes.SimData;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
    
    //Inventory status listview
    @FXML
    private ListView inventory_listview;
    
    //Event log text area
    @FXML
    private TextArea log_txtarea;
    
    //Advance button
    @FXML
    private Button advance_btn;
    
    //Loads the chosen data file into the working simulation data
    public void loadSim() {    
        
        //Get load location
        FileChooser chooser = new FileChooser();
        File chosenFile = chooser.showOpenDialog(null);
        
        //If valid file was chosen
        if (chosenFile != null) {
        
            //Load working simulation data from file
            SimLoader simLoader = new SimLoader();
            SimData loadedData = null;
            
            try {
                
                loadedData = simLoader.load(chosenFile.getAbsolutePath());
            } catch (Exception e) {
                
                e.printStackTrace();
                
                //Log successful load
                logEvent("Simulation file: '" + chosenFile.getAbsolutePath() + "' could not be successfully loaded");
            }
            
            //If load was successful
            if (loadedData != null) {
                
                //Setup data
                BusifixAppData.SetWorkingData(loadedData);
                
                //Log successful load
                logEvent("Simulation file: '" + chosenFile.getAbsolutePath() + "' successfully loaded");
            }
        }
    }
    
    //Advances the simulation to the next day
    public void advanceDay() {
        
        //Perform updates for the next day
        BusifixAppProgress.NextDay();
    }
    
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
    
    //Logs the given message to the event log
    public void logEvent(String eventMessage) {
        
        //Retrieve the current event text
        String logText = log_txtarea.getText();
        
        //Add the event message to the text
        log_txtarea.appendText(eventMessage + "\n--\n");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize simulation progress
        BusifixAppProgress.InitializeSimProgress();
        
        //Log initialization
        logEvent("Initializing student interface");
    }    
    
}
