import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import java.awt.BorderLayout;


public class TextBax extends JPanel implements ActionListener{
    
    public static void main(String[] args){
    	JFrame frame = new JFrame("TextBax");
    	JPanel p = new JPanel(new BorderLayout());
    	JTextField textField = new JTextField(20);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	frame.add(textField);
    }

    public void actionPerformed(ActionEvent e){
    	System.out.println("An action was perfomed. Now do something about it.");
    }


}
