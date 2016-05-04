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
    private Ellipse2D bolaG;
    private double speedX;
    private double speedY;
    private int sX;
    private int sY;
    private Screen tela;
    private Core myCore;

    public Bola (int screenX, int screenY, Screen t, Core c){
        Random r = new Random();
        myCore = c;
        tela = t;
        sX = screenX;
        sY = screenY;
        x =  (r.nextDouble()*screenX/3)+ (r.nextDouble()*screenX/2) ;
        y =  (r.nextDouble()*screenY/3)+ (r.nextDouble()*screenY/2);
        direction = r.nextBoolean();
        setColor();
        alive = true;
        speedX = r.nextDouble()/2 ;
        speedY = r.nextDouble()/2;
        System.out.println("velocidade: " +speedX);
        bolaG = new Ellipse2D.Double(this.x,this.y,30,30);
    }

    public void moveBall(){
        if(direction)
            x+=speedX;
        else
            x-=speedX;
        y+=speedY;
        bolaG.setFrame(this.x,this.y,30,30);
    }
    
    
    public void setColor(){
        Random cr = new Random();
        int R = cr.nextInt(256);
        int G = cr.nextInt(256);
        int B = cr.nextInt(256);
        color = new Color(R,G,B);
    }
    
   
    public boolean checkLife(){
        if((x > sX || x < 0)||(y > sY || y< 0))
            return false;           
        return true;    
    }
    
    public void destroyBall(){
        x = 0;
        y = 0;
        speedX = 0;
        speedY = 0;
        myCore.removeBola(this);
        tela.removeBola(this);
    } 
  
    public Ellipse2D getGraphic(){
        return bolaG;
    } 
    public Color getColor(){
        return color;
    }
}
