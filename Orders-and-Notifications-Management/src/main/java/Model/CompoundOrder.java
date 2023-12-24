package Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CompoundOrder extends Order {
    private String Location;
    private Order O;

    // CalculateTotalPrice(): double
    // addOrder(Order O): Void
    // removeOrder(Order O): Void
    // GetChild(OrderID: int): Order

}
