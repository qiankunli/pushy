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

import com.turo.pushy.apns.ApnsPushNotification;
import com.turo.pushy.apns.DeliveryPriority;

import java.util.Date;

class LenientApnsPushNotification implements ApnsPushNotification {
    private final String token;
    private final String payload;
    private final Date invalidationTime;
    private final DeliveryPriority priority;
    private final String topic;
    private final String collapseId;

    public LenientApnsPushNotification(final String token, final String topic, final String payload, final Date invalidationTime, final DeliveryPriority priority, final String collapseId) {
        this.token = token;
        this.payload = payload;
        this.invalidationTime = invalidationTime;
        this.priority = priority;
        this.topic = topic;
        this.collapseId = collapseId;
    }

    /**
     * Returns the token of the device to which this push notification should be delivered.
     *
     * @return the token of the device to which this push notification should be delivered
     */
    @Override
    public String getToken() {
        return this.token;
    }

    /**
     * Returns the payload to include in this push notification.
     *
     * @return the payload to include in this push notification
     */
    @Override
    public String getPayload() {
        return this.payload;
    }

    /**
     * Returns the time at which this push notification is no longer valid and should no longer be delivered.
     *
     * @return the time at which this push notification is no longer valid and should no longer be delivered
     */
    @Override
    public Date getExpiration() {
        return this.invalidationTime;
    }

    /**
     * Returns the priority with which this push notification should be delivered to the receiving device.
     *
     * @return the priority with which this push notification should be delivered to the receiving device
     */
    @Override
    public DeliveryPriority getPriority() {
        return this.priority;
    }

    /**
     * Returns the topic to which this push notification should be sent.
     *
     * @return the topic to which this push notification should be sent
     */
    @Override
    public String getTopic() {
        return this.topic;
    }

    /**
     * Returns the "collapse ID" for this push notification, which allows it to supersede or be superseded by other
     * notifications with the same ID.
     *
     * @return the "collapse ID" for this push notification
     */
    @Override
    public String getCollapseId() {
        return this.collapseId;
    }
}
