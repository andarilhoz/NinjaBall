import java.util.ArrayList;
import java.util.List;
public class Game {
    public static void main (String args[])throws InterruptedException {
        System.out.println("Game Initialized");
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
            
        Screen tela = new Screen(800,400);
        tela.addJPanel(tela);
               
        ArrayList<Thread> th = new ArrayList<Thread>();
 
        for(int t=0;t<cores;t++){
            th.add(new Thread(new Core(tela,t),"T"+String.valueOf(t)));
            th.get(t).start();
        }

        while(true){
            tela.repaint();
            Thread.sleep(4);
        }
    }
}
