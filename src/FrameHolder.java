import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by abrunner on 29.06.2016.
 */
public class FrameHolder {
    private final JFrame frame;
    private final JPanel listenAnzeiger;
    private List<Auto> autos;

    public FrameHolder() {
        this.frame = new JFrame("Autos");



        //hier
        // alt .... surround try catch....
        try {
            autos = DateiHaendler1.leseAutoEins("autos.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException e1){
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
/*
// Hier wird eine ArrayListe per Hand befüllt
        autos = new ArrayList<>();
        autos.add(new Auto(Color.RED, "Audi", "WTM-UJD-848", 220));
        autos.add(new Auto(Color.RED, "Audi", "WTM-UJD-848", 220));
        autos.add(new Auto(Color.GREEN, "BMW", "ADSDSA-GG-1111", 170));
        autos.add(new Auto(Color.BLACK, "Ford", "SU-KK-923", 121));
        autos.add(new Auto(Color.ORANGE, "Mercedes", "JJ-F-10", 110));
        autos.add(new Auto(Color.WHITE, "Toyota", "M-IG-1234", 60));
        autos.add(new Auto(Color.GRAY, "Porsche", "P-OR-66", 310));
        autos.add(new Auto(Color.BLUE, "MINI", "T-B-75", 90));
        autos.add(new Auto(Color.YELLOW, "VW", "SU-PQ-8965", 140));
*/

        listenAnzeiger = new JPanel(new GridLayout(0, 1));

        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();

        centerPanel.add(listenAnzeiger);

        this.frame.setLayout(new BorderLayout());
        this.frame.add(northPanel, BorderLayout.NORTH);
        this.frame.add(centerPanel, BorderLayout.CENTER);

        JPanel kennzeichenPanel = new JPanel();
        kennzeichenPanel.setBorder(BorderFactory.createTitledBorder("Kennzeichen"));

        JTextField kennzeichen = new JTextField(15);
        kennzeichenPanel.add(kennzeichen);
        northPanel.add(kennzeichenPanel);

        JPanel markePanel = new JPanel();
        markePanel.setBorder(BorderFactory.createTitledBorder("Marke"));

        String[] items = getMarken();

        JComboBox<String> marke = new JComboBox<>(items);
        markePanel.add(marke);
        northPanel.add(markePanel);


        JPanel sliderPanel = new JPanel(new BorderLayout());
        JPanel sliderPanelNorth = new JPanel();
        JPanel sliderPanelSouth = new JPanel();

        List<Integer> psZahlen = getPs();

        sliderPanelNorth.add(new JLabel("Min"));
        // Collection sucht aus einer liste z.b. den minimalwert raus
        JSlider minSlider = new JSlider(Collections.min(psZahlen), Collections.max(psZahlen), Collections.min(psZahlen));
        sliderPanelNorth.add(minSlider);
        JTextField minTextField = new JTextField(5);
        minTextField.setText(Integer.toString((Collections.min(psZahlen))));

        minSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                minTextField.setText(Integer.toString(minSlider.getValue()));

                List<Auto> neueAutoLoste = new ArrayList<Auto>();
                for (Auto auto : autos) {
                    if (auto.getPs() >= minSlider.getValue()) {
                        neueAutoLoste.add(auto);
                    }
                }
                machDieAutoListe(neueAutoLoste);


            }
        });

        sliderPanelNorth.add(minTextField);
        sliderPanelSouth.add(new JLabel("Max"));
        // Collection sucht aus einer liste z.b. den maximalwert raus
        JSlider maxSlider = new JSlider(Collections.min(psZahlen), Collections.max(psZahlen), Collections.max(psZahlen));
        sliderPanelSouth.add(maxSlider);
        JTextField maxTextField = new JTextField(5);
        maxTextField.setText(Integer.toString((Collections.max(psZahlen))));

        maxSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                maxTextField.setText(Integer.toString(maxSlider.getValue()));

                List<Auto> neueAutoLoste = new ArrayList<Auto>();
                for (Auto auto : autos) {
                    if (auto.getPs() <= maxSlider.getValue()) {
                        neueAutoLoste.add(auto);
                    }
                }
                machDieAutoListe(neueAutoLoste);

            }
        });


        sliderPanelSouth.add(maxTextField);
        sliderPanel.add(sliderPanelNorth, BorderLayout.NORTH);
        sliderPanel.add(sliderPanelSouth, BorderLayout.SOUTH);
        northPanel.add(sliderPanel);


        kennzeichen.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                List<Auto> neueAutoListe = new ArrayList<Auto>();

                for (Auto auto : autos) {
                    if (auto.getKennzeichen().contains(kennzeichen.getText())) {
                        neueAutoListe.add(auto);
                    }
                }
                machDieAutoListe(neueAutoListe);
            }
        });


        marke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;

                List<Auto> neueAutoListe = new ArrayList<Auto>();

                for (Auto auto : autos) {
                    if (auto.getMarke().equals(marke.getSelectedItem())) {
                        neueAutoListe.add(auto);
                    }
                }
                machDieAutoListe(neueAutoListe);
            }
        });


        machDieAutoListe(autos);


        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private List<Integer> getPs() {
        List<Integer> psZahlen = new ArrayList<>();
        for (Auto auto : autos) {
            psZahlen.add(auto.getPs());
        }
        return psZahlen;
    }


    private String[] getMarken() {

        // Tree Set sortiert automatisch a-z
        // Set weil somit keine doppelten Automarken!

        Set<String> marke = new TreeSet<>();
        for (Auto auto : autos) {
            marke.add(auto.getMarke());
        }
        return marke.toArray(new String[0]);
    }

    private void machDieAutoListe(List<Auto> autos) {
        listenAnzeiger.removeAll();


        for (Auto auto : autos) {
            listenAnzeiger.add(new AutoAnezigePanel(auto));
        }

        frame.pack();
    }


}
