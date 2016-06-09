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
    
    //Carrega os assets das respectivas pastas
    public static void main (String args[])throws InterruptedException {
        Filewalker fw = new Filewalker();
        fw.walk("./Assets/Balls");//Carrega os assests das bolas
        Images imgs = new Images();

        LinkedList<String> paths; 
        paths = fw.getPaths();
        
        LinkedList<Thread> thImport = new LinkedList<Thread>();//Cria lista das threads

        for(String p : paths){
            System.out.println(p);
            thImport.add(new Thread(new Import(p,imgs,0)));//Cria e adiciona thread na lista
        }

        fw.walk("./Assets/Energy");//Carrega o asset da barra de energia
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,1)));//Cria e adiciona thread na lista
        
        fw.walk("./Assets/Fonts");//Carrega o asset da fonte
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,2)));//Cria e adiciona thread na lista

        fw.walk("./Assets/Line");//Carrega o asset da linha
        paths = fw.getPaths();

        thImport.add(new Thread(new Import(paths.getFirst(),imgs,3)));//Cria e adiciona thread na lista

        
        //Inicia a thread
        for(Thread t: thImport)
            t.start();
        
        //Junta a thread após ser carregada
        for(Thread t: thImport)
            t.join();
        
        

        System.out.println("Game Initialized");
        int cores = Runtime.getRuntime().availableProcessors();//Checa a disponibilidade de cores da máquina
        System.out.println("Processadores disponiveis: "+ String.valueOf(cores));
        UI ui = new UI();
        Screen tela = new Screen(800,400,ui,imgs);
        tela.addJPanel(tela);//Abre tela do jogo
               
        ArrayList<Thread> th = new ArrayList<Thread>();
        
        //Cria e inicia as threads principais de acordo com a quantidade de cores
        for(int t=0;t<cores;t++){
            th.add(new Thread(new Core(ui,tela,t,imgs.getList()),"T"+String.valueOf(t)));
            th.get(t).start();
        }

     //  for(int t=0;t<cores;t++){
     //       th.add(new Thread(new Import(ui,tela,t),"T"+String.valueOf(t)));
     //       th.get(t).start();
     //   }

        //Repinta a tela enquanto o jogo estiver rodando
        while(true){
            tela.repaint();
            Thread.sleep(4);
        }
    }
}
