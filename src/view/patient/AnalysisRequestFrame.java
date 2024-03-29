package view.patient;

import manage.Database;
import manage.DatabaseHandler;
import modules.DTO.AnalysisRequestDTO;
import modules.entities.Analysis;
import modules.entities.AnalysisGroup;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.Patient;
import modules.utils.MyPassword;
import net.miginfocom.swing.MigLayout;
import services.entities.AnalysisRequestServices;
import services.utils.PasswordUtils;
import view.utils.AnalysisGroupPanel;
import view.utils.HomeVisitPanel;
import view.validators.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AnalysisRequestFrame extends JFrame {

    private Patient patient;
    private Database<AnalysisGroup> analysisGroupDatabase;
    private JPanel analysesPanel;
    private JLabel price;
    private HomeVisitPanel homeVisitPanel;
    private List<Analysis> analyses;
    private boolean homeVisit;
    private DatabaseHandler dH;
    private List<AnalysisGroupPanel> aGPList;


    public AnalysisRequestFrame(Patient patient, DatabaseHandler dH) {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.patient = patient;
        this.dH = dH;
        this.analysisGroupDatabase = dH.getEntityDatabase().getAnalysisGroupDatabase();
        this.analyses = new ArrayList<>();
        this.homeVisit = false;
        this.aGPList = new ArrayList<>();
        analysisRequest();
    }

    private void analysisRequest() {
        setTitle("Naručivanje analize");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        pack();
        setVisible(true);
    }

    private void initGUI() {
        setIconImage(new ImageIcon("img/analysisRequest.png").getImage());
        MigLayout layout = new MigLayout("wrap", "[sg1]", "[][][]");
        setLayout(layout);

        analysesPanel = new JPanel(new MigLayout("wrap 2", "[sg1][sg1]", ""));

        JButton btnConfirm = new JButton("Potvrdi");
        JButton btnCancel = new JButton("Poništi");

        JScrollPane scrollPane = new JScrollPane(analysesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        homeVisitPanel = new HomeVisitPanel();

        price = new JLabel("0");
        price.setForeground(Color.white);

        addAnalysisGroupPanels();
        add(scrollPane, "pushx, growx");
        add(homeVisitPanel, "pushx, growx");
        add(btnConfirm, "split 2");
        add(btnCancel, "wrap");
        add(pricePanel(), BorderLayout.SOUTH);

        setHomeVisitActions();
        recommend();

        btnConfirm.addActionListener(e -> {
            AnalysisRequestDTO aDTO = new AnalysisRequestDTO(patient, analyses, homeVisit, Double.parseDouble(price.getText()));
            Map<String, String> errCodes = Validator.validateAnalysisRequest(aDTO);
            if (errCodes.isEmpty()) {
                new AnalysisRequestServices(dH).requestAnalysis(aDTO);
                setVisible(false);
                dispose();
            } else {
                processErrors(errCodes);
            }
        });

        btnCancel.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

    }

    private void processErrors(Map<String, String> errCodes) {
        if (errCodes.containsKey("analyses"))
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("analyses")), "Greška - Izbor tipova analiza", JOptionPane.ERROR_MESSAGE);

    }

    private void addAnalysisGroupPanels() {
        for (AnalysisGroup a : analysisGroupDatabase.getData()) {
            AnalysisGroupPanel aGP = new AnalysisGroupPanel(a.getType(), a);
            aGPList.add(aGP);
            setAnalysisBtnActions(aGP);
            analysesPanel.add(aGP, "pushx, growx");
        }
    }

    private void setAnalysisBtnActions(AnalysisGroupPanel aGP) {
        for (Map.Entry<Analysis, JCheckBox> entry : aGP.getBtnMap().entrySet()) {
            entry.getValue().addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    price.setText(String.valueOf(Double.parseDouble(price.getText()) + entry.getKey().getCost()));
                    analyses.add(entry.getKey());
                } else {
                    price.setText(String.valueOf(Double.parseDouble(price.getText()) - entry.getKey().getCost()));
                    analyses.remove(entry.getKey());
                }
            });
        }
    }

    private void setHomeVisitActions() {
        homeVisitPanel.getBtn().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                price.setText(String.valueOf(Double.parseDouble(price.getText()) + 500));
                homeVisit = true;
            } else {
                price.setText(String.valueOf(Double.parseDouble(price.getText()) - 500));
                homeVisit = false;
            }
        });
    }

    protected JPanel pricePanel() {
        JPanel p = new JPanel();
        p.setBackground(new Color(235, 64, 52));

        JLabel lblPrice = new JLabel("Cena:");
        lblPrice.setForeground(Color.white);

        p.add(lblPrice);
        p.add(price);

        return p;
    }

    private List<Analysis> priorAnalysisReport() {
        List<AnalysisRequest> aRList = new ArrayList<>();
        for (AnalysisRequest aR : dH.getEntityDatabase().getAnalysisRequestDatabase().getData()) {
            if (aR.getPatient().getId() == patient.getId()) {
                aRList.add(aR);
            }
        }

        List<Analysis> retVal = new ArrayList<>();
        if (!aRList.isEmpty()) {
            Collections.reverse(aRList);
            try {
                for (int i = 0; i < 3; i++) {
                    for (Map.Entry<Analysis, Measurement> entry : aRList.get(i).getAnalysisMeasurementMap().entrySet()) {
                        if ((entry.getValue().getValue() != 0) && !(entry.getKey().getReferenceValue().contains(entry.getValue().getValue()))) {
                            retVal.add(entry.getKey());
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return retVal;
            }
        }
        return retVal;
    }

    private void recommend() {
        List<Analysis> aList = priorAnalysisReport();
        StringBuilder message = getStringBuilder(aList);
        if (message != null) {
            int selection = JOptionPane.showConfirmDialog(null, message, "Primetili smo", JOptionPane.YES_NO_OPTION);
            if (selection == JOptionPane.YES_OPTION) {
                for (AnalysisGroupPanel aGP : aGPList) {
                    for (Map.Entry<Analysis, JCheckBox> entry : aGP.getBtnMap().entrySet()) {
                        for (Analysis a : aList) {
                            if (a.getType().equals(entry.getKey().getType())) {
                                entry.getValue().setSelected(true);
                            }
                        }
                    }
                }
            }
        }
    }

    private StringBuilder getStringBuilder(List<Analysis> aList) {
        StringBuilder m = null;
        if (!aList.isEmpty()) {
            m = new StringBuilder("Prosli put ste imali povisene sledece vrednosti:");
            for (Analysis a : aList) {
                m.append(", ").append(a.getType());
            }
            m.append(".\nDa li želite da dodate navedene analize u zahtev?");
        }
        return m;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        MyPassword mP = PasswordUtils.generateRandomPass("Password");
        Patient p = new Patient(7, "sadasda", "asdasd", "asdasd", mP, "56523562", "MUŠKO", null, null);
//        f.add(new AnalysisRequestFrame(p, new DatabaseHandler().getEntityDatabase().getAnalysisGroupDatabase()));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
