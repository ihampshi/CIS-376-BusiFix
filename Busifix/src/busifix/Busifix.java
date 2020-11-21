/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import static busifix.FactorMode.VALUE;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Busifix extends Application{


    public static void main(String[] args) throws IOException{
        ProductType e = new ProductType();
        Factor f = new Factor();
        Task t = new Task();
        e.name= "hi";
        e.saleDeviation = 3;
        e.saleMean = 5;
        e.salePrice  = 8;
        e.meanShiftFactors = new ArrayList();
        f.name = "jake";
        f.mode = VALUE;
        f.baseValue = 8;        
        
        t.completionEffect = new Effect();
        t.failureEffect = new Effect();
        t.completionEffect.target = new Factor();
        t.failureEffect.target = new Factor();
        
        t.name = "joe";
        t.completionEffect.change = 9;
        t.completionEffect.target.name = "gary";
        t.completionEffect.target.baseValue = 12;
        t.completionEffect.target.mode = VALUE;
        t.failureEffect.change = 2;
        t.failureEffect.target.name = "kyle";
        t.failureEffect.target.baseValue = 19;
        t.failureEffect.target.mode = VALUE;
        e.meanShiftFactors.add(f);
        SimData simData = new SimData();
        simData.products = new ArrayList();
        simData.factors = new ArrayList();
        simData.tasks = new ArrayList();
        simData.products.add(e);
        simData.factors.add(f);
        simData.tasks.add(t);
        String path = "SimulationFile.txt";
        SimSaver simSaver = new SimSaver();
        simSaver.save(simData, path);
        SimLoader sim = new SimLoader();
        SimData SM;
        SM = sim.load(path);
        System.out.println(SM.tasks.get(0).name);
        System.out.println(SM.tasks.get(0).completionEffect.change);
        System.out.println(SM.tasks.get(0).failureEffect.target.name);
        //launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        //first scene
        Parent root = FXMLLoader.load(getClass().getResource("BusifixFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Busifix");
        stage.setScene(scene);
        stage.show();
    }
}
