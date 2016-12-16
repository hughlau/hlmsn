package com.hl.es.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class JavaCli {

    public Client buildClient(String myClusterName,String ip,String port) throws NumberFormatException, UnknownHostException {
        Settings settings = Settings.builder()
                        .put("cluster.name", myClusterName).build();
        TransportClient client = TransportClient.builder().settings(settings).build()
        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), Integer.parseInt(port)));
        return client;
    }
}
