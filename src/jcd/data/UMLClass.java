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
    String classType;
    UMLClass parent;
    VBox displayClass;
    
    double x;
    double y;
    
    ArrayList<Variable> variables;    
    ArrayList<Method> methods;
    Effect selected;
    
    Text classNameText;
    Text variableName;
    Text methodName;
    
    ArrayList<Line> line;
    ArrayList<Point2D> point;
    public UMLClass(double initX, double initY){

        line = new ArrayList<Line>();
        point = new ArrayList<Point2D>();
        
        variables = new ArrayList<Variable>();
        methods = new ArrayList<Method>();
        
        
        classType = "";
        isSelected = false;
        className = "";
        packageName = "";
        parent = null;
        
        x = initX;
        y = initY;
        classNameText = new Text();
        
        
        VBox classBox = new VBox();
        classBox.getStyleClass().add("uml_class");
        classBox.getChildren().addAll(classNameText);
        classBox.setLayoutX(x);
        classBox.setLayoutY(y);
        classBox.setMinSize(150, 100);
        
        variableName = new Text();
        VBox variableBox = new VBox();
        variableBox.getStyleClass().add("uml_class");
        variableBox.getChildren().add(variableName);
        variableBox.setLayoutX(x);
        variableBox.setLayoutY(y);
        variableBox.setMinSize(150, 100);
        
       
        methodName = new Text();
        VBox methodBox = new VBox();
        methodBox.getStyleClass().add("uml_class");
        methodBox.getChildren().add(methodName);
        methodBox.setLayoutX(x);
        methodBox.setLayoutY(y);
        methodBox.setMinSize(150, 100);
        
        displayClass = new VBox();
        displayClass.setLayoutX(x);
        displayClass.setLayoutY(y);
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
    public String getClassType(){
        return classType;
    }
    public void setClassType(String initType){
        classType = initType;
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
        x = initX;
        y = initY;
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
    public ArrayList<Line> getLine(){
        return line;
    }
    public void addPoint(Point2D initPoint){
        point.add(initPoint);
    }
    public void addLine(Line initLine){
        line.add(initLine);
    }
    public void setParent(UMLClass initClass){
        //x is 150 wide, y is 300 tall
        parent = initClass;  
        if(parent!= null){
            double parentX = parent.getX();
            double parentY = parent.getY();

            double diffX = Math.abs(x-parentX);
            double diffY = Math.abs(y-parentY);

            if(diffX > diffY){
                if(x > parentX){
                    Point2D childPoint = new Point2D(x, y-150);
                    Point2D parentPoint = new Point2D(x+150, y-150);
                    Point2D middlePoint = new Point2D(x/2, y/2);
                    Line seg1 = new Line(x, y-150, x/2, y/2);
                    Line seg2 = new Line(x/2, y/2, x+150, y-150);
                    line.add(seg1);
                    line.add(seg2);
                    point.add(childPoint);
                    point.add(parentPoint);
                    point.add(middlePoint);
                }
                else if(x > parentX){
                    Point2D childPoint = new Point2D(x+150, y-150);
                    Point2D parentPoint = new Point2D(x, y-150);
                    Point2D middlePoint = new Point2D(parentX/2, parentY/2);
                    Line seg1 = new Line(x+150, y-150, parentX/2, parentY/2);
                    Line seg2 = new Line(parentX/2, parentY/2, x, y-150);
                    line.add(seg1);
                    line.add(seg2);
                    point.add(childPoint);
                    point.add(parentPoint);
                    point.add(middlePoint);
                }
            }
            else if(diffX < diffY){
                if(y < parentY){
                    Point2D childPoint = new Point2D(x+75, y);
                    Point2D parentPoint = new Point2D(x+75, y+300);
                    Point2D middlePoint = new Point2D(x/2, y/2);
                    Line seg1 = new Line(x+75, y, x/2, y/2);
                    Line seg2 = new Line(x/2, y/2, x+75, y+300);
                    line.add(seg1);
                    line.add(seg2);
                    point.add(childPoint);
                    point.add(parentPoint);
                    point.add(middlePoint);
                }
                else if(y > parentY){
                    Point2D childPoint = new Point2D(x+75, y+300);
                    Point2D parentPoint = new Point2D(x+75, y);
                    Point2D middlePoint = new Point2D(parentX/2, parentY/2);
                    Line seg1 = new Line(x+75, y+300, parentX/2, parentY/2);
                    Line seg2 = new Line(parentX/2, parentY/2, x+75, y);
                    line.add(seg1);
                    line.add(seg2);
                    point.add(childPoint);
                    point.add(parentPoint);
                    point.add(middlePoint);
                }
            }
            if(parent.getClassType().equals("interface")){
                for(Variable k : parent.getVariables()){
                    variables.add(k);
                }
                for(Method k : parent.getMethods()){
                    methods.add(k);
                }
            }
        }
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
    public ArrayList<Point2D> getPoint(){
        return point;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
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
