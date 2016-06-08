/**
* Esta classe é a que define as bolas do jogo, recebe a imagem, 
* posição, a thread que está e a tela do jogo
* Aqui também tem a função para checar se a bola saiu ou não da tela 
* para posteriormente printar ou não ela
*/


import java.awt.Color;
import java.awt.Graphics; import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import java.util.Random;

public class Bola{
    public double x;
    public double y;
    private  boolean direction;
    private BufferedImage color;
    private boolean alive;
    private Ellipse2D bolaG;
    private double speedX;
    private double speedY;
    private int sX;
    private int sY;
    private Screen tela;
    private Core myCore;

    public Bola (int screenX, int screenY, Screen t, Core c, BufferedImage cor){
        Random r = new Random();
        color = cor;
        myCore = c;
        tela = t;
        sX = screenX;
        sY = screenY-80 ;
        x =  (r.nextDouble()*screenX/3)+ (r.nextDouble()*screenX/2) ;
        y = (-10); //(r.nextDouble()*screenY/3)+ (r.nextDouble()*screenY/2);
        alive = true;
        speedX = 0; //r.nextDouble()/2 ;
        speedY = r.nextDouble()/2;
        bolaG = new Ellipse2D.Double(x,y,50,50);
    }

    public void moveBall(){
        synchronized(this){
            y+=speedY;
            bolaG.setFrame(this.x,this.y,50,50);
        }
    }
   
    public boolean checkLife(){
        if((x > sX || x < 0)||(y > sY || y< -11))
            return false;           
        return true;    
    }
    
    public void destroyBall(){
        tela.removeBola(this);
    }
    
    public void removeFromCore(){
        myCore.removeBola(this);
    }
    
    public Ellipse2D getGraphic(){
        return bolaG;
    } 
    public BufferedImage getColor(){
        return color;
    }
}
