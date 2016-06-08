import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Font;

public  class Images {
    private BufferedImage green;
    private ArrayList<BufferedImage> bolas = new ArrayList<BufferedImage>(3);    
    private Font fonte;
    private BufferedImage linha;
    private BufferedImage energy;
    
    public void addBolas(BufferedImage img){
        System.out.println("Importado com sucesso");
        bolas.add(img);  
    }

    public ArrayList<BufferedImage> getList(){
        return bolas;
    }

    public void addLinha(BufferedImage lin){
        linha = lin;
    }
    
    public void addFont(Font fnt){
        fonte = fnt;
    }

    public void addEnergy(BufferedImage eng){
        energy = eng;
    }

    public BufferedImage getEnergy(){
        return energy;
    }

    public BufferedImage getLinha(){
        return linha;
    } 
    
    public Font getFont(){
        return fonte;
    }  


}
