/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

import busifix.BusifixAppData;
import busifix.simdatatypes.activetypes.ActiveInventory;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class SimProgress {
    
    //The current day of the simulation
    public int day;
    
    //Inventory data
    public ArrayList<ActiveInventory> inventories;
    
    //Employees
    public ArrayList<Employee> employeePool;
    public ArrayList<Employee> hiredEmployees;
    
    //Initialize the simulation progress
    public SimProgress() {
        
        //Initialize on the first day
        day = 1;
        
        //Initialize data
        inventories = new ArrayList();
        employeePool = new ArrayList();
        hiredEmployees = new ArrayList();
        
        //If simulation data exists
        if (BusifixAppData.IsWorkingDataInitialized()) {
            
            //Load from simulation data
            initializeInventories();
            initializeEmployees();
        }
    }
    
    private void initializeInventories() {
        
        ArrayList<Inventory> sourceInventories = BusifixAppData.GetWorkingData().inventories;
        
        for (int index = 0; index < sourceInventories.size(); index++) {
            
            ActiveInventory newInventory = new ActiveInventory();
            newInventory.source = sourceInventories.get(index);
            
            inventories.add(newInventory);
        }
    }
    
    private void initializeEmployees() {
        
        ArrayList<Employee> sourceEmployeePool = BusifixAppData.GetWorkingData().employeePool;
        ArrayList<Employee> sourceHiredEmployees = BusifixAppData.GetWorkingData().hiredEmployees;
        
        for (int index = 0; index < sourceEmployeePool.size(); index++) {
            
            employeePool.add(sourceEmployeePool.get(index));
        }
        
        for (int index = 0; index < sourceHiredEmployees.size(); index++) {
            
            hiredEmployees.add(sourceHiredEmployees.get(index));
        }
    }
}
