package PROGAME;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.net.URL;


public class Player extends Rectangle {
    GamePanel panel;

    URL Sauces = this.getClass().getResource("Image/Sausage.png");
    Image Image = new ImageIcon(Sauces).getImage();

    int x;
    int y;
    int width;
    int height;

    double Xspeed;

    Rectangle HitBox ;

    boolean keyLeft;
    boolean keyRight;
    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50;
        height = 100;
        HitBox = new Rectangle(x,y,width,height);
    }

    public void set(){
        if(keyLeft && keyRight || !keyLeft && !keyRight ){
            Xspeed *=0.8;
        }
        else if(keyLeft && !keyRight){
            Xspeed--;
        }
        else if(keyRight && !keyLeft){
            Xspeed++;
        }

        if (Xspeed >7) Xspeed = 7;
        if(Xspeed<-7) Xspeed = -7;

        if(keyLeft){

            HitBox.x ++;
            for(Wall wall: panel.walls){
                if(wall.HitBox.intersects(HitBox)) Xspeed = -6;
            }
            HitBox.x--;
        }


        HitBox.x += Xspeed;
        for(Wall wall: panel.walls){
            if(HitBox.intersects(wall.HitBox)){
                HitBox.x -=Xspeed;
                while(!wall.HitBox.intersects(HitBox)) HitBox.x += Math.signum(Xspeed);
                HitBox.x -= Math.signum(Xspeed);
                Xspeed = 0;
                x = HitBox.x;
            }
        }

        x+=Xspeed;
        HitBox.x = x;
        HitBox.y = y;
    }
    public Rectangle2D HitBox(){
        return(new Rectangle2D.Double(x,y+200,75,75));
    }

    public void draw(Graphics2D gtd){
        gtd.drawImage(Image, x , y+150 , 200, 300,panel);
    }
}

