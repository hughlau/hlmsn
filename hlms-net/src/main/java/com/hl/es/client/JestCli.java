package com.hl.es.client;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import com.hl.util.PropertiesUtil;

public class JestCli {

	private static JestClient jestclient;
    
    private static String connectionUrl = "";
    
    private static HttpClientConfig clientConfig(){
    	if("".equals(connectionUrl)){
    		String path = "/props/es.properties";
        	String ip = PropertiesUtil.getValue(path, "ip");
        	String port = PropertiesUtil.getValue(path, "port");
        	connectionUrl="http://"+ip+":"+port;
    	}
        return new HttpClientConfig.Builder(connectionUrl)
        .multiThreaded(true)
        .build();
    }
    
    public static JestClient jestClient(){
        
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(clientConfig());
        jestclient = factory.getObject();  
        return jestclient;
    }
    
    public static void setUrl(String url){
        connectionUrl=url;
    }
}
