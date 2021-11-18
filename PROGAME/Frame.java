package PROGAME;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(){
        this.setSize(1000,800);
        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setVisible(true);
        this.add(panel);
        addKeyListener(new KeyChecker(panel));
    }
}