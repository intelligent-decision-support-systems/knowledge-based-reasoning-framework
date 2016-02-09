package org.uclab.mm.kcl.edket.krf.service;

import java.io.Serializable;

public class SituationConditions implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1297159941862178410L;
    private String conditionKey;
    private String conditionValue;
    private String conditionType;
    private String conditionValueOperator;

    /**
     * SituationCondions
     * 
     * @param conditionKey
     * @param conditionValue
     * @param conditionType
     * @param conditionValueOperator
     */
    public SituationConditions(String conditionKey, String conditionValue, String conditionType, String conditionValueOperator) {
        this.conditionKey = conditionKey;
        this.conditionValue = conditionValue;
        this.conditionType = conditionType;
        this.conditionValueOperator = conditionValueOperator;
    }

    public SituationConditions() {

    }

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

}
