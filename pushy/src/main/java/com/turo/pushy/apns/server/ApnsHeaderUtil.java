/*
 * Copyright (c) 2013-2017 Turo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.turo.pushy.apns.server;

import com.turo.pushy.apns.DeliveryPriority;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;

import java.util.Date;

class ApnsHeaderUtil {
    private static final String APNS_PATH_PREFIX = "/3/device/";
    private static final AsciiString APNS_TOPIC_HEADER = new AsciiString("apns-topic");
    private static final AsciiString APNS_PRIORITY_HEADER = new AsciiString("apns-priority");
    private static final AsciiString APNS_EXPIRATION_HEADER = new AsciiString("apns-expiration");
    private static final AsciiString APNS_ID_HEADER = new AsciiString("apns-id");
    private static final AsciiString APNS_COLLAPSE_ID_HEADER = new AsciiString("apns-collapse-id");

    public static String getDeviceToken(final Http2Headers headers) {
        final String deviceToken;
        final CharSequence pathSequence = headers.get(Http2Headers.PseudoHeaderName.PATH.value());

        if (pathSequence != null) {
            final String pathString = pathSequence.toString();

            deviceToken = pathString.startsWith(APNS_PATH_PREFIX) ? pathString.substring(APNS_PATH_PREFIX.length()) : null;
        } else {
            deviceToken = null;
        }

        return deviceToken;
    }

    public static String getTopic(final Http2Headers headers) {
        final CharSequence topicSequence = headers.get(APNS_TOPIC_HEADER);
        return topicSequence != null ? topicSequence.toString() : null;
    }

    public static DeliveryPriority getPriority(final Http2Headers headers) {
        final Integer priorityCode = headers.getInt(APNS_PRIORITY_HEADER);

        try {
            return priorityCode != null ? DeliveryPriority.getFromCode(priorityCode) : null;
        }  catch (final IllegalArgumentException e) {
            return null;
        }
    }

    public static Date getExpiration(final Http2Headers headers) {
        final Integer expirationTimestamp = headers.getInt(APNS_EXPIRATION_HEADER);
        return expirationTimestamp != null ? new Date(expirationTimestamp * 1000) : null;
    }

    public static String getCollapseId(final Http2Headers headers) {
        final CharSequence collapseIdSequence = headers.get(APNS_COLLAPSE_ID_HEADER);
        return collapseIdSequence != null ? collapseIdSequence.toString() : null;
    }
}
