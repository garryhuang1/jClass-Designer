/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    UMLClass parent;
    VBox displayClass;
    
    double startX;
    double startY;
    
    ArrayList<Variable> variables;    
    ArrayList<Method> methods;
    Effect selected;
    
    Text classNameText;
    Text variableName;
    Text methodName;
    
    Line toParent1;
    Line toParent2;
    Point2D point1;
    Point2D point2;
    Point2D point3;
    public UMLClass(double initX, double initY){

        
        variables = new ArrayList<Variable>();
        methods = new ArrayList<Method>();
        
        
        
        isSelected = false;
        className = "";
        packageName = "";
        parent = null;
        
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
    public void setParent(UMLClass initClass){
        parent = initClass;  
    }
    public UMLClass getParent(){
        return parent;
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
    public ArrayList<Method> getMethods(){
        return methods;
    }
    public ArrayList<Variable> getVariables(){
        return variables;
    }
    public void addMethod(Method initMethod){
        methods.add(initMethod);
    }
    public void addVariable(Variable initVariable){
        variables.add(initVariable);
    }
    
}
