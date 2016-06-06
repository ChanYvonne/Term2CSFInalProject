import java.util.*;
import java.awt.*;
public class Textbank{
    //change font to be type Font;
    private class Node{
	private char text;
	private Font font;
	private int alignment;

	public Node(char character,Font font, int align){
	    text = character;
	    this.font = font;
	    alignment = align;
	}

	public char text(){
	    return text;
	}

	public Font font(){
	    return font;
	}

	public int size(){
	    return font.getSize();
	}

	public int style(){
	    return font.getStyle();
	}

	public int alignment(){
	    return alignment;
	}

	/*
	public int getLength(){
	    if (text != ""){
		return text.length();
	    }
	    return 0;
	}
	*/

	//set methods for later so we can change only certain parts of the text

	public void setText(char character){
	    text = character;
	}

	public void setFont(Font f){
	    font = new Font(f.getFamily(), f.getStyle(), font.getSize());
	}	
    }
    
    private Node[] text;
    private int length;

    public Textbank(){
	text = new Node[100];
	for (int x = 0; x < text.length;x++){
	    text[x] = new Node(' ',new Font("Arial",Font.PLAIN,20),0);
	}
	length = 0;
    }

    public void set(int index,char word,Font font,String align){
	//System.out.println(text.length);
	if (length == text.length){
	  grow();
	}
	int alignment = 0;
	if (align.equals("center")){
	    alignment = 1;
	}else if (align.equals("right")){
	    alignment = 2;
	}
	text[index] = new Node(word,font,alignment);
	length++;
    }

    public void undo(){
	if (length == 0){
	    throw new NoSuchElementException();
	}
	length--;
	text[length] = null;
    }

    public char getText(int index){
	return text[index].text();
    }

    
    public int getStyle(int index){
	return text[index].style();
    }

    public int getSize(int index){
	return text[index].size();
    }

    public String getAlignment(int index){
	//the reason I'm converting the int to string is because it would make it easier to getStyle() of the string
	int temp = text[index].alignment();
	if (temp == 0){
	    return "left";
	}else if(temp == 1){
	    return "center";
	}else{
	    return "right";
	}
    }
    
    public void grow(){ //for if the user is wordy
	Node[] temp = new Node[length*2+1];
	for (int x = 0; x < length; x++){
	    temp[x] = text[x];
	}
	text = temp;
    }

    public int getLength(){
	return length;
    }
    
    /*
    public int totalLength(){
	int ans = 0;
	if (length != 0 && text != null){
	    for (Node i: text){
		ans += i.text().length(); //causing null pointer exception
	    }
	}
	return ans;
    }
    */

    public String toString(){
	String ans = "";
	for (int x = 0; x < length; x++){
	    ans += text[x].text();
	    //System.out.println("does it work");
	}
	return ans;
    }

    // for some reason when I print test, it doesn't print out the text() of nodes I added
    public static void main(String[] args){
	Textbank test = new Textbank();
	Font test2 = new Font("Courier",Font.PLAIN,16);
	test.set(0,'h',test2,"center");
	test.set(1,'e',test2,"right");
	test.set(2,'l',test2, "center");
	test.set(3,'l',test2,"left");
	test.set(4,'o',test2,"right");
	System.out.println(test.getLength());
	System.out.println(test.toString());
    }
}
