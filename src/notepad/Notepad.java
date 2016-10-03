package notepad;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;


public class Notepad extends JFrame implements ActionListener {
	private TextArea textArea = new TextArea("", 0,0, TextArea.SCROLLBARS_VERTICAL_ONLY);
	private MenuBar menuBar = new MenuBar(); // first, create a MenuBar item
	private Menu file = new Menu(); // our File menu
	//file menue inside
	private MenuItem openFile = new MenuItem();  // an open option
	private MenuItem saveFile = new MenuItem(); // a save option
	private MenuItem close = new MenuItem(); // and a close option!
	
	public Notepad(){
		this.setSize(500, 300); // set the initial size of the window
		this.setTitle("Java Notepad Tutorial"); // set the title of the window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // set the default close operation (exit when it gets closed)
		this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 12)); // set a default font for the TextArea
		this.getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
		this.getContentPane().add(textArea);
		
		// add our menu bar into the GUI
		this.setMenuBar(this.menuBar);
		this.menuBar.add(this.file); // we'll configure this later
		
		this.file.setLabel("File");
		
		this.openFile.setLabel("Open");
		this.openFile.addActionListener(this);
		this.openFile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
		
		this.file.add(this.openFile);
		
		
		this.saveFile.setLabel("Save");
		this.saveFile.addActionListener(this);
		this.saveFile.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
		this.file.add(this.saveFile);
		
		this.close.setLabel("Close");
		this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
		this.close.addActionListener(this);
		this.file.add(this.close);
		
	}
	
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() == this.close)
			this.dispose(); // dispose all resources and close the application
		
		else if (e.getSource() == this.openFile){
			 JFileChooser open = new JFileChooser(); // open up a file chooser (a dialog for the user to browse files to open)
			 int option = open.showOpenDialog(this); // get the option that the user selected (approve or cancel)
			 if (option == JFileChooser.APPROVE_OPTION) {
				 this.textArea.setText(""); // clear the TextArea before applying the file contents
				 try {
					 Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					 
				 }catch (Exception ex) {
					 System.out.println(ex.getMessage());
				 }
			 }
		}
		
		else if (e.getSource() == this.saveFile) {
			JFileChooser save = new JFileChooser(); // again, open a file chooser
			int option = save.showSaveDialog(this); // similar to the open file, only this time we call
			if (option == JFileChooser.APPROVE_OPTION) {
				try{
					BufferedWriter out = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					out.write(this.textArea.getText()); // write the contents of the TextArea to the file
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}

		}

	}
	
	public static void main(String args[]) {
		Notepad app = new Notepad();
		app.setVisible(true);
	}
}
