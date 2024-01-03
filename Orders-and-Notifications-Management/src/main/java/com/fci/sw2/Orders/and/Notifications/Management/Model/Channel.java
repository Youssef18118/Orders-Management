package com.fci.sw2.Orders.and.Notifications.Management.Model;

public abstract class Channel {
    protected String Type;

    abstract String ToSend();
}
