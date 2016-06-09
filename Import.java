/**
* Esta classe busca as imagens e fontes e faz o import de cada uma
* mandando posteriormente para a classe Imagens
*/


import java.io.IOException;
import java.awt.GraphicsEnvironment;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.FontFormatException;

public class Import implements Runnable{
    private String path;   
    private Images imgs;
    private int option;
    private BufferedImage image;
    public Import(String p, Images i, int opt){
        path = p;
        imgs = i;
        option = opt;
    }
    
    @Override
    public void run(){
        Thread thread = Thread.currentThread(); // Pega this thread
        System.out.println("Thread: "+thread.getName()+" iniciada");// mostra o nome da thread iniciada
        // dependendo da option é um tipo de arquivo
		// imagem da bola, imagem de UI ou fonte
		if(option == 0){
            try{
                image = ImageIO.read(new File(path));
                synchronized(imgs){imgs.addBolas(image);} // adiciona imagem de bola na lista array
            }catch(IOException e){
                e.printStackTrace();
            }   
        }else if(option == 1){
            try{
                image = ImageIO.read(new File(path));
                synchronized(imgs){imgs.addEnergy(image);} // adiciona imagem da energia
            }catch(IOException e){
                e.printStackTrace();
            }
        }else if(option == 2){
            try{
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));// importa e registra a fonte no sistema
            }catch(IOException | FontFormatException e){
                e.printStackTrace();
            }
        }else if(option == 3){
             try{
                image = ImageIO.read(new File(path));
                synchronized(imgs){imgs.addLinha(image);}//adiciona imagens das linhas
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    
    }
}
