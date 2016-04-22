package jcd.gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static jcd.PropertyType.ADD_CLASS_ICON;
import static jcd.PropertyType.ADD_CLASS_TOOLTIP;
import static jcd.PropertyType.ADD_INTERFACE_ICON;
import static jcd.PropertyType.ADD_INTERFACE_TOOLTIP;
import static jcd.PropertyType.MINUS_ICON;
import static jcd.PropertyType.MINUS_TOOLTIP;
import static jcd.PropertyType.PLUS_ICON;
import static jcd.PropertyType.PLUS_TOOLTIP;
import static jcd.PropertyType.REDO_ICON;
import static jcd.PropertyType.REDO_TOOLTIP;
import static jcd.PropertyType.REMOVE_ICON;
import static jcd.PropertyType.REMOVE_TOOLTIP;
import static jcd.PropertyType.UNDO_ICON;
import static jcd.PropertyType.UNDO_TOOLTIP;
import static jcd.PropertyType.ZOOM_IN_ICON;
import static jcd.PropertyType.ZOOM_IN_TOOLTIP;
import static jcd.PropertyType.ZOOM_OUT_ICON;
import static jcd.PropertyType.ZOOM_OUT_TOOLTIP;
import static jcd.PropertyType.SELECT_ICON;
import static jcd.PropertyType.SELECT_TOOLTIP;
import static jcd.PropertyType.RESIZE_ICON;
import static jcd.PropertyType.RESIZE_TOOLTIP;
import jcd.controller.PageEditController;
import jcd.data.DataManager;
import jcf.ui.AppGUI;
import jcf.AppTemplate;
import jcf.components.AppWorkspaceComponent;
import static jcf.settings.AppStartupConstants.FILE_PROTOCOL;
import static jcf.settings.AppStartupConstants.PATH_IMAGES;

/**
 * This class serves as the workspace component for this application, providing
 * the user interface controls for editing work.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class Workspace extends AppWorkspaceComponent {

    // HERE'S THE APP
    AppTemplate app;

    // IT KNOWS THE GUI IT IS PLACED INSIDE
    AppGUI gui;
    
    ArrayList<VBox> classList;
    
    PageEditController pageEditController;
    DataManager dataManager;
    
    HBox toolbar;
    HBox editToolbar;
    HBox viewToolbar;
    
    VBox componentToolbar;
    VBox gridSnap;
    
    VBox selectedClass;
    
    HBox row1;
    HBox row2;
    HBox row3;
    HBox row4;
    HBox row5;
    HBox row6;
    HBox row7;
    
    ScrollPane workarea;
    ScrollPane variablesTablePane;
    ScrollPane methodsTablePane;
    
    Pane workareaCanvas;
    
    Button selectionButton;
    Button resizeButton;
    Button addClassButton;
    Button addInterfaceButton;
    Button removeButton;
    Button undoButton;
    Button redoButton;
    Button zoomInButton;
    Button zoomOutButton;
    
    Button plusVariablesButton;
    Button minusVariablesButton;
    Button plusMethodsButton;
    Button minusMethodsButton;
    
    Label classNameLabel;
    Label packageLabel;
    Label parentLabel;
    Label variablesLabel;
    Label methodsLabel;
    
    TextField classNameText;
    TextField packageText;
    
    ComboBox parentCombo;
    
    CheckBox gridBox;
    CheckBox snapBox;
    
    TableView variablesTable;
    TableColumn variableNameColumn;
    TableColumn variableTypeColumn;
    TableColumn variableStaticColumn;
    TableColumn variableAccessColumn;
    //ObservableList<TableColumn> variableColumn;
    
    TableView methodsTable;
    TableColumn methodNameColumn;
    TableColumn methodReturnColumn;
    TableColumn methodStaticColumn;
    TableColumn methodAbstractColumn;
    TableColumn methodAccessColumn;
    TableColumn methodArgColumn;
    
    Effect selected;
    /**
     * Constructor for initializing the workspace, note that this constructor
     * will fully setup the workspace user interface for use.
     *
     * @param initApp The application this workspace is part of.
     *
     * @throws IOException Thrown should there be an error loading application
     * data for setting up the user interface.
     */
    public Workspace(AppTemplate initApp) throws IOException {
	// KEEP THIS FOR LATER
	app = initApp;

	// KEEP THE GUI FOR LATER
	gui = app.getGUI();
        classList = new ArrayList();
        
        gui.getAppPane().setCursor(Cursor.DEFAULT);
        selectedClass = new VBox();
        
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(0.0f);
	dropShadowEffect.setOffsetY(0.0f);
	dropShadowEffect.setSpread(1.0);
	dropShadowEffect.setColor(Color.YELLOW);
	dropShadowEffect.setBlurType(BlurType.GAUSSIAN);
	dropShadowEffect.setRadius(15);
        selected = dropShadowEffect;
        
        
        layoutGUI();
        eventHandlers();
    }
    private void layoutGUI(){
 
        
        editToolbar = new HBox();
        selectionButton = gui.initChildButton(editToolbar, SELECT_ICON.toString(), SELECT_TOOLTIP.toString(), false);
        selectionButton.setPrefSize(35, 35);
        
        resizeButton = gui.initChildButton(editToolbar, RESIZE_ICON.toString(), RESIZE_TOOLTIP.toString(), false);
        resizeButton.setPrefSize(35, 35);
        addClassButton = gui.initChildButton(editToolbar, ADD_CLASS_ICON.toString(), ADD_CLASS_TOOLTIP.toString(), false);
        
        addClassButton.setPrefSize(35, 35);
        addInterfaceButton = gui.initChildButton(editToolbar, ADD_INTERFACE_ICON.toString(), ADD_INTERFACE_TOOLTIP.toString(), false);
        addInterfaceButton.setPrefSize(35, 35);
        removeButton = gui.initChildButton(editToolbar, REMOVE_ICON.toString(), REMOVE_TOOLTIP.toString(), false);
        removeButton.setPrefSize(35, 35);
        undoButton = gui.initChildButton(editToolbar, UNDO_ICON.toString(), UNDO_TOOLTIP.toString(), false);
        undoButton.setPrefSize(35, 35);
        redoButton = gui.initChildButton(editToolbar, REDO_ICON.toString(), REDO_TOOLTIP.toString(), false);
        redoButton.setPrefSize(35, 35);
        
        
        viewToolbar = new HBox();
        zoomInButton = gui.initChildButton(viewToolbar, ZOOM_IN_ICON.toString(), ZOOM_IN_TOOLTIP.toString(), false);
        zoomInButton.setPrefSize(35, 35);
        zoomOutButton = gui.initChildButton(viewToolbar, ZOOM_OUT_ICON.toString(), ZOOM_OUT_TOOLTIP.toString(), false);
        zoomOutButton.setPrefSize(35, 35);
        gridBox = new CheckBox("Grid");
        snapBox = new CheckBox("Snap");
        gridSnap = new VBox();
        gridSnap.getChildren().add(gridBox);
        gridSnap.getChildren().add(snapBox);
        viewToolbar.getChildren().addAll(gridBox, gridSnap);
        
        componentToolbar = new VBox();
        
        classNameLabel = new Label("Class Name:");
        classNameText = new TextField("");
        row1 = new HBox();
        row1.getChildren().addAll(classNameLabel, classNameText);
        
        packageLabel = new Label("Package: ");
        packageText = new TextField("");
        row2 = new HBox();
        row2.getChildren().addAll(packageLabel, packageText);
        
        parentLabel = new Label("Parent: ");
        parentCombo = new ComboBox();
        row3 = new HBox();
        row3.getChildren().addAll(parentLabel, parentCombo);
        
        variablesLabel = new Label("Variables: ");
        row4 = new HBox(); 
        row4.getChildren().addAll(variablesLabel);
        plusVariablesButton = gui.initChildButton(row4, PLUS_ICON.toString(), PLUS_TOOLTIP.toString(), false);
        minusVariablesButton = gui.initChildButton(row4, MINUS_ICON.toString(), MINUS_TOOLTIP.toString(), false);
        plusVariablesButton.setPrefSize(35, 35);
        minusVariablesButton.setPrefSize(35, 35);
        
        
        variableNameColumn = new TableColumn("Name");
        variableTypeColumn = new TableColumn("Type");
        variableStaticColumn = new TableColumn("Static");
        variableAccessColumn = new TableColumn("Access");
        variablesTable = new TableView();
        variablesTable.getColumns().addAll(variableNameColumn, variableTypeColumn, variableStaticColumn, variableAccessColumn);
        variablesTablePane = new ScrollPane(variablesTable);
        row5 = new HBox();
        row5.getChildren().add(variablesTablePane);
        
        methodsLabel = new Label("Methods: ");
        row6 = new HBox();
        row6.getChildren().addAll(methodsLabel);
        plusMethodsButton = gui.initChildButton(row6, PLUS_ICON.toString(), PLUS_TOOLTIP.toString(), false);
        minusMethodsButton = gui.initChildButton(row6, MINUS_ICON.toString(), MINUS_TOOLTIP.toString(), false);
        plusMethodsButton.setPrefSize(35, 35);
        minusMethodsButton.setPrefSize(35, 35);
        
       
        methodNameColumn = new TableColumn("Name");
        methodReturnColumn = new TableColumn("Return");
        methodStaticColumn =  new TableColumn("Static");
        methodAbstractColumn = new TableColumn("Abstract");
        methodAccessColumn = new TableColumn("Access");
        methodArgColumn = new TableColumn("Arg");
        
        methodsTable = new TableView();
        methodsTable.getColumns().addAll(methodNameColumn, methodReturnColumn, methodStaticColumn, methodAbstractColumn, methodAccessColumn, methodArgColumn);
        methodsTablePane = new ScrollPane(methodsTable);
        row7 = new HBox();
        row7.getChildren().add(methodsTablePane);
        
        componentToolbar.getChildren().addAll(row1, row2, row3, row4, row5, row6, row7);
        workspace = new BorderPane();
        toolbar = new HBox();
        
        viewToolbar.setMinWidth(250);
        toolbar.setLayoutX(320);
        toolbar.getChildren().addAll(editToolbar, viewToolbar);
        gui.getAppPane().getChildren().addAll(toolbar);

        ((BorderPane)workspace).setRight(componentToolbar);
        workareaCanvas = new Pane();
        workareaCanvas.setMinSize(1500,1000);
        workarea = new ScrollPane(workareaCanvas);
        ((BorderPane)workspace).setCenter(workarea);
        
        
    }
    private void eventHandlers(){
        pageEditController = new PageEditController(app);
        dataManager = (DataManager) app.getDataComponent();
        selectionButton.setOnAction(e->{
            pageEditController.processSelectTool();
        });
        
        addClassButton.setOnAction(e->{
            pageEditController.processAddClass();
        });
        packageText.setOnKeyReleased(e->{
            if(dataManager.getSelected() != null){
                dataManager.getSelected().setPackageName(packageText.getText());
            }
        });
        
        classNameText.setOnKeyReleased(e->{
            if(dataManager.getSelected() != null){
                dataManager.getSelected().setClassName(classNameText.getText());
            }
        });
        workareaCanvas.setOnMouseClicked(e->{
            if(gui.getAppPane().getCursor().toString().equals("CROSSHAIR")){
                dataManager.createUMLClass(e.getX(), e.getY());
            }
            else if(gui.getAppPane().getCursor().toString().equals("DEFAULT")){
                try{
                    double x = e.getSceneX();
                    double y = e.getSceneY();
                    System.out.println(x +" "+ y);
                    dataManager.getSelected().setSelected();
                }
                catch(Exception z){
                    System.out.println("No objects created");
                }
            }
        });
        
    }
    
    /**
     * This function specifies the CSS style classes for all the UI components
     * known at the time the workspace is initially constructed. Note that the
     * tag editor controls are added and removed dynamicaly as the application
     * runs so they will have their style setup separately.
     */
    @Override
    public void initStyle() {
	viewToolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        editToolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        toolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        componentToolbar.getStyleClass().add(CLASS_BORDERED_PANE);
        
        selectionButton.getStyleClass().add(CLASS_FILE_BUTTON);
        resizeButton.getStyleClass().add(CLASS_FILE_BUTTON);
        addClassButton.getStyleClass().add(CLASS_FILE_BUTTON);
        addInterfaceButton.getStyleClass().add(CLASS_FILE_BUTTON);
        removeButton.getStyleClass().add(CLASS_FILE_BUTTON);
        undoButton.getStyleClass().add(CLASS_FILE_BUTTON);
        redoButton.getStyleClass().add(CLASS_FILE_BUTTON);
        
        
        zoomInButton.getStyleClass().add(CLASS_FILE_BUTTON);
        zoomOutButton.getStyleClass().add(CLASS_FILE_BUTTON);
        
        workarea.getStyleClass().add(CLASS_MAX_PANE);
    }

    /**
     * This function reloads all the controls for editing tag attributes into
     * the workspace.
     */
    @Override
    public void reloadWorkspace() {
    }
    public void setImage(ButtonBase button, String fileName) {
	// LOAD THE ICON FROM THE PROVIDED FILE
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + fileName;
        Image buttonImage = new Image(imagePath);
	
	// SET THE IMAGE IN THE BUTTON
        button.setGraphic(new ImageView(buttonImage));	
    }
    public void setClassText(String newText){
        classNameText.setText(newText);
    }
    public void setPackageText(String newText){
        packageText.setText(newText);
    }
}
