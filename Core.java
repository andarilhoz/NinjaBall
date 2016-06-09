/**
* Essa classe é a Thread principal, onde irá gerar as bolas,
* checar se elas ainda estão vivas e atualizar elas na tela
* é usado uma synchronizedList junto com o metodo synchronized 
* para controlar o acesso a lista que contem todas as bolas ativas
*/



import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Collections;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Core implements Runnable{
    private int id; //id da thread
    private List<Bola> bolas = Collections.synchronizedList(new ArrayList());// lista que contem todas bolas as bolas ativas
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();// lista com imagem de todas bolas
    private Screen tela; // Tela do jogo
    private double startTime;   //tempo inicial para spawn da bola
    private double lastBall; // tempo do ultimo lançamento de bola
    private double time; // tempo geral
    private UI ui; // User Interface

    public Core(UI u,Screen s,int i, ArrayList<BufferedImage> im){
        tela = s;
        images = im;
        id = i;
        time = 0.2;
        ui = u;
    }
    
    @Override
    public void run(){
        Thread thread = Thread.currentThread(); //pega a própria Thread
        System.out.println("Thread: "+thread.getName()+" iniciada"); // imprime no console o nome da tela
        startTime = System.currentTimeMillis();   // define starttime como o tempo atual
        lastBall = startTime; // ultima bola criada foi no tempo atual
        System.out.println("Tempo: " + (startTime - System.currentTimeMillis())/1000 + " Seconds");// mostra o tempo em que foi criado a bola
        Random r = new Random();  // gerador de aleatoridade
        while(true){
            if(!ui.getAlive()) // se jogador estiver morto para o loop
                break;
            long thisTime = System.currentTimeMillis(); // define tempo atual
            if((thisTime - lastBall)/10000 > time+(id)  ){  // se o tempo atual é maior do que o tempo de espera, gera bola
                System.out.println("Criado bola em: "+ (thisTime - lastBall)/1000); 
                System.out.println("Thread: "+thread.getName());
                Bola b = new Bola(800,400,tela,this,images.get(r.nextInt(4))); // Cria a bola de fato
                bolas.add(b); // adiciona bola na ArrayList
                System.out.println("Bola:" + b.y); 
                tela.addToFrame(b); // adiciona a bola na tela
                lastBall = thisTime; // define o tempo da ultima bola como agora
                if(time > 0.10) // se tempo for menor do que 0.10 diminui 0.01
                    time -= 0.01;
            }

                synchronized(bolas){ // Utiliza o metodo synchronized para controlar o acesso das threads a lista
                Iterator<Bola> bo = bolas.iterator(); // cria Iterator para o loop na lista
                while(bo.hasNext()){ // loop para checar se a bola ainda tá viva
                    Bola b = bo.next();     
                    b.moveBall(); // move a bola
                    if(!b.checkLife()){ //caso a bola tenha saido da tela, jogador perde vida
                        ui.loseLife();
                        if(ui.getLifes() <= 0)//se vida for igual ou menor a zero perde o jogo
                            ui.gameOver(); 
                        b.destroyBall();//destroi a bola
                        bo.remove();//remove da lista
                    }
                }
            }     
            
            try{
                Thread.sleep(4);//espera um tempo de iteração
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        tela.removeAllBolas();        // caso de game over remove todas as bolas
    }
    public void removeBola(Bola b){ // função para remover bola da Tela
        Iterator<Bola> bo = bolas.iterator();
        while(bo.hasNext()){
            Bola s = bo.next();
            if(b == s)
                bo.remove(); 
        }
    }

}
