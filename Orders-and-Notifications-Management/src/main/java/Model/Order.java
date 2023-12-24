package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Order {

    private int OrderID;
    private double TotalPrice;
    private Shipping shipping;

    // CalculateTotalPrice(): double
    // addOrder(Order O): Void
    // removeOrder(Order O): Void
    // GetChild(OrderID: int): Order
}
