/**
* Filewalker percorre todos arquivos de um path 
* e retorna uma linkedlist com o path definitivo
*/

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


//Instrução para percorrer nas pastas de assets
public class Filewalker {
    private LinkedList<String> paths = new LinkedList<String>();
    public void walk( String path ) {
        paths = new LinkedList<String>();
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        //Percorre a pasta
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );//Entra em mais uma pasta, caso haja
            }
            else {
                try{
                    paths.add(f.getCanonicalPath());//Adiciona assests na lista
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //Instrução para pegar os assets
    public LinkedList<String> getPaths(){
        return paths;
    }

}
