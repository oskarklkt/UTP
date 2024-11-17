/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package UTP1.EX2;


import java.util.Objects;

public class Purchase {
    private final String id;
    private final String name;
    private final String productName;
    private final Double productPrice;
    private final Double amount;

    public Purchase(String id, String name, String productName, Double productPrice, Double amount) {
        this.id = id;
        this.name = name;
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getTotalCost() {
        return productPrice * amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;

        return Objects.equals(id, purchase.id)
                && Objects.equals(name, purchase.name)
                && Objects.equals(productName, purchase.productName)
                && Objects.equals(productPrice, purchase.productPrice)
                && Objects.equals(amount, purchase.amount);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(productName);
        result = 31 * result + Objects.hashCode(productPrice);
        result = 31 * result + Objects.hashCode(amount);
        return result;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + productName + ";" + productPrice + ";" + amount;
    }

    public void showWithCosts() {
        System.out.println(this + " " + "(koszt: " + String.format("%.1f", getTotalCost()) + ")");
    }
}