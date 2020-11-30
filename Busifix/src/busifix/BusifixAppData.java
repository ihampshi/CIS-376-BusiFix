/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import busifix.simdatatypes.*;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class BusifixAppData {
    
    //The current working simulation data used by the application
    static SimData workingSimData;
    
    //Returns whether the working simulation data has been initialized
    public static boolean WorkingDataInitialized() {
        
        return workingSimData != null;
    }  
    
    //Initializes simulation data
    public static void InitializeData() {
        
        //Initialize working simulation data
        workingSimData = new SimData();
    }
    
    //Sets the simulation data reference (used when loading)
    public static void SetWorkingData(SimData data) {
        
        workingSimData = data;
    }
    
    //Retrieves the working simulation data
    public static SimData GetWorkingData() {
        
        return workingSimData;
    }
}
