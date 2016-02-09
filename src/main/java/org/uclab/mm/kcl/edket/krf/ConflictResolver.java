package org.uclab.mm.kcl.edket.krf;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;

public class ConflictResolver {
    private static Logger log = LogManager.getLogger(ConflictResolver.class);
    
    public static List<KRFRule> resolveConflict(List<KRFRule> firedRules) {
        log.info("resolving conflict...");
        if (firedRules == null || firedRules.size() < 2) {
            return firedRules;
        }
        List<KRFRule> finalResolvedRules = new ArrayList<KRFRule>();
        finalResolvedRules.add(firedRules.get(0));

        for (int i = 1; i < firedRules.size(); i++) {
            KRFRule nextRule = firedRules.get(i);
            int nc1 = finalResolvedRules.get(0).getConditionList().size();
            int nc2 = nextRule.getConditionList().size();

            if (nc2 < nc1) {
                continue;
            } else if (nc2 > nc1) {
                finalResolvedRules.clear();
            }
            finalResolvedRules.add(nextRule);
        }
        log.info("done");
        return finalResolvedRules;
    }
}
