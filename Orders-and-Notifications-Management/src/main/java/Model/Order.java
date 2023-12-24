package Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Order {

    protected int OrderID;
    protected double TotalPrice;
    protected Shipping shipping;
    protected List<Product> Products;

    public abstract double CalculateTotalPrice();

}
