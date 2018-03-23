import models.structures.Structure;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Shape {
    final static  int MAsize = 5;
    final static int CharSize = 6;
    final static int height = 12;
    final static int TextHeight = 13;
    int AtLines = 0;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    int MeLines = 0;
    volatile int draggedAtX,draggedAtY;
    public Structure structure;

    private JLabel Cname = new JLabel();
    private JTextArea Attribute = new JTextArea();
    private JTextArea Method = new JTextArea();

    private int level = 0;
    private double scale = 1;
    private int LowerBound = 0;
    private int UpperBound = 0;
    private int width = 0;
    private int max = 0;

    /**
     * get the shape level
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * set the shape level
     * @param level : shape level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Constructor of shape
     * @param st : the given Structure
     */
    public Shape(Structure st){
        this.structure = st;
        Cname.setText(structure.getName());
        //Set up the "Dragging ability" for all the shape object
        Cname.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        Attribute.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        Method.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        Cname.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Cname.setLocation(e.getX() - draggedAtX + Cname.getLocation().x,
                        e.getY() - draggedAtY + Cname.getLocation().y);
                Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
                        e.getY() - draggedAtY + Attribute.getLocation().y);
                Method.setLocation(e.getX() - draggedAtX +Method.getLocation().x,
                        e.getY() - draggedAtY + Method.getLocation().y);
                setWidth(Cname.getX());
                setUpperBound(Cname.getY());
                Gui.diagram.DrawLink(Gui.panel);
                Gui.frame.revalidate();
                Gui.frame.repaint();

            }
        });

        Attribute.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Cname.setLocation(e.getX() - draggedAtX + Cname.getLocation().x,
                        e.getY() - draggedAtY + Cname.getLocation().y);
                Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
                        e.getY() - draggedAtY + Attribute.getLocation().y);
                Method.setLocation(e.getX() - draggedAtX +Method.getLocation().x,
                        e.getY() - draggedAtY + Method.getLocation().y);
                setWidth(Cname.getX());
                setUpperBound(Cname.getY());
                Gui.diagram.DrawLink(Gui.panel);
                Gui.frame.revalidate();
                Gui.frame.repaint();
            }
        });

        Method.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Cname.setLocation(e.getX() - draggedAtX + Cname.getLocation().x,
                        e.getY() - draggedAtY + Cname.getLocation().y);
                Attribute.setLocation(e.getX() - draggedAtX + Attribute.getLocation().x,
                        e.getY() - draggedAtY + Attribute.getLocation().y);
                Method.setLocation(e.getX() - draggedAtX +Method.getLocation().x,
                        e.getY() - draggedAtY + Method.getLocation().y);
                setWidth(Cname.getX());
                setUpperBound(Cname.getY());
                Gui.diagram.DrawLink(Gui.panel);
                Gui.frame.revalidate();
                Gui.frame.repaint();
            }
        });
    }

    /**
     * checl if a shape is inherited from another shape
     * @param a : the given shape
     * @return true: inherited
     * @return false: not inherited
     */
    public boolean isInheritedFrom(Shape a){
        for (Structure st : structure.getSuperStructures()){
            if (st.equalsTo(a.structure))
                return true;
        }
        return false;
    }

//Setters and getters
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public JLabel getCname() {
        return Cname;
    }

    public JTextArea getAttribute() {
        return Attribute;
    }

    public JTextArea getMethod() {
        return Method;
    }

    public int getLowerBound() {
        return LowerBound;
    }

    public void setLowerBound(int lowerBound) {
        LowerBound = lowerBound;
    }

    public int getUpperBound() {
        return Cname.getY();
    }

    public int getWidth() {
        return Cname.getX();
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public void setUpperBound(int upperBound) {
        UpperBound = upperBound;
    }

    /**
     * get the info of the shape then set the info for the shape element
     */
    public void getInfo(){
        int a = Cname.getText().length();
        int b =0 ;
        int c = 0;
        if(structure.getAttributes().size() > 0) {
            b = structure.getAttributes().get(0).toString().length();
            for (int i = 0; i < structure.getAttributes().size(); i++) {
                if (b < structure.getAttributes().get(i).toString().length()) {
                    b = structure.getAttributes().get(i).toString().length();
                }
            }
        }
        if(structure.getMethods().size() > 0){
            c = structure.getMethods().get(0).toString().length();
            for(int i = 0 ; i < structure.getMethods().size() ; i++){
                if ( c < structure.getMethods().get(i).toString().length()) {
                    c = structure.getMethods().get(i).toString().length();
                }
            }
        }

        a = a*CharSize;
        b = b*MAsize;
        c = c*MAsize;
        int max = 0;
        String newAt = new String();
        String newMethod = new String();
        if (a >= b && a >= c) max = a;
        if (b >= a && b >= c) max = b;
        if (c >= a && c >= b) max = c;
        setMax(max);
        //System.out.println("max"+max);
        for(int i = 0 ; i < structure.getAttributes().size() ; i++) {
            if(i > 0) {
                newAt = newAt + "\n" + structure.getAttributes().get(i).toString();
            }
            else{
                newAt = structure.getAttributes().get(i).toString();
            }
            AtLines++;
        }
        Attribute.setText(newAt);
        for(int i = 0 ; i < structure.getMethods().size() ; i++) {
            if(i > 0) {
                newMethod = newMethod + "\n" + structure.getMethods().get(i).toString();
            }
            else{
                newMethod = structure.getMethods().get(i).toString();
            }
            MeLines++;
        }
        Method.setText(newMethod);
    }

    /**
     * Draw the shape
     * @param panel : the draw panel
     */
    public void DrawContent(JPanel panel) {

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        Font f1 = new Font(Font.DIALOG,3,(int)(9*scale));
        Cname.setFont(f1);
        Cname.setBounds((int)(width*scale),(int)(UpperBound*scale),(int)(max*scale),(int)(height*scale));
        Cname.setBorder(border);
        panel.add(Cname);


        Attribute.setDragEnabled(true);
        Attribute.setEditable(false);
        Attribute.setBounds((int)(width*scale),Cname.getY()+Cname.getHeight(),(int)(max*scale),(int)(TextHeight*AtLines*scale));
        Attribute.setBorder(border);
        Font f = new Font(Font.SANS_SERIF,4,(int)(9*scale));
        Attribute.setFont(f);
        panel.add(Attribute);

        Method.setDragEnabled(true);
        Method.setEditable(false);
        Method.setBounds((int)(width*scale),Attribute.getY()+Attribute.getHeight(),(int)(max*scale),(int)(TextHeight*MeLines*scale));
        Method.setBorder(border);
        Method.setFont(f);
        panel.add(Method);
        System.out.println("scale"+scale+"max"+max);
        setLowerBound(Cname.getHeight()+Attribute.getHeight()+Method.getHeight());
        //setMax(Cname.getWidth());
    }


}
