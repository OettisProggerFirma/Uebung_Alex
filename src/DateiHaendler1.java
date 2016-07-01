import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abrunner on 29.06.2016.
 */
public class DateiHaendler1 {
    //Exception 2x alt enter
    public static List<Auto> leseAutoEins(String s) throws IOException {
        List<Auto> autos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(s))){
            String zeile = "";
            while ((zeile = br.readLine()) != null){

                if (zeile.startsWith("Auto")){
                String[] autoDaten = zeile.split(":-:");
                    Color farbe = new Color(Integer.parseInt(autoDaten[1]));
                    String marke = autoDaten[2];
                    String kennzeichen = autoDaten[3];
                    Integer ps = Integer.parseInt(autoDaten[4]);

                    autos.add(new Auto(farbe, marke, kennzeichen, ps));
                }


            }
        }

        return autos;
    }
}
