/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.controller;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import jcd.data.DataManager;
import jcd.gui.Workspace;
import jcf.AppTemplate;

/**
 *
 * @author Garry Huang
 */
public class PageEditController {
    AppTemplate app;
    DataManager dataManager;
    
    public PageEditController(AppTemplate initApp) {
        app = initApp;
        dataManager = (DataManager)app.getDataComponent();
    }
    public void processSelectTool(){
        app.getGUI().getAppPane().setCursor(Cursor.DEFAULT);
        
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        workspace.reloadWorkspace();
    }
    public void processAddClass(){
        app.getGUI().getAppPane().setCursor(Cursor.CROSSHAIR);
        
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        workspace.reloadWorkspace();
    }
}
