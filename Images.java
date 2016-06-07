import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Font;

public class Images {
    private BufferedImage green;
    public ArrayList<BufferedImage> list = new ArrayList<BufferedImage>(3);    
    public void addImage(BufferedImage img){
        System.out.println("Importado com sucesso");
        list.add(img);  
    }
    public ArrayList<BufferedImage> getList(){
        return list;
    }
}
