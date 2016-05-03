import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import java.util.Random;

public class Bola{
    public double x;
    public double y;
    private int range; 
    public int identifier;
    public  boolean direction;
    private Color color;
    private boolean alive;
    public int vidas;
    private Ellipse2D bolaG;
    private double speed = 0.05;

    public Bola (int id, int lifes,int screenX, int screenY){
        this.identifier = id;
        Random r = new Random();
        this.x =  r.nextDouble()*screenX;
        this.y =  r.nextDouble()*screenY;
        this.direction = r.nextBoolean();
        this.setColor();
        this.alive = true;
        this.vidas = lifes;
        this.bolaG = new Ellipse2D.Double(this.x,this.y,30,30);
    }

    public void moveBall(){
        if(this.direction)
            this.x+=speed;
        else
            this.x-=speed;
        this.y+=speed;
        this.bolaG.setFrame(this.x,this.y,30,30);
    }
    
    public void setColor(){
        Random color = new Random(identifier);
        int R = color.nextInt(256);
        int G = color.nextInt(256);
        int B = color.nextInt(256);
        this.color = new Color(R,G,B);
    }
    
   
    public boolean checkLife(){
        if(this.vidas <=0)
            return false;
        return true;
    }
    
    public void destroyBall(){
        this.x = 0+(this.identifier*35);
        this.y = 0;
        this.speed = 0;
    } 
  
    public Ellipse2D getGraphic(){
        return this.bolaG;
    } 
    public Color getColor(){
        return this.color;
    }
}
