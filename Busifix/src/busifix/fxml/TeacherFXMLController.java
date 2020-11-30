/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.fxml;

import javafx.fxml.FXML;
import busifix.BusifixAppData;
import busifix.io.SimLoader;
import busifix.io.SimSaver;
import busifix.simdatatypes.SimData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class TeacherFXMLController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    /*
    
    */
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
            }
            
            //If load was successful
            if (loadedData != null) {
                
                //Setup data
                BusifixAppData.SetWorkingData(loadedData);
            }
        }
    }
    
    /*
    
    */
    public void saveSim() throws IOException{
        
        //Get save location
        FileChooser chooser = new FileChooser();
        File chosenFile = chooser.showSaveDialog(null);
        
        //If valid file was chosen
        if (chosenFile != null) {
        
            //Save working simulation data
            SimSaver simSaver = new SimSaver();
            simSaver.save(BusifixAppData.GetWorkingData(), chosenFile.getAbsolutePath());
        }
    }
    
    
    public void toStudentMode() {
        
        AnchorPane pane = new AnchorPane();
        
        try {
            
            //Load student mode
            pane = FXMLLoader.load(getClass().getResource("studentFXML.fxml"));
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
