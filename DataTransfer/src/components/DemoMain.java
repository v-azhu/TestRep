package components;


import java.io.File;

import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DemoMain extends JFrame {

	public static void main(String[] args) {
		new DemoMain();
	}
	
	
	public DemoMain() {
		Document document = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			document = builder.parse(new File("./src/tasktree.xml"));
			document.normalize();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		XMLTreePanel panel = new XMLTreePanel();
		panel.setDocument(document);
		getContentPane().add(panel, "Center");
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("XML Tree Demo");
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
