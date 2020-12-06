/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

/**
 *
 * @author Isaac
 */
public class PlacedOrder {
    
    //The order that was placed
    public Order source;
    
    //The number of days until the order arrives
    public int daysRemaining;
    
    //Constructor
    public PlacedOrder(Order order) {
        
        if (order == null) {
            
            System.out.println("Warning: null order placed.");
        }
        
        source = order;
        daysRemaining = source.distance;
    }
}
