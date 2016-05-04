import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import java.util.Random;

public class Bola{
    public double x;
    public double y;
    private  boolean direction;
    private Color color;
    private boolean alive;
    private int vidas;
    private Ellipse2D bolaG;
    private double speed = 0.05;

    public Bola (int lifes,int screenX, int screenY){
        Random r = new Random();
        x =  (r.nextDouble()*screenX/3)+ (r.nextDouble()*screenX/2) ;
        y =  (r.nextDouble()*screenY/3)+ (r.nextDouble()*screenY/2);
        direction = r.nextBoolean();
        setColor();
        alive = true;
        vidas = lifes;
        bolaG = new Ellipse2D.Double(this.x,this.y,30,30);
    }

    public void moveBall(){
        if(direction)
            x+=speed;
        else
            x-=speed;
        y+=speed;
        bolaG.setFrame(this.x,this.y,30,30);
    }
    
    
    public void setColor(){
        Random color = new Random();
        int R = color.nextInt(256);
        int G = color.nextInt(256);
        int B = color.nextInt(256);
        color = new Color(R,G,B);
    }
    
   
    public boolean checkLife(){
        if(vidas <=0)
            return false;
        return true;
    }
    
    public void destroyBall(){
        x = 0;
        y = 0;
        speed = 0;
    } 
  
    public Ellipse2D getGraphic(){
        return bolaG;
    } 
    public Color getColor(){
        return color;
    }
}
