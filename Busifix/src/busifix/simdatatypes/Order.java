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
public class Order {
    
    //The product type to be delivered
    public ProductType productType;
    
    //The cost of placing the order
    public double cost;
    
    //The number of units in the delivery
    public int quantity;
    
    //The number of days before the order arrives after placing the order
    public int distance;
    
    //Constructor
    public Order() {
        
        productType = null;
        cost = 0;
        quantity = 0;
        distance = 0;
        
    }
}
