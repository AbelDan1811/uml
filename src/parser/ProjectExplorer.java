package parser;

import models.components.Method;
import models.structures.Structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author dungdang
 * Help get all the Java files in the project
 */
public class ProjectExplorer {
    private Diagram diagram; // The digram
    public ProjectExplorer(String path) throws IOException{
        diagram = new Diagram();
        parseAllFiles(path);
        diagram.reArrangeStructures();
        diagram.getLevelForStructures();
    }

    private void parseAllFiles(String path) throws IOException{
        File file = new File(path);
        if (file.isFile() && file.getName().endsWith(".java")){
            Parser.parse(diagram, new FileInputStream(file));
        }
        else if (file.isDirectory()){
            for (File f : file.listFiles()){
                parseAllFiles(f.getAbsolutePath());
            }
        }
    }

    public ArrayList<Structure> getAllStructures(){
        return diagram.getStructures();
    }

    public ArrayList<Method> filterMethodByName(String name){
        return diagram.filterMethodByName(name);
    }

    public Structure filterClassOrInterfaceByName(String name) { return diagram.filterClassOrInterfaceByName(name); }

    public HashMap<Structure, Structure> detectCircularDependencies(){
        return diagram.detectCircularDependencies();
    }

}
