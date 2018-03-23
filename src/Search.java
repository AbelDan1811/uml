import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * the search class
 */
public class Search {
    Border border = BorderFactory.createLineBorder(Color.BLUE,2,true);

    /**
     * search the class
     * @param shapes : the shape list
     */
    public void SearchClass(List<Shape> shapes){
        JFrame NewF = new JFrame();
        JPanel pn = new JPanel();
        NewF.add(pn);
        JTextArea Insert = new JTextArea(2,2);
        JButton Enter = new JButton("enter here");
        pn.setLayout(new BoxLayout(pn,BoxLayout.PAGE_AXIS));
        pn.add(Insert);
        pn.add(Enter);
        NewF.setBounds(500, 500, 100, 100);
        NewF.pack();
        NewF.setVisible(true);
        NewF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0 ; i < shapes.size() ; i++){
                    if(shapes.get(i).getCname().getText().equals(Insert.getText())){
                        shapes.get(i).getCname().setBorder(border);
                    }
                }
                NewF.setVisible(false);
                NewF.dispose();
            }
        });

    }


    /**
     * search the method
     * @param shapes : the shape list
     */
    public void SearchMethod(List<Shape> shapes){
        JFrame NewF = new JFrame();
        JPanel pn = new JPanel();
        NewF.add(pn);
        JTextArea Insert = new JTextArea(2,2);
        JButton Enter = new JButton("enter here");
        pn.setLayout(new BoxLayout(pn,BoxLayout.PAGE_AXIS));
        pn.add(Insert);
        pn.add(Enter);
        NewF.setBounds(500, 500, 30, 30);
        NewF.pack();
        NewF.setVisible(true);
        NewF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3) {
                for(int i = 0 ; i < shapes.size() ; i++){
                    if(shapes.get(i).getMethod().getText().contains(Insert.getText())){
                        shapes.get(i).getMethod().setBorder(border);
                        Highlighter highlighter = shapes.get(i).getMethod().getHighlighter();
                        Highlighter.HighlightPainter painter =
                                new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
                        int p0 = shapes.get(i).getMethod().getText().indexOf(Insert.getText());
                        int p1 = p0 + Insert.getText().length();
                        try {
                            highlighter.addHighlight(p0, p1, painter );
                        }
                       catch (Exception e){
                            e.printStackTrace();
                       }
                    }
                }
                NewF.setVisible(false);
                NewF.dispose();
            }
        });
    }
}
