package models.components;

import java.util.ArrayList;

/**
 * @author dungdang
 * Represent Method Component in Java
 * A method has name, type, access modifier and params
 */
public class Method extends Component {
    private boolean isConstructor; // whether the method is constructor or not
    private ArrayList<Parameter> params; // The list of params
    private String sourceCode; // source code of the method

    /**
     * Default Constructor
     */
    public Method() {
        params = new ArrayList<>();
    }

    /**
     * Constructor with 2 params
     *
     * @param name String
     */
    public Method(String name, String type, String[] modifiers) {
        this.name = name;
        this.type = type;
        /* Default value */
        this.accessModifier = "public";
        this.isStatic = false;

        /* Check if this method is a constructor */
        if (type.equals(name))
            this.isConstructor = true;
        else
            this.isConstructor = false;

        /* Get the modifiers */
        for(String st : modifiers) {
            if (st.equals("public") || st.equals("private") || st.equals("protected"))
                this.accessModifier = st;
            if (st.equals("static"))
                this.isStatic = true;
        }

        params = new ArrayList<>();
    }


    /**
     * Add a param to the param list
     *
     * @param param Parameter
     */
    public void addParam(Parameter param) {
        params.add(param);
    }

    /**
     * Represennt the Method
     *
     * @return a string represents the method
     */
    @Override
    public String toString() {
        String paramString = "(";
        for (Parameter p : this.params)
            paramString = paramString + p.toString()+",";
        if (this.params.size() != 0)
            paramString = paramString.substring(0,paramString.length()-2);
        if (!isConstructor)
            return /*this.owner.getName() +": " + this.getAccessModifier() + " " + */this.getType() + ": "+ this.getName()+paramString+")";
        else
            return this.getName()+ paramString + ")";

    }

    /**
     * Get the param list of the method
     *
     * @return params
     */
    public ArrayList<Parameter> getParams() {
        return params;
    }

    /**
     * @return A string which is the source code of the method
     */
    public String getSourceCode(){
        return this.sourceCode;
    }

    /**
     * Set the source code for the method
     * @param sourceCode
     */
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    /**
     * Compare two Methods, they are equal if they have the same return type, same name and param list have the same types
     *
     * @param m Component
     * @return true if they are equal
     */
    @Override
    public boolean equalsTo(Component m) {
        if (m == this)
            return true;
        if (m.getClass() != this.getClass() || m == null)
            return false;
        Method method = (Method) m;
        if (this.getName() != method.getName())
            return false;
        if (this.getType() != method.getType())
            return false;
        if (this.getAccessModifier() != method.getAccessModifier())
            return false;
        if (this.isStatic() != method.isStatic())
            return false;
        if (this.params.size() != method.params.size())
            return false;
        for (int i = 0; i < this.params.size(); i++)
            if (this.params.get(i).getType() != method.params.get(i).getType())
                return false;
        return true;
    }
}
