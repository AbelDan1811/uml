import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The diagram class , which consist of many shapes
 */
public class Diagram {
    private List<Shape> shapes = new ArrayList<>(0);
    int PanelIndex[] = new int[100];
    volatile int draggedAtX,draggedAtY;
    String TrianglePath = "C:\\Users\\Administrator\\Desktop\\YOUR LIFE IN THE NEXT 2 DAYS\\Swang\\src\\triangle.png";
    String ReverseTrianglePath = "C:\\Users\\Administrator\\Desktop\\YOUR LIFE IN THE NEXT 2 DAYS\\Swang\\src\\reversetriangle.png";
    String LeftTrianglePath = "C:\\Users\\Administrator\\Desktop\\YOUR LIFE IN THE NEXT 2 DAYS\\Swang\\src\\lefttriangle.png";
    String RightTrianglePath = "C:\\Users\\Administrator\\Desktop\\YOUR LIFE IN THE NEXT 2 DAYS\\Swang\\src\\righttriangle.png";
    List<JLabel> LbList = new ArrayList<>(0);

    /**
     * Draw the link between shapes
     * @param panel : the drawing panel
     */
    public void DrawLink(JPanel panel){
        //4 arrow icon
        ImageIcon arrow = new ImageIcon(TrianglePath);
        Image image = arrow.getImage();
        Image newImg = image.getScaledInstance(10,10,Image.SCALE_SMOOTH);
        arrow = new ImageIcon(newImg);

        ImageIcon arrow2 = new ImageIcon(ReverseTrianglePath);
        Image image2 = arrow2.getImage();
        Image newImg2 = image2.getScaledInstance(10,10,Image.SCALE_SMOOTH);
        arrow2 = new ImageIcon(newImg2);

        ImageIcon arrow3 = new ImageIcon(LeftTrianglePath);
        Image image3 = arrow3.getImage();
        Image newImg3 = image3.getScaledInstance(10,10,Image.SCALE_SMOOTH);
        arrow3 = new ImageIcon(newImg3);

        ImageIcon arrow4 = new ImageIcon(RightTrianglePath);
        Image image4 = arrow4.getImage();
        Image newImg4 = image4.getScaledInstance(10,10,Image.SCALE_SMOOTH);
        arrow4 = new ImageIcon(newImg4);



        Border border = BorderFactory.createLineBorder(Color.BLACK);
        for(int i = 0 ; i < LbList.size() ; i++){
            panel.remove(LbList.get(i));
        }

        panel.revalidate();
        panel.repaint();
        LbList.removeAll(LbList);
        for (int i = 0; i < shapes.size(); i++) {

            for (int j = 0; j < shapes.size(); j++) {
                if ((shapes.get(j).isInheritedFrom(shapes.get(i))&& (i != j)) ) {
                    JLabel ArrowImg = new JLabel(arrow);
                    JLabel ArrowImg2 = new JLabel(arrow2);
                    JLabel ArrowImg3 = new JLabel(arrow3);
                    JLabel ArrowImg4 = new JLabel(arrow4);
                    JLabel line1 = new JLabel();
                    JLabel line2 = new JLabel();
                    JLabel line3 = new JLabel();;
                    int ShapeHdistant = -shapes.get(i).getLowerBound()+ shapes.get(j).getUpperBound()-shapes.get(i).getUpperBound();
                    int ShapeHdistant2 = -shapes.get(j).getLowerBound()+shapes.get(i).getUpperBound()-shapes.get(j).getUpperBound();

                    //shapes.get(j) under
                    if(shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound() <= shapes.get(j).getUpperBound()){
                        line1.setBounds(shapes.get(i).getWidth()+shapes.get(i).getMax()/2,
                                shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound(),
                                1,ShapeHdistant/2);
                        line1.setBorder(border);

                        ArrowImg2.setBounds(line1.getX()-5,line1.getY(),10,10);

                        line2.setBounds(shapes.get(j).getWidth()+shapes.get(j).getMax()/2,
                                line1.getY()+line1.getHeight(),
                                1,line1.getHeight());
                        line2.setBorder(border);
                        //shape(j) on the right
                        if(shapes.get(i).getWidth()+shapes.get(i).getMax()/2 < shapes.get(j).getWidth()+shapes.get(j).getMax()/2){
                            line3.setBounds(line1.getX(),line2.getY(),
                                    line2.getX()-line1.getX(),1);
                            line3.setBorder(border);
                        }
                        else{
                            //shape(j) on the left
                            line3.setBounds(line2.getX(),line2.getY(),
                                    line1.getX()-line2.getX(),1);
                            line3.setBorder(border);
                        }
                    }

                    //shapes.get(i) over
                    if(shapes.get(j).getUpperBound()+shapes.get(j).getLowerBound() <= shapes.get(i).getUpperBound()){
                        line1.setBounds(shapes.get(i).getWidth()+shapes.get(i).getMax()/2,
                                shapes.get(i).getUpperBound()-ShapeHdistant2/2,
                                1,ShapeHdistant2/2);
                        line1.setBorder(border);

                        ArrowImg.setBounds(line1.getX()-5,line1.getY()+line1.getHeight()-10,10,10);

                        line2.setBounds(shapes.get(j).getWidth()+shapes.get(j).getMax()/2,
                                shapes.get(j).getUpperBound()+shapes.get(j).getLowerBound(),
                                1,ShapeHdistant2/2);
                        line2.setBorder(border);
                        //shape(j) on the right
                        if(shapes.get(i).getWidth()+shapes.get(i).getMax()/2 < shapes.get(j).getWidth()+shapes.get(j).getMax()/2){
                            line3.setBounds(line1.getX(),line1.getY(),
                                    line2.getX()-line1.getX(),1);
                            line3.setBorder(border);
                        }
                        else{
                            //shape(j) on the left
                            line3.setBounds(line2.getX(),line1.getY(),
                                    line1.getX()-line2.getX(),1);
                            line3.setBorder(border);
                        }
                    }
                    int DistantS = -shapes.get(i).getWidth()-shapes.get(i).getMax()+shapes.get(j).getWidth()+shapes.get(j).getMax()/2;
                    int DistantS2 = shapes.get(i).getWidth()-shapes.get(j).getWidth()-shapes.get(j).getMax()/2;

                    //shape(j) is on same row as shape(i)
                    if(shapes.get(i).getUpperBound() <= shapes.get(j).getUpperBound()+shapes.get(j).getLowerBound() &&
                            shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound() >= shapes.get(j).getUpperBound()){
                        //shapes(j) on the right
                        if(shapes.get(i).getWidth()+shapes.get(i).getMax() < shapes.get(j).getWidth()+shapes.get(j).getMax()){
                            line1.setBounds(shapes.get(i).getWidth()+shapes.get(i).getMax(),
                                    shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2,
                                    DistantS,1);
                            line1.setBorder(border);

                            ArrowImg3.setBounds(line1.getX(),line1.getY()-5,10,10);

                            if(shapes.get(j).getUpperBound() > shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2
                                    || shapes.get(j).getUpperBound()+shapes.get(j).getLowerBound() < shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2) {
                                if (shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2 < shapes.get(j).getUpperBound()) {
                                    line2.setBounds(line1.getX() + line1.getWidth(), line1.getY(), 1, shapes.get(j).getUpperBound() - line1.getY());
                                    line2.setBorder(border);

                                } else {
                                    line2.setBounds(line1.getX() + line1.getWidth(),
                                            shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound(), 1,
                                            -shapes.get(j).getUpperBound() - shapes.get(j).getLowerBound() + line1.getY());
                                    line2.setBorder(border);
                                }
                            }
                        }
                        else{
                            //shapes(j) on the right
                            line1.setBounds(shapes.get(j).getWidth()+shapes.get(j).getMax()/2,
                                    shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2,
                                    DistantS2,1);
                            line1.setBorder(border);

                            ArrowImg4.setBounds(line1.getX()+line1.getWidth()-10,line1.getY()-5,10,10);

                            if(shapes.get(j).getUpperBound() > shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2
                                    || shapes.get(j).getUpperBound()+shapes.get(j).getLowerBound() < shapes.get(i).getUpperBound()+shapes.get(i).getLowerBound()/2) {
                                if (shapes.get(i).getUpperBound() + shapes.get(i).getLowerBound() / 2 < shapes.get(j).getUpperBound()) {
                                    line2.setBounds(line1.getX(), line1.getY(), 1, shapes.get(j).getUpperBound() - line1.getY());
                                    line2.setBorder(border);

                                } else {
                                    line2.setBounds(line1.getX(),
                                            shapes.get(j).getUpperBound() + shapes.get(j).getLowerBound(), 1,
                                            -shapes.get(j).getUpperBound() - shapes.get(j).getLowerBound() + line1.getY());
                                    line2.setBorder(border);
                                }
                            }
                        }


                    }

                    LbList.add(line1);
                    LbList.add(line2);
                    LbList.add(line3);
                    LbList.add(ArrowImg);
                    LbList.add(ArrowImg2);
                    LbList.add(ArrowImg3);
                    LbList.add(ArrowImg4);
                }
            }
        }



        for(int i = 0 ; i < LbList.size() ; i++){
            panel.add(LbList.get(i));
        }


    }

    /**
     * Add a new shape into the diagram
     * @param shape : the given shape
     */
    public void addShape(Shape shape){
        shapes.add(shape);
    }

    /**
     * Draw the diagram
     * @param panel : the drawing panel
     */
    public void DrawDiagram(JPanel panel){
        int LevelMax = 0;
        int sizeW = 0;
        int sizeH = 0;
        for(int i = 0 ; i < shapes.size() ; i++){
            if(shapes.get(i).structure.getLevel() > LevelMax){
                LevelMax = shapes.get(i).structure.getLevel();
            }
        }

        //System.out.println(LevelMax);

        List<Shape> shapesByLevel = new ArrayList<>(0);
        for(int i = LevelMax ; i >= 0 ; i--){
            for(int j = 0 ; j < shapes.size() ; j++) {
                if (shapes.get(j).structure.getLevel() == i) {
                    shapesByLevel.add(shapes.get(j));
                    //System.out.println(shapes.get(j).getCname().getText());
                }
            }
        }


        int maxH = 0;
        for(int j = 0 ; j <= LevelMax ; j++) {
            sizeH = sizeH+maxH +30;
            for (int i = 0; i < shapesByLevel.size(); i++) {
                if (shapesByLevel.get(i).structure.getLevel() == j) { ;
                    sizeW = 0;
                    if (i > 0) {
                        sizeW = shapesByLevel.get(i - 1).getWidth() + shapesByLevel.get(i - 1).getMax() + 30;
                    }
                    shapesByLevel.get(i).setUpperBound(sizeH);
                    shapesByLevel.get(i).setWidth(sizeW);

                    //System.out.println(shapesByLevel.get(i).getCname().getText()+shapesByLevel.get(i).getLowerBound()+" level " + j + " W " + sizeW + " H " + sizeH);
                    shapesByLevel.get(i).DrawContent(panel);;
                    if(shapesByLevel.get(i).getLowerBound() > maxH){
                        maxH = shapesByLevel.get(i).getLowerBound();
                        //System.out.println("maxh  " + maxH);
                    }
                }
            }
        }
    }

    /**
     * remove all element of the diagram
     */
    public void removeall(){
        shapes.removeAll(shapes);
    }
    //Default construtor
    public Diagram(){
        Gui.panel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                System.out.println(e.getX()+ " + " + e.getY()+" " + e.getWheelRotation());
                for(int i = 0 ; i < shapes.size(); i++){
                    if(e.getWheelRotation() == -1) {
                        shapes.get(i).setScale(shapes.get(i).getScale() + 0.1);

                    }
                    else{
                        shapes.get(i).setScale(shapes.get(i).getScale() - 0.1);

                    }
                    shapes.get(i).DrawContent(Gui.panel);
                }
                int a[] = new int[100];
                for(int i = 0 ; i < shapes.size(); i++){
                    a[i] = shapes.get(i).getMax();
                }
                for(int i = 0 ; i < shapes.size(); i++){
                    shapes.get(i).setMax((int)(shapes.get(i).getMax()*shapes.get(i).getScale()));
                }
                Gui.diagram.DrawLink(Gui.panel);
                Gui.frame.revalidate();
                Gui.frame.repaint();
                for(int i = 0 ; i < shapes.size(); i++){
                    shapes.get(i).setMax(a[i]);
                }
            }
        });
    }
}
