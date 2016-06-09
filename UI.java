/**
* Esta classe controla a UI do jogo, como vidas e pontuação
*/

public class UI{
    private int lifes = 5;
    private int points;
    public int x = 50;
    public int y = 20;
    private int pointsPorBola = 5;    
    private boolean alive = true;

    public UI(){
        
    }

    public int getLifes(){
        return lifes;
    }
    public int getPoints(){
        return points;
    }
    public void addPoints(){
        points+= pointsPorBola;
    }
    public void loseLife(){
        lifes--;
    } 
    public void gameOver(){
        System.out.println("Você perdeu!");
        alive = false;
    }
    public boolean getAlive(){
        return alive;
    }
}
