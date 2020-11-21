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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shado
 */
public class SimLoader {

    public SimData load(String path) throws IOException {
        SimData simData = new SimData();
        simData.products = new ArrayList();
        simData.factors = new ArrayList();
        simData.tasks = new ArrayList();
        //Creates the simulation file
        File simFile = new File(path);
        //Creates the file  writer class
        Scanner s = new Scanner(simFile);

        String line = s.nextLine();
        if ("Begin ProductType:".equals(line)) {
            line = s.nextLine();
            while (!"Begin Factor:".equals(line) && s.hasNextLine()) { //loop for products
                String[] data = line.split("\\|");
                String name = data[0];
                double salePrice = Double.parseDouble(data[1]);
                double saleMean = Double.parseDouble(data[2]);
                double saleDeviation = Double.parseDouble(data[3]);
                String Mname = data[4];
                String mode = data[5];
                double baseValue = Double.parseDouble(data[6]);
                // converts the string into FactorMode
                Enum Mode = Enum.valueOf(FactorMode.class, mode);
                Mode = FactorMode.valueOf(mode);

                ProductType p = new ProductType();
                Factor f = new Factor();
                p.meanShiftFactors = new ArrayList();
                p.name = name;
                p.salePrice = salePrice;
                p.saleMean = saleMean;
                p.saleDeviation = saleDeviation;
                f.name = Mname;
                f.mode = (FactorMode) Mode;
                f.baseValue = baseValue;
                p.meanShiftFactors.add(f);
                simData.products.add(p);
                line = s.nextLine();
            }
            while (!"Begin Task:".equals(line) && s.hasNextLine()) {//loop for factors
                line = s.nextLine();
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
                line = s.nextLine();
            }
            while (s.hasNextLine()) {//loop for Tasks
                line = s.nextLine();
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
                if(s.hasNext())
                line = s.nextLine();
            }
        }
        return simData;
    }
}
