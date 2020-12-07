/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

import busifix.BusifixAppData;
import busifix.simdatatypes.activetypes.ActiveInventory;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class SimProgress {
    
    //The current day of the simulation
    public int day;
    
    //Inventory data
    public ArrayList<ActiveInventory> inventories;
    
    //Initialize the simulation progress
    public SimProgress() {
        
        //Initialize on the first day
        day = 1;
        
        //Initialize data
        inventories = new ArrayList();
        
        //If simulation data exists
        if (BusifixAppData.IsWorkingDataInitialized()) {
            
            //Load from simulation data
            initializeInventories();
        }
    }
    
    private void initializeInventories() {
        
        ArrayList<Inventory> sourceInventories = BusifixAppData.GetWorkingData().inventories;
        
        for (int index = 0; index < sourceInventories.size(); index++) {
            
            ActiveInventory newInventory = new ActiveInventory();
            newInventory.source = sourceInventories.get(index);
            
            inventories.add(newInventory);
        }
    }
}
