package com.br.curso.communistcoach;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
            result.append("&");
        }
        String resulString = result.toString();
        return resulString.length()>0?resulString.substring(0,resulString.length()-1):resulString;
    }
}
