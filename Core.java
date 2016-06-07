import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Collections;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Core implements Runnable{
    private int id;
    private List<Bola> bolas = Collections.synchronizedList(new ArrayList());
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private Screen tela;
    private double startTime;   
    private double lastBall; 
    private double time;
    private UI ui;

    public Core(UI u,Screen s,int i, ArrayList<BufferedImage> im){
        tela = s;
        images = im;
        id = i;
        time = 0.2;
        ui = u;
    }
    
    @Override
    public void run(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread: "+thread.getName()+" iniciada");
        startTime = System.currentTimeMillis();   
        lastBall = startTime;
        System.out.println("Tempo: " + (startTime - System.currentTimeMillis())/1000 + " Seconds");
        Random r = new Random(); 
        while(true){
            if(!ui.getAlive())
                break;
            long thisTime = System.currentTimeMillis();
            if((thisTime - lastBall)/10000 > time+(id)  ){ 
                System.out.println("Criado bola em: "+ (thisTime - lastBall)/1000);
                System.out.println("Thread: "+thread.getName());
                Bola b = new Bola(800,400,tela,this,images.get(r.nextInt(4)));
                bolas.add(b);
                System.out.println("Bola:" + b.y);
                tela.addToFrame(b);
                lastBall = thisTime;
                if(time > 0.10)
                    time -= 0.01;
            }

                Iterator<Bola> bo = bolas.iterator();
                synchronized(bo){
                while(bo.hasNext()){
                    Bola b = bo.next();     
                    b.moveBall();
                    if(!b.checkLife()){
                        ui.loseLife();
                        if(ui.getLifes() <= 0)
                            ui.gameOver();
                        b.destroyBall();
                        bo.remove();
                    }
                }
            }     
            
            try{
                Thread.sleep(4);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        tela.removeAllBolas();        
    }
    public void removeBola(Bola b){
        Iterator<Bola> bo = bolas.iterator();
        while(bo.hasNext()){
            Bola s = bo.next();
            if(b == s)
                bo.remove(); 
        }
    }

}
