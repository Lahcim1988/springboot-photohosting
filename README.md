# springboot-photohosting

1. Projekt

Hosting zdjęć. 

Aplikacja posiada dwa rodzaje użytkowników, których przechowuje w bazie danych – animistrator, oraz user. 
Po zalogowaniu administrator ma możliwość dodawanie zdjęć. 
Zalogowany użytkownik przegląda dostępne galerie. Użytkownik niezalogowany ma dostępną formatkę logowania.

2. Przygotowanie

Projekt został stworzony z wykorzystaniem Spring Boot. Zależności dodane do projektu:

- Spring Web Starter – umożliwa towrzenie aplikacji webowej
- Vaadin – biblioteka do tworzenia GUI
- Spring Security – zestaw narzędzi umożliwiających na tworzenie zabezpieczeń aplikacji przed niepowołanym dostępem
- Spring Data JPA – zestaw narzędzi pozwalających na komunikowanie się z relacyjną bazą danych
- MySQL Driver – sterownik umożliwiający na łączenie się z bazą danych MySQL
