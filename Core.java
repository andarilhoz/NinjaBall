import java.util.ArrayList;


public class Core implements Runnable{
    private int id;
    private static ArrayList<Bola> bolas = new ArrayList<Bola>();
    private Screen tela;
    
    public Core(ArrayList<Bola> b,Screen s){
        this.bolas = b;
        this.tela = s;
    }
    @Override
    public void run(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread: "+thread.getName()+" iniciada");
        for(int a = 0;a<this.bolas.size();a++){
            tela.addToFrame(this.bolas.get(a));
            System.out.println("Bola id: "+String.valueOf(this.bolas.get(a).identifier)+" no core: "+thread.getName());
        }
            
        while(true){
            for(int a = 0;a<this.bolas.size();a++){
                this.bolas.get(a).moveBall();
            }
            try{
                Thread.sleep(4);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void printBolas(){


    }

}
