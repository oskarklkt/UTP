W pliku allwords.txt, znajdującym się w katalogu  {user.home} zapisane są (rozdzielone białymi znakami) słowa.
Znaleźć wszystkie anagramy, które można utworzyć z  tych słów i wypisac je jako listy słów na konsoli w porządku liczby anagramów.
Przy takiej samej liczbie anagramów listy wypisywane są w porządku alfabetycznym pierwszego słowa na liście.

Dla realizacji tego zadania w klasie Anagrams utworzyć metodę getSortedByAnQty(), która zwraca listę list słów będacych anagramami, uporządkowaną wedle podanych wyżej kryteriów.
W klasie tej dostarczyć także metody String getAnagramsFor(String word), która zwraca napis, przedstwiający listę anagramów dla podanego słowa w postaci:

słowo: [ anagram1, anagram2, ... , anagramN]

Jeśli  słowo nie ma nagramow lista jest pusta (po dwukropku mamy [] ). Jesli podanego słowa nie ma w pliku allwords.txt to po dwukropku powinnien znaleźć się napis null.

Słowa dla których będziemy szukać anagramów, wczytywane są z pliku o nazwie {user.home}/wordsToFind.

Rozwiązanie zadania ułatwi klasa Main, utworzona przez generator projektów. Ma ona następującą postać (nie wolno jej zmieniać):

import java.io.*;
import java.util.*;

public class Main {

public static void main(String[] args) throws FileNotFoundException {
String home = System.getProperty("user.home");
String allWords = home + "/allwords.txt";
Anagrams an = new Anagrams(allWords);
for(List<String> wlist : an.getSortedByAnQty()) {
System.out.println(wlist);
}
System.out.println("************************");
Scanner scan = new Scanner(new File(home, "wordsToFind.txt"));
while(scan.hasNext()) {
System.out.println(an.getAnagramsFor(scan.next()));
}
scan.close();
}

}

Przykładowo, jeśli plik allwords.txt zawiera  słowa:

andes danes deans evil gals lags levi live sedan
slag streets testers uprising veil vile

a plik wordsToFind słowa:
evil streets uprising

- to program (zaczynający wykonanie od obowiązkowej klasy Main) powinien wyprowadzić następującą informację

[evil, levi, live, veil, vile]
[andes, danes, deans, sedan]
[gals, lags, slag]
[streets, testers]
[uprising]
************************
evil: [levi, live, veil, vile]
streets: [testers]
uprising: []

Uwaga: programy nie dające pokazanej formy wydruku otrzymują 0 punktów.

Uwaga: nazwy i umiejscowienie plików są obowiązkowe. Niespełnienie tego warunku skutkuje brakiem punktów.

Utworzona przez generator projektów klasa Main zawiera fragment pomocny dla uzyskania wymaganej nazwy pliku.

Uwaga: aby dowiedzieć się który  katalog jest  {user.home} i umieścić w nim pliki testowe można z poziomu Javy użyć:
System.getProperty("user.home");
Np. jeśli identyfikatorem użytkownika jest Janek, to w Windows 7 katalog {user.home} to C:\Users\Janek.

Należy samodzielnie utworzyć testowe pliki i umieścić je w katalogu {user.home}

