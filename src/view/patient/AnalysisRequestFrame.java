package view.patient;

import manage.Database;
import manage.DatabaseHandler;
import modules.DTO.AnalysisRequestDTO;
import modules.entities.Analysis;
import modules.entities.AnalysisGroup;
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


    public AnalysisRequestFrame(Patient patient) {
        this.patient = patient;
        this.analysisGroupDatabase = new DatabaseHandler().getEntityDatabase().getAnalysisGroupDatabase();
        this.analyses = new ArrayList<>();
        this.homeVisit = false;
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
        add(btnCancel,"wrap");
        add(pricePanel(), BorderLayout.SOUTH);

        setHomeVisitActions();
        btnConfirm.addActionListener(e -> {
            AnalysisRequestDTO aDTO = new AnalysisRequestDTO(patient, analyses, homeVisit, Double.parseDouble(price.getText()));
            Map<String, String> errCodes = Validator.validateAnalysisRequest(aDTO);
            if (errCodes.isEmpty()) {
                new AnalysisRequestServices().requestAnalysis(aDTO);
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
