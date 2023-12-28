package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SmsChannel implements Channel {
    private String PhoneNumber;

    @Override
    public String ToSend() {
        String message = "Simulating Sending Notification through Sms TO number : " + PhoneNumber;
        return message;
    }

}
