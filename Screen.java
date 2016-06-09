/**
* Esta classe controla a tela do jogo, faz de fato o 
* desenho das imagens das bolas, da GUI, e toda a parte grafica
* essa classe também é usada para checar os cliques na tela para 
* destruir as bolas 
*/


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Composite;
import java.awt.AlphaComposite;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import java.awt.geom.Ellipse2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Screen extends JPanel{
    private int screenX;
    private int screenY;
    private JFrame frame;
    private List<Bola> bolas = Collections.synchronizedList(new ArrayList());    
    private UI ui;
    private Images imgs;

    public Screen(int screenSizeX,int screenSizeY,UI u,Images img){
        screenX = screenSizeX;
        screenY = screenSizeY;
        ui = u;
        imgs = img;
        frame = new JFrame("GameBalls");
        frame.setSize(screenSizeX,screenSizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Screen iniciada");
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                super.mouseClicked(me);
                synchronized(bolas){
                    Iterator<Bola> bo = bolas.iterator();
                    while(bo.hasNext()){
                        Bola s = bo.next();
                        if(s.getGraphic().contains(me.getPoint())){
                            synchronized(s){
                                bo.remove();
                                s.removeFromCore();
                            }
                            ui.addPoints();
                        }
                    }
                }
            }
        });
    }
    
    public void removeBola(Bola b){
        Iterator<Bola> bo = bolas.iterator();
        while(bo.hasNext()){
            Bola s = bo.next();
            if(s == b)
                bo.remove();
        }
    }

    public void addToFrame(Bola b){
        bolas.add(b);
    }
  
    public void addJPanel(JPanel jp){
        frame.add(jp);
    }

    public void removeAllBolas(){
        synchronized(bolas){
            Iterator<Bola> bo = bolas.iterator();
            while(bo.hasNext()){
                Bola b = bo.next();
                bo.remove(); 
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight()); 
        synchronized(bolas){
            Iterator<Bola> bo = bolas.iterator();
            while(bo.hasNext()){
              Bola b = bo.next();
              //  g2d.setPaint(new Color(0,255,255));
              //  g2d.fill(b.getGraphic());
                g2d.drawImage(b.getColor(),(int)b.x+3,(int)b.y-3,null);
            }
       }
       g2d.setPaint(Color.WHITE);
       Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .75f);
       g2d.setComposite(c);
       g2d.setFont(new Font("Future Ex",Font.PLAIN, 25));
       g2d.drawString("Energy ",ui.x,ui.y+10);
       for(int en = 0; en < ui.getLifes();en++ ){
            g2d.drawImage(imgs.getEnergy(),ui.x+(en*22)+105,ui.y-5,null);
       }
       g2d.drawString(ui.getPoints()+"pts",ui.x+600,ui.y+10);
       Composite co = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
       g2d.setComposite(co);
       g2d.drawString("Do not touch this line",ui.x+150,ui.y+320);
       if(!ui.getAlive()){
           g2d.setFont(new Font(g2d.getFont().getFontName(),Font.PLAIN, 60));
           g2d.drawString("Voce perdeu!",ui.x+100,ui.y+180);
       }
       g2d.drawImage(imgs.getLinha(),0,350,null);
    }   
}
