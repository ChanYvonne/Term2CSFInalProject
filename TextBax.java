import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextBax extends JPanel implements ActionListener{
    
    public static void main(String[] args){
    	JTextField textField = new JTextField(20);
    }

    public void actionPerformed(ActionEvent e){
    	System.out.println("An action was perfomed. Now do something about it.");
    }
}
