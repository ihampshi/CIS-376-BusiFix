/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix;

import busifix.io.SimSaver;
import busifix.io.SimLoader;
import busifix.simdatatypes.SimData;
import busifix.simdatatypes.Task;
import busifix.simdatatypes.ProductType;
import busifix.simdatatypes.Factor;
import static busifix.simdatatypes.FactorMode.VALUE;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;


public class Busifix extends Application{

    public static void main(String[] args) throws IOException{
        
        //Initialize working simulation data
        BusifixAppData.InitializeData();
        
        launch(args);
    }

    private void testSimData() {
        
        ProductType p = new ProductType();
        Factor f = new Factor();
        Task t = new Task();
        p.name= "hi";
        p.saleDeviation = 3;
        p.saleMean = 5;
        p.salePrice  = 8;
        p.meanShiftFactors = new ArrayList();
        f.name = "jake";
        f.mode = VALUE;
        f.baseValue = 8;        
        
        //t.completionEffect = null;
        // t.failureEffect = null;
        //t.completionEffect.target = null;
        //t.failureEffect.target = null;
        
        //t.name = "joe";
        
        p.meanShiftFactors.add(f);
        SimData simData = new SimData();
        simData.products = new ArrayList();
        simData.factors = new ArrayList();
        simData.tasks = new ArrayList();
        simData.products.add(p);
        simData.factors.add(f);
        //simData.tasks.add(t);
        String path = "SimulationFile.txt";
        
        try {
            SimSaver simSaver = new SimSaver();
            simSaver.save(simData, path);
            SimLoader sim = new SimLoader();
            SimData SM;
            SM = sim.load(path);
            
            System.out.println(SM.products.get(0).name);
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //first scene
        Parent root = FXMLLoader.load(getClass().getResource("/busifix/fxml/BusifixFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Busifix");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
