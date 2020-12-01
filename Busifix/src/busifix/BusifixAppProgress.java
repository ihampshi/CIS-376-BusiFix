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
    public static SimProgress studentSimProgress;
    
    //Initializes the simulation progress
    public static void InitializeSimProgress() {
        
        studentSimProgress = new SimProgress();
    }
    
    //Returns the simulation progress
    public static SimProgress GetSimProgress() {
        
        return studentSimProgress;
    }
}
