/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author shado
 */
public class SimSaver {

    public void save(SimData simData, String path) throws IOException {
        //Creates the simulation file
        File simFile = new File(path);
        //Creates the file  writer class
        FileWriter Fwriter = new FileWriter(simFile);
        //Creates the file printer class
        PrintWriter Pwriter = new PrintWriter(Fwriter);
        Pwriter.println("Begin ProductType:");
        for (int i = 0; i < simData.products.size(); i++) {
            //Stores the data for a product as one line in the simulation file into the variable "product" and is separated by "|" key 
            String product = simData.products.get(i).name + "|" + simData.products.get(i).salePrice + "|" + 
                    simData.products.get(i).saleMean + "|" + simData.products.get(i).saleDeviation + "|" +
                    simData.products.get(i).meanShiftFactors.get(i).name + "|" + simData.products.get(i).meanShiftFactors.get(i).mode
                    + "|" + simData.products.get(i).meanShiftFactors.get(i).baseValue;//FIX ME - needs the source factor data
            Pwriter.println(product);
        }
        
        Pwriter.println("Begin Factor:");
        for (int i = 0; i < simData.factors.size(); i++) {
            //Stores the data for factors as one line in the simulation file into the variable "factor" and is separated by "|" key
            String factor = simData.factors.get(i).name + "|" + simData.factors.get(i).mode + "|" + 
                    simData.factors.get(i).baseValue;//implement getting data
            
            Pwriter.println(factor);
        }
        
        Pwriter.println("Begin Task:");
        for (int i = 0; i < simData.tasks.size(); i++) {
            //Stores the data for a task as one line in the simulation file into the variable "task" and is separated by "|" key
            String task = simData.tasks.get(i).name + "|" + simData.tasks.get(i).completionEffect.change + "|" + 
                    simData.tasks.get(i).completionEffect.target.name + "|" + simData.tasks.get(i).completionEffect.target.baseValue
                    + "|" + simData.tasks.get(i).completionEffect.target.mode + "|" + simData.tasks.get(i).failureEffect.change + "|" + 
                    simData.tasks.get(i).failureEffect.target.name + "|" + simData.tasks.get(i).failureEffect.target.baseValue
                    + "|" + simData.tasks.get(i).failureEffect.target.mode;
            
            Pwriter.println(task);
        }
        
        
        Pwriter.close();
    }
}
