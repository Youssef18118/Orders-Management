package com.fci.sw2.Orders.and.Notifications.Management.Common;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;

public class TempCustomer {

    static Customer TempCustomer;

    static Customer getTempCustomer() {
        return TempCustomer;
    }

}
