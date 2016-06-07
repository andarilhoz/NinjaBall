import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

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

    public Screen(int screenSizeX,int screenSizeY,UI u){
        screenX = screenSizeX;
        screenY = screenSizeY;
        ui = u;
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
        Iterator<Bola> bo = bolas.iterator();
        while(bo.hasNext()){
            Bola b = bo.next();
            bo.remove(); 
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
       g2d.drawString("Vidas: "+ ui.getLifes(),ui.x,ui.y);
       g2d.drawString("Pontos: "+ ui.getPoints(),ui.x+100,ui.y);
       if(!ui.getAlive()){
           g2d.setFont(new Font(g2d.getFont().getFontName(),Font.PLAIN, 30));
           g2d.drawString("Você perdeu!",ui.x+250,ui.y+180);
       }
    }   
}
