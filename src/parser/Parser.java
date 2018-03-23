package parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import models.components.Attribute;
import models.components.Method;
import models.structures.Class;
import models.structures.Interface;
import models.structures.Structure;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumSet;

/**
 * @author dungdang
 * Parse through java files
 */
public class Parser {
    /**
     * Parse a new java file and add it's content into the diagram
     * @param diagram
     * @param in
     */
    public static void parse(Diagram diagram, FileInputStream in){

        /* Initialize javaparser */
        CompilationUnit cu = JavaParser.parse(in);
        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        /* Parsing through every class or interface in that file */
        for (TypeDeclaration<?> type : types){
            if (type instanceof ClassOrInterfaceDeclaration) {

                /* Get the name and modifiers of the structure */
                ClassOrInterfaceDeclaration struc = (ClassOrInterfaceDeclaration) type;
                Structure str = null;
                String name = struc.getNameAsString();
                String[] modifiers = getModifiersAsStrings(struc.getModifiers());

                /* Get type of the structures (class, interface, enum) and declare that structures */
                if (struc.isInterface())
                    str = new Interface(name, modifiers);
                else if (struc.isEnumDeclaration()) ;
                else{
                    str = new Class(name, modifiers);
                    getIherit(struc, str);
                }


                if (str != null){
                    // Parsing for methods and properties before adding it to the diagram
                    parseMethodsAndProperties(struc, str);
                    // Get the source code for the structure
                    str.setSourceCode(struc.toString());
                    diagram.add(str);
                }
            }
        }
    }

    /**
     * Get the extended structures of this one
     * @param struc
     * @param str
     */
    private static void getIherit(ClassOrInterfaceDeclaration struc, Structure str){
        for (ClassOrInterfaceType tp : struc.getExtendedTypes())
            str.iherit(tp.getNameAsString());
        for (ClassOrInterfaceType tp : struc.getImplementedTypes())
            str.iherit(tp.getNameAsString());

    }

    /**
     * Get modifiers as an array of string
     * @param mdfier
     * @return an array or string
     */
    private static String[] getModifiersAsStrings(EnumSet<Modifier> mdfier){
        ArrayList<String> modifiers = new ArrayList<>();
        for (Modifier m : mdfier){
            modifiers.add(m.asString());
        }
        return modifiers.toArray(new String[0]);
    }

    /**
     * Parsing the method and properties of the structure
     * @param struc
     * @param str
     */
    private static void parseMethodsAndProperties(ClassOrInterfaceDeclaration struc, Structure str){
        // Get the methods
        parseMethods(struc,str);

        // Get the properties
        parseProperties(struc, str);

        // Get the constructors
        parseConstructors(struc, str);
    }

    /**
     * Get all the constructors of the structure
     * @param struc
     * @param str
     */
    private static void parseConstructors(ClassOrInterfaceDeclaration struc, Structure str){
        for (ConstructorDeclaration method : struc.getConstructors()){
            /* Get name, type, and modifiers */
            String name = method.getNameAsString();
            String type = struc.getNameAsString();
            String[] modifiers = getModifiersAsStrings(method.getModifiers());

            Method m = new Method(name, type, modifiers);

            /* Get the params of the constructor */
            for (Parameter p : method.getParameters()){
                m.addParam(new models.components.Parameter(p.getNameAsString(),p.getType().asString()));
            }

            /* Get the source code*/
            m.setSourceCode(method.toString());
            /* Save it */
            str.addMethod(m);
        }
    }

    /**
     * Get all the properties of the structure
     * @param struc
     * @param str
     */
    private static void parseProperties(ClassOrInterfaceDeclaration struc, Structure str){
        for(FieldDeclaration field : struc.getFields()){
            /* Get name and modifers */
            String type = field.getCommonType().asString();
            String[] modifiers = getModifiersAsStrings(field.getModifiers());

            /* In one line user may declair more than 1 property, so we have to get every single one as a field*/
            for (VariableDeclarator var : field.getVariables()){
                String name = var.getNameAsString();
                /* Save it */
                str.addAttribute(new Attribute(name,type,modifiers));
            }
        }
    }

    /**
     * Get all the methods of the structure
     * @param struc
     * @param str
     */
    private static void parseMethods(ClassOrInterfaceDeclaration struc , Structure str){
        for (MethodDeclaration method : struc.getMethods()){
            /* Get name, return type and modifiers of the method */
            String name = method.getNameAsString();
            String type = method.getType().asString();
            String[] modifiers = getModifiersAsStrings(method.getModifiers());

            Method m = new Method(name,type ,modifiers);

            /* Get the params of the method */
            for (Parameter p : method.getParameters()){
                m.addParam(new models.components.Parameter(p.getNameAsString(), p.getType().asString()));
            }
            /* Get the source code */
            m.setSourceCode(method.toString());
            /* Save it */
            str.addMethod(m);
        }
    }


}

