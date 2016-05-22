import java.util.*;

public class Textbox{
    
    private ArrayList<ArrayList<String>> text;
    private int size;

    public Textbox{
	text = new ArrayList<ArrayList<String>>();
    }

    public String toString(){
	String ans = "";
	for (int x = 0; x < size; x++){
	    ans += text.get(x).get(0);
	}
	return ans;
    }

    public static void main(String[] args){
	Textbox test = new Textbox();
    }
}
