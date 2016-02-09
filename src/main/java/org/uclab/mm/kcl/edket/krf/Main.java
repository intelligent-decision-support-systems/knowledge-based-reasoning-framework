package org.uclab.mm.kcl.edket.krf;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.uclab.mm.kcl.edket.krf.model.inputcasebase.InputCaseBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFKnowledgeBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;
import org.uclab.mm.kcl.edket.krf.ui.ReasonerGui;
import org.uclab.mm.kcl.edket.krf.util.KRFUtil;

public class Main {
    private static Logger log = LogManager.getLogger(Main.class);

    public static final String KRF_INPUT_CASES = "krf_input_cases.json";
    public static final String KRF_KNOWLEDGE_BASE = "krf_knowledge_base.json";

    public static void main(String[] args) {
       initReasoner();
    }

    public static void initReasoner() {
        log.info("loading rules and facts...");
        try {
            String loadedRules = ReasonerGui.loadData(KRF_KNOWLEDGE_BASE);
            String loadedFacts = ReasonerGui.loadData(KRF_INPUT_CASES);

            KRFKnowledgeBase knowledgeBase = KRFUtil.objectMapper.readValue(loadedRules, new TypeReference<KRFKnowledgeBase>() {});
            InputCaseBase inputCaseBase = KRFUtil.objectMapper.readValue(loadedFacts, new TypeReference<InputCaseBase>() {});
            
            RecommendationBuilder recommendationBuilder = new RecommendationBuilder(new PatternMatcher());
            for(Map<String, List<String>> conditionsValue : inputCaseBase.getInputCaseBase()){
                List<KRFRule> finalResolvedRules = recommendationBuilder.buildRecommendation(conditionsValue, knowledgeBase);
                recommendationBuilder.generateResults(finalResolvedRules);
            }
                
            log.info("Done.");

        } catch (Exception ex) {
            log.error("Cannot load data!!!");
        }
    }

}
