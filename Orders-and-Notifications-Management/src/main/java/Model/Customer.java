package Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Customer {
    private int customerId;
    private String name;
    private String Email;
    private String Password;
    private double Balance;
    private String Location;
    private List<Notification> NotificationList;

}
