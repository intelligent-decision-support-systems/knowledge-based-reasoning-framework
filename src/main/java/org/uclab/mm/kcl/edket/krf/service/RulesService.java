package org.uclab.mm.kcl.edket.krf.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.uclab.mm.kcl.edket.krf.util.KRFUtil;

public class RulesService {
    private static Logger log = LogManager.getLogger(RulesService.class);
    
    private String method = "POST";
    private Situations situationsData = new Situations();
    private String url = "http://163.180.116.106:7070/SpringMVCHibernate/situationReasoner";
    private String contentType = "application/json";
    private String accept = "application/json";
    private String charset = "UTF-8";

    private String response;

    private InputStream inputStream;

    public RulesService() {
    };

    public String getMethod() {
        return method;
    }

    public RulesService setMethod(String method) {
        this.method = method;
        return this;
    }

    public RulesService setSituationsData(Situations data) {
        this.situationsData = data;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public RulesService setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public RulesService setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String getAccept() {
        return accept;
    }

    public RulesService setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public RulesService setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public RulesService connect(String _url) {
        this.url = _url;
        return connect();
    }

    public RulesService connect() {
        if (url == null) {
            log.error("URL not set!");
            System.exit(0);
        }
        if (method.equals("POST") && situationsData.getListSConditions().isEmpty()) {
            log.error("No post data set!");
            System.exit(0);
        }

        OutputStream outputStream = null;
        byte[] outputBytes = null;
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) ((new URL(url).openConnection()));
            httpConnection.setDoOutput(true);
            httpConnection.setRequestProperty("Content-Type", getContentType());
            httpConnection.setRequestProperty("Accept", getAccept());
            httpConnection.setRequestMethod(getMethod());
            httpConnection.connect();
            outputBytes = situationsDataToJsonString().getBytes(getCharset());
            outputStream = httpConnection.getOutputStream();
            outputStream.write(outputBytes);
            outputStream.close();
            inputStream = httpConnection.getInputStream();
        } catch (MalformedURLException ex) {
            log.error("MalformedURLException");
            log.error(ex.getMessage());
        } catch (ProtocolException ex) {
            log.error("ProtocolException");
            log.error(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            log.error("UnsupportedEncodingException");
            log.error(ex.getMessage());
        } catch (IOException ex) {
            log.error("IOException");
            log.error(ex.getMessage());
        }
        return this;
    }

    public RulesService getResponse() {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            log.error("IOException");
            log.error(ex.getMessage());
        }
        response = responseBuilder.toString();
        return this;
    }

    public RulesService setSituationID(String situationID) {
        this.situationsData.setSituationID(situationID);
        return this;
    }

    public RulesService setSituationDescription(String situationDescription) {
        this.situationsData.setSituationDescription(situationDescription);
        return this;
    }

    public RulesService addSituationCondition(SituationConditions situationCondition) {
        this.situationsData.addSituationCondition(situationCondition);
        return this;
    }

    public List<Map<String, Object>> parse() {
        List<Map<String, Object>> rulesList = new ArrayList<Map<String, Object>>();
        try {
            rulesList = KRFUtil.objectMapper.readValue(response,
                    new TypeReference<ArrayList<Map<String, Object>>>() {});

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rulesList;
    }

    public String situationsDataToJsonString() {
        String situationsAsJson = null;
        try {
            situationsAsJson = KRFUtil.objectMapper.writeValueAsString(this.situationsData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return situationsAsJson;
    }
}
