package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
    private static Integer notificationCounter = 0;

    private final Integer notificationID;
    private List<Integer> customerIDs;
    private List<Template> templates;
    private List<Channel> channels;

    public Notification() {
        this.notificationID = ++notificationCounter;
    }

    public Integer getNotificationID() {
        return notificationID;
    }

    public String DisplayNotification() {
        return "Notification with ID " + notificationID + " was sent through " + channels.size() + " channels with "
                + templates.size() + " templates";
    }
}
