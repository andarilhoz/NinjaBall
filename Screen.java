import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.LinkedList;
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
    private List<Bola> bolas = Collections.synchronizedList(new LinkedList());    
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
                    for(Bola s: bolas){
                        if(s.getGraphic().contains(me.getPoint())){
                            s.destroyBall();
                            ui.addPoints();
                        }
                    }
                }
            }
        });
    }
    
    public void removeBola(Bola b){
        bolas.remove(b);
    }

    public void addToFrame(Bola b){
        bolas.add(b);
    }
  
    public void addJPanel(JPanel jp){
        frame.add(jp);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight()); 
        synchronized(bolas){
            for(Bola b: bolas){
                g2d.setPaint(b.getColor());
                g2d.fill(b.getGraphic());
            }
       }
       g2d.setPaint(Color.WHITE);
       g2d.drawString("Vidas: "+ ui.getLifes(),ui.x,ui.y);
       g2d.drawString("Pontos: "+ ui.getPoints(),ui.x+100,ui.y);
    }   
}
