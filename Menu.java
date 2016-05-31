
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
    private int size,caretPosition, prevPosition;
    private JComboBox fontselect, textsize;
    private Textbank bank;
    private boolean BoldOn, ItalicOn;

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
	textPane = new JTextPane();
	textPane.setEditable(true);
	setUpFont();
	setUpSize();
	//textPane.addActionListener(this);
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);
	caretPosition = doc.getLength();
	
	try {
	    for (int i=0; i < bank.getLength(); i++) {
		doc.insertString(caretPosition,bank.getText(i),doc.getStyle("regular"));
		//doc.insertString(caretPosition,bank.getText(i),doc.getStyle(convertStyles(i)));
	    }
	} catch (BadLocationException e) {
	    System.out.println("unable to insert text into text pane.");
	}
	
	

	
	
		
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
	BoldOn = false;
	ItalicOn = false;
	
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
	size = 20;
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

	String fontname = fontlist[fontselect.getSelectedIndex()].getFamily();
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def,fontname);
       
        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

	s = doc.addStyle("plain", regular);
	StyleConstants.setBold(s, false);
	StyleConstants.setItalic(s, false);

	int newsize = 12 + 4*textsize.getSelectedIndex();
	s = doc.addStyle("size", regular);
	StyleConstants.setFontSize(s, newsize);
	
        s = doc.addStyle("center", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

	StyleConstants.setComponent(s, center);

	s = doc.addStyle("left", regular);
	StyleConstants.setAlignment(s, StyleConstants.ALIGN_LEFT);
	
	
	StyleConstants.setComponent(s, lalign);

	s = doc.addStyle("right", regular);
	StyleConstants.setAlignment(s, StyleConstants.ALIGN_RIGHT);
	
	
	StyleConstants.setComponent(s, ralign);

	
    }
    
    public void actionPerformed(ActionEvent e){
	
	String event = e.getActionCommand();
	size = 16;
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);
	int newStyle = 0;

	String words = textPane.getText();
	//System.out.println(words);
	//doc.insertString(doc.getLength(),words, doc.getStyle("regular"));

	String selected = textPane.getSelectedText();
	int start = textPane.getSelectionStart();
	int end = textPane.getSelectionEnd();
	System.out.println(selected);
	System.out.println(start);
	System.out.println(end);
	if (selected == null){
	    selected = "";
	}

	
	if(event.equals("turnB")){
	    if (BoldOn){
		doc.setParagraphAttributes(start,end,doc.getStyle("plain"),false);
		BoldOn = false;
	    }else{
		doc.setParagraphAttributes(start,end,doc.getStyle("bold"),false);
		BoldOn = true;
		newStyle = 1;
	    }
	    
	    
	}else if(event.equals("turnI")){
	    if (ItalicOn){
		doc.setParagraphAttributes(start,end,doc.getStyle("plain"),false);
		ItalicOn = false;
	    }else{
		doc.setParagraphAttributes(start,end,doc.getStyle("italic"),false);
		ItalicOn = true;
		newStyle = 2;
	    }
	}else if (event.equals("Left-aligned")){
	    lalign.setSelected(true);
	    doc.setParagraphAttributes(start,end,doc.getStyle("left"),false);
	}else if (event.equals("Right-aligned")){
	    ralign.setSelected(true);
	    doc.setParagraphAttributes(start,end,doc.getStyle("right"),false);
	}else if (event.equals("Center")){
	    center.setSelected(true);
	    doc.setParagraphAttributes(start, end,doc.getStyle("center"),false);
	}else if (event.equals("font")){
	    doc.setParagraphAttributes(start,end,doc.getStyle("regular"),false);
	     
	}else{
	    doc.setParagraphAttributes(start, end,doc.getStyle("size"),false);
	    size = textsize.getSelectedIndex();
	}
        
	
	if (newStyle == 0){
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.PLAIN , size);
	}else if (newStyle == 1){
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.BOLD , size);
	}else{
	    font = new Font(fontlist[fontselect.getSelectedIndex()].getFamily(),Font.ITALIC , size);
	}
	
	/*
	try{
	if (textPane.getText().length() > bank.totalLength()){
	
	    String words = textPane.getText().substring(caretPosition);
	    //System.out.println(words);
	    

	    bank.add(words,new Font(font.getFamily(),font.getStyle(),size));
	    //System.out.println(bank);
	}
	}
	catch(NullPointerException x){
	    System.out.println("There is a Null Pointer Exception here. This issue must be addressed, but this is just a quick and dirty fix so the program doesn't absolutely halt.");
	}
	*/

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
	//System.out.println(test.wordBank());
	test.setVisible(true);
    }
    
}
