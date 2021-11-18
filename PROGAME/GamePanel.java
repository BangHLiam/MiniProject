package PROGAME;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GamePanel extends JPanel implements ActionListener {
    Player player;
    Timer gameTimer;
    ArrayList<Wall> walls = new ArrayList<Wall>();
    ArrayList<Apple> ap1 = new ArrayList<Apple>();
    ArrayList<Bomb> bm2 = new ArrayList<Bomb>();
    ArrayList<RedBomb> bmr = new ArrayList<RedBomb>();
    URL bg = this.getClass().getResource("Image/bgt.jpg");
    URL bg2 = this.getClass().getResource("Image/bg2.jpg");
    URL win = this.getClass().getResource("Image/win.png");
    URL lose = this.getClass().getResource("Image/LOST.png");
    Image winn = new ImageIcon(win).getImage();
    Image background = new ImageIcon(bg).getImage();
    Image background2 = new ImageIcon(bg2).getImage();
    Image loser = new ImageIcon(lose).getImage();
    int x=400;
    int y=300;
    public int times;
    public int HP = 1;

    boolean timestart = true;
    boolean startball = false;

    boolean paralyze1 = false;
    int time_paralyze = 5;


    Thread actor = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {}
                repaint();
            }
        }
    });

    Thread tapple1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 100) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    ap1.add(new Apple());
                }
            }
        }
    });

    Thread tbombred = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 1000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    bmr.add(new PROGAME.RedBomb());
                }
            }
        }
    });

    Thread tbomb2 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    if (startball == false) {
                        Thread.sleep((long) (Math.random() * 1000) + 2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (startball == false) {
                    bm2.add(new PROGAME.Bomb());
                }
            }
        }
    });

    Thread paralyze = new Thread(new Runnable(){
        public void run(){
            while (true){
                if(time_paralyze < 1){
                    paralyze1 = false;
                    time_paralyze = 5;
                }
                try{
                    Thread.sleep(5000);
                }catch(InterruptedException e){e.printStackTrace();}
            }
        }
    });

    Thread countdown = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                if (timestart == false) {
                    times = (times - 1);
                    if (paralyze1) {
                        time_paralyze--;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    public GamePanel() {

        player = new Player(x, y, this);
        makeWalls();
        gameTimer = new Timer();

        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.set();
                repaint();

            }
        },0,10);
        actor.start();
        countdown.start();
        tapple1.start();
        tbomb2.start();
        tbombred.start();
        paralyze.start();
    }

    public void makeWalls(){
        for(int i = 50; i<650; i+=50){
            walls.add(new Wall(933,i,50,50));
        }
        for(int i = 50; i<650; i+=50){
            walls.add(new Wall(-150,i,50,50));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if(HP<50 && HP>=0){
            Graphics2D gtd = (Graphics2D) g;
            g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
            player.draw(gtd);
            for(Wall wall: walls) wall.draw(gtd);
            g.setFont(new Font("Digital-7 Mono", Font.PLAIN, 90));
            g.setColor(Color.RED);



            for (int i = 0; i < ap1.size(); i++) {
                g.drawImage(ap1.get(i).getImage(), ap1.get(i).getX(), ap1.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < ap1.size(); i++) {
                if (Intersect(player.HitBox(), ap1.get(i).HitBox())) {
                    ap1.remove(i);
                    HP += 10;

                }
            }
            for (int i = 0; i < bm2.size(); i++) {
                g.drawImage(bm2.get(i).getImage(), bm2.get(i).getX(), bm2.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < bm2.size(); i++) {
                if (Intersect(player.HitBox(), bm2.get(i).HitBox())) {
                    bm2.remove(i);
                    HP -= 10;
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(15,15,100,25);
            g.setColor(Color.GREEN);
            g.fillRect(15,15,HP,25);
            g.setColor(Color.WHITE);
            g.drawRect(15,15,100,25);



        }else if (HP>50 && HP<=100){
            Graphics2D gtd = (Graphics2D) g;
            g.drawImage(background2, 0, 0, this.getWidth(), this.getHeight(), this);
            player.draw(gtd);
            for(Wall wall: walls) wall.draw(gtd);
            g.setFont(new Font("Digital-7 Mono", Font.PLAIN, 90));
            g.setColor(Color.RED);


            for (int i = 0; i < ap1.size(); i++) {
                g.drawImage(ap1.get(i).getImage(), ap1.get(i).getX(), ap1.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < ap1.size(); i++) {
                if (Intersect(player.HitBox(), ap1.get(i).HitBox())) {
                    ap1.remove(i);
                    HP += 10;
                }
            }
            for (int i = 0; i < bm2.size(); i++) {
                g.drawImage(bm2.get(i).getImage(), bm2.get(i).getX(), bm2.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < bm2.size(); i++) {
                if (Intersect(player.HitBox(), bm2.get(i).HitBox())) {
                    bm2.remove(i);
                    HP -= 10;
                }
            }

            for (int i = 0; i < bmr.size(); i++) {
                g.drawImage(bmr.get(i).getImage(), bmr.get(i).getX(), bmr.get(i).getY(), 100, 100, this);
            }
            for (int i = 0; i < bmr.size(); i++) {
                if (Intersect(player.HitBox(), bmr.get(i).HitBox())) {
                    bmr.remove(i);
                    HP -= 20;
                }
            }
            g.setColor(Color.GRAY);
            g.fillRect(15,15,100,25);
            g.setColor(Color.GREEN);
            g.fillRect(15,15,HP,25);
            g.setColor(Color.WHITE);
            g.drawRect(15,15,100,25);
        }else if(HP>100){
            g.drawImage(winn, 0, 0, this.getWidth(), this.getHeight(), this);
            g.setFont(new Font("Angsana New",Font.PLAIN,90));
            g.setColor(Color.RED);



        }else{
            g.drawImage(loser,0,0,this.getWidth(),this.getHeight(),this);
        }


    }

    private boolean Intersect(Rectangle2D hitBox, Rectangle2D hitBox1) {
        return (hitBox.intersects(hitBox1));
    }

    void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 'A') player.keyLeft = true;
        if(e.getKeyCode() == 'D') player.keyRight = true;
    }

    void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 'A') player.keyLeft = false;
        if(e.getKeyCode() == 'D') player.keyRight = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
