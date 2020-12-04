/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import busifix.simdatatypes.SimProgress;

/**
 *
 * @author Isaac
 */
public class BusifixAppProgress {
    
    //The simulation progress information
    public static SimProgress simProgress;
    
    //Initializes the simulation progress
    public static void InitializeSimProgress() {
        
        simProgress = new SimProgress();
    }
    
    //Returns whether the simulation progress is initialized
    public static boolean IsSimProgressInitialized() {
        
        return simProgress != null;
    }
    
    //Returns the simulation progress
    public static SimProgress GetSimProgress() {
        
        return simProgress;
    }
    
    //Update the simulation progress and data for the current day
    public static void NextDay() {
        
        //If initialized
        if (IsSimProgressInitialized()) {
            
            // --> Perform all required simulation update algorithms here
            
            
            //Increment the day counter
            simProgress.day += 1;
            
        } else {
            
            System.out.println("Simulation progress not initialized, but application is attempting to advance to the next day.");
        }
    }
}
