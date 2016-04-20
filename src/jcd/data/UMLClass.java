/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.util.ArrayList;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jcf.AppTemplate;

/**
 *
 * @author Garry Huang
 */
public class UMLClass {
    boolean isSelected;
    String className;
    String packageName;
    String parent;
    VBox displayClass;
    
    double startX;
    double startY;
    
    ArrayList<String> varName;
    ArrayList<String> varType;
    ArrayList<Boolean> varStatic;
    ArrayList<String> varAccess;
    
    ArrayList<String> metName;
    ArrayList<String> metReturn;
    ArrayList<Boolean> metStatic;
    ArrayList<Boolean> metAbstract;
    ArrayList<String> metAccess;
    ArrayList<String> arg;
    Effect selected;
    
    Text classNameText;
    Text variableName;
    Text methodName;
    public UMLClass(double initX, double initY){
        AppTemplate app;
        
        varName = new ArrayList<String>();
        varType = new ArrayList<String>();
        varStatic = new ArrayList<Boolean>();
        varAccess = new ArrayList<String>();
    
        metName = new ArrayList<String>();
        metReturn = new ArrayList<String>();
        metStatic = new ArrayList<Boolean>();
        metAbstract = new ArrayList<Boolean>();
        metAccess = new ArrayList<String>();
        arg = new ArrayList<String>();
        
        /* HARD CODE */
        varName.add("HELLO");
        varType.add("String");
        varAccess.add("public");
        varStatic.add(true);
        metName.add("kek");
        metReturn.add("int");
        metStatic.add(true);
        metAbstract.add(false);
        metAccess.add("public");
        arg.add("int");
        
        
        
        isSelected = false;
        className = "";
        packageName = "";
        
        startX = initX;
        startY = initY;
        classNameText = new Text();
        
        
        VBox classBox = new VBox();
        classBox.getStyleClass().add("uml_class");
        classBox.getChildren().addAll(classNameText);
        classBox.setLayoutX(startX);
        classBox.setLayoutY(startY);
        classBox.setMinSize(150, 100);
        
        variableName = new Text();
        VBox variableBox = new VBox();
        variableBox.getStyleClass().add("uml_class");
        variableBox.getChildren().add(variableName);
        variableBox.setLayoutX(startX);
        variableBox.setLayoutY(startY);
        variableBox.setMinSize(150, 100);
       
        methodName = new Text();
        VBox methodBox = new VBox();
        methodBox.getStyleClass().add("uml_class");
        methodBox.getChildren().add(methodName);
        methodBox.setLayoutX(startX);
        methodBox.setLayoutY(startY);
        methodBox.setMinSize(150, 100);
        
        displayClass = new VBox();
        displayClass.setLayoutX(startX);
        displayClass.setLayoutY(startY);
        displayClass.getChildren().addAll(classBox, variableBox, methodBox);
        
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(0.0f);
	dropShadowEffect.setOffsetY(0.0f);
	dropShadowEffect.setSpread(1.0);
	dropShadowEffect.setColor(Color.YELLOW);
	dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
	dropShadowEffect.setRadius(15);
        selected = dropShadowEffect;
     
    }
    public void setClassName(String initName){
        VBox editText = (VBox)displayClass.getChildren().get(0);
        Text editTextBox = (Text)editText.getChildren().get(0);
        className = initName;
        editTextBox.setText(className);
        
    }
    
    public void setPackageName(String initName){
        packageName = initName;
    }
    public void setTextBox(VBox initBox){
        displayClass = initBox;
    }
    public void setSelected(){
        if(isSelected == true){
            displayClass.setEffect(null);
            isSelected = false;
        }
        else if(isSelected == false){
            displayClass.setEffect(selected);
            isSelected = true;
        }
    }
    public void setNewCoordinate(double initX, double initY){
        startX = initX;
        startY = initY;
    }
    public void setClassNameText(String initText){
        classNameText.setText(initText);
    }
    public void setVariableNameText(String initText){
        variableName.setText(initText);
    }
    public void setMethodNameText(String initText){
        methodName.setText(initText);
    }
    public String getClassName(){
        return className;
    }
    public String getPackageName(){
        return packageName;
    }
    public VBox getTextBox(){
        return displayClass;
    }
    public boolean getIsSelected(){
        return isSelected;
    }
    public double getX(){
        return startX;
    }
    public double getY(){
        return startY;
    }
    public Text getClassText(){
        return classNameText;
    }
    public Text getVariableText(){
        return variableName;
    }
    public Text getMethodText(){
        return methodName;
    }
    public ArrayList<String> getVarName(){
        return varName;
    }
    public ArrayList<String> getVarType(){
        return varType;
    }
    public ArrayList<Boolean> getVarStatic(){
        return varStatic;
    }
    public ArrayList<String> getVarAccess(){
        return varAccess;
    }
    public ArrayList<String> getMetName(){
        return metName;
    }
    public ArrayList<String> getMetReturn(){
        return metReturn;
    }
    public ArrayList<Boolean> getMetStatic(){
        return metStatic;
    }
    public ArrayList<Boolean> getMetAbstract(){
        return metAbstract;
    }
    public ArrayList<String> getMetAccess(){
        return metAccess;
    }
    public ArrayList<String> getMetArg(){
        return arg;
    }
}
