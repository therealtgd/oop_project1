package view.utils;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class QualificationsComboBox extends JComboBox<String> {

    List<String> qualifications = Arrays.asList(
            "Osnovne akademske studije(OAS, 180 ESPB)",
            "Osnovne strukovne studije (OSS, 180 ESPB)",
            "Osnovne akademske studije(OAS, 240)",
            "Master akademske studije (MAS,180+120 ili 240+60 ESPB)",
            "Doktorske studije (DS, 180 ESPB)");

    public QualificationsComboBox() {
        for (String s: qualifications) {
            addItem(s);
        }
    }

}
