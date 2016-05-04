import java.util.LinkedList;
import java.text.DecimalFormat;

public class Core implements Runnable{
    private int id;
    private LinkedList<Bola> bolas = new LinkedList<Bola>();
    private Screen tela;
    private long startTime;   
    private long lastBall; 

    public Core(Screen s,int id){
        this.tela = s;
        this.id = id;
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
            if((thisTime - lastBall)/1000 > 1 ){
                System.out.println("Criado bola em: "+ (thisTime - lastBall));
                Bola b = new Bola(3,800,400);
                bolas.add(b);
                tela.addToFrame(b);
                lastBall = thisTime;
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
