import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;


public class Import implements Runnable{
    private String path;   
    private Images imgs;
    private BufferedImage image;
    public Import(String p, Images i){
        path = p;
        imgs = i;
    }
    
    @Override
    public void run(){
        Thread thread = Thread.currentThread();
        System.out.println("Thread: "+thread.getName()+" iniciada");
        try{
            image = ImageIO.read(new File(path));
            synchronized(imgs){imgs.addImage(image);}
        }catch(IOException e){
            e.printStackTrace();
        }   
    }
}
