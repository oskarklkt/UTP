/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package UTP1.EX2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {
    private final List<Purchase> purchases;
    private final static String FIELDS_SEPARATOR = ";";

    public CustomersPurchaseSortFind() {
        purchases = new ArrayList<>();
    }

    public void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FIELDS_SEPARATOR);
                String id = fields[0];
                String name = fields[1];
                String productName = fields[2];
                Double productPrice = Double.parseDouble(fields[3]);
                Double amount = Double.parseDouble(fields[4]);
                purchases.add(new Purchase(id, name, productName, productPrice, amount));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showPurchaseFor(String id) {
        System.out.println("Klient " + id);
        purchases.stream()
                .filter(purchase -> purchase.getId().equals(id))
                .forEach(System.out::println);
        System.out.println();
    }

    public void showSortedBy(String by) {
        if (by.equals("Koszty")) {
            sortByTotalCost();
        } else {
            sortByName();
        }
    }

    private void sortByName() {
        System.out.println("Nazwiska");
        purchases.stream()
                .sorted(Comparator.comparing(Purchase::getName).thenComparing(Purchase::getId))
                .forEach(System.out::println);
        System.out.println();
    }

    private void sortByTotalCost() {
        System.out.println("Koszty");
        purchases.stream()
                .sorted(Comparator.comparing(Purchase::getTotalCost).reversed().thenComparing(Purchase::getId))
                .forEach(Purchase::showWithCosts);
        System.out.println();
    }
}
