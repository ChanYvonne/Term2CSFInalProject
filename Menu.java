
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
    private int size;
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
	//editor = setEditable(false);

        setUpStyle();
	setUpAlignment();

	textbox = new JTextField(2);
	textbox.addActionListener(this);

	String[] sample ={"testing ", "please ", "work ", "before ", "I ", "pass ", "out"};
	String[] styles = {"regular","italic","bold","small","large","regular","regular"};
	
	textPane = new JTextPane();
	textbox.addActionListener(this);
	StyledDocument doc = textPane.getStyledDocument();
	addStylesToDocument(doc);

	try {
	    for (int i=0; i < sample.length; i++) {
		doc.insertString(doc.getLength(), sample[i],
				 doc.getStyle(styles[i]));
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
	textbox.setFont(font);

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

     protected void addStylesToDocument(StyledDocument doc) {
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

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

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
	String doc = textbox.getText();
	bank.add(doc.substring(doc.length()-1),"",0,0);

	String event = e.getActionCommand();
	size = Integer.parseInt((String)textsize.getSelectedItem());
	
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
	System.out.println(test.wordBank());
    }
    
}

/*


import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

public class Menu extends JPanel
                             implements ActionListener {
    private String newline = "\n";
    protected static final String textFieldString = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String ftfString = "JFormattedTextField";
    protected static final String buttonString = "JButton";

    protected JLabel actionLabel;

    public Menu() {
        setLayout(new BorderLayout());

       //Create a regular text field.
        JTextField textField = new JTextField(10);
        textField.setActionCommand(textFieldString);
        textField.addActionListener(this);

        //Create a password field.
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setActionCommand(passwordFieldString);
        passwordField.addActionListener(this);

        //Create a formatted text field.
        JFormattedTextField ftf = new JFormattedTextField(
                java.util.Calendar.getInstance().getTime());
        ftf.setActionCommand(textFieldString);
        ftf.addActionListener(this);

        //Create some labels for the fields.
        JLabel textFieldLabel = new JLabel(textFieldString + ": ");
        textFieldLabel.setLabelFor(textField);
        JLabel passwordFieldLabel = new JLabel(passwordFieldString + ": ");
        passwordFieldLabel.setLabelFor(passwordField);
        JLabel ftfLabel = new JLabel(ftfString + ": ");
        ftfLabel.setLabelFor(ftf);

        //Create a label to put messages during an action event.
        actionLabel = new JLabel("Type text in a field and press Enter.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {textFieldLabel, passwordFieldLabel, ftfLabel};
        JTextField[] textFields = {textField, passwordField, ftf};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Text Fields"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));
 
       
        //Create a text area.
        JTextArea textArea = new JTextArea(
                "This is an editable JTextArea. " +
                "A text area is a \"plain\" text component, " +
                "which means that although it can display text " +
                "in any font, all of the text is in the same font."
        );
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Plain Text"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));

        //Create a text pane.
        JTextPane textPane = createTextPane();
        JScrollPane paneScrollPane = new JScrollPane(textPane);
        paneScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScrollPane.setPreferredSize(new Dimension(250, 155));
        paneScrollPane.setMinimumSize(new Dimension(10, 10));


        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);
	leftPane.add(paneScrollPane, BorderLayout.CENTER);

        add(leftPane, BorderLayout.LINE_START);

    }

    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String prefix = "You typed \"";
        if (textFieldString.equals(e.getActionCommand())) {
            JTextField source = (JTextField)e.getSource();
            actionLabel.setText(prefix + source.getText() + "\"");
        } else if (passwordFieldString.equals(e.getActionCommand())) {
            JPasswordField source = (JPasswordField)e.getSource();
            actionLabel.setText(prefix + new String(source.getPassword())
                                + "\"");
        } else if (buttonString.equals(e.getActionCommand())) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    
    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = TextSamplerDemo.class.getResource(
                                        "TextSamplerDemoHelp.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }

        return editorPane;
    }
    
    
    private JTextPane createTextPane() {
        String[] initString =
                { "This is an editable JTextPane, ",            //regular
                  "another ",                                   //italic
                  "styled ",                                    //bold
                  "text ",                                      //small
                  "component, ",                                //large
                  "which supports embedded components..." + newline,//regular
                  " " + newline,                                //button
                  "...and embedded icons..." + newline,         //regular
                  " ",                                          //icon
                  newline + "JTextPane is a subclass of JEditorPane that " +
                    "uses a StyledEditorKit and StyledDocument, and provides " +
                    "cover methods for interacting with those objects."
                 };

        String[] initStyles =
                { "regular", "italic", "bold", "small", "large",
                  "regular", "button", "regular", "icon",
                  "regular"
                };

        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                                 doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        return textPane;
    }

    protected void addStylesToDocument(StyledDocument doc) {
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

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Menu());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
*/
