package components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class NewTask extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	public NewTask() throws ParserConfigurationException, SAXException, IOException
	{

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );


		JPanel leftPanel = new JPanel();
		leftPanel.setLayout( new BorderLayout() );
		JScrollPane leftscrollPane = new JScrollPane( leftPanel );
		leftscrollPane.setPreferredSize(getPreferredSize());
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout( new FlowLayout() );
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout( new GridLayout(1,5) );  
		
		// Create a splitter pane
		JSplitPane splitPaneV = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		topPanel.add( splitPaneV, BorderLayout.CENTER );

		
		JSplitPane splitPaneH = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		splitPaneH.setLeftComponent( leftscrollPane );
		splitPaneH.setRightComponent( rightPanel );
		splitPaneH.setOneTouchExpandable(true);
		splitPaneH.setContinuousLayout(true);
		splitPaneH.setPreferredSize(new Dimension(800, 200));
		splitPaneH.setDividerLocation(250);
		
		splitPaneV.setLeftComponent( splitPaneH );
		splitPaneV.setRightComponent( bottomPanel );
		
		// start build a tree
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
		XMLTreeModel model = new XMLTreeModel();
		model.setDocument(document);
		JTree tree = new JTree();
		tree.setModel(model);
		tree.setShowsRootHandles(true);
		tree.setEditable(false);

		
		tree.setShowsRootHandles(true);
		tree.setEditable(false);
		// end build tree
		// adding tree to panel
		leftPanel.add(tree);
		
        JButton OK = new JButton("OK");
        JButton Cancel = new JButton("Cancel");
        OK.addActionListener(this);
        Cancel.addActionListener(this);
        bottomPanel.add(OK);
        bottomPanel.add(Cancel);
}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    public static void createAndShowGUI() {
		NewTask childNewTaskFrame = null;
		try {
			childNewTaskFrame = new NewTask();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		childNewTaskFrame.pack();
		childNewTaskFrame.setVisible( true );
		
		childNewTaskFrame.addWindowListener(new WindowAdapter(){
		     public void windowClosing(WindowEvent we){
		        System.exit(0);
		     }});
    }
	public static void main( String args[] )
	{
		createAndShowGUI();
	}

}
