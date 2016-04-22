/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import jcd.file.FileManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Garry Huang
 */
public class TestThree {
    
    ArrayList<UMLClass> umlList = new ArrayList<UMLClass>();
    ArrayList<UMLClass> testList = new ArrayList<UMLClass>();
    public TestThree(){
       FileManager fileManager = new FileManager();
        UMLClass tExample = new UMLClass(100,0);
        UMLClass cTask = new UMLClass(300, 0);
        UMLClass dTask = new UMLClass(500, 0);
        UMLClass pHandler = new UMLClass(100, 400);
        UMLClass sHandler = new UMLClass(300, 400);
        UMLClass testAbstract = new UMLClass(600, 600);
        
        testAbstract.setPackageName("tExample");
        testAbstract.setClassName("TestAbstract");
        testAbstract.setClassType("abstract");
        
        tExample.setClassType("interface");
        tExample.setPackageName("tExample");
        cTask.setPackageName("tExample.cTask");
        dTask.setPackageName("tExample.cTask.dTask");
        pHandler.setPackageName("tExample");
        sHandler.setPackageName("tExample");
        
        tExample.setClassName("ThreadExample");
        cTask.setClassName("CounterTask");
        cTask.setParent(tExample);
        dTask.setClassName("DateTask");
        dTask.setParent(testAbstract);
        pHandler.setClassName("PauseHandler");
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
        
        umlList.add(testAbstract);
        umlList.add(tExample);
        umlList.add(cTask);
        umlList.add(dTask);
        umlList.add(pHandler);
        umlList.add(sHandler);
        
        
        try{
            fileManager.saveData(umlList, "./work/DesignSaveTest");
            testList = fileManager.loadData("./work/DesignSaveTest");
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    @Test //UNSURE OF HOW TO JUNIT TEST FILE PATH FOR PACKAGE SAVING
    public void checkPath(){
        System.out.println("* Check file destination");
        assertEquals("./work/DesignSaveTest", "./work/DesignSaveTest");
    }
    @Test
    public void checkVar1(){
        System.out.println("* Check variable name 1");
        assertEquals("window", testList.get(2).getVariables().get(0).getName());
    }
    @Test
    public void checkVar2(){
        System.out.println("* Check variable type 1");
        assertEquals("Stage", testList.get(2).getVariables().get(0).getType());
    }

    @Test
    public void checkMet1(){
        System.out.println("* Check Method Name 1");
        assertEquals("start", testList.get(2).getMethods().get(0).getName());
    }

    @Test
    public void checkMet2(){
        System.out.println("* Check method argument");
        int end = testList.get(2).getMethods().get(0).getArg().toString().length();
        assertEquals("Stage", testList.get(2).getMethods().get(0).getArg().toString().substring(1, end-1));
    }

    @Test
    public void checkPoint1(){
        System.out.println("* Check point 1");
        Point2D a = new Point2D(300, -150);
        assertEquals(a, testList.get(2).getPoint().get(0));
    }

    @Test
    public void checkPoint2(){
        System.out.println("* Check point 2");
        Point2D a = new Point2D(450, -150);
        assertEquals(a, testList.get(2).getPoint().get(1));
    }

    @Test
    public void checkPoint3(){
        System.out.println("* Check point 3");
        Point2D a = new Point2D(150, 0);
        assertEquals(a, testList.get(2).getPoint().get(2));
    }
    @Ignore
    @Test
    public void checkLine1(){
        System.out.println("* Check line 1");
        Line a = new Line(300, -150, 150, 0);
        assertEquals(a, testList.get(1).getLine().get(0));
    }
    @Ignore
    @Test
    public void checkLine2(){
        System.out.println("* Check line 2");
        Line a = new Line(150, 0, 450, -150);
        assertEquals(a, testList.get(1).getLine().get(1));
    }
}
