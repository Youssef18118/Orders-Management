package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Notification {
    private int NotificationID;
    private String Template;
    private int CustomerID;

    private Template template;
    private Channel channel;

}
