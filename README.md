# EMedia

## Krótki opis
Emedia aplikacja napisana w java pozwalająca szyfrować dane obrazka jpg w celu zabezpieczenia przed ujrzeniem zawartości przez osoby nieupoważnione. Do szyfryzacj został użyty XOR z kluczem 255, a także w celu większego zabezpieczenia zastosowano kodowanie klucza XOR za pomocą RSA dwoma 128 bit lub 8 bit liczbami pierwszymi.

### O Metadanych w jpg
JPG wykorzystuje standard metadanych Exif dla plików z obrazkami. Metaznaczniki Exif opisują m.in.:

- nazwę aparatu, którym wykonano zdjęcie
- ustawienia aparatu, takie jak czas naświetlenia, wartość przysłony, czułość matrycy w ISO czy ogniskowa obiektywu oraz położenie aparatu (pionowe/poziome)
- datę wykonania zdjęcia oraz przetworzenia na postać cyfrową
- informację o prawach autorskich (przeważnie dodawaną w post-processingu, niewiele aparatów dodaje ją automatycznie)
- miniaturkę obrazka
- rozdzielczość w pikselach
- sposób pomiaru światła przez aparat
- współrzędne GPS miejsca wykonania zdjęcia


## Kamienie milowe

| NR | Deadline |                      Kamienie milowe                       |
| :---------: | :------: | :--------------------------------------------------: |
|      1      |  26.03   |     repozytorium na github, plan      |
|      2      |  02.04   |   Implementacja Algorytmu RSA   |
|      3      |  16.04   |       Metadane       |
|      6      |  23.04   |       Generator liczb pierwszy klasyczny      |
|      5      |  30.04   |          XOR - szyfowanie danych obrazka        |
|      7      |  07.05   | Generator liczb pierwszy Sieve of Atkin  |
|      7      |  21.05   | Menu |
|      8      |  11.06   |    Metoda Miller Rabin     |
|      9      |  15.06   |                    Podsumowanie, dokuemtowanie                  |

## Budowane z

- [VISUAL CODE](https://code.visualstudio.com/)
