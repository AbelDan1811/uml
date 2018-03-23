package models.components;

/**
 * @author dungdang
 * Represent Parameter in Java
 * A parameter has name and type( INT, STRING, OBJECT.. )
 */
public class Parameter {
    private String name;// Name of the param
    private String type;// type of the param

    /**
     * Default constructor
     */
    public Parameter() {
    }

    /**
     * Constructor with two params
     * @param name String
     * @param type String
     */
    public Parameter(String name, String type) {

        this.name = name;
        this.type = type;
    }

    /**
     * Get the name of the param
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name for the param
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the type of the param
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type for the param
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Represent the param
     * @return a string represents the param
     */
    public String toString(){
        return this.type + " ";
    }
}
