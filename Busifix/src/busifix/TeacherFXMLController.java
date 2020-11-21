/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author shado
 */
public class TeacherFXMLController implements Initializable {
    private SimData simData;
    
    
    /*
    
    */
    public void loadSim() {    
        
    }
    
    /*
    
    */
    public void saveSim() throws IOException{
        ProductType e = new ProductType();
        e.name= "hi";
        simData.products.add(e);
        String path = "SimulationFile.txt";
        SimSaver simSaver = new SimSaver();
        simSaver.save(simData, path);
    }    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
