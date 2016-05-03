import java.util.ArrayList;

public class Game {
    private static ArrayList<Bola> bolas = new ArrayList<Bola>();
    private static Screen tela;
    private static int quantidadeBolas = 10;
    private static int screenX = 400;
    private static int screenY = 800;

    public static void main (String args[])throws InterruptedException {
        System.out.println("Game Initialized");
        tela = new Screen(screenX,screenY);
        tela.addJPanel(tela);
        for(int a=0;a<quantidadeBolas;a++ )
            criaAddBola(a);
        while(true){
            for(int a=0;a<bolas.size();a++)
                bolas.get(a).moveBall();
            tela.repaint();
            Thread.sleep(4);
        }
    }

    private static void criaAddBola(int id){
        Bola b = new Bola(id,3,screenX,screenY);
        tela.addToFrame(b);
        bolas.add(b);

    }
}
