import java.util.*;
import java.awt.*;
public class Textbank{
    //change font to be type Font;
    private class Node{
	private String text;
	private Font font;

	public Node(String character,Font font){
	    text = character;
	    this.font = font;
	}

	public String text(){
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

	//set methods for later so we can change only certain parts of the text

	public void setText(String character){
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
	/*for (int x = 0; x < text.length;x++){
	    text[x] = new Node("","",14,0);
	}*/
	length = 0;
    }

    public void add(String word,Font font){
	//System.out.println(text.length);
	if (length == text.length){
	  grow();
	}
	text[length] = new Node(word,font);
	length++;
    }

    public void undo(){
	if (length == 0){
	    throw new NoSuchElementException();
	}
	length--;
	text[length] = null;
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
    
    public int totalLength(){
	int ans = 0;
	if (length != 0){
	    for (Node i: text){
		ans += i.text().length();
	    }
	}
	return ans;
    }

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
	test.add("hello",test2);
	test.add("my",test2);
	test.add("textbox",test2);
	test.add("please",test2);
	test.add("work",test2);
	System.out.println(test.getLength());
	System.out.println(test.toString());
    }
}
