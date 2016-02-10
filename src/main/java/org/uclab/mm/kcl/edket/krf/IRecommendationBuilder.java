package org.uclab.mm.kcl.edket.krf;

import java.util.List;
import java.util.Map;

import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFKnowledgeBase;

public interface IRecommendationBuilder {
    public KRFResult buildRecommendation(Map<String, List<String>> conditionsValue, KRFKnowledgeBase krfKnowledgeBase);

    public void generateResults(KRFResult krfResult);
}
