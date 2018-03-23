package models.structures;

import models.components.Attribute;
import models.components.Method;

import java.util.ArrayList;

/**
 * Represent Structure like Class or Method in Java
 * A Structure has a name, accessModifier
 * A Structure has methods , attributes
 * A Structure may be the super class of other Structure or derive from other Structure
 * Ex : A Class derives from another Class and many other Interfaces
 * An Interface derives from many other Interfaces
 */
abstract public class Structure {
    protected String name; // Get the name of the structures
    protected String accessModifier; // Get modifiers of the structures
    protected ArrayList<Method> methods; // Method of the structures
    protected ArrayList<Attribute> attributes; // Attributes of the structures
    protected ArrayList<Structure> superStructures; // Get extended structures implemented in the project
    protected ArrayList<Structure> derivedStructures; // Get derived structures implemented in the project
    protected ArrayList<String> superStructureNames; // Get extended structures names included other source structures
    protected int level; // Level of the structure in the diagram
    protected boolean isInterface; // whether the structure is interface or not
    protected String sourceCode; // source code of the structure
    /**
     * Default constructor
     */
    public Structure() {
        accessModifier = "public";

        methods = new ArrayList<>();
        attributes = new ArrayList<>();
        superStructures = new ArrayList<>();
        derivedStructures = new ArrayList<>();
        superStructureNames = new ArrayList<>();
    }


    /**
     * Get the method list of the Structure

     *
     * @return methods
     */
    public ArrayList<Method> getMethods() {
        return methods;
    }

    /**
     * Get the attribute list of the Structure
     *
     * @return attributes
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Get the name of the structures
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the structures
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return access modifier
     */
    public String getAccessModifier() {
        return accessModifier;
    }

    /**
     * Set access modifier for the strucutre
     * @param accessModifier
     */
    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    /**
     * Get the list of Structures this structures inherits
     *
     * @return superStructures
     */
    public ArrayList<Structure> getSuperStructures() {
        return superStructures;
    }

    /**
     * Get the list of Structures that inherit from this structures
     *
     * @return derivedStructures
     */
    public ArrayList<Structure> getDerivedStructure() {
        return derivedStructures;
    }

    /**
     * @return the level of the structure
     */

    public int getLevel(){
        return level;
    }
    /**
     * Represent the Structure
     *
     * @return a string that represent
     */
    abstract public String toString();

    /**
     * Add method to the method list of the Structure
     *
     * @param m Method
     */
    public void addMethod(Method m) {
        this.methods.add(m);
        m.setOwner(this);
    }

    /**
     * Add attribute to the attribute list of the Structure
     *
     * @param a Attribute
     */
    public void addAttribute(Attribute a) {
        this.attributes.add(a);
        a.setOwner(this);
    }

    /**
     * Add Structure to the subStructure list of this one
     *
     * @param st Structure
     */
    public void addSubStructure(Structure st) {
        this.derivedStructures.add(st);
    }

    /**
     * This Structure inherits from another one
     *
     * @param st Structure
     */
    public void iherit(Structure st) {
        this.superStructures.add(st);
        st.addSubStructure(this);
    }

    /**
     * Get the name of the extended structure
     * @param st the name of the extended structure
     */
    public void iherit(String st){
        this.superStructureNames.add(st);
    }

    /**
     * Get the SuperStructures' name
     */
    public ArrayList<String> getSuperStructureNames(){
        return superStructureNames;
    }
    /**
     * Compare two Structures. They are equal if they have the same type, same name, same package
     *
     * @param st Structure
     * @return true if two Structures are equal
     */
    public boolean equalsTo(Structure st) {
        return (st.getClass() == this.getClass() &&
                st.getName() == this.getName());
    }

    /**
     * @return A String show out accendant of this structure
     */
    protected String showAscendant(){
        if (superStructureNames.size() == 0)
            return "";
        String t = "<-";
        for (String str : superStructureNames)
            t = t+ str;
        return t;
    }

    /**
     * Get the source code of the structure
     */
    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Set the source code for the structure
     * @param sourceCode
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * Set the level for the strucure
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return true if this structure is interface
     */
    public boolean isInterface() {
        return isInterface;
    }
}
