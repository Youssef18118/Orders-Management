package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Channel;
import com.fci.sw2.Orders.and.Notifications.Management.Model.CompoundOrder;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.EmailChannel;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Notification;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.OrderTemplate;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Shipping;
import com.fci.sw2.Orders.and.Notifications.Management.Model.ShippingTemplate;
import com.fci.sw2.Orders.and.Notifications.Management.Model.SmsChannel;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Template;

@Service
public class NotificationBsl {
    private static DbLists db = new DbLists();

    public Notification CreateNotification(Order order) {
        Notification notification = new Notification();
        List<Integer> customerIDs = new ArrayList<>();
        List<Template> templates = new ArrayList<>();
        List<Channel> channels = new ArrayList<>();
        // boolean flag = false;

        if (order.getType().equals("Simple")) {

            customerIDs.add(order.getCustomerID());

            // handle SimpleOrder-specific logic here
            OrderTemplate template = new OrderTemplate();
            template.setCustomerID(order.getCustomerID());
            template.setPrice(order.getTotalPrice());
            templates.add(template);

            if (order.getShipping() != null) {
                ShippingTemplate temp2 = new ShippingTemplate();
                temp2.setShippingID(order.getShipping().getShipID());
                temp2.setLocation(order.getShipping().getShipLocation());
                temp2.setFees(100);
                templates.add(temp2);
                db.Templates_sent.put("Shipping Template", db.Templates_sent.getOrDefault("Shipping Template", 0) + 1); // should
                                                                                                                        // add
                                                                                                                        // in
                                                                                                                        // map
            }

        } else if (order.getType().equals("Compound")) {
            for (Order temp : ((CompoundOrder) order).getOrderes()) {
                // handle CompoundOrder-specific logic here
                customerIDs.add(temp.getCustomerID());

                OrderTemplate template = new OrderTemplate();
                template.setCustomerID(temp.getCustomerID());

                for (Order orderItem : ((CompoundOrder) order).getOrderes()) {
                    if (temp.getCustomerID() == orderItem.getCustomerID()) {
                        template.setPrice(orderItem.getTotalPrice());
                    }
                }

                templates.add(template);
                db.Templates_sent.put("Order Template", db.Templates_sent.getOrDefault("Order Template", 0) + 1); // should
                                                                                                                  // add
                                                                                                                  // in
                                                                                                                  // map

                if (order.getShipping() != null) {
                    ShippingTemplate temp2 = new ShippingTemplate();
                    temp2.setShippingID(order.getShipping().getShipID());
                    temp2.setLocation(order.getShipping().getShipLocation());
                    temp2.setFees(100);
                    templates.add(temp2);
                    db.Templates_sent.put("Shipping Template",
                            db.Templates_sent.getOrDefault("Shipping Template", 0) + 1); // should
                                                                                         // add
                                                                                         // in
                                                                                         // map
                }

            }
        }

        for (Integer CID : customerIDs) {
            Customer cus = findCustomerById(CID);

            EmailChannel emailChannel = new EmailChannel();
            emailChannel.setEmail(cus.getEmail());
            emailChannel.setType("email"); // momken tedrab
            channels.add(emailChannel);

            db.NotifiedEmails.put(cus.getEmail(), db.NotifiedEmails.getOrDefault(cus.getEmail(), 0) + 1); // should add
                                                                                                          // in map

            SmsChannel sms = new SmsChannel();
            sms.setPhoneNumber(cus.getPhoneNumber());
            sms.setType("phone"); // momken tedrab
            channels.add(sms);

            db.NotifiedPhoneNums.put(cus.getPhoneNumber(),
                    db.NotifiedPhoneNums.getOrDefault(cus.getPhoneNumber(), 0) + 1); // should add in map

        }

        notification.setCustomerIDs(customerIDs);
        notification.setTemplates(templates);
        notification.setChannels(channels);

        db.NotificationQueue.add(notification);
        db.NotificationList.add(notification);
        return notification;
    }

    public Customer findCustomerById(Integer CID) {
        for (Customer c : db.Customers) {
            if (c.getCustomerId() == CID) {
                return c;
            }
        }
        return null;
    }

    public Map.Entry<String, Integer> DisplayMostNotifiedEmail() {
        return db.FindMostNotifedEmail();
    }

    public Map.Entry<String, Integer> DisplayMostNotifiedPhone() {
        return db.FindMostNotifedPhoneNums();
    }

    public Map.Entry<String, Integer> DisplayMostSentTemplate() {
        return db.FindMostSentTemplate();
    }

    public List<Notification> DisplayAllNotifications() {
        return db.NotificationList;
    }

}
