package models.components;

import models.structures.Structure;

/**
 * @author dungdang
 * Abstract Class Represents component of a Class or Interface such as Attribute and Method in Java
 * A component must be in 2 types : Method or Attribute
 * They have names, types (INT, STRING, VOID.. ) and access modifier (PUBLIC, PRIVATE..)
 * Some components in a class or interface is static
 */
abstract public class Component {
    protected String name; // name of the component
    protected String type; // type of the component
    protected String accessModifier; // access modifier of the component
    protected boolean isStatic; // whether the component is static or not
    protected Structure owner; // the structure this component belonged to

    /**
     * Default Constructor
     */
    public Component() {
    }

    /**
     *
     * @return a String represents this component
     */
    abstract public String toString();

    /**
     * Compare two Component
     *
     * @param o Component
     * @return true if they are equal
     */
    abstract public boolean equalsTo(Component o);

    /**
     * Get name of the component
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name for the component
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get type of the component
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type for the component
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get access modifier of the component
     *
     * @return accessModifier
     */
    public String getAccessModifier() {
        return accessModifier;
    }

    /**
     * Set access modifier for the component
     *
     * @param accessModifier AccessModifier
     */
    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    /**
     * Check if the component is static
     *
     * @return isStatic
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     * Set the static attribute for the component
     *
     * @param aStatic boolean
     */
    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    /**
     * Set owner structure for the component
     * @param str structure
     */
    public void setOwner(Structure str){
        this.owner = str;
    }

    /**
     * Get the owner structure of the component
     * @return owner structure
     */
    public Structure getOwner() {
        return owner;
    }
}
