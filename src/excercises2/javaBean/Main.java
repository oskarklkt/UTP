/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package excercises2.javaBean;


import java.beans.PropertyVetoException;

public class Main {
    public static void main(String[] args) {
        Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
        System.out.println(purch);

        purch.addVetoableChangeListener(new PriceChangeListener());
        purch.addPropertyChangeListener(new DataChangeListener());

        try {
            purch.setData("w promocji");
            purch.setPrice(2000.00);
            System.out.println(purch);

            purch.setPrice(500.00);

        } catch (PropertyVetoException exc) {
            System.out.println(exc.getMessage());
        }
        System.out.println(purch);
    }
}
