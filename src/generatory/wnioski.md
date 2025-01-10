# Generatory liczb losowych

## Wnioski:
Uzyskany rezultat to oszacowanie wartości liczby **pi** na podstawie teorii Cesaro. Im
większa liczba iteracji, tym wynik jest bliższy rzeczywistej wartości **pi ≈ 3.14159**.
Odchylenia od tej wartości wynikają z losowego charakteru generowania liczb oraz
ograniczonej liczby prób. Metoda ta bazuje na statystycznym przybliżeniu, dlatego przy
mniejszej liczbie iteracji wynik może być mniej dokładny. Aby uzyskać przybliżenie
wartości **pi** z dokładnością do dwóch miejsc po przecinku, potrzeba co najmniej: **100,000 - 1,000,000** iteracji.

Algorytm ten nie nadaje się do kryptografii, ponieważ używa **losowości statystycznej**, a
nie **kryptograficznej** (czyli silnej losowości trudnej do przewidzenia nawet przy
znajomości części sekwencji w porównaniu do losowości statystycznej). Kolejnym
powodem jest **efektywność** algorytmu świadcząca, że algorytm jest wolny i wymaga
dużej liczby iteracji dla przybliżenia wartości **pi**.

W Javie istnieje biblioteka mająca zastosowanie kryptograficzne znajdująca się w
pakiecie **java.security**. Klasa ta nazywa się SecureRandom i jest stosowana do
generowania kryptograficznie bezpiecznych liczb losowych używając silnika opierającego
się na systemowych źródłach entropii jak np. **/dev/random** na linuxie. Jest znacznie
bardziej losowa niż klasa **Random**. 