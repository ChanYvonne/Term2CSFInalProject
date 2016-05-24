import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

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
    private JComboBox fontselect, textsize;
    private Textbank bank;
    private JMenuBar menu;
    private JMenu filemenu;
    private JMenuItem save;
    private File savefile;
    

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
	
	textbox = new JTextField(5);
	//textbox.setActionCommand("wordsAdded");
	textbox.addActionListener(this);
	
	size = 16;

	setUpFont();
	setUpSize();

	menu = new JMenuBar();
	setJMenuBar(menu);
	filemenu = new JMenu("File");
	save = filemenu.add("Save");
	save.setActionCommand("Save");
	save.addActionListener(this);
	menu.add(filemenu);
		
	editor.add(bold);
	editor.add(italic);
	editor.add(lalign);
	editor.add(center);
	editor.add(ralign);
	editor.add(textsize);
	editor.add(fontselect);
	editor.add(textbox);	
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
    
    public void actionPerformed(ActionEvent e){
	String doc = textbox.getText();
	try{
		bank.add(doc.substring(doc.length()-1),"",0,0);
	}
	catch(IndexOutOfBoundsException i){
		System.out.println("There is no text.");
	}

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
	else if(event.equals("Save")){
		System.out.println("LOL I'M SAVING");
		System.out.println(System.getProperty("user.dir"));
		savefile = new File(System.getProperty("user.dir"), "saved.txt");
		try{
			savefile.createNewFile();
			FileWriter writer = new FileWriter(savefile);
			String text = textbox.getText();
			writer.write(text);
			writer.flush();
			writer.close();
		}
		catch(IOException i){}
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

    public static void main(String[] args){
	Menu test = new Menu();
	test.setVisible(true);
	System.out.println(test.wordBank());
    }
    
}
