/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.test_bed;


import java.io.IOException;
import java.util.ArrayList;
import jcd.JClassDesigner;
import jcd.data.DataManager;
import jcd.data.Method;
import jcd.data.UMLClass;
import jcd.data.Variable;
import jcd.file.FileManager;
import jcf.AppTemplate;
import jcf.components.AppDataComponent;

/**
 *
 * @author Garry Huang
 */
public class TestSave{
    
    public static void main(String[] args){
       FileManager fileManager = new FileManager();
        UMLClass tExample = new UMLClass(100,0);
        UMLClass cTask = new UMLClass(300, 0);
        UMLClass dTask = new UMLClass(500, 0);
        UMLClass pHandler = new UMLClass(100, 400);
        UMLClass sHandler = new UMLClass(300, 400);
        
        tExample.setPackageName("tExample");
        cTask.setPackageName("tExample.cTask");
        dTask.setPackageName("tExample.cTask.dTask");
        pHandler.setPackageName("tExample");
        sHandler.setPackageName("tExample");
        
        tExample.setClassName("ThreadExample");
        cTask.setClassName("CounterTask");
        cTask.setParent(tExample);
        dTask.setClassName("DateTask");
        dTask.setParent(tExample);
        pHandler.setClassName("PauseHandler");
        pHandler.setParent(tExample);
        sHandler.setClassName("StartHandler");
        sHandler.setParent(tExample);
        
        
        //----------------------------COUNTER TASK--------------------------
        Variable app = new Variable();
        app.setName("app");
        app.setType("ThreadExample");
        cTask.addVariable(app);
        
        Variable counter = new Variable();
        counter.setName("counter");
        counter.setType("int");
        cTask.addVariable(counter);
        
        Method counterCounterTask = new Method();
        counterCounterTask.setName("CounterTask");
        counterCounterTask.setReturn("");
        counterCounterTask.addArg("ThreadExample");
        cTask.addMethod(counterCounterTask);
        
        Method counterCall = new Method();
        counterCall.setName("call");
        counterCall.setAccess("protected");
        cTask.addMethod(counterCall);
        //----------------------------DATE TASK-----------------------------------
        dTask.addVariable(app);
        
        Variable now = new Variable();
        now.setName("now");
        now.setType("Date");
        dTask.addVariable(now);
        
        Method dateTaskMethod = new Method();
        dateTaskMethod.setName("DateTask");
        dateTaskMethod.setReturn("");
        dateTaskMethod.addArg("ThreadExample");
        dTask.addMethod(dateTaskMethod);
        
        dTask.addMethod(counterCall);
        //-----------------------------PAUSE HANDLER----------------------------------
        pHandler.addVariable(app);
        
        Method pauseHandler = new Method();
        pauseHandler.setName("PauseHandler");
        pauseHandler.setReturn("");
        pauseHandler.addArg("ThreadExample");
        pHandler.addMethod(pauseHandler);
        
        Method handle = new Method();
        handle.setName("handle");
        handle.addArg("Event");
        pHandler.addMethod(handle);
        //----------------------------START HANDLER---------------------------------------
        sHandler.addVariable(app);
        
        Method startHandler = new Method();
        startHandler.setName("StartHandler");
        startHandler.setReturn("");
        startHandler.addArg("ThreadExample");
        sHandler.addMethod(startHandler);
        
        sHandler.addMethod(handle);
        //-----------------------------THREAD EXAMPLE--------------------------------------------
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
        
        ArrayList<UMLClass> umlList = new ArrayList<UMLClass>();
        umlList.add(tExample);
        umlList.add(cTask);
        umlList.add(dTask);
        umlList.add(pHandler);
        umlList.add(sHandler);
        try{
            fileManager.saveData(umlList, "./work/DesignSaveTest");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        String filePath = "./hello";
        String packageName = "jtest.test1";
        do{
            int check = packageName.indexOf('.');
            if(check == -1){
                filePath += "/" + packageName;
                packageName = "";
            }
            else{
                String temp = packageName.substring(0, check);
                packageName = packageName.substring(check+1, packageName.length());
                filePath += "/" + temp;
            }
            
        }while(packageName.isEmpty()==false);
        System.out.println(filePath);
    }
    
}
