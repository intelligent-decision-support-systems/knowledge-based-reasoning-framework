package org.uclab.mm.kcl.edket.krf.model.knowledgebase;

import java.io.Serializable;

public class KRFCondition implements Serializable {

    private static final long serialVersionUID = 4672043610791173466L;

    private String conditionKey;
    private String conditionValue;
    private String conditionType;
    private String conditionValueOperator;
    private Boolean isItSituation;
    private Integer conditionID;

    public String getConditionKey() {
        return conditionKey;
    }

    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionValueOperator() {
        return conditionValueOperator;
    }

    public void setConditionValueOperator(String conditionValueOperator) {
        this.conditionValueOperator = conditionValueOperator;
    }

    public Boolean getIsItSituation() {
        return isItSituation;
    }

    public void setIsItSituation(Boolean isItSituation) {
        this.isItSituation = isItSituation;
    }

    public Integer getConditionID() {
        return conditionID;
    }

    public void setConditionID(Integer conditionID) {
        this.conditionID = conditionID;
    }

    @Override
    public String toString() {
        return "KRFCondition [conditionKey=" + conditionKey
                + ", conditionValue=" + conditionValue + ", conditionType="
                + conditionType + ", conditionValueOperator="
                + conditionValueOperator + ", isItSituation=" + isItSituation
                + ", conditionID=" + conditionID + "]";
    }

}
