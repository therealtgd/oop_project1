package view.patient;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.entities.AnalysisRequest;
import modules.users.Patient;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;
import view.validators.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientFrame extends JFrame {

    private Patient patient;
    private List<UserDatabase> userDatabase;
    private DatabaseHandler dH;
    private JPanel noReq;

    public PatientFrame(Patient p, DatabaseHandler dH) {
        this.patient = p;
        this.dH = dH;
        this.userDatabase = dH.getUserDatabase().getUsers();
        patientFrame();
    }

    public Patient getPatient() {
        return patient;
    }

    private void patientFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.setTitle("Meni pacjenta");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initPatientGUI();
        this.setVisible(true);
    }

    private void initPatientGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new ProfileMenu(patient);
        JMenuItem phoneItem = new JMenuItem("Promeni br. telefona");
        JMenuItem addressItem = new JMenuItem("Promeni adresu");

        profileMenu.add(phoneItem);
        profileMenu.add(addressItem);

        JMenu analysisMenu = new JMenu("Analize");
        JMenuItem requestItem = new JMenuItem("Naruči analizu");
//        JMenuItem anamnesisItem = new JMenuItem("Anamneza");
//        JMenuItem activeAnalysisItem = new JMenuItem("Aktivna analiza");

        analysisMenu.add(requestItem);
//        analysisMenu.add(anamnesisItem);
//        analysisMenu.add(activeAnalysisItem);

        mainMenu.add(profileMenu);
        mainMenu.add(analysisMenu);

        this.setJMenuBar(mainMenu);

        addAllAnalysisRequestPanel();


        phoneItem.addActionListener(e -> {
            String phone = JOptionPane.showInputDialog("Unesite novi br. telefona:");
            Map<String, String> errCodes = Validator.validatePhone(new HashMap<>(), phone);
            if (errCodes.isEmpty())
                patient.setPhone(phone);
            else
                processErrors(errCodes);
        });

        addressItem.addActionListener(e -> {
            String address = JOptionPane.showInputDialog("Unesite novu adresu:");
            Map<String, String> errCodes = Validator.validateAddress(new HashMap<>(), address);
            if (errCodes.isEmpty())
                patient.setAddress(address);
            else
                processErrors(errCodes);
        });

        requestItem.addActionListener(e -> {
            new AnalysisRequestFrame(patient, dH);
            addAllAnalysisRequestPanel();
        });

    }

    private void addAllAnalysisRequestPanel() {
        boolean empty = true;
        for (AnalysisRequest aR : dH.getEntityDatabase().getAnalysisRequestDatabase().getData()) {
            if (aR.getPatient().getId() == patient.getId()) {
                empty = false;
                break;
            }
        }
        if (!empty) {
            if (noReq != null) {
                remove(noReq);
                noReq = null;
            }
            add(new AllAnalysisRequestsPanel(dH, patient));
        } else {
            if (noReq == null) {
                noReq = new JPanel();
                noReq.add(new JLabel("Nemate ni jedanu naručenu analizu."), BorderLayout.CENTER);
                add(noReq);
            }
        }
    }

    private void processErrors(Map<String, String> errCodes) {
        if (errCodes.containsKey("phone")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("phone")), "Greška", JOptionPane.ERROR_MESSAGE);
        }
        if (errCodes.containsKey("address")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("address")), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        Patient p = new Patient(2, "pcjent", "pacjentko", "Pacjentkic", mP2, "11114523");
        DatabaseHandler dH = new DatabaseHandler();
        PatientFrame aF = new PatientFrame(p, dH);
    }
}
