/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.io.IOException;
import java.util.ArrayList;
import jcd.file.FileManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Garry Huang
 */
public class TestOne {
    ArrayList<UMLClass> umlList = new ArrayList<UMLClass>();
    ArrayList<UMLClass> testList = new ArrayList<UMLClass>();
    public TestOne(){
        FileManager fileManager = new FileManager();
        UMLClass tExample = new UMLClass(100,0);
        tExample.setClassName("ThreadExample");
        
        Variable window = new Variable();
        window.setName("window");
        window.setType("Stage");
        tExample.addVariable(window);
        
        Variable appPane = new Variable();
        appPane.setName("appPane");
        appPane.setType("BorderPane");
        tExample.addVariable(appPane);
        
        Variable topPane = new Variable();
        topPane.setName("topPane");
        topPane.setType("FlowPane");
        tExample.addVariable(topPane);
        
        Variable startButton = new Variable();
        startButton.setName("startButton");
        startButton.setType("Button");
        tExample.addVariable(startButton);
        
        Variable pauseButton = new Variable();
        pauseButton.setName("pauseButton");
        pauseButton.setType("Button");
        tExample.addVariable(pauseButton);
        
        Variable scrollPane = new Variable();
        scrollPane.setName("scrollPane");
        scrollPane.setType("ScrollPane");
        tExample.addVariable(scrollPane);
        
        Variable textArea = new Variable();
        textArea.setName("textArea");
        textArea.setType("TextArea");
        tExample.addVariable(textArea);
        
        Variable dateThread = new Variable();
        dateThread.setName("dateThread");
        dateThread.setType("Thread");
        tExample.addVariable(dateThread);
        
        Variable dateTask = new Variable();
        dateTask.setName("dateTask");
        dateTask.setType("Task");
        tExample.addVariable(dateTask);
        
        Variable counterThread = new Variable();
        counterThread.setName("counterThread");
        counterThread.setType("Thread");
        tExample.addVariable(counterThread);
        
        Variable counterTask = new Variable();
        counterTask.setName("counterTask");
        counterTask.setType("Task");
        tExample.addVariable(counterTask);
        
        Variable work = new Variable();
        work.setName("work");
        work.setType("boolean");
        tExample.addVariable(work);
        
        Method start = new Method();
        start.setName("start");
        start.addArg("Stage");
        tExample.addMethod(start);
        
        Method startWork = new Method();
        startWork.setName("startWork");
        tExample.addMethod(startWork);
        
        Method pauseWork = new Method();
        pauseWork.setName("pauseWork");
        tExample.addMethod(pauseWork);
        
        Method doWork = new Method();
        doWork.setName("doWork");
        doWork.setReturn("boolean");
        tExample.addMethod(doWork);
        
        Method appendText = new Method();
        appendText.setName("appendText");
        appendText.addArg("String");
        tExample.addMethod(appendText);
        
        Method sleep = new Method();
        sleep.setName("sleep");
        sleep.addArg("int");
        tExample.addMethod(sleep);
        
        Method initLayout = new Method();
        initLayout.setName("initLayout");
        initLayout.setAccess("private");
        tExample.addMethod(initLayout);
        
        Method initHandlers = new Method();
        initHandlers.setName("initHandlers");
        initHandlers.setAccess("private");
        tExample.addMethod(initHandlers);
        
        Method initWindow = new Method();
        initWindow.setName("initWindow");
        initWindow.setAccess("private");
        initWindow.addArg("Stage");
        tExample.addMethod(initWindow);
        
        Method initThreads = new Method();
        initThreads.setName("initThreads");
        initThreads.setAccess("private");
        tExample.addMethod(initThreads);
        
        Method main = new Method();
        main.setName("main");
        main.setStatic(true);
        main.addArg("String[]");
        tExample.addMethod(main);
        
        
        umlList.add(tExample);

        try{
            fileManager.saveData(umlList, "./work/DesignSaveTest");
            testList = fileManager.loadData("./work/DesignSaveTest");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    @Test
    public void checkVar1(){
        System.out.println("* Check variable name 1");
        assertEquals("window", testList.get(0).getVariables().get(0).getName());
    }
    @Test
    public void checkVar2(){
        System.out.println("* Check variable type 1");
        assertEquals("Stage", testList.get(0).getVariables().get(0).getType());
    }
    @Test
    public void checkMet1(){
        System.out.println("* Check Method Name 1");
        assertEquals("start", testList.get(0).getMethods().get(0).getName());
    }
    @Test
    public void checkMet2(){
        System.out.println("* Check method argument");
        int end = testList.get(0).getMethods().get(0).getArg().toString().length();
        assertEquals("Stage", testList.get(0).getMethods().get(0).getArg().toString().substring(1, end-1));
    }
}
