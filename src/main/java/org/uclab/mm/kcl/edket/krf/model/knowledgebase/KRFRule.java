package org.uclab.mm.kcl.edket.krf.model.knowledgebase;

import java.io.Serializable;
import java.util.List;

public class KRFRule implements Serializable {

    private static final long serialVersionUID = -3608483411293578402L;

    private String ruleConclusion;
    private List<KRFConclusion> conclusionList;
    private List<KRFCondition> conditionList;
    private Integer ruleID;

    public String getRuleConclusion() {
        return ruleConclusion;
    }

    public void setRuleConclusion(String ruleConclusion) {
        this.ruleConclusion = ruleConclusion;
    }

    public List<KRFConclusion> getConclusionList() {
        return conclusionList;
    }

    public void setConclusionList(List<KRFConclusion> conclusionList) {
        this.conclusionList = conclusionList;
    }

    public List<KRFCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<KRFCondition> conditionList) {
        this.conditionList = conditionList;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    @Override
    public String toString() {
        return "KRFRule [ruleConclusion=" + ruleConclusion
                + ", conclusionList=" + conclusionList + ", conditionList="
                + conditionList + ", ruleID=" + ruleID + "]";
    }
}
