/**
* Esta classe apenas armazena e disponibiliza os Assets, imagens e fontes
*/


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Font;

public  class Images {
    private BufferedImage green;
    private ArrayList<BufferedImage> bolas = new ArrayList<BufferedImage>(3);    
    private Font fonte;
    private BufferedImage linha;
    private BufferedImage energy;
    
    //Set das imagens das bolas e alocação em uma lista
    public void addBolas(BufferedImage img){
        System.out.println("Importado com sucesso");
        bolas.add(img);  
    }

    //Get da lista das imagens das bolas 
    public ArrayList<BufferedImage> getList(){
        return bolas;
    }

    //Set da imagem da linha na interface do jogo
    public void addLinha(BufferedImage lin){
        linha = lin;
    }
    
    //Set da fonte usada na interface do jogo
    public void addFont(Font fnt){
        fonte = fnt;
    }

    //Set da imagem da barra de energia do jogador
    public void addEnergy(BufferedImage eng){
        energy = eng;
    }

    //Get da imagem da da barra de energia do jogador
    public BufferedImage getEnergy(){
        return energy;
    }

    //Get da imagem da linha na interface do jogo
    public BufferedImage getLinha(){
        return linha;
    } 

    //Get da fonte usada na interface do jogo
    public Font getFont(){
        return fonte;
    }  


}
