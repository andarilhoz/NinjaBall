import java.util.LinkedList;
import java.text.DecimalFormat;

public class Core implements Runnable{
    private int id;
    private LinkedList<Bola> bolas = new LinkedList<Bola>();
    private Screen tela;
    private double startTime;   
    private double lastBall; 
    private double time;

    public Core(Screen s,int i){
        tela = s;
        id = i;
        time = 0.5;
    }
    
    @Override
    public void run(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread: "+thread.getName()+" iniciada");
        startTime = System.currentTimeMillis();   
        lastBall = startTime;
        System.out.println("Tempo: " + (startTime - System.currentTimeMillis())/1000 + " Seconds");
            
        while(true){
            long thisTime = System.currentTimeMillis();
            if((thisTime - lastBall)/10000 > time+(id)  ){ 
                System.out.println("Criado bola em: "+ (thisTime - lastBall)/1000);
                System.out.println("Thread: "+thread.getName());
                Bola b = new Bola(3,800,400);
                bolas.add(b);
                tela.addToFrame(b);
                lastBall = thisTime;
                if(time > 0.10)
                    time -= 0.01;
            }


            for(Bola b: bolas){
                b.moveBall();
            }     
            
            try{
                Thread.sleep(4);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
