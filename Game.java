/**
* Classe Main do jogo, aqui são iniciadas todas as Threads
* Primeiro as Threads de importação com o path de cada pasta dentro de Assets
* posteriormente a Thread principal Core que controla o jogo por si
*/

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;
import java.awt.image.BufferedImage;


public class Game {
    
    public static void main (String args[])throws InterruptedException {
        Filewalker fw = new Filewalker();
        fw.walk("./Assets/Balls");
        Images imgs = new Images();

        LinkedList<String> paths; 
        paths = fw.getPaths();
        
        LinkedList<Thread> thImport = new LinkedList<Thread>();

        for(String p : paths){
            System.out.println(p);
            thImport.add(new Thread(new Import(p,imgs,0)));
        }

        fw.walk("./Assets/Energy");
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,1)));
        
        fw.walk("./Assets/Fonts");
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,2)));

        fw.walk("./Assets/Line");
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,3)));

        

        for(Thread t: thImport)
            t.start();
        
        for(Thread t: thImport)
            t.join();
        
        

        System.out.println("Game Initialized");
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
        UI ui = new UI();
        Screen tela = new Screen(800,400,ui,imgs);
        tela.addJPanel(tela);
               
        ArrayList<Thread> th = new ArrayList<Thread>();
 
        for(int t=0;t<cores;t++){
            th.add(new Thread(new Core(ui,tela,t,imgs.getList()),"T"+String.valueOf(t)));
            th.get(t).start();
        }

     //  for(int t=0;t<cores;t++){
     //       th.add(new Thread(new Import(ui,tela,t),"T"+String.valueOf(t)));
     //       th.get(t).start();
     //   }

        while(true){
            tela.repaint();
            Thread.sleep(4);
        }
    }
}
