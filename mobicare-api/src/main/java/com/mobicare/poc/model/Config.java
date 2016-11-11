package com.mobicare.poc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinicius.martins on 07/06/2016.
 */
public class Config {

    private String version;
    private Map<String,String> parameters;

    public Config(){
        this.version = "1.0";
        this.parameters = new HashMap<String,String>();
        parameters.put("param1","value1");
        parameters.put("param2","value2");

    }
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
