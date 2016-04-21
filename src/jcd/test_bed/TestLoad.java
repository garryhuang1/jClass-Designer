/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;

import java.io.IOException;
import java.util.ArrayList;
import jcd.data.Method;
import jcd.data.UMLClass;
import jcd.data.Variable;
import jcd.file.FileManager;

/**
 *
 * @author Garry Huang
 */
public class TestLoad {
    public static void main(String[] args){
        FileManager fileManager = new FileManager();
        ArrayList <UMLClass> cList = new ArrayList<UMLClass>();
        try{
            cList = fileManager.loadData("./work/DesignSaveTest");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        for(int i = 0; i < cList.size(); i++){
            System.out.println(cList.get(i).getClassName());
            for(int x = 0; x < cList.get(i).getVariables().size(); x++){
                ArrayList<Variable> varList = cList.get(i).getVariables();
                System.out.println(varList.get(x).getType() + " " + varList.get(x).getName());
            }
            for(int x = 0; x < cList.get(i).getMethods().size(); x++){
                ArrayList<Method> metList = cList.get(i).getMethods();
                int end = metList.get(x).getArg().toString().length();
                System.out.println(metList.get(x).getAccess() + " " + metList.get(x).getName() + "(" + metList.get(x).getArg().toString().substring(1, end-1) + ")");
            }
            System.out.println("---------------------------------------------------------------");
        }

    }
}
