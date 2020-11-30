/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

import java.util.ArrayList;

/**
 *
 * @author shado
 */
public class SimData {
    public ArrayList<ProductType> products;
    public ArrayList<Factor> factors;
    public ArrayList<Task> tasks;
    
    //Constructor
    public SimData() {
        
        //Initialize data lists
        products = new ArrayList();
        factors = new ArrayList();
        tasks = new ArrayList();
    }
}
