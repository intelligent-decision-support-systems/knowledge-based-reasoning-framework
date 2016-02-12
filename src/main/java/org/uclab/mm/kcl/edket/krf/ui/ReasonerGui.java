package org.uclab.mm.kcl.edket.krf.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import org.uclab.mm.kcl.edket.krf.KRFResult;
import org.uclab.mm.kcl.edket.krf.PatternMatcher;
import org.uclab.mm.kcl.edket.krf.RecommendationBuilder;
import org.uclab.mm.kcl.edket.krf.model.inputcasebase.InputCaseBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFConclusion;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFCondition;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFKnowledgeBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;
import org.uclab.mm.kcl.edket.krf.util.KRFConditionType;
import org.uclab.mm.kcl.edket.krf.util.KRFConditionValueOperator;
import org.uclab.mm.kcl.edket.krf.util.KRFUtil;

public class ReasonerGui {

    private JFrame frame;
    private JPanel leftPanel;
    private JPanel panel_1;
    private JButton ruleBtn;
    private JButton factsBtn;
    private JTextArea addRuleArea;
    private JTextArea addFactArea;
    private JTextArea loadRuleArea;
    private JTextArea loadFactArea;
    private JLabel lblNewLabel;
    private JButton runBtn;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel panel_2;
    private JScrollPane scrollPane_1;
    private JPanel panel_3;
    private JScrollPane scrollPane_2;
    private JPanel messagePanel;
    private JLabel message;
    private JPanel panel_4;
    private JScrollPane scrollPane_3;

    private KRFKnowledgeBase knowledgeBase;
    private InputCaseBase inputCaseBase;
    
    private static Logger log = LogManager.getLogger(ReasonerGui.class);
    private static final String KRF_INPUT_CASES = "krf_input_cases.json";
    private static final String KRF_KNOWLEDGE_BASE = "krf_knowledge_base.json";

    private static final String APP_TITLE = "Knowledge-based Reasoning and Recommendation Framework (KRF) -v1.0";

    /**
     * Launch the application.
     */
    public static void invokeGui(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReasonerGui window = new ReasonerGui();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    log.error("Cannot invoke KRF GUI!");
                    System.exit(0);
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ReasonerGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame(APP_TITLE);
        frame.setBounds(100, 100, 890, 545);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(getLeftPanel());
        frame.getContentPane().add(getPanel_1_1());
        frame.getContentPane().add(getMessagePanel());
        frame.setResizable(false);
        // . loading rules and facts
        
        loadRules();
        loadFacts();
        loadRuleModel();
        loadFactModel();
    }

    public static String loadData(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(
                new File(filePath)));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        br.close();
        return sb.toString();
    }

    private JPanel getLeftPanel() {
        if (leftPanel == null) {
            leftPanel = new JPanel();
            leftPanel.setBorder(new TitledBorder(UIManager
                    .getBorder("TitledBorder.border"), "add panel",
                    TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,
                            0, 0)));
            leftPanel.setBounds(-6, 0, 253, 440);
            leftPanel.setLayout(null);
            leftPanel.add(getRuleBtn());
            leftPanel.add(getFactsBtn());
            leftPanel.add(getPanel());
            leftPanel.add(getPanel_2());
        }
        return leftPanel;
    }

    private JPanel getPanel_1_1() {
        if (panel_1 == null) {
            panel_1 = new JPanel();
            panel_1.setBorder(new TitledBorder(UIManager
                    .getBorder("TitledBorder.border"), "loaded panel",
                    TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,
                            0, 0)));
            panel_1.setBounds(253, 0, 601, 440);
            panel_1.setLayout(null);
            panel_1.add(getPanel_3());
            panel_1.add(getLblNewLabel());
            panel_1.add(getRunBtn());
            panel_1.add(getPanel_4());

            JLabel lblFactsLoaded = new JLabel("Facts Loaded");
            lblFactsLoaded.setForeground(Color.DARK_GRAY);
            lblFactsLoaded.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC,
                    11));
            lblFactsLoaded.setBounds(38, 214, 382, 14);
            panel_1.add(lblFactsLoaded);
        }
        return panel_1;
    }

    private JButton getRuleBtn() {
        if (ruleBtn == null) {
            ruleBtn = new JButton("Add Rule");
            ruleBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if(addNewRule()){
                        if(updateKRFKnowledgeBaseFile()){
                            loadRuleModel();
                            loadRules();
                            message.setText("Rule added Successfully!");   
                        }
                    }
                    
                }
            });
            ruleBtn.setBounds(154, 214, 89, 23);
        }
        return ruleBtn;
    }

    private JButton getFactsBtn() {
        if (factsBtn == null) {
            factsBtn = new JButton("Add Fact");
            factsBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(addNewFact()){
                        if(updateKRFInputCaseFile()){
                            loadFactModel();
                            loadFacts();
                            message.setText("ConditionValue added Successfully!");   
                        }
                    }
                }
            });
            factsBtn.setBounds(154, 404, 89, 23);
        }
        return factsBtn;
    }

    private JTextArea getRule() {
        if (addRuleArea == null) {
            addRuleArea = new JTextArea();
        }
        return addRuleArea;
    }

    private JTextArea getFacts() {
        if (addFactArea == null) {
            addFactArea = new JTextArea();
        }
        return addFactArea;
    }

    private JTextArea getRulesLoaded() {
        if (loadRuleArea == null) {
            loadRuleArea = new JTextArea();
            loadRuleArea.setEditable(false);
        }
        return loadRuleArea;
    }

    private JLabel getLblNewLabel() {
        if (lblNewLabel == null) {
            lblNewLabel = new JLabel("Rules Loaded");
            lblNewLabel.setForeground(Color.DARK_GRAY);
            lblNewLabel
                    .setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
            lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
            lblNewLabel.setBounds(38, 24, 167, 23);
        }
        return lblNewLabel;
    }

    private JButton getRunBtn() {
        if (runBtn == null) {
            runBtn = new JButton("Generate Recommendation");
            runBtn.setBackground(new Color(0, 153, 0));
            runBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    long startTime = System.currentTimeMillis();
                    loadRules();
                    loadFacts();
                    generateRecommendations();
                    long overallTime = System.currentTimeMillis() - startTime;
                    message.setText("Overall Operation Time: " + overallTime + " milliseconds");
                }
            });
            runBtn.setBounds(316, 404, 260, 25);
        }
        return runBtn;
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setBounds(20, 45, 223, 158);
            panel.setLayout(null);
            panel.add(getScrollPane());
        }
        return panel;
    }

    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setBounds(0, 0, 223, 158);
            scrollPane.setViewportView(getRule());
        }
        return scrollPane;
    }

    private JPanel getPanel_2() {
        if (panel_2 == null) {
            panel_2 = new JPanel();
            panel_2.setBounds(20, 248, 223, 145);
            panel_2.setLayout(null);
            panel_2.add(getScrollPane_1());
        }
        return panel_2;
    }

    private JScrollPane getScrollPane_1() {
        if (scrollPane_1 == null) {
            scrollPane_1 = new JScrollPane();
            scrollPane_1.setBounds(0, 0, 223, 145);
            scrollPane_1.setViewportView(getFacts());
        }
        return scrollPane_1;
    }

    private JPanel getPanel_3() {
        if (panel_3 == null) {
            panel_3 = new JPanel();
            panel_3.setBounds(38, 45, 538, 158);
            panel_3.setLayout(null);
            panel_3.add(getScrollPane_2());
        }
        return panel_3;
    }

    private JScrollPane getScrollPane_2() {
        if (scrollPane_2 == null) {
            scrollPane_2 = new JScrollPane();
            scrollPane_2.setBounds(0, 0, 538, 158);
            scrollPane_2.setViewportView(getRulesLoaded());
        }
        return scrollPane_2;
    }

    private JPanel getMessagePanel() {
        if (messagePanel == null) {
            messagePanel = new JPanel();
            messagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED,
                    new Color(240, 240, 240), UIManager
                            .getColor("InternalFrame.borderHighlight"),
                    new Color(240, 240, 240), null));
            messagePanel.setBackground(UIManager
                    .getColor("InternalFrame.inactiveBorderColor"));
            messagePanel.setBounds(-6, 484, 890, 32);
            messagePanel.setLayout(null);
            messagePanel.add(getMessage());
        }
        return messagePanel;
    }

    private JLabel getMessage() {
        if (message == null) {
            message = new JLabel("Status");
            message.setFont(new Font("Tahoma", Font.ITALIC, 11));
            message.setBounds(15, 0, 854, 32);
            message.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return message;
    }

    private JPanel getPanel_4() {
        if (panel_4 == null) {
            panel_4 = new JPanel();
            panel_4.setLayout(null);
            panel_4.setBounds(38, 235, 538, 158);
            panel_4.add(getScrollPane_3());
        }
        return panel_4;
    }

    private JScrollPane getScrollPane_3() {
        if (scrollPane_3 == null) {
            scrollPane_3 = new JScrollPane();
            scrollPane_3.setBounds(0, 0, 538, 158);

            loadFactArea = new JTextArea();
            loadFactArea.setEditable(false);
            scrollPane_3.setViewportView(loadFactArea);
        }
        return scrollPane_3;
    }

    private void loadFacts(){
        try{
            message.setText("loading facts...");
            String loadedFacts = loadData(KRF_INPUT_CASES);
            inputCaseBase = KRFUtil.objectMapper.readValue(loadedFacts, InputCaseBase.class);
            String prettyFacts = KRFUtil.objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(inputCaseBase);
            loadFactArea.setText(prettyFacts);
            message.setText("ConditionsValue loaded successfully!");
        } catch(Exception e) {
            message.setText("Error Loading Facts: {} " + e.getMessage());
        }
    }
    private void loadRules(){
        try{
            message.setText("loading rules...");
            String loadedRules = loadData(KRF_KNOWLEDGE_BASE);

            knowledgeBase = KRFUtil.objectMapper.readValue(loadedRules, KRFKnowledgeBase.class);
            String prettyRules = KRFUtil.objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(knowledgeBase);
            loadRuleArea.setText(prettyRules);
            message.setText("Rules loaded successfully!");
        }catch(Exception e){
            message.setText("Error Loaded Rules: {} " + e.getMessage());
        }
    }
    private void loadRuleModel(){
        String ruleModel = getRuleModel();
        addRuleArea.setText(ruleModel);
    }
    private void loadFactModel(){
        String factModel = getFactModel();
        addFactArea.setText(factModel);
    }
    
    private String getFactModel(){
        String sampleFact = "{\"key\":null}";
        String prettyFact = null;
        try {
            Object jsonFact = KRFUtil.objectMapper.readValue(sampleFact, Object.class);
            prettyFact = KRFUtil.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonFact);
            addFactArea.setText(prettyFact);
        } catch (Exception e) {
            message.setText("Error loading conditionValue model");
        }
        
        return prettyFact;
    }
    public void generateRecommendations(){
        try {            
            RecommendationBuilder recommendationBuilder = new RecommendationBuilder(new PatternMatcher());
            for(Map<String, List<String>> conditionsValue : inputCaseBase.getInputCaseBase()){
                KRFResult krfResult = recommendationBuilder.buildRecommendation(conditionsValue, knowledgeBase);
                recommendationBuilder.generateResults(krfResult);
            }
                
            message.setText("Done.");

        } catch (Exception ex) {
            message.setText("Cannot load data!!!");
        }
    }
    
    private String getRuleModel(){
        
        KRFRule sampleRuleModel = new KRFRule();
        List<KRFConclusion> sampleConclusionList = new ArrayList<KRFConclusion>();
        sampleConclusionList.add(new KRFConclusion());
        List<KRFCondition> sampleConditionList = new ArrayList<KRFCondition>();
        sampleConditionList.add(new KRFCondition());
        
        sampleRuleModel.setConclusionList(sampleConclusionList);
        sampleRuleModel.setConditionList(sampleConditionList);
        
        String prettyRuleModel = null;
        try {
            prettyRuleModel = KRFUtil.objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(sampleRuleModel);
        } catch (JsonGenerationException e) {
            log.error(e.getMessage());
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return prettyRuleModel;
    }
    
    private boolean addNewRule(){
        boolean succeeded = false;
        try{
            String ruleStr = addRuleArea.getText();
            KRFRule newRule = KRFUtil.objectMapper.readValue(ruleStr, new TypeReference<KRFRule>() {});
            if(isValidRule(newRule)){
                knowledgeBase.getRules().add(newRule);
                succeeded = true;
                message.setText("New Rule added successfully!");  
            }
        }catch(Exception e){
            message.setText("Erorr adding rule {} " + e.getMessage());
        }
        return succeeded;
    }
    private boolean addNewFact(){
        boolean succeeded = false;
        try{
            String factStr = addFactArea.getText();
            Map<String, List<String>> newFact = KRFUtil.objectMapper.readValue(factStr, new TypeReference<Map<String, List<String>>>() {});
            if(!isValidFact(newFact)){
                throw new Exception("Invalid Format Specified For Fact!");
            }
            inputCaseBase.getInputCaseBase().add(newFact);
            succeeded = true;
            message.setText("New Fact added successfully");
        }catch(Exception e){
            message.setText("Error adding fact {} " + e.getMessage());
        }
        
        return succeeded;
    }
    
    private boolean updateKRFKnowledgeBaseFile(){
        boolean updated = false;
        FileWriter writer = null;
        try{
            String krfRulesStr = KRFUtil.objectMapper.writeValueAsString(knowledgeBase);
            writer = new FileWriter(KRF_KNOWLEDGE_BASE, false);
            writer.write(krfRulesStr);
            writer.flush();
            writer.close();
            writer = null;
            updated = true;
        } catch(Exception e) {
            message.setText("Error: Cannot update " + KRF_KNOWLEDGE_BASE + "!");
            if(writer != null){try{writer.close();}catch(IOException ex){}}
        }
        return updated;
    }
    private boolean updateKRFInputCaseFile(){
        boolean updated = false;
        FileWriter writer = null;
        try{
            String krfFactsStr = KRFUtil.objectMapper.writeValueAsString(inputCaseBase);
            writer = new FileWriter(KRF_INPUT_CASES, false);
            writer.write(krfFactsStr);
            writer.flush();
            writer.close();
            writer = null;
            updated = true;
        } catch(Exception e) {
            message.setText("Error: Cannot update " + KRF_INPUT_CASES + "!");
            if(writer != null){try{writer.close();}catch(IOException ex){}}
        }
        return updated;
    }
    
    private boolean isValidRule(KRFRule rule){
        if(rule.getRuleConclusion() == null || rule.getRuleConclusion().length()==0){
            message.setText("Error: RuleConclusion cannot be empty!");
            return false;
        }
        if(rule.getRuleID() == null){
            message.setText("Error: RuleId cannot be empty!");
            return false;
        }
        if(rule.getConditionList() == null || rule.getConditionList().isEmpty()){
            message.setText("Error: RuleConditionList cannot be empty!");
            return false;
        }
        for(KRFCondition condition : rule.getConditionList()){
            if(condition == null){
                message.setText("Error: RuleCondition cannot be empty!");
                return false;
            }
            if(condition.getConditionKey() == null || condition.getConditionKey().isEmpty()){
                message.setText("Error: ConditionKey cannot be empty!");
                return false;
            }
            if(condition.getConditionValue() == null || condition.getConditionValue().isEmpty()){
                message.setText("Error: ConditionValue cannot be empty!");
                return false;
            }
            String type = condition.getConditionType();
            if(type == null || type.isEmpty()){
                message.setText("Error: ConditionType cannot be empty!");
                return false;
            }
            try{
                if(KRFConditionType.valueOf(type) == null){
                    message.setText("Error: Invalid conditionType specified!");
                    return false;
                }
            }catch(Exception e){
                message.setText("Error: Invalid conditionType specified!");
                return false;
            }
            String cvo = condition.getConditionValueOperator();
            if(cvo == null || cvo.isEmpty()){
                message.setText("Error: ConditionValueOperator cannot be empty!");
                return false;
            }
            if(KRFConditionValueOperator.getValueOperator(cvo) == null){
                message.setText("Error: Invalid operator specified!");
                return false;
            }
        }
        return true;
    }
    private boolean isValidFact(Map<String, List<String>> fact){
        if(fact.isEmpty()){
            return false;
        }
        for(String k : fact.keySet()){
            if(fact.get(k) == null || fact.get(k).isEmpty()){
                return false;
            }
        }
        return true;
    }
}