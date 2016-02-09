package org.uclab.mm.kcl.edket.krf;

import java.util.List;
import java.util.Map;

import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFKnowledgeBase;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;

public interface IRecommendationBuilder {
    public List<KRFRule> buildRecommendation(Map<String, List<String>> conditionsValue, KRFKnowledgeBase krfKnowledgeBase);

    public void generateResults(List<KRFRule> finalResolvedRules);
}
