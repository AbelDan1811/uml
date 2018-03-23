package models.components;

/**
 * @author dungdang
 * Represent Attribute component in Java
 */
public class Attribute extends Component {


    private boolean isConstant; // Check if the attribute is constant or not

    /**
     * Constructor with 3 params
     * @param name name of the attribute
     * @param type type of the attribute
     * @param modifiers modifiers of the attribute
     */
    public Attribute(String name, String type, String[] modifiers){
        this.name = name;
        this.type = type;

        /* Default values */
        this.accessModifier = "public";
        this.isStatic = false;
        this.isConstant = false;

        /* Get the modifiers */
        for(String st : modifiers) {
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
            if (st.equals("static"))
                this.isStatic = true;
            if (st.equals("final"))
                this.isConstant = true;
        }

    }


    /**
     * Check whether the attribute is a constant variable
     *
     * @return isConstant
     */
    public boolean isConstant() {
        return isConstant;
    }

    /**
     * Make the attribute be constant or not
     *
     * @param constant boolean
     */
    public void setConstant(boolean constant) {
        isConstant = constant;
    }

    /**
     * Represent the attribute
     *
     * @return the string that represents the attribute
     */
    @Override
    public String toString() {
        return this.getAccessModifier() + " " + this.getType() + ": "  + this.getName();
    }

    /**
     * Compare two attributes , they are the same for having same name.
     *
     * @param attr Component
     * @return true if two attributes are equal
     */
    @Override
    public boolean equalsTo(Component attr) {
        if (attr == this)
            return true;
        if (attr.getClass() != this.getClass() || attr == null)
            return false;
        return (this.getName() == attr.getName());
    }
}
