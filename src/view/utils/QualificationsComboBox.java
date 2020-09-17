package view.utils;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class QualificationsComboBox extends JComboBox<String> {

    List<String> qualifications = Arrays.asList(
            "Osnovne akademske studije(180 ESPB)",
            "Osnovne strukovne studije (180 ESPB)",
            "Master akademske studije (180+120 ili 240+60 ESPB)",
            "Doktorske studije (180 ESPB)");

    public QualificationsComboBox() {
        for (String s: qualifications) {
            addItem(s);
        }
    }

}
