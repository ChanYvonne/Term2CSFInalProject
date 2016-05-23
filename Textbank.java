import java.util.*;

public class Textbank{

    private class Node{
	private String text,font;
	private int size,style;

	public Node(String character,String font, int size, int style){
	    text = character;
	    this.font = font;
	    this.size = size;
	    this.style = style;
	}

	public String text(){
	    return text;
	}

	public String font(){
	    return font;
	}

	public int size(){
	    return size;
	}

	public int style(){
	    return style;
	}

	//set methods for later so we can change only certain parts of the text

	public void setText(String character){
	    text = character;
	}

	public void setFont(String f){
	    font = f;
	}

	public void setSize(int s){
	    size = s;
	}

	public void setStyle(int st){
	    style = st;
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

    public void add(String word,String font,int size, int style){
	//System.out.println(text.length);
	if (length == text.length){
	  grow();
	}
	text[length] = new Node(word,font,size,style);
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
	test.add("hello","Times New Roman", 14, 0);
	test.add("my","Times New Roman", 14, 0);
	test.add("textbox","Times New Roman", 14, 0);
	test.add("please","Times New Roman", 14, 0);
	test.add("work","Times New Roman", 14, 0);
	System.out.println(test.getLength());
	System.out.println(test.toString());
    }
}
