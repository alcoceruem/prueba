
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeerArchivos {

    private static final FileNameExtensionFilter FILTRO = new FileNameExtensionFilter("txt Texto", "txt");
    private static Map<String, Integer> mapa = new HashMap<>();

    public static void main(String args[]) {

        File archivo = null;
        archivo = new File(args[0]);//"archivo.txt" es el archivo que va a leer

        mapa = contarPalabras(archivo, mapa);

        //System.out.println("Palabras "+ mapa.keySet());
        //System.out.println("Mapa "+ mapa);
        System.out.println("Mapa ");
        printMap(mapa);
    }
    public static Map<String, Integer> contarPalabras(File archivo, Map<String, Integer> mapa) {
        if(archivo != null) {
            Scanner lectura;
            Pattern patron = Pattern.compile("[.;\\s,?¿!]");
            try {
                lectura = new Scanner(archivo);
                lectura.useDelimiter(patron);
                String palabra;
                while(lectura.hasNext()) {
                    palabra = lectura.next();
                    Integer frecuencia = mapa.get(palabra);
                    if(palabra.length() != 0) {
                        mapa.put(palabra.toLowerCase(), (frecuencia == null) ? 1 : frecuencia + 1);
                    }
                }
                lectura.close();
                return mapa;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return mapa;
    }
    private static void printMap(Map<String, Integer> mapa){

        Iterator palabras = mapa.entrySet().iterator();
        while   (palabras.hasNext()){
            Map.Entry thisEntry = (Map.Entry) palabras.next();
            System.out.println( thisEntry.getKey() + " = "+ thisEntry.getValue());
        }

    }

}
