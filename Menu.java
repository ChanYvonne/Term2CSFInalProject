import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//note for later: create our own icon(?)
public class Menu extends JFrame implements ActionListener{
    private Container editor;
    private JTextField textbox;
    private JButton bold,italic;
    private JRadioButton lalign,center,ralign;
    private ButtonGroup alignment;
    private Font font;    
    private Font[] fontlist;
    private int size;
    

    public Menu(){
	this.setTitle("Word Processor");
	this.setSize(800,600);
	this.setLocation(400,200);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	editor = this.getContentPane();
	editor.setLayout(new BoxLayout(editor, BoxLayout.PAGE_AXIS));
	//editor = setEditable(false);

        bold = new JButton("bold");
	bold.addActionListener(this);
	bold.setActionCommand("turnB");
	bold.setMnemonic(KeyEvent.VK_B);
	
        italic = new JButton("italic");
	italic.addActionListener(this);
	italic.setActionCommand("turnI");
	italic.setMnemonic(KeyEvent.VK_I);

	alignment = new ButtonGroup();
	
	lalign = new JRadioButton("Left-aligned");
	lalign.setActionCommand("Left-aligned");
	lalign.addActionListener(this);
	lalign.setSelected(true);
	
	center = new JRadioButton("Center");
	center.setActionCommand("Center");
	center.addActionListener(this);
	
	ralign = new JRadioButton("Right-aligned");
	ralign.setActionCommand("Right-aligned");
	ralign.addActionListener(this);

	alignment.add(lalign);
	alignment.add(center);
	alignment.add(ralign);

	textbox = new JTextField(5);

	editor.add(bold);
	editor.add(italic);
	editor.add(lalign);
	editor.add(center);
	editor.add(ralign);
	editor.add(textbox);

	size = 12;
	GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	fontlist = e.getAllFonts();
	font = new Font(fontlist[0].getFontName(), Font.PLAIN, size);
	textbox.setFont(font);
    }

    public void actionPerformed(ActionEvent e){
	// ____ function = new ___(); name of class that turns text
	String event = e.getActionCommand();
	if(event.equals("turnB")){
	    font = new Font(font.getFontName(), Font.BOLD, size);
	    textbox.setFont(font);
	}
	else if(event.equals("turnI")){
	    font = new Font(font.getFontName(), Font.ITALIC, size);
	    textbox.setFont(font);
    	}
	else if (event.equals("Left-aligned")){
	    //alignment.setSelected(getSelection(),false);
	    lalign.setSelected(true);
	    textbox.setHorizontalAlignment(JTextField.LEFT);
	}else if (event.equals("Right-aligned")){
	    //alignment.setSelected(getSelection(),false);
	    ralign.setSelected(true);
	    textbox.setHorizontalAlignment(JTextField.RIGHT);
	}else{
	    //alignment.setSelected(getSelection(),false);
	    center.setSelected(true);
	    textbox.setHorizontalAlignment(JTextField.CENTER);
	}
 
    }

    public static void main(String[] args){
	Menu test = new Menu();
	test.setVisible(true);
    }
    
    
}
