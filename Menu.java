import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//note for later: create our own icon(?)
@SuppressWarnings("unchecked")
public class Menu extends JFrame implements ActionListener{
    private Container editor;
    private JTextField textbox;
    private JButton bold,italic;
    private JRadioButton lalign,center,ralign;
    private ButtonGroup alignment;
    private Font font;    
    private Font[] fontlist;
    private int size;
    private JComboBox fontselect;
    

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

	size = 16;
	GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	fontlist = e.getAllFonts();
	font = new Font(fontlist[0].getFamily(), Font.PLAIN, size);
	textbox.setFont(font);

	String[] listfont = new String[fontlist.length];
	for(int i = 0; i < fontlist.length; i++){
		listfont[i] = fontlist[i].getFamily();
	}
	fontselect = new JComboBox(listfont);
	fontselect.setSelectedIndex(4);
	fontselect.addActionListener(this);
	editor.add(fontselect);
    }

    public void actionPerformed(ActionEvent e){
	// ____ function = new ___(); name of class that turns text
	String event = e.getActionCommand();
	if(event.equals("turnB")){
		String temp = font.getFamily();
		if(font.isBold() && !font.isItalic()){
			font = new Font(temp, Font.PLAIN, size);
		}
		else if(!font.isBold() && font.isItalic()){
			font = new Font(temp, Font.ITALIC + Font.BOLD, size);
		}
		else if(font.isBold() && font.isItalic()){
			font = new Font(temp, Font.ITALIC, size);
		}
		else{
	    	font = new Font(temp, Font.BOLD, size);
	    }
	    textbox.setFont(font);
	}
	else if(event.equals("turnI")){
		String temp = font.getFamily();
		if(font.isItalic() && !font.isBold()){
			font = new Font(temp, Font.PLAIN, size);
		}
		else if(!font.isItalic() && font.isBold()){
			font = new Font(temp, Font.BOLD + Font.ITALIC, size);
		}
		else if(font.isItalic() && font.isBold()){
			font = new Font(temp, Font.BOLD, size);
		}
		else{
	    	font = new Font(temp, Font.ITALIC, size);
	    }
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
	}else if (event.equals("Center")){
	    //alignment.setSelected(getSelection(),false);
	    center.setSelected(true);
	    textbox.setHorizontalAlignment(JTextField.CENTER);
	}
	else{
		if(font.isPlain()){
			font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(), Font.PLAIN, size);
		}
		else if(font.isBold() && !font.isItalic()){
			font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(), Font.BOLD, size);
		}
		else if(font.isItalic() && !font.isBold()){
			font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(), Font.ITALIC, size);
		}
		else if(font.isBold() && font.isItalic()){
			font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(), Font.BOLD + Font.ITALIC, size);
		}
		textbox.setFont(font);
	}
 
    }

    public static void main(String[] args){
	Menu test = new Menu();
	test.setVisible(true);
    }
    
}
