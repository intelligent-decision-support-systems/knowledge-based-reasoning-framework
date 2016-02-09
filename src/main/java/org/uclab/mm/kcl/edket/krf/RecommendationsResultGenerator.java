package org.uclab.mm.kcl.edket.krf;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.uclab.mm.kcl.edket.krf.model.knowledgebase.KRFRule;
import org.uclab.mm.kcl.edket.krf.util.KRFUtil;


public class RecommendationsResultGenerator {
    private static Logger log = LogManager.getLogger(PatternMatcher.class);
    
    /**
     * Generate Results of the recommended rules
     * 
     * @param finalResolvedRules
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static void generateResults(List<KRFRule> finalResolvedRules) throws JsonGenerationException, JsonMappingException, IOException {
        for(KRFRule resolvedRule : finalResolvedRules){
            log.info(KRFUtil.objectMapper.writeValueAsString(resolvedRule));
        }
    }
}
