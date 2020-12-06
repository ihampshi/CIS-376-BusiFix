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
public class ProductType {
    public String name;
    public double salePrice;
    public double saleMean;
    public double saleDeviation;
    public ArrayList<Factor> meanShiftFactors;
    
    //Constructor
    public ProductType() {
        
        name = "";
        salePrice = 0;
        saleMean = 0;
        saleDeviation = 0;
        meanShiftFactors = new ArrayList();
    }
}
