
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;

@SuppressWarnings("unchecked")
public class Menu extends JFrame implements ActionListener{
    private Container editor;
    private JTextPane textPane;
    private JButton bold,italic;
    private JRadioButton lalign,center,ralign;
    private ButtonGroup alignment;
    private Font font;    
    private Font[] fontlist;
    private int size,caretPosition;
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
	//setUpAlignment();

	//String[] sample ={"testing ", "please ", "work ", "before ", "I ", "pass ", "out"};
	//String[] styles = {"regular","italic","bold","small","large","regular","regular"};
	
	textPane = new JTextPane();
	//textPane.addActionListener(this);
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);
	caretPosition = doc.getLength();
	try {
	    for (int i=0; i < bank.getLength(); i++) {
		doc.insertString(caretPosition,bank.getText(i),doc.getStyle(convertStyles(i)));
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
	fontselect.setActionCommand("font");
	fontselect.addActionListener(this);
    }

    /*
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
    */

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

    // converts int format of font class to intialized styles documentation
    private String convertStyles(int index){
	if (index > 0 && index < bank.getLength()){
	    int tempStyle = bank.getStyle(index);
	    if (tempStyle == Font.BOLD){
		return "bold";
	    }else if (tempStyle == Font.ITALIC){
		return "italic";
	    }else{
		return "regular";
	    }
	}
	return "";
    }

    private void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles and fonts
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def,"Arial");
				     //fontlist[fontselect.getSelectedIndex()].getFamily());

	
        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);

        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);

	s = doc.addStyle("size", regular);
	StyleConstants.setFontSize(s, 30);
				   //textsize.getSelectedIndex());

	alignment = new ButtonGroup();
	
        s = doc.addStyle("center", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

	center = new JRadioButton("Center");
	center.setActionCommand("Center");
	center.addActionListener(this);
	StyleConstants.setComponent(s, center);

	s = doc.addStyle("left", regular);
	StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
	
	lalign = new JRadioButton("Left-aligned");
	lalign.setActionCommand("Left-aligned");
	lalign.addActionListener(this);
	lalign.setSelected(true);
	StyleConstants.setComponent(s, lalign);

	s = doc.addStyle("right", regular);
	StyleConstants.setAlignment(s, StyleConstants.ALIGN_RIGHT);
	
	ralign = new JRadioButton("Right-aligned");
	ralign.setActionCommand("Right-aligned");
	ralign.addActionListener(this);
	StyleConstants.setComponent(s, ralign);

	alignment.add(lalign);
	alignment.add(center);
	alignment.add(ralign);    
    }
    
    public void actionPerformed(ActionEvent e){
	
	String event = e.getActionCommand();
	size = 16;
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);
	int newStyle = 0;
	
	if(event.equals("turnB")){
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("bold"),false);
	    newStyle = 1;
	}else if(event.equals("turnI")){
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("italic"),false);
	    newStyle = 2;
    	}
	else if (event.equals("Left-aligned")){
	    lalign.setSelected(true);
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("left"),false);
	}else if (event.equals("Right-aligned")){
	    ralign.setSelected(true);
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("right"),false);
	}else if (event.equals("Center")){
	    center.setSelected(true);
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("center"),false);
	}else if (event.equals("font")){
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("regular"),false);
	     
	}else{
	    doc.setParagraphAttributes(0, doc.getLength(),doc.getStyle("size"),false);
	    size = textsize.getSelectedIndex();
	}
	if (newStyle == 0){
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.PLAIN , size);
	}else if (newStyle == 1){
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.BOLD , size);
	}else{
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.ITALIC , size);
	}
	
	if (textPane.getText().length() > bank.totalLength()){
	
	    String words = textPane.getText().substring(caretPosition);
	    System.out.println(words);
	    

	    bank.add(words,new Font(font.getFamily(),font.getStyle(),size));
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
