import java.util.*;

public class Textbox{

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
    private int size;

    public Textbox{
	text = new Node[100];
	size = 100;
	for (int x = 0; x < size;x++){
	    text[x] = new Node("","",0,0);
	}
    }

    public void grow(){
	Node[] temp = new Node[size*2+1];
	for (int x = 0; x < data.length; x++){
	    temp[x] = text[x];
	}
	text = temp;
    }
    
    public String toString(){
	String ans = "";
	for (int x = 0; x < size; x++){
	    ans += text[x].get(0);
	}
	return ans;
    }

    public static void main(String[] args){
	Textbox test = new Textbox();
    }
}
