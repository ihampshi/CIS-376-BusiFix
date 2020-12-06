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
public class Factor {
    public String name;
    public FactorMode mode;
    public double baseValue;
    public ArrayList<Factor> sourceFactors; 
    
    //Constructor
    public Factor() {
        
        name = "";
        mode = FactorMode.VALUE;
        baseValue = 0.0;
        sourceFactors = new ArrayList();
    }
}
