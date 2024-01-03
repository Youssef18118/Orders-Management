package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EmailChannel extends Channel {
    private String Email;

    @Override
    public String ToSend() {
        String message = "Simulating Sending Notification through Email TO : " + Email;
        return message;
    }

    public void setType(String type) {
        this.Type = type;
    }

}
