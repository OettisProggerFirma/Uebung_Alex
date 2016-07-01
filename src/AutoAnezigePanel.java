import javax.swing.*;
import java.awt.*;

/**
 * Created by abrunner on 29.06.2016.
 */
public class AutoAnezigePanel extends JPanel {


    public AutoAnezigePanel(Auto auto) {



        this.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        JPanel farbe = new JPanel();
        JPanel jpanelmarke = new JPanel();

        JPanel jpanelkennzeichen = new JPanel();
        JPanel jpanelps = new JPanel();
        farbe.setPreferredSize(new Dimension(15, 15));
        farbe.setBackground(auto.getFarbe());



        JPanel jpanelfarbe = new JPanel();

        jpanelfarbe.setPreferredSize(new Dimension(50,50));

        JTextField jtextmarke = new JTextField(15);
        JTextField jtextkennzeichen = new JTextField(15);
        JTextField jtextps = new JTextField(5);

        jtextkennzeichen.setEditable(false);
        jtextmarke.setEditable(false);
        jtextps.setEditable(false);

        jtextkennzeichen.setText(auto.getKennzeichen());
        jtextmarke.setText(auto.getMarke());
        jtextps.setText(Integer.toString(auto.getPs()));


        jpanelmarke.add(jtextmarke);
        jpanelkennzeichen.add(jtextkennzeichen);
        jpanelps.add(jtextps);


        jpanelfarbe.setBorder(BorderFactory.createTitledBorder("Farbe"));

        jpanelmarke.setBorder(BorderFactory.createTitledBorder("Marke"));
        jpanelps.setBorder(BorderFactory.createTitledBorder("PS"));
        jpanelkennzeichen.setBorder(BorderFactory.createTitledBorder("Kennzeichen"));

        jpanelfarbe.add(farbe);

        panel.add(jpanelfarbe);
        panel.add(jpanelmarke);
        panel.add(jpanelkennzeichen);
        panel.add(jpanelps);

        this.add(panel);
        this.setVisible(true);

    }


}
