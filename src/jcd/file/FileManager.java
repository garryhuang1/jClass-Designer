package jcd.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import jcd.data.DataManager;
import jcd.data.UMLClass;
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
            arrayBuilder.add(classObject);
        }
    }
    private JsonObject makeClassObject(UMLClass a){
        JsonObject jso = Json.createObjectBuilder()
                .add("class name", a.getClassName())
                .add("package name", a.getPackageName())
                .add("class name text", a.getClassText().getText())
                .add("variable name text", a.getVariableText().getText())
                .add("method name text", a.getMethodText().getText())
                .add("x", a.getX())
                .add("y", a.getY())
                .add("var name", a.getVarName().toString())
                .add("var type", a.getVarType().toString())
                .add("var static", a.getVarStatic().toString())
                .add("var access", a.getVarAccess().toString())
                .add("method names", a.getMetName().toString())
                .add("method return", a.getMetReturn().toString())
                .add("method static", a.getMetStatic().toString())
                .add("method abstract", a.getMetAbstract().toString())
                .add("method access", a.getMetAccess().toString())
                .add("method arg", a.getMetArg().toString())
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

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    public void exportCode(AppDataComponent data, String filePath) throws IOException{
        DataManager dataManager = (DataManager) data;
        ArrayList<UMLClass> classList = dataManager.getClassList();
        PrintWriter pw = new PrintWriter(filePath);
        for(int i = 0; i < classList.size(); i++){
            ArrayList<String> varType = classList.get(i).getVarType();
            ArrayList<String> varName = classList.get(i).getVarName();
            ArrayList<String> varAccess = classList.get(i).getVarAccess();
            ArrayList<Boolean> varStatic = classList.get(i).getVarStatic();
            
            ArrayList<String> metName = classList.get(i).getMetName();
            ArrayList<String> metReturn = classList.get(i).getMetReturn();
            ArrayList<Boolean> metStatic = classList.get(i).getMetStatic();
            ArrayList<Boolean> metAbstract = classList.get(i).getMetAbstract();
            ArrayList<String> metAccess = classList.get(i).getMetAccess();
            ArrayList<String> metArg = classList.get(i).getMetArg();
            
            pw.write("package " + classList.get(i).getPackageName() + ";\n");
            pw.write("public class " + classList.get(i).getClassName() + "{\n");
            for(int x = 0; x < varName.size(); x++){
                pw.write("\t" + varAccess.get(x) + " ");
                if(varStatic.get(x) == true){
                    pw.write("static ");
                }
                pw.write(varType.get(x) + " ");
                pw.write(varName.get(x) + ";\n");
            }
            
            for(int x = 0; x < metName.size() ; x++){
                if(metAccess.get(x).toString().equals("private") && metAbstract.get(x) == true){
                    pw.write("\tabstract ");
                }
                else if(!metAccess.get(x).toString().equals("private") && metAbstract.get(x) == true){
                    pw.write("\t" + metAccess.get(x) + " abstract ");
                }
                else{
                    pw.write("\t" + metAccess.get(x) + " ");
                }
                if(metStatic.get(x) == true && metAbstract.get(x) == false){
                    pw.write("static ");
                }
                pw.write(metReturn.get(x) + " ");
                pw.write(metName.get(x) + "(");
                pw.write(metArg.get(x)+ ") {\n");
                if(!metReturn.get(x).toString().equals("void")){
                    pw.write("\t\treturn " + metReturn.get(x) + "\n");
                }
                pw.write("\t}\n");
            }
            pw.write("}\n");
        }
        pw.close();
    }
    private void loadClass(JsonArray jsonArray, DataManager dataManager){
        for(int i = 0; i < jsonArray.size(); i++){
            JsonObject classJso = jsonArray.getJsonObject(i);
            String className = classJso.getString("class name");
            String packageName = classJso.getString("package name");
            String classNameText = classJso.getString("class name text");
            String variableNameText = classJso.getString("variable name text");
            String methodNameText = classJso.getString("method name text");
            double x = Double.parseDouble(classJso.getJsonNumber("x").toString());
            double y = Double.parseDouble(classJso.getJsonNumber("y").toString());
            
            dataManager.makeClass(className, packageName, classNameText, 
                    variableNameText, methodNameText, x, y);
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
