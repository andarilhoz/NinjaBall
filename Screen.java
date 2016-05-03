import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Arrays;

import java.awt.geom.Ellipse2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Screen extends JPanel{
    private int screenX;
    private int screenY;
    private JFrame frame;
    private ArrayList<Bola> bolas = new ArrayList<Bola>();    
    private ArrayList<Ellipse2D> bolasG = new ArrayList<>();

    public Screen(int screenSizeX,int screenSizeY){
        screenX = screenSizeX;
        screenY = screenSizeY;
        frame = new JFrame("GameBalls");
        frame.setSize(screenSizeX,screenSizeY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Screen iniciada");

        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){
                super.mouseClicked(me);
                for(Ellipse2D s: bolasG){
                    if(s.contains(me.getPoint())){
                        int clicked = bolasG.indexOf(s);
                        bolas.get(clicked).destroyBall();
                    }
                }
            }
        });
    }

    public void addToFrame(Bola b){
        bolas.add(b);
        bolasG.add(b.getGraphic());
    }
  
    public void addJPanel(JPanel jp){
        frame.add(jp);
    } 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for(int i=0;i<bolas.size();i++){
            g2d.setPaint(bolas.get(i).getColor());
            g2d.fill(bolas.get(i).getGraphic());
        }       
    }   
}
