package org.uclab.mm.kcl.edket.krf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFKnowledgeBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;


public class RecommendationBuilder implements IRecommendationBuilder {
    private static Logger log = LogManager.getLogger(RecommendationBuilder.class);
    private PatternMatcher patternMatcher;
    
    public RecommendationBuilder(PatternMatcher patternMatcher){
        this.patternMatcher = patternMatcher;
    }
    
    @Override
    public List<KRFRule> buildRecommendation(Map<String, List<String>> conditionsValue, KRFKnowledgeBase krfKnowledgeBase) {
        log.info("Building recommendation...");
        List<KRFRule> firedRules = patternMatcher.fireRule(conditionsValue, krfKnowledgeBase);
        List<KRFRule> finalResolvedRules = ConflictResolver.resolveConflict(firedRules);
        return finalResolvedRules;
    }

    @Override
    public void generateResults(List<KRFRule> finalResolvedRules) {
        log.info("Generating Results...");
        try {
            RecommendationsResultGenerator.generateResults(finalResolvedRules);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
