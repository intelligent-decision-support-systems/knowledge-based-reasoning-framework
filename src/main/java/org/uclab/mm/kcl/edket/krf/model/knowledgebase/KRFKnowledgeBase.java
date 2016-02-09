package org.uclab.mm.kcl.edket.krf.model.knowledgebase;

import java.io.Serializable;
import java.util.List;

public class KRFKnowledgeBase implements Serializable {

    private static final long serialVersionUID = 2577947434339517656L;

    List<KRFRule> rules;

    public List<KRFRule> getRules() {
        return rules;
    }

    public void setRules(List<KRFRule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "KRFKnowledgeBase [rules=" + rules + "]";
    }
}
