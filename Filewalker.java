import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Filewalker {
    private LinkedList<String> paths = new LinkedList<String>();
    public void walk( String path ) {
        paths = new LinkedList<String>();
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
                try{
                    paths.add(f.getCanonicalPath());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public LinkedList<String> getPaths(){
        return paths;
    }

}
