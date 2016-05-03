import java.util.ArrayList;
import java.util.List;
public class Game {
    private static ArrayList<Bola> bolas = new ArrayList<Bola>();
    private static Screen tela;
    private static int quantidadeBolas = 8;
    private static int screenX = 400;
    private static int screenY = 800;
    private static int cores = Runtime.getRuntime().availableProcessors();
    private static int bolaId;

    public static void main (String args[])throws InterruptedException {
        System.out.println("Game Initialized");
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
        
        int bolasPorCores = quantidadeBolas/cores;

        System.out.println("Bolas para cada core: "+ String.valueOf(bolasPorCores));    

            
        tela = new Screen(screenX,screenY);
        tela.addJPanel(tela);
        
               
        for(int a=0;a<quantidadeBolas;a++)
            criaAddBola();
        
        ArrayList<Thread> th = new ArrayList<Thread>();
/*

    0 - 2
    2 - 4
    4 - 6
    6 - 8 
*/        
        for(int t=0;t<cores;t++){
            ArrayList<Bola> bolaCore = new ArrayList<Bola>(bolas.subList(t*bolasPorCores,(t*bolasPorCores)+bolasPorCores));
            for(int b=0;b<bolaCore.size();b++)
                System.out.println("Bola: "+ bolaCore.get(b).identifier + " Adicionada ao core: "+t);
            th.add(new Thread(new Core(bolaCore,tela),"T"+String.valueOf(t)));
            //th.get(t).start();
        }

        while(true){
            tela.repaint();
            Thread.sleep(4);
           /* for(int t=0;t<th.size();t++){
                try{
                    synchronized(th.get(t)){
                        th.get(t).notify();
                        Thread.sleep(4);
                        th.get(t).wait();          
                    }
                }catch(Exception e ){
                    e.printStackTrace();
                }
            }*/
        }
    }

    private static void criaAddBola(){
        Bola b = new Bola(bolaId,3,screenX,screenY);
        bolas.add(b);
        bolaId++;
    }
}
