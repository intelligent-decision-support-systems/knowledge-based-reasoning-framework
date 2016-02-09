package org.uclab.mm.kcl.edket.krf.model.knowledgebase;

import java.io.Serializable;

public class KRFConclusion implements Serializable {

    private static final long serialVersionUID = -2403438459005621858L;

    private String conclusionKey;
    private String conclusionValue;
    private String conclusionOperator;
    private Integer ruleID;
    private Integer conclusionID;

    public String getConclusionKey() {
        return conclusionKey;
    }

    public void setConclusionKey(String conclusionKey) {
        this.conclusionKey = conclusionKey;
    }

    public String getConclusionValue() {
        return conclusionValue;
    }

    public void setConclusionValue(String conclusionValue) {
        this.conclusionValue = conclusionValue;
    }

    public String getConclusionOperator() {
        return conclusionOperator;
    }

    public void setConclusionOperator(String conclusionOperator) {
        this.conclusionOperator = conclusionOperator;
    }

    public Integer getRuleID() {
        return ruleID;
    }

    public void setRuleID(Integer ruleID) {
        this.ruleID = ruleID;
    }

    public Integer getConclusionID() {
        return conclusionID;
    }

    public void setConclusionID(Integer conclusionID) {
        this.conclusionID = conclusionID;
    }

    @Override
    public String toString() {
        return "KRFConclusion [conclusionKey=" + conclusionKey
                + ", conclusionValue=" + conclusionValue
                + ", conclusionOperator=" + conclusionOperator + ", ruleID="
                + ruleID + ", conclusionID=" + conclusionID + "]";
    }
}
