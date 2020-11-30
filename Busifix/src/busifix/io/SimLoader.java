/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.io;

import busifix.simdatatypes.Effect;
import busifix.simdatatypes.Factor;
import busifix.simdatatypes.FactorMode;
import busifix.simdatatypes.ProductType;
import busifix.simdatatypes.SimData;
import busifix.simdatatypes.Task;
import busifix.simdataloadables.PTloadable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shado
 */
public class SimLoader {

    //The current line of the file
    String line;
    
    //The simulation data
    SimData simData;
    
    //The loaded product type data
    ArrayList<PTloadable> PTLoadArry;
    
    public SimData load(String path) throws IOException {
        
        //Initialize simulation data
        simData = new SimData();
        
        //Creates the simulation file
        File simFile = new File(path);
        
        //Creates the file  writer class
        Scanner s = new Scanner(simFile);
        
        //Read data objects from file
        dataInitialization(s);
        
        //Establish references between objects
        dataConnection();
        
        return simData;
    }
    
    //PHASE 1
    //Reads the simulation data file for data objects and reference indices
    private void dataInitialization(Scanner s) {
        
        //Initialize array of product loadables
        PTLoadArry = new ArrayList<PTloadable>();
        
        //Get the first line of the file
        line = s.nextLine();
        
        //Detect for product types
        if ("Begin ProductType:".equals(line)) {
            
            //Load the products
            loadProducts(s);
        }
        
        //Detect for factors
        if ("Begin Factor:".equals(line)) {
        
            //Load the factors
            loadFactors(s);
        }
        
        //Detect for tasks
        if ("Begin Task:".equals(line)) {
        
            //Load the factors
            loadTasks(s);
        }
    }
    
    private void loadProducts(Scanner s) {
        
        //Get the next line
        line = s.nextLine();
        
        while (!"Begin Factor:".equals(line) && s.hasNextLine()) { //loop for products
            //First pass, load data into ProductLoadable
            //boolean S_pass = false;
            String[] data = line.split("\\|");
            String name = data[0];
            double salePrice = Double.parseDouble(data[1]);
            double saleMean = Double.parseDouble(data[2]);
            double saleDeviation = Double.parseDouble(data[3]);
            int index = Integer.parseInt(data[4]);
            PTloadable load = new PTloadable();
            load.name = name;
            load.salePrice = salePrice;
            load.saleMean = saleMean;
            load.saleDeviation = saleDeviation;
            load.meanShiftFactors = new ArrayList();
            load.meanShiftFactors.add(index);
            PTLoadArry.add(load);   
            
            //Get the next line
            line = s.nextLine();
        }
        
        int size = 0;
        
        //For each product loadable
        while(size < PTLoadArry.size()) {

            //Get the current loadable product
            PTloadable loadable = PTLoadArry.get(size);

            //Copy information to product type
            ProductType product = new ProductType();
            simData.products.add(product);
            product.name = loadable.name;
            product.salePrice = loadable.salePrice;
            product.saleMean = loadable.saleMean;
            product.saleDeviation = loadable.saleDeviation;
            product.meanShiftFactors = new ArrayList<Factor>();

            //Add the product to the simulation data
            simData.products.add(product);

            size++;
        }
    }
    
    private void loadFactors(Scanner s) {
        
        //Get the next line
        line = s.nextLine();
        
        while (!"Begin Task:".equals(line) && s.hasNextLine()) {//loop for factors

                String[] data = line.split("\\|");
                String name = data[0];
                String mode = data[1];
                double baseValue = Double.parseDouble(data[2]);
                // converts the string into FactorMode
                Enum Mode = Enum.valueOf(FactorMode.class, mode);
                Mode = FactorMode.valueOf(mode);
                Factor f = new Factor();
                f.name = name;
                f.mode = (FactorMode) Mode;
                f.baseValue = baseValue;
                simData.factors.add(f);
                
                //Get the next line
                line = s.nextLine();
            }
    }
    
    private void loadTasks(Scanner s) {
        
        //Get the next line if possible
        if (s.hasNextLine()) {
            
            line = s.nextLine();
        }
        
        while (s.hasNextLine()) {//loop for Tasks
            String[] data = line.split("\\|");
            String name = data[0];
            double C_change = Double.parseDouble(data[1]);
            String C_name = data[2];
            double C_baseValue = Double.parseDouble(data[3]);
            String c_mode = data[4];
            // converts the string into FactorMode
            Enum C_mode = Enum.valueOf(FactorMode.class, c_mode);
            C_mode = FactorMode.valueOf(c_mode);
            double F_change = Double.parseDouble(data[5]);
            String F_name = data[6];
            double F_baseValue = Double.parseDouble(data[7]);
            String f_mode = data[8];
            // converts the string into FactorMode
            Enum F_mode = Enum.valueOf(FactorMode.class, f_mode);
            F_mode = FactorMode.valueOf(f_mode);

            Task t = new Task();
            t.completionEffect = new Effect();
            t.failureEffect = new Effect();
            t.completionEffect.target = new Factor();
            t.failureEffect.target = new Factor();
            t.name = name;
            t.completionEffect.change = C_change;
            t.completionEffect.target.name = C_name;
            t.completionEffect.target.baseValue = C_baseValue;
            t.completionEffect.target.mode = (FactorMode) C_mode;
            t.failureEffect.change = F_change;
            t.failureEffect.target.name = F_name;
            t.failureEffect.target.baseValue = F_baseValue;
            t.failureEffect.target.mode = (FactorMode) F_mode;
            simData.tasks.add(t);
            
            //Get the next line if possible
            if(s.hasNext()) {
                
                line = s.nextLine();
            }
        }
    }
    
    //PHASE 2
    //Establishes connections between loaded data items
    private void dataConnection() {
        
        int size = 0;
        
        //For each product
        while(size < PTLoadArry.size()) {

            //Get the current loadable product
            PTloadable loadable = PTLoadArry.get(size);

            //Get the product type
            ProductType product = simData.products.get(size);

            //Get referenced factor indices
            ArrayList<Integer> meanShiftFactors = loadable.meanShiftFactors;

            //For each referenced factor
            for (int loopIndex = 0; loopIndex < meanShiftFactors.size(); loopIndex++) {

                //Get the referenced factor index
                int factorIndex = meanShiftFactors.get(loopIndex).intValue();

                //Get the corresponding factor
                Factor referencedFactor = simData.factors.get(factorIndex);

                //Add the reference to the product
                product.meanShiftFactors.add(referencedFactor);
            }
            size++;
        }
    }
}
