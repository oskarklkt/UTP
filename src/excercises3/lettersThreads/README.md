Uruchamianie kodów w wątkach i kończenie wątków
Zbudować klasę Letters, która posłuży do równoleglego uruchamiania kodów, wypisujących co sekundę litery podane w napisie przekazanym do konstruktora klasy.
Po stworzeniu obiektu klasy Letters w metodzie main(...) klasy Main wystartować wszystkie wątki, w których mają być wypisywane podane litery.
Po wstrzymaniu działania metody main(...) na 5 sekund - zakończyć działanie wszystkich kodów, wypisujących litery.

Uruchomienie poniższego programu:
public class Main {

      public static void main(String[] args) throws InterruptedException {
        Letters letters = new Letters("ABCD");
        for (Thread t : letters.getThreads()) System.out.println(t.getName());
        /*<- tu uruchomić 
             wszystkie kody w wątkach 
         */

        Thread.sleep(5000);

        /*<- tu trzeba zapisać
           fragment, który kończy działanie kodów, wypisujących litery 
        */
        System.out.println("\nProgram skończył działanie");
      }

    }
powinno (w tej postaci) wypisać:
Thread A
Thread B
Thread C
Thread D

(po 5 sekundach)
Program skończył działanie

Natomiast po uzupełnieniu kodu w miejscah wskazanych przez /*<-   */ - coś w rodzaju:

Thread A
Thread B
Thread C
Thread D
ACDBDBACACDBCBDA
Program skończył działanie.

Uwaga 1: modyfikacje klasy Main są dopuszczalne tylko w miejscach wskazanych przez /*<- */
Uwaga 2: nie wolno stosować System.exit
Uwaga 3: warto przy definiowaniu metody run() zastosować lambda-wyrażenie
Uwaga 4. W tyn zadania można nie stosować (a nawet nie należy stosować) ExecutorService
