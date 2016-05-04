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
        time = 1.0;
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
            System.out.println(String.valueOf((thisTime - lastBall)/10000));
            if((thisTime - lastBall)/10000 > time  ){ 
                System.out.println("Criado bola em: "+ (thisTime - lastBall)/1000);
                System.out.println("Thread: "+thread.getName());
                Bola b = new Bola(3,800,400);
                bolas.add(b);
                tela.addToFrame(b);
                lastBall = thisTime;
                time -= 0.05;
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
