package Model;

public class SimpleOrder extends Order {

    public double CalculateTotalPrice() {
        for (int i = 0; i < Products.size(); i++) {
            TotalPrice += Products.get(i).getPrice();
        }

        return TotalPrice;
    }

}
