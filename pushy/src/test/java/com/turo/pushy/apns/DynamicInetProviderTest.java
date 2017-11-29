package com.turo.pushy.apns;

import org.junit.Test;

public class DynamicInetProviderTest {
    @Test
    public void test(){
        DynamicInetProvider provider = new DynamicInetProvider("api.push.apple.com", ApnsClientBuilder.DEFAULT_APNS_PORT);
        provider.addDnsServer("114.114.114.114");
        provider.addDnsServer("223.5.5.5");
        provider.addDnsServer("180.76.76.76");
        provider.start();
        try {
            Thread.sleep(5000); //wait for fetching address async
        } catch (InterruptedException e1) {
        }
        int count = 8;
        while(count -- > 0) {
            System.out.println(provider.getInetAddress());
        }
    }
}
