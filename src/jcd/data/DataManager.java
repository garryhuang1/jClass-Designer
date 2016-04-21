package jcd.data;

import java.util.ArrayList;
import javafx.scene.effect.Effect;
import jcd.JClassDesigner;
import jcd.controller.PageEditController;
import jcd.gui.Workspace;
import jcf.components.AppDataComponent;
import jcf.AppTemplate;

/**
 * This class serves as the data management component for this application.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class DataManager implements AppDataComponent {
    // THIS IS A SHARED REFERENCE TO THE APPLICATION
    AppTemplate app;
    ArrayList<UMLClass> classList;
    Effect selected;
    /**
     * THis constructor creates the data manager and sets up the
     *
     *
     * @param initApp The application within which this data manager is serving.
     */
    public DataManager(AppTemplate initApp) throws Exception {
	// KEEP THE APP FOR LATER
	app = initApp;
        classList = new ArrayList();
    }
    public ArrayList<UMLClass> getClassList(){
        return classList;
    }
    public UMLClass getSelected(){
        for(UMLClass test : classList){
            if(test.getIsSelected() == true){
                return test;
            }
        }
        return null;
    }
    public void createUMLClass(double x, double y){
        UMLClass nClass = new UMLClass(x, y);
        
        classList.add(nClass);
        app.getWorkspaceComponent().getWorkspace().getChildren().add(nClass.getTextBox());
        nClass.getTextBox().setOnMousePressed(e->{
            if(app.getGUI().getAppPane().getCursor().toString().equals("DEFAULT")){
                for(UMLClass test : classList){
                    if(test.getIsSelected() == true){
                        test.setSelected();
                    }
                }
                nClass.setSelected();
            }

            app.getWorkspaceComponent().setClassText(nClass.getClassName());
            app.getWorkspaceComponent().setPackageText(nClass.getPackageName());
        });
        nClass.getTextBox().setOnMouseDragged(e->{
            nClass.getTextBox().setLayoutX(e.getSceneX());
            nClass.getTextBox().setLayoutY(e.getSceneY()/2);
            nClass.setNewCoordinate(e.getSceneX(), e.getSceneY()/2);
        });
    }
    public void makeClass(String cName, String pName,UMLClass parent, String cNameText,
            String vNameText, String mNameText, double initX, double initY){
        UMLClass nClass = new UMLClass(initX, initY);
        classList.add(nClass);
        nClass.setClassName(cName);
        nClass.setParent(parent);
        nClass.setPackageName(pName);
        nClass.setClassNameText(cNameText);
        nClass.setVariableNameText(vNameText);
        nClass.setMethodNameText(mNameText);
        
        nClass.getTextBox().setOnMousePressed(e->{
            if(app.getGUI().getAppPane().getCursor().toString().equals("DEFAULT")){
                for(UMLClass test : classList){
                    if(test.getIsSelected() == true){
                        test.setSelected();
                    }
                }
                nClass.setSelected();
            }

            app.getWorkspaceComponent().setClassText(nClass.getClassName());
            app.getWorkspaceComponent().setPackageText(nClass.getPackageName());
        });
        nClass.getTextBox().setOnMouseDragged(e->{
            nClass.getTextBox().setLayoutX(e.getSceneX());
            nClass.getTextBox().setLayoutY(e.getSceneY()/2);
            nClass.setNewCoordinate(e.getSceneX(), e.getSceneY()/2);
        });
        
        
        app.getWorkspaceComponent().getWorkspace().getChildren().add(nClass.getTextBox());
        
    }
    /**
     * This function clears out the HTML tree and reloads it with the minimal
     * tags, like html, head, and body such that the user can begin editing a
     * page.
     */
   
    @Override
    public void reset() {
        Workspace workspace = (Workspace) app.getWorkspaceComponent();
        PageEditController pageEditController = new PageEditController((JClassDesigner) app);
    }
}
