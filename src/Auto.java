import java.awt.*;

/**
 * Created by abrunner on 29.06.2016.
 */
public class Auto {

    private final Color farbe;
    private  final String marke;
    private  final String kennzeichen;
    private final Integer ps;


    public Auto(Color farbe, String marke, String kennzeichen, Integer ps) {
        this.farbe = farbe;
        this.marke = marke;
        this.kennzeichen = kennzeichen;
        this.ps = ps;
    }

    public Color getFarbe() {
        return farbe;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public String getMarke() {
        return marke;
    }

    public Integer getPs() {
        return ps;
    }
}
