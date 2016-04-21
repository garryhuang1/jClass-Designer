package jcd.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import jcd.data.DataManager;
import jcd.data.Method;
import jcd.data.UMLClass;
import jcd.data.Variable;
import jcf.AppTemplate;
import jcf.components.AppDataComponent;
import jcf.components.AppFileComponent;

/**
 * This class serves as the file management component for this application,
 * providing all I/O services.
 *
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class FileManager implements AppFileComponent {
    AppTemplate app;
    /**
     * This method is for saving user work, which in the case of this
     * application means the data that constitutes the page DOM.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        StringWriter sw = new StringWriter();
        DataManager dataManager = (DataManager)data;
        ArrayList<UMLClass> cList = dataManager.getClassList();
        
        JsonArrayBuilder classBuilder = Json.createArrayBuilder();
        fillArray(cList, classBuilder);
        JsonArray nodesArray = classBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add("class", nodesArray)
                .build();

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    public void saveData(ArrayList<UMLClass> umlList, String filePath) throws IOException {
        StringWriter sw = new StringWriter();
        ArrayList<UMLClass> cList = umlList;
        
        JsonArrayBuilder classBuilder = Json.createArrayBuilder();
        fillArray(cList, classBuilder);
        JsonArray nodesArray = classBuilder.build();
        
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add("class", nodesArray)
                .build();

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }  
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error reading
     * in data from the file.
     */
    private void fillArray(ArrayList<UMLClass> cList, JsonArrayBuilder arrayBuilder){
        for(UMLClass a : cList){
            JsonObject classObject = makeClassObject(a);
            JsonArrayBuilder helperArray = Json.createArrayBuilder();
            arrayBuilder.add(classObject);
            
            /*for(Variable b : a.getVariables()){
                JsonObject variableObject = makeVariableObject(b);
                helperArray.add(variableObject);
            }
            for(Method b : a.getMethods()){
                JsonObject methodObject = makeMethodObject(b);
                helperArray.add(methodObject);
            }*/
        }
    }
    private void fillVariableArray(UMLClass a, JsonArrayBuilder arrayBuilder){
        for(Variable b : a.getVariables()){
            JsonObject variableObject = makeVariableObject(b);
            arrayBuilder.add(variableObject);
        }
    }
    private void fillMethodArray(UMLClass a, JsonArrayBuilder arrayBuilder) {
        for(Method b : a.getMethods()){
            JsonObject methodObject = makeMethodObject(b);
            arrayBuilder.add(methodObject);
        }
    }
    private JsonObject makeClassObject(UMLClass a){
        JsonArrayBuilder variableArray = Json.createArrayBuilder();
        fillVariableArray(a, variableArray);
        JsonArrayBuilder methodArray = Json.createArrayBuilder();
        JsonObject jso;
        fillMethodArray(a, methodArray);
        if(a.getParent() != null){
             jso = Json.createObjectBuilder()
                    .add("class name", a.getClassName())
                    .add("package name", a.getPackageName())
                    .add("parent", a.getParent().getClassName())
                    .add("class name text", a.getClassText().getText())
                    .add("variable name text", a.getVariableText().getText())
                    .add("method name text", a.getMethodText().getText())
                    .add("x", a.getX())
                    .add("y", a.getY())
                    .add("variable", variableArray)
                    .add("method", methodArray)
                    .build();
        }
        else{
            jso = Json.createObjectBuilder()
                    .add("class name", a.getClassName())
                    .add("package name", a.getPackageName())
                    .add("class name text", a.getClassText().getText())
                    .add("variable name text", a.getVariableText().getText())
                    .add("method name text", a.getMethodText().getText())
                    .add("x", a.getX())
                    .add("y", a.getY())
                    .add("variable", variableArray)
                    .add("method", methodArray)
                    .build();
        }
        return jso;
    }
    private JsonObject makeVariableObject(Variable b){
        JsonObject jso = Json.createObjectBuilder()
                .add("variable name", b.getName())
                .add("variable type", b.getType())
                .add("variable static", b.getStatic())
                .add("variable access", b.getAccess())
                .build();
        return jso;
    }
    private JsonObject makeMethodObject(Method b){
        JsonObject jso = Json.createObjectBuilder()
                .add("method name", b.getName())
                .add("method return", b.getReturn())
                .add("method static", b.getStatic())
                .add("method abstract", b.getAbstract())
                .add("method access", b.getAccess())
                .add("method arg", b.getArg().toString())
                .build();
        return jso;
    }
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        DataManager dataManager = (DataManager) data;
        dataManager.reset();
        
        JsonObject json = loadJSONFile(filePath);
        JsonArray jsonClass = json.getJsonArray("class");
        loadClass(jsonClass, dataManager);
        
    }
    public ArrayList<UMLClass> loadData(String filePath) throws IOException{
        JsonObject json = loadJSONFile(filePath);
        JsonArray jsonClass = json.getJsonArray("class");
        return loadClass(jsonClass);
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    public void exportCode(AppDataComponent data) throws IOException{
        DataManager dataManager = (DataManager) data;
        ArrayList<UMLClass> classList = dataManager.getClassList();
        PrintWriter pw;
        for(int i = 0; i < classList.size(); i++){
            ArrayList<Variable> varList = classList.get(i).getVariables();
            ArrayList<Method> metList = classList.get(i).getMethods();
            
            pw = new PrintWriter("./work/exportedCode/" + classList.get(i).getClassName());
            
            pw.write("package " + classList.get(i).getPackageName() + ";\n");
            pw.write("public class " + classList.get(i).getClassName() + "{\n");
            for(int x = 0; x < varList.size(); x++){
                pw.write("\t" + varList.get(x).getAccess() + " ");
                if(varList.get(x).getStatic() == true){
                    pw.write("static ");
                }
                pw.write(varList.get(x).getType() + " ");
                pw.write(varList.get(x).getName() + ";\n");
            }
            
            for(int x = 0; x < metList.size() ; x++){
                if(metList.get(x).getAccess().equals("private") && metList.get(x).getAbstract() == true){
                    pw.write("\tabstract ");
                }
                else if(!metList.get(x).getAccess().equals("private") && metList.get(x).getAbstract() == true){
                    pw.write("\t" + metList.get(x).getAccess() + " abstract");
                }
                else{
                    pw.write("\t" + metList.get(x).getAccess() + " ");
                }
                if(metList.get(x).getStatic() == true && metList.get(x).getAbstract() == false){
                    pw.write("static ");
                }
                pw.write(metList.get(x).getReturn() + " ");
                pw.write(metList.get(x).getName() + "(");
                for(int y = 0; y < metList.get(x).getArg().size(); y++){
                    pw.write(metList.get(x).getArg().get(y));
                    if(y == metList.get(x).getArg().size()-1){
                        pw.write(")");
                    }
                    else{
                        pw.write(", ");
                    }
                }
                
                pw.write("\n");
                if(!metList.get(x).getReturn().equals("void") && !metList.get(x).getReturn().equals("")){
                    pw.write("\t\treturn " + metList.get(x).getReturn()+ ";\n");
                }
                
                pw.write("\t}\n");
            }
            pw.write("}\n");
            pw.close();
        }
        
    }
    private ArrayList<UMLClass> loadClass(JsonArray jsonArray){
        ArrayList<UMLClass> hello = new ArrayList<UMLClass>();
        for(int i = 0; i < jsonArray.size(); i++){
            UMLClass a = new UMLClass(0,0);
            JsonObject classJso = jsonArray.getJsonObject(i);
            a.setClassName(classJso.getString("class name"));

            a.setPackageName(classJso.getString("package name"));

            
            //a.setParent(classJso.getString("parent"));
            a.setClassNameText(classJso.getString("class name text"));
            a.setVariableNameText(classJso.getString("variable name text"));
            a.setMethodNameText(classJso.getString("method name text"));
            
            double x = Double.parseDouble(classJso.getJsonNumber("x").toString());
            double y = Double.parseDouble(classJso.getJsonNumber("y").toString());
            
            a.setNewCoordinate(x, y);
            JsonArray variableArray = classJso.getJsonArray("variable");
            JsonArray methodArray = classJso.getJsonArray("method");
            
            for(int z = 0; z < variableArray.size(); z++){
                Variable var = new Variable();
                JsonObject variableJso = variableArray.getJsonObject(z);
                var.setName(variableJso.getString("variable name"));
                var.setType(variableJso.getString("variable type"));
                var.setStatic(variableJso.getBoolean("variable static"));
                var.setAccess(variableJso.getString("variable access"));
                a.addVariable(var);
            }
            for(int z = 0; z < methodArray.size(); z++){
                Method met = new Method();
                JsonObject methodJso = methodArray.getJsonObject(z);
                met.setName(methodJso.getString("method name"));
                met.setReturn(methodJso.getString("method return"));
                met.setStatic(methodJso.getBoolean("method static"));
                met.setAbstract(methodJso.getBoolean("method abstract"));
                met.setAccess(methodJso.getString("method access"));
                int end = methodJso.getString("method arg").length();
                met.addArg(methodJso.getString("method arg").toString().substring(1, end-1));
                a.addMethod(met);
            }
            
            hello.add(a);
        }
        return hello;
    }
    private void loadClass(JsonArray jsonArray, DataManager dataManager){
        for(int i = 0; i < jsonArray.size(); i++){
            JsonObject classJso = jsonArray.getJsonObject(i);
            String className = classJso.getString("class name");

            String packageName = classJso.getString("package name");
            String parentName = classJso.getString("parent");
            String classNameText = classJso.getString("class name text");
            String variableNameText = classJso.getString("variable name text");
            String methodNameText = classJso.getString("method name text");
            
            double x = Double.parseDouble(classJso.getJsonNumber("x").toString());
            double y = Double.parseDouble(classJso.getJsonNumber("y").toString());
            
            //dataManager.makeClass(className, packageName, parentName,classNameText, 
                    //variableNameText, methodNameText, x, y);
            JsonArray variableArray = classJso.getJsonArray("variable");
            JsonArray methodArray = classJso.getJsonArray("method");
            
            for(int z = 0; z < variableArray.size(); z++){
                Variable var = new Variable();
                JsonObject variableJso = variableArray.getJsonObject(z);
                var.setName(variableJso.getString("variable name"));
                var.setType(variableJso.getString("variable type"));
                var.setStatic(variableJso.getBoolean("variable static"));
                var.setAccess(variableJso.getString("variable access"));
                dataManager.getClassList().get(i).addVariable(var);
            }
            for(int z = 0; z < methodArray.size(); z++){
                Method met = new Method();
                JsonObject methodJso = methodArray.getJsonObject(z);
                met.setName(methodJso.getString("method name"));
                met.setReturn(methodJso.getString("method return"));
                met.setStatic(methodJso.getBoolean("method static"));
                met.setAbstract(methodJso.getBoolean("method abstract"));
                met.setAccess(methodJso.getString("method access"));
                int end = methodJso.getString("method arg").length();
                met.addArg(methodJso.getString("method arg").toString().substring(1, end-1));
                dataManager.getClassList().get(i).addMethod(met);
            }
        }
    }
    
    /**
     * This method exports the contents of the data manager to a 
     * Web page including the html page, needed directories, and
     * the CSS file.
     * 
     * @param data The data management component.
     * 
     * @param filePath Path (including file name/extension) to where
     * to export the page to.
     * 
     * @throws IOException Thrown should there be an error writing
     * out data to the file.
     */
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {

    }
    
    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
	// NOTE THAT THE Web Page Maker APPLICATION MAKES
	// NO USE OF THIS METHOD SINCE IT NEVER IMPORTS
	// EXPORTED WEB PAGES
    }
}
