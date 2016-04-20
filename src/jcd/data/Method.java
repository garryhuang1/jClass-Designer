/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.util.ArrayList;

/**
 *
 * @author Garry Huang
 */
public class Method {
    private String metName;
    private String metReturn;
    private Boolean metStatic;
    private Boolean metAbstract;
    private String metAccess;
    private ArrayList<String> arg = new ArrayList<String>();
    
    public void setName(String initName){
        metName = initName;
    }
    public void setReturn(String initReturn){
        metReturn = initReturn;
    }
    public void setStatic(Boolean initStatic){
        metStatic = initStatic;
    }
    public void setAbstract(Boolean initAbstract){
        metAbstract = initAbstract;
    }
    public void setAccess(String initAccess){
        metAccess = initAccess;
    }
    public void addArg(String initArg){
        arg.add(initArg);
    }
    public void setArg(int pos, String initArg){
        arg.set(pos, initArg);
    }
    
    public String getName(){
        return metName;
    }
    public String getReturn(){
        return metReturn;
    }
    public Boolean getStatic(){
        return metStatic;
    }
    public Boolean getAbstract(){
        return metAbstract;
    }
    public String getAccess(){
        return metAccess;
    }
    public ArrayList<String> getArg(){
        return arg;
    }
    
}
