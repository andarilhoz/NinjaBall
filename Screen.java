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

    //Set da tela do jogo
    public Screen(int screenSizeX,int screenSizeY,UI u,Images img){
        screenX = screenSizeX; //tamanho da tela 
        screenY = screenSizeY; 
        ui = u;
        imgs = img;
        frame = new JFrame("GameBalls"); // nome da janela
        frame.setSize(screenSizeX,screenSizeY); // define tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // seta função para fechar janela
        frame.setVisible(true); // deixa a janela visivel
        System.out.println("Screen iniciada"); 
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me){//Checa evento de mouse
                super.mouseClicked(me);
                synchronized(bolas){ // Percorre a lista de bolas, e remove caso tenha sido a bola clicada
                    Iterator<Bola> bo = bolas.iterator();
                    while(bo.hasNext()){
                        Bola s = bo.next();
                        if(s.getGraphic().contains(me.getPoint())){
                            synchronized(s){
                                bo.remove(); // remove a bola da lista
                                s.removeFromCore(); // remove a bola das threads core
                            }
                            ui.addPoints(); // adiciona pontos pra UI
                        }
                    }
                }
            }
        });
    }
    
    //Remove bola da tela
    public void removeBola(Bola b){
        Iterator<Bola> bo = bolas.iterator();
        while(bo.hasNext()){
            Bola s = bo.next();
            if(s == b)
                bo.remove();
        }
    }

    //Adiciona bola
    public void addToFrame(Bola b){
        bolas.add(b);
    }
  
    //Adiciona a tela
    public void addJPanel(JPanel jp){
        frame.add(jp);
    }

    //Remove todas as bolas quando o jogador perde o jogo
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
    public void paintComponent(Graphics g){//Pinta os componentes da interface
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // cria o Graphics 2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); // seta o Antialiasing 
        g2d.setColor(Color.BLACK); // seleciona o preto pra pintar
        g2d.fillRect(0,0,getWidth(),getHeight());  // pinta o fundo 
        synchronized(bolas){ // pinta cada uma das bolas
            Iterator<Bola> bo = bolas.iterator();
            while(bo.hasNext()){
              Bola b = bo.next();
              //  g2d.setPaint(new Color(0,255,255));
              //  g2d.fill(b.getGraphic());
                g2d.drawImage(b.getColor(),(int)b.x+3,(int)b.y-3,null);
            }
       }
       g2d.setPaint(Color.WHITE); // seta a cor de branco para escrita
       Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .75f); // seta transparencia a 75%
       g2d.setComposite(c); // adiciona a transparencia pro Graphics2D
       g2d.setFont(new Font("Future Ex",Font.PLAIN, 25));//Set da fonte a ser usada nos textos da interface
       g2d.drawString("Energy ",ui.x,ui.y+10);//Pinta o texto da barra de energia
       for(int en = 0; en < ui.getLifes();en++ ){
            g2d.drawImage(imgs.getEnergy(),ui.x+(en*22)+105,ui.y-5,null); // mostra as imagens de energia pra cada vida que o player tiver
       }
       g2d.drawString(ui.getPoints()+"pts",ui.x+600,ui.y+10);//Pinta o texto da pontuação
       Composite co = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f); // seta transparencia a 4%
       g2d.setComposite(co); // adiciona transparencia no Graphics2D
       g2d.drawString("Do not touch this line",ui.x+150,ui.y+320);//Pinta o texto da instrução do jogo
       if(!ui.getAlive()){ //se o Jogador perder mostra texto de Game Over
           g2d.setFont(new Font(g2d.getFont().getFontName(),Font.PLAIN, 60));
           g2d.drawString("Voce perdeu!",ui.x+100,ui.y+180);//Pinta a notificação de derrota quando ojogador perde
       }
       g2d.drawImage(imgs.getLinha(),0,350,null);//Pinta a linha na interface
    }   
}
