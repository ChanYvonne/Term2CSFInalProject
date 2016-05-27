
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

@SuppressWarnings("unchecked")
public class Menu extends JFrame implements ActionListener{
    private Container editor;
    private JTextField textbox;
    private JTextPane textPane;
    private JButton bold,italic;
    private JRadioButton lalign,center,ralign;
    private ButtonGroup alignment;
    private Font font;    
    private Font[] fontlist;
    private int size,current;
    private JComboBox fontselect, textsize;
    private Textbank bank;

    public Menu(){
	
	this.setTitle("Wizarding Word of Java");
	this.setSize(800,600);
	this.setLocation(400,200);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	bank = new Textbank();
	
	
	editor = this.getContentPane();
	editor.setLayout(new BoxLayout(editor, BoxLayout.PAGE_AXIS));

        setUpStyle();
	setUpAlignment();

	//String[] sample ={"testing ", "please ", "work ", "before ", "I ", "pass ", "out"};
	//String[] styles = {"regular","italic","bold","small","large","regular","regular"};
	current = doc.getLength();
	textPane = new JTextPane();
	//textPane.addActionListener(this);
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);

	try {
	    for (int i=0; i < bank.getLength(); i++) {
		doc.insertString(current,bank.get(i),bank.get(i).style());
	    }
	} catch (BadLocationException e) {
	    System.out.println("unable to insert text into text pane.");
	}
	
	size = 16;

	setUpFont();
	setUpSize();
		
	editor.add(bold);
	editor.add(italic);
	editor.add(lalign);
	editor.add(center);
	editor.add(ralign);
	editor.add(textsize);
	editor.add(fontselect);
	editor.add(textPane);	
    }

    

    public void setUpFont(){
	GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	fontlist = e.getAllFonts();
	font = new Font(fontlist[10].getFamily(), Font.PLAIN, size);
	textPane.setFont(font);

	String[] listfont = new String[fontlist.length];
	for(int i = 0; i < fontlist.length; i++){
		listfont[i] = fontlist[i].getFamily();
	}
	
	fontselect = new JComboBox(listfont);
	fontselect.setSelectedIndex(10);
	fontselect.setEditable(true);
	fontselect.setPreferredSize(new Dimension(225,25));
	fontselect.setMaximumSize(fontselect.getPreferredSize());
	fontselect.addActionListener(this);
    }

    public void setUpAlignment(){
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

    }

    public void setUpStyle(){
	bold = new JButton("bold");
	bold.addActionListener(this);
	bold.setActionCommand("turnB");
	//bold.setMnemonic(KeyEvent.VK_B);
	
        italic = new JButton("italic");
	italic.addActionListener(this);
	italic.setActionCommand("turnI");
	//italic.setMnemonic(KeyEvent.VK_I);
    }

    public void setUpSize(){
	String[] sizelist = new String[20];
	int tempsize = 12;
	for (int x = 0; x < sizelist.length; x++){
	    sizelist[x] = tempsize + "";
	    tempsize += 4;
	}

	textsize = new JComboBox(sizelist);
	textsize.setSelectedIndex(2);
	textsize.setEditable(true);
	textsize.setPreferredSize(new Dimension(50,25));
        textsize.setMaximumSize(textsize.getPreferredSize());
        textsize.addActionListener(this);

    }

    private void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

        s = doc.addStyle("button", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
       
        JButton button = new JButton();
	button.setText("BEEP");
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0,0,0,0));
        button.setActionCommand("Jbutton");
        button.addActionListener(this);
        StyleConstants.setComponent(s, button);
    }
    
    public void actionPerformed(ActionEvent e){
	
	String event = e.getActionCommand();
	size = 16;
	int style = 0;
	
	if(event.equals("turnB")){
		String temp = font.getFamily();
		if(font.isBold() && !font.isItalic()){
			font = new Font(temp, Font.PLAIN, size);
		}
		else if(!font.isBold() && font.isItalic()){
			font = new Font(temp, Font.ITALIC + Font.BOLD, size);
			style = 1;
		}
		else if(font.isBold() && font.isItalic()){
			font = new Font(temp, Font.ITALIC, size);
			style = 2;
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
	
	if (textPane.getText().getLength() > bank.totalLength()){
	
	    String words = textPane.getText().substring(current);
	    System.out.println(words);
	    

	    bank.add(words,font,size,style);
	    System.out.println(bank);
	}

    }

    public String wordBank(){
	return bank.toString();
    }

    

    /*
    private static void createAndShowGUI(){
	
    }
    */

    public static void main(String[] args){
	Menu test = new Menu();
	test.setVisible(true);
    }
    
}
