import javax.swing.*;
import java.io.File;

/**
 * The class for open project
 */
public class OpenFile {
	 JFileChooser fileChooser = new JFileChooser();
	 StringBuilder sb = new StringBuilder();
	 private String DirectoryPath;

	/**
	 * get the directory path
	 * @return the fixed directory path for reading
	 */
	public String getDirectoryPath() {
		System.out.println(DirectoryPath.replace("\\", "\\\\"));
		return DirectoryPath.replace("\\", "\\\\");
	}

	/**
	 * set the diractory path
	 * @param directoryPath : the directory path
	 */
	public void setDirectoryPath(String directoryPath) {
		DirectoryPath = directoryPath;
	}


	/**
	 * Pick the directory for reading
 	 * @throws Exception
	 */
	 public void PickDirectory() throws Exception{
		 fileChooser.setCurrentDirectory(new File("."));
		 fileChooser.setDialogTitle("Choose the Project");
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 fileChooser.setAcceptAllFileFilterUsed(false);
		 
		  if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		      System.out.println("getCurrentDirectory(): " + fileChooser.getCurrentDirectory());
		      System.out.println("getSelectedFile() : " + fileChooser.getSelectedFile());
		      setDirectoryPath(fileChooser.getSelectedFile().toString());
		    } else {
		      System.out.println("No Selection ");
		    }
	 }
}
