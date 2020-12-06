/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busifix.simdatatypes;

/**
 *
 * @author shado
 */
public class Task {
    public String name;
    public Effect completionEffect;
    public Effect failureEffect;
    
    //Constructor
    public Task() {
        
        name = "";
        completionEffect = null;
        failureEffect = null;
    }
}
