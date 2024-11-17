/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package UTP2.EX2;


import java.beans.*;
import java.io.Serializable;

public class Purchase implements Serializable {

    private final PropertyChangeSupport propertyChangeSupport;
    private final VetoableChangeSupport vetoableChangeSupport;
    private String prod;
    private String data;
    private Double price;

    public Purchase() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        vetoableChangeSupport = new VetoableChangeSupport(this);
    }

    public Purchase(String prod, String data, Double price) {
        this();
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }

    public String getProd() {
        return prod;
    }

    public synchronized void setProd(String prod) {
        this.prod = prod;
    }

    public String getData() {
        return data;
    }

    public synchronized void setData(String data) {
        String oldData = this.data;
        propertyChangeSupport.firePropertyChange("data", oldData, data);
        this.data = data;
    }

    public Double getPrice() {
        return price;
    }

    public synchronized void setPrice(Double price) throws PropertyVetoException {
        Double oldPrice = this.price;
        vetoableChangeSupport.fireVetoableChange("price", oldPrice, price);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchase [" +
                "prod=" + prod +
                ", data=" + data +
                ", price=" + price + ']';
    }
}
