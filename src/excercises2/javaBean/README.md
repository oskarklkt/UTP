Zdefiniować klasę JavaBean o nazwie  Purchase z trzema właściwościami: prod (String), data (typu String) i price (typu Double).
Własciwość prod jest prosta, właściwości data i price są związane (bounded), włąsciwośc price jest dodatkowo ograniczane (constrained).
Za pomoca mechanizmu nasłuchu  i wetowania zmian właściwości umożliwić:
wypisywanie na konsoli wszystkich zmian dat i cen,
kontrolę poprawności zmian: nie można zmienić ceny na liczbę mniejszą od 1000.
Przykładowy program (klasa Main utworzona przez generatir projektów):

public class Main {
public static void main(String[] args) {

    Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
    System.out.println(purch);
    
    // --- tu należy dodać odpowiedni kod    
    // ...     
    // ----------------------------------
    
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
winien wypisać na konsoli:
Purchase [prod=komputer, data=nie ma promocji, price=3000.0]
Change value of: data from: nie ma promocji to: w promocji
Change value of: price from: 3000.0 to: 2000.0
Purchase [prod=komputer, data=w promocji, price=2000.0]
Price change to: 500.0 not allowed
Purchase [prod=komputer, data=w promocji, price=2000.0]

