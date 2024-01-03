package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.NotificationBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Bsl.OrderBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Cart;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Category;
import com.fci.sw2.Orders.and.Notifications.Management.Model.CompoundOrder;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Notification;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Shipping;
import com.fci.sw2.Orders.and.Notifications.Management.Model.SimpleOrder;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Template;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/Notification")
public class NorificationController {
    @Autowired
    private NotificationBsl NBsl;

    @GetMapping("/DisplayAllNotifications")
    public List<Notification> DisplayAllNotifications() {
        return NBsl.DisplayAllNotifications();
    }

    @GetMapping("/DisplayMostNotifiedEmail")
    public Map.Entry<String, Integer> DisplayMostNotifiedEmail() {
        return NBsl.DisplayMostNotifiedEmail();
    }

    @GetMapping("/DisplayMostNotifiedPhone")
    public Map.Entry<String, Integer> DisplayMostNotifiedPhone() {
        return NBsl.DisplayMostNotifiedPhone();
    }

    @GetMapping("/DisplayMostSentTemplate")
    public Map.Entry<String, Integer> DisplayMostSentTemplate() {
        return NBsl.DisplayMostSentTemplate();
    }

}
