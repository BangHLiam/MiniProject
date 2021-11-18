package PROGAME;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreFrame extends JFrame implements ActionListener {
    Frame Sausage = new Frame();
    private final ImageIcon start = new ImageIcon(this.getClass().getResource("Image/Resume.png"));
    JButton BStart = new JButton(start);
    public PreFrame(){
        add(BStart);
        BStart.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == BStart){
            this.remove(BStart);
            this.add(Sausage);
        }
    }
}
