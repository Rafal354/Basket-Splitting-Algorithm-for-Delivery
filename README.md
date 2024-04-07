# Algorytm optymalnego podziału koszyka na grupy dostaw w sklepie internetowym

## Opis

Biblioteka ta implementuje algorytm optymalnego podziału przedmiotów w koszyku klienta na grupy dostaw w internetowym 
supermarkecie. Podział ten ma na celu zminimalizowanie liczby wymaganych dostaw, a w drugiej kolejności sprawić, żeby
największa grupa zawierała możliwie najwięcej produktów.

## Założenia

Biblioteka ma dzielić produkty w koszyku na grupy dostaw, opierając się na:

* Liście produktów w koszyku 

* Pliku konfiguracyjnym zawierającym dostępne sposoby dostawy dla każdego produktu

## Problem

1. Podział produktów w koszyku na jak najmniejszą liczbę dostaw.
2. Największa grupa ma zawierać możliwie najwięcej produktów.

## Przykład

### Dane wejściowe:

1. Lista produktów:

``` javascript
[
    "Steak (300g)",
    "Carrots (1kg)",
    "Soda (24x330ml)",
    "AA Battery (4 Pcs.)",
    "Espresso Machine",
    "Garden Chair"
]
```

2. Plik konfiguracyjny:

``` javascript
{
    "Carrots (1kg)": ["Express Delivery", "Click&Collect"],
    "Cold Beer (330ml)": ["Express Delivery"],
    "Steak (300g)": ["Express Delivery", "Click&Collect"],
    "AA Battery (4 Pcs.)": ["Express Delivery", "Courier"],
    "Espresso Machine": ["Courier", "Click&Collect"],
    "Garden Chair": ["Courier"]
}
```

## Spodziewany wynik

Algorytm w wyniku ma zwrócić podział produktów na grupy dostawy jako mapę. Jej kluczem
ma być sposób dostawy, a wartością lista produktów:

``` javascript
{
    "Express Delivery": ["Steak (300g)", "Carrot (1kg)", "Cold Beer
    (330ml)", "AA Battery (4 Pcs.)"],
    "Courier": ["Espresso Machine", "Garden Chair"]
}
```

## Ograniczenia

* Maksymalnie 1000 produktów w katalogu
* Do 10 różnych sposobów dostawy
* Maksymalnie 100 produktów w koszyku

## Opis algorytmu

### Wpływ ograniczeń na kształt algorytmu:

Obecny algorytm został w dużej mierze ukształtowany przez wprowadzone ograniczenia, szczególnie przez limit maksymalnej 
liczby sposobów dostawy. To ograniczenie umożliwia wygenerowanie i sprawdzenie wszystkich kombinacji dostawców, których 
liczba nie przekracza ```2^10``` (czyli 1024).

### Kluczowe etapy algorytmu:

1. **Generowanie i sortowanie podzbiorów:**
    - Generowane są wszystkie podzbiory dostępnych sposobów dostaw.
    - Następnie podzbiory te są sortowane według rozmiaru, zaczynając od najmniejszych.

2. **Sprawdzanie możliwości dostarczenia:**
    - Dla każdego podzbioru, iterując od najmniejszych, sprawdzana jest możliwość dostarczenia wszystkich produktów z 
   koszyka.

3. **Określanie liczby dostaw:**
    - Jeśli dostarczenie wszystkich produktów jest możliwe, oznacza to, że liczba dostaw będzie równa rozmiarowi 
   znalezionego podzbioru.

4. **Wyszukiwanie najliczniejszych grup:**
    - Dla każdego podzbioru sposobów dostaw, który umożliwia dostarczenie wszystkich produktów, algorytm szuka 
   najliczniejszej grupy produktów do dostarczenia w ramach jednej dostawy.

5. **Zakończenie przetwarzania:**
    - Po znalezieniu jakiegokolwiek rozwiązania algorytm nie przeszukuje rozwiązań dla większej liczby elementów 
   podzbioru, a tylko kończy eksplorować podzbiory o rozmiarze znalezionego rozwiązania w celu zmaksymalizowania
   najliczniejszej grupy.

6. **Odtwarzanie optymalnego rozwiązania:**
    - Po zakończeniu przeszukiwania algorytm tworzy wynikową mapę, korzystając z zapisanych informacji o najlepszym 
   dostawcy oraz podzbiorze, w którym się on znajduje.

## Przykładowe użycie biblioteki

``` java
  // Provide the absolute path to the configuration file
  String absolutePathToConfigFile = "path/to/config.json";

  // Create an instance of BasketSplitter
  BasketSplitter basketSplitter = new BasketSplitter(absolutePathToConfigFile);
  
  // Create a list of items to be split
  List<String> items = List.of(
       "Steak (300g)",
       "Carrots (1kg)",
       "Soda (24x330ml)",
       "AA Battery (4 Pcs.)",
       "Espresso Machine",
       "Garden Chair"
  );
  /*
      IMPORTANT: config.json have to contains all products in the basket - items list
   */
  
  // Split the items based on delivery methods
  Map<String, List<String>> deliveryProductMap = basketSplitter.split(items);
  
  // Print the results
  for (Map.Entry<String, List<String>> entry : deliveryProductMap.entrySet()) {
      String deliveryMethod = entry.getKey();
      List<String> products = entry.getValue();
      System.out.println("Delivery Method: " + deliveryMethod);
      System.out.println("Products: " + products);
      System.out.println();
  }
```

## Stos technologiczny

### Język: Java

### Narzędzie do budowania: Maven

### Budowanie:

1. Zainstaluj JDK i Maven.
2. Przejdź do katalogu projektu.
3. Uruchom `mvn clean package`.

### Importowanie biblioteki:

- Dodaj plik JAR do ścieżki klas.
- Użyj instrukcji import języka Java.

### Wykorzystane zewnętrzne biblioteki:

- **jackson**: przetwarzanie plików JSON
- **lombok**: czytelność kodu kodu
- **junit/junit-jupiter**: testowanie

### Licencja:

- Licencja MIT.

## Autorzy

Rafał Tekielski
