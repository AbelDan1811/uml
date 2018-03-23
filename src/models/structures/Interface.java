package models.structures;

import java.util.ArrayList;

/**
 * @author dungdang
 * Represent Interface Structure in Java
 */
public class Interface extends Structure {

    /**
     * Default Constructor
     */
    public Interface() {
        super();
    }

    /**
     * Constructor with 2 params
     * @param name
     * @param modifiers
     */
    public Interface(String name, String[] modifiers) {

        /* Default values */
        this.name = name;
        this.accessModifier = "public";
        this.level = 0;
        this.isInterface = true;

        /* Get the modifiers */
        for(String st : modifiers) {
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
     * Represent the Interface
     *
     * @return a string that represents the Interface
     */
    @Override
    public String toString() {
        return this.getAccessModifier() + " interface : " + this.getName();
    }

}
