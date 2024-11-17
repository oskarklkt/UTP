/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package UTP2.EX2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DataChangeListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Change value of: " + evt.getPropertyName() + " from: " + evt.getOldValue() +
                " to: " + evt.getNewValue());
    }
}
