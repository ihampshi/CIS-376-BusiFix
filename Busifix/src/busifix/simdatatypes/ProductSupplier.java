/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class ProductSupplier {
    
    public String name;
    
    //The orders offered by this supplier
    public ArrayList<Order> ordersOffered;
    
    //Constructor
    public ProductSupplier() {
        
        name = "";
        ordersOffered = new ArrayList();
        ordersOffered.add(new Order());
    }
}
