package models.structures;

import java.util.ArrayList;

/**
 * @author dungdang
 * Represent Class Structure in Java
 */
public class Class extends Structure {
    private boolean isAbstract;

    /**
     * Constructor with no params
     */
    public Class(){
        super();
    }

    /**
     * Constructor with 2 params
     * @param name
     * @param modifiers
     */
    public Class(String name, String[] modifiers) {

        /* Default values */
        this.name = name;
        this.isAbstract = false;
        this.accessModifier = "public";
        this.level = 0;
        this.isInterface = false;

        /* Get the modifiers */
        for(String st : modifiers) {
            if (st.equals("abstract"))
                isAbstract = true;
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
        }

        methods = new ArrayList<>();
        attributes = new ArrayList<>();
        superStructures = new ArrayList<>();
        derivedStructures = new ArrayList<>();
        superStructureNames = new ArrayList<>();
    }


    /**
     * Check if the Class is abstract
     *
     * @return true if the Class is abstract
     */
    public boolean isAbstract() {
        return isAbstract;
    }

    /**
     * Set the class to be abstract or not
     *
     * @param anAbstract boolean
     */
    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    /**
     * Represent the class
     *
     * @return a string that represents the class
     */
    @Override
    public String toString() {
        return /*(this.isAbstract()?"abstract ":"") +*/ this.getAccessModifier().toString()+ " class : " + this.getName()+ " " ;
    }

}
