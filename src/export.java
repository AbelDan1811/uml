import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class export {
    public void ToImage(){
        BufferedImage image = new BufferedImage(Gui.panel.getWidth(), Gui.panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Gui.panel.printAll(g);
        g.dispose();
        try {
            ImageIO.write(image, "png", new File("D://Bai1_tuan5//new.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ToText(){
        try {
            File file = new File("D://Bai1_Tuan5//Exported.txt");
            PrintWriter writer = new PrintWriter("D://Bai1_Tuan5//Exported.txt", "UTF-8");
            for(int i = 0 ; i < Gui.shapes.size() ; i++){
                writer.println(Gui.shapes.get(i).getCname().getText());
                writer.println(Gui.shapes.get(i).getAttribute().getText());
                writer.println(Gui.shapes.get(i).getMethod().getText());
                writer.println("--------------------------------");

            }
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
