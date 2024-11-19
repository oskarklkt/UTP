/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package excercises1.customers;


public class Main {

    public static void main(String[] args) {
        CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
        String fname = System.getProperty("user.home") + "/customers.txt";
        cpsf.readFile(fname);
        cpsf.showSortedBy("Nazwiska");
        cpsf.showSortedBy("Koszty");

        String[] custSearch = { "c00001", "c00002" };

        for (String id : custSearch) {
            cpsf.showPurchaseFor(id);
        }
    }

}
