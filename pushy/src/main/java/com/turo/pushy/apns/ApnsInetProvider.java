package com.turo.pushy.apns;

import java.net.InetSocketAddress;

public interface ApnsInetProvider {
    InetSocketAddress getInetAddress();
}
