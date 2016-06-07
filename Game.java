import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;
import java.awt.image.BufferedImage;


public class Game {
    public static void main (String args[])throws InterruptedException {
        Filewalker fw = new Filewalker();
        fw.walk("./Assets/Balls");
        Images bolasImgs = new Images();

        LinkedList<String> paths = fw.getPaths();
        
        LinkedList<Thread> thImport = new LinkedList<Thread>();
        
        for(String p : paths){
            System.out.println(p);
            thImport.add(new Thread(new Import(p,bolasImgs)));
        }
        
        for(Thread t: thImport)
            t.start();
        
        for(Thread t: thImport)
            t.join();

        

        System.out.println("Game Initialized");
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
        UI ui = new UI();
        Screen tela = new Screen(800,400,ui);
        tela.addJPanel(tela);
               
        ArrayList<Thread> th = new ArrayList<Thread>();
 
        for(int t=0;t<cores;t++){
            th.add(new Thread(new Core(ui,tela,t,bolasImgs.getList()),"T"+String.valueOf(t)));
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
