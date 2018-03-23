package parser;

import models.components.Attribute;
import models.components.Method;
import models.structures.Structure;

import java.util.*;

/**
 * @author dungdang
 * The Diagram which holds all the structure
 */
public class Diagram {
    /* The basic data type */
    public final List<String> BASIC_DATA_TYPES = Arrays.asList("int", "byte", "short", "long", "float", "double", "boolean", "char");

    private ArrayList<Structure> structures; // List of the structures

    /**
     * Default Constructor
     */
    public Diagram(){
        structures = new ArrayList<>();
    }

    /**
     * Add new structure
     * @param st
     */
    public void add(Structure st){
        structures.add(st);
    }

    /**
     * Get the list of the structures
     * @return
     */
    public ArrayList<Structure> getStructures() {
        return structures;
    }

    /**
     * By the superStructure names , now we iherit in the real way
     */
    public void reArrangeStructures(){
        for (int i=0;i<structures.size();i++){
            Structure structure = structures.get(i);
            for (int j=0;j<structures.size();j++){
                Structure structure1 = structures.get(j);
                for (String name : structure.getSuperStructureNames()) {
                    if (name.equals(structure1.getName())) {
                        structure.iherit(structure1);
                        break;
                    }
                }
             }
        }
    }

    /**
     * From the iheritances , using the dfs algorithm to get the levels for the structures
     */
    public void getLevelForStructures(){
        for (Structure str : structures)
            if (str.getSuperStructures().size() == 0 ){
                dfs(str);
            }
        sortStructuresByLevel();
    }

    /**
     * Search for the methods by name
     * @param name
     * @return A list of method
     */
    public ArrayList<Method> filterMethodByName(String name){
        ArrayList<Method> methods = new ArrayList<>();
        for (Structure structure : structures){
            for (Method method: structure.getMethods())
                if (method.getName().equals(name))
                    methods.add(method);
        }
        return methods;
    }

    /**
     * Search for class or interface by name
     * @param name
     * @return a class or interface
     */
    public Structure filterClassOrInterfaceByName(String name){
        for (Structure structure : structures){
            if (structure.getName().equals(name))
                return structure;
        }
        return null;
    }

    /**
     * Detect the circular dependencies in the project
     * @return
     */
    public HashMap<Structure, Structure> detectCircularDependencies(){
        HashMap<Structure, Structure> dependencies = new HashMap<>();
        for (Structure structure : this.structures){
            String name = structure.getName();
            Queue<Structure> queue = new LinkedList<Structure>();
            queue.add(structure);

            while (queue.isEmpty() == false){
                Structure temp = queue.poll();
                for(Attribute attribute : temp.getAttributes()){
                    if (attribute.getType().equals(name)){
                        dependencies.put(structure, temp);
                    }
                    else if (!BASIC_DATA_TYPES.contains(attribute.getType())){
                        for (Structure s : this.structures){
                            if (s.getName().equals(attribute.getType())) {
                                queue.add(s);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dependencies;
    }

    /**
     * DFS to get the level for the structure
     * @param st
     */
    private void dfs(Structure st){
        if (st.isInterface() == false)
            for (Structure subst : st.getDerivedStructure() ) {
                subst.setLevel(st.getLevel()+1);
                dfs(subst);
            }
        else
            for (Structure subst : st.getDerivedStructure() ){
                if (subst.isInterface())
                    subst.setLevel(st.getLevel()+1);
                dfs(subst);
            }
    }

    /**
     * Sort the list of structures as the level
     */
    private void sortStructuresByLevel(){
        for(int i = 0;i<structures.size()-1;i++)
            for (int j=i+1;j<structures.size();j++)
                if (structures.get(i).getLevel() > structures.get(j).getLevel()){
                    Structure s = structures.get(i);
                    structures.set(i, structures.get(j));
                    structures.set(j, s);
                }
    }
}
