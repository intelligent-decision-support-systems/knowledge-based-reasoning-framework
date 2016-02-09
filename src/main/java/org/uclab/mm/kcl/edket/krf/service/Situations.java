package org.uclab.mm.kcl.edket.krf.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Situations implements Serializable {

    private static final long serialVersionUID = 7222498736081149676L;
    private String situationID;
    private String situationDescription;
    private Set<SituationConditions> listSConditions = new HashSet<SituationConditions>();

    public Situations() {

    }

    public Situations(String situationID) {
        this.situationID = situationID;
    }

    public String getSituationID() {
        return situationID;
    }

    public void setSituationID(String situationID) {
        this.situationID = situationID;
    }

    public String getSituationDescription() {
        return situationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        this.situationDescription = situationDescription;
    }

    public Set<SituationConditions> getListSConditions() {
        return listSConditions;
    }

    public void setListSConditions(Set<SituationConditions> listSConditions) {
        this.listSConditions = listSConditions;
    }

    public void addSituationCondition(SituationConditions situationCondition) {
        this.listSConditions.add(situationCondition);
    }

}
