/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

/**
 *
 * @author Garry Huang
 */
public class Variable {
    private String varName;
    private String varType;
    private Boolean varStatic;
    private String varAccess;
    
    public void setName(String initName){
        varName = initName;
    }
    public void setType(String initType){
        varType = initType;
    }
    public void setStatic(Boolean initStatic){
        varStatic = initStatic;
    }
    public void setAccess(String initAccess){
        varAccess = initAccess;
    }
    
    public String getName(){
        return varName;
    }
    public String getType(){
        return varType;
    }
    public Boolean getStatic(){
        return varStatic;
    }
    public String getAccess(){
        return varAccess;
    }
}
