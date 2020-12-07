/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes.activetypes;

import busifix.simdatatypes.Inventory;

/**
 *
 * @author Isaac
 */
public class ActiveInventory {
    
    //The inventory referenced
    public Inventory source;
    
    //The number of units in the inventory
    public int amount;
    
    public ActiveInventory() {
        
        source = null;
        amount = 0;
    }
}
