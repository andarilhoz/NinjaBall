import java.util.ArrayList;
import java.util.List;
public class Game {
    private static ArrayList<Bola> bolas = new ArrayList<Bola>();
    private static Screen tela;
    private static int quantidadeBolas = 8;
    private static int screenX = 800;
    private static int screenY = 400;
    private static int cores = Runtime.getRuntime().availableProcessors();
    private static int bolaId;

    public static void main (String args[])throws InterruptedException {
        System.out.println("Game Initialized");
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
        
        int bolasPorCores = quantidadeBolas/cores;

            
        tela = new Screen(screenX,screenY);
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
