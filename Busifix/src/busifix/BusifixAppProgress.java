/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import busifix.simdatatypes.ProductType;
import busifix.simdatatypes.SimProgress;
import busifix.simdatatypes.activetypes.ActiveInventory;
import java.util.ArrayList;

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
    
    //Retrieves the names of the current inventory data
    public static ArrayList<String> GetInventoryNames() {
        
        ArrayList<String> inventoryNames = new ArrayList<String>();
        
        if (simProgress != null) {
            
            ArrayList<ActiveInventory> inventories = simProgress.inventories;
            
            for (int index = 0; index < inventories.size(); index++) {
                
                ActiveInventory current = inventories.get(index);
                
                if (current.source != null && current.source.productType != null) {
                
                    ProductType productType = current.source.productType;
                    String name = productType.name;
                    String size = String.valueOf(current.source.size);
                    String amount = String.valueOf(current.amount);
                    
                    inventoryNames.add("Type: " + name + ", " + amount + "/" + size);
                } else {
                    
                    inventoryNames.add("<Invalid inventory>");
                }
            }
        }
        
        return inventoryNames;
    }
}
