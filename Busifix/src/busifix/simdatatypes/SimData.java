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
    
    //Metadata
    public String businessName;
    public String businessDescription;
    
    //Resource lists
    public ArrayList<ProductType> products;
    public ArrayList<Factor> factors;
    public ArrayList<Task> tasks;
    public ArrayList<Inventory> inventories;
    public ArrayList<Employee> employeePool;
    public ArrayList<Position> positions;
    public ArrayList<ProductSupplier> productSuppliers;
    public ArrayList<RandomEvent> randomEvents;
    
    //Initial conditions
    public double balance;
    public String welcomeMessage;
    public ArrayList<Employee> hiredEmployees;
    public ArrayList<PlacedOrder> placedOrders;
    
    //Constructor
    public SimData() {
        
        //Initialize metadata
        businessName = "";
        businessDescription = "";
        
        //Initialize data lists
        products = new ArrayList();
        factors = new ArrayList();
        tasks = new ArrayList();
        inventories = new ArrayList();
        employeePool = new ArrayList();
        positions = new ArrayList();
        productSuppliers = new ArrayList();
        randomEvents = new ArrayList();
        
        //Initialize conditions
        balance = 0.0;
        welcomeMessage = "";
        hiredEmployees = new ArrayList();
        placedOrders = new ArrayList();
    }
}
