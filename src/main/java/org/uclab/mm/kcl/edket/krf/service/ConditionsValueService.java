package org.uclab.mm.kcl.edket.krf.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class ConditionsValueService {

    public static String readFromFile(String filePath) {
        BufferedReader br = null;
        StringBuilder ruleBuilder = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                ruleBuilder.append(line);
            }

        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException iox) {
                System.err.println(iox.getMessage());
            }
        }

        return ruleBuilder.toString();
    }

    public static Map<String, Object> parse(String filePath) {
        String response = readFromFile(filePath);
        Map<String, Object> facts = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            facts = mapper.readValue(response,
                    new TypeReference<HashMap<String, Object>>() {
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return facts;
    }
}
