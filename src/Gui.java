import models.structures.Structure;
import parser.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

//Main class

public class Gui{

	public static JFrame frame;
	public static final int WindowWidth = 1200;
	public static final int WindowHeight = 900;
	ProjectExplorer pj ;
	public static ArrayList<Shape> shapes = new ArrayList<>();
	public static int shapeCount = 0;
	public static JPanel panel = new JPanel();
	public static JPanel panel3 = new JPanel();
	public static Diagram diagram = new Diagram();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					//Dimension dimension = new Dimension(WindowWidth,WindowHeight);
					//frame.setMinimumSize(dimension);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, WindowWidth, WindowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//Create buttons
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JButton btnOpen = new JButton("Open Project");

		mnFile.add(btnOpen);

		JButton btnClose = new JButton("Close Project");

		mnFile.add(btnClose);

		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);

		JButton btnExportText = new JButton("Export Text");
		mnExport.add(btnExportText);

		JButton btnEportImage = new JButton("Export Image");
		mnExport.add(btnEportImage);

		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		JMenu Circular =  new JMenu("Circular");
		menuBar.add(Circular);

		JButton searchClass = new JButton("Search Class");
		mnSearch.add(searchClass);


		JButton searchMethod = new JButton("Search Method");
		mnSearch.add(searchMethod);

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		//Create listeners
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Draw();
				frame.pack();
				frame.setMinimumSize(new Dimension(1200,900));
			}
		});


		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapes.removeAll(shapes);
				diagram.removeall();
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
			}
		});

		btnEportImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				export ex = new export();
				ex.ToImage();
			}
		});

		btnExportText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				export ex = new export();
				ex.ToText();
			}
		});

		searchClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Search searchClass = new Search();
				searchClass.SearchClass(shapes);
			}
		});


		searchMethod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Search search = new Search();
				search.SearchMethod(shapes);
			}
		});

		Circular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					HashMap<Structure, Structure> pairs = pj.detectCircularDependencies();
					JFrame NewF = new JFrame();
					JPanel pn = new JPanel();
					NewF.add(pn);
					JTextArea Insert = new JTextArea(2,2);
					Insert.setEditable(false);
					JButton Enter = new JButton("Exit");
					pn.setLayout(new BoxLayout(pn,BoxLayout.PAGE_AXIS));
					pn.add(Insert);
					pn.add(Enter);
					NewF.setBounds(500, 500, 30, 40);
					NewF.pack();
					NewF.setVisible(true);
					NewF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					String Text = new String();
					for (Structure key : pairs.keySet()) {
						Text= Text+(key.getName() + ": " + pairs.get(key).getName())+"\n";
					}
					Insert.setText(Text);
					Enter.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							NewF.setVisible(false);
							NewF.dispose();
						}
					});
				}
				catch (Exception e3){
					e3.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the shapes with the given pj:Project explorer
	 */
	public void createShapes(){
		int count = 0;
		for (Structure structure : pj.getAllStructures()){
			shapes.add(new Shape(structure));
			shapes.get(count).getInfo();
			count++;
		}

	}

	/**
	 * Draw after open the file
	 * @param panel : the main panel
	 * @param diagram: the diagram
	 */
	public void DrawAfterOpen(JPanel panel,Diagram diagram){
		int counter = 0;
		for (int i = 0; i < shapes.size(); i++) {
			diagram.addShape(shapes.get(i));
		}

		for (int i = 0; i < shapes.size(); i++) {
			for (int j = 0; j < shapes.size(); j++) {
				if ((shapes.get(j).isInheritedFrom(shapes.get(i))&& (i != j)) ) {
					//diagram.DrawLink(panel, shapes.get(i), shapes.get(j));
					int lv = shapes.get(i).getLevel()+1;
					shapes.get(i).setLevel(lv);
				}
			}
		}
		diagram.DrawDiagram(panel);
		diagram.DrawLink(panel);
	}


	//Draw the diagram
	public void Draw(){
		try {
			OpenFile Open = new OpenFile();
			Open.PickDirectory();
			pj = new ProjectExplorer(Open.getDirectoryPath());
			createShapes();
			DrawAfterOpen(panel,diagram);
		}
		catch (Exception e3){
			e3.printStackTrace();
		}

	}
}
