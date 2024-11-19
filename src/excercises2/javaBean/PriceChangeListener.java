/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package excercises2.javaBean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class PriceChangeListener implements VetoableChangeListener {
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ((double) evt.getNewValue() < 1000.0) {
            throw new PropertyVetoException("Price change to: " + evt.getNewValue() + " not allowed", evt);
        } else {
            System.out.println("Change value of: " + evt.getPropertyName() + " from: " + evt.getOldValue() +
                    " to: " + evt.getNewValue());
        }
    }
}