# Prosjektet
Prosjektet inneholder en påbegynt applikasjon for et REST API for brukere.
Applikasjonen er delt inn i tre lag: REST Controller -> Service -> DAO.


## Flyway
Flyway sørger for automatisk databasemigrering ved oppstart av applikasjonen. 
Migreringsscriptene ligger under `src/main/resources/db/migration`.

Ved oppstart av applikasjonen er det allerede lagret noen brukere.

## H2 Web Console
For å enkelt se hvilke data som ligger i databasen kan H2 Web Console benyttes.
Web consolet finner du på adressen `http://localhost:8080/h2` når applikasjonen kjører.

JDBC URL: `jdbc:h2:./target/users`

Test f.eks. spørringen `SELECT * FROM USERS`

# Oppgavene

## Del 1
REST-endepunkt for å liste alle brukere i databasen.

Hint: Service og DAO skal automatisk registreres som Spring-bønner vha component scan.

1. Implementer metoden `mapRow` i klassen `UserRowMapper`
2. Implementer metoden `getAll()` i `UserDaoImpl`
3. Implementer metoden `getAll()` i `UserServiceImpl`
4. Implementer et endepunkt i `UserRestController` som kaller service og svarer med alle brukere.

```
GET http://localhost:8080/users
```

For å verifisere at implementasjonen er korrekt for del 1:
```
mvn test -Doppgave=del1
```

Test endepunktet i Postman.

## Del 2
1. Implementer metoden `getById(long)` i klassen `UserDaoImpl`
2. Implementer metoden `getById(long)` i `UserServiceImpl`
3. Implementer et endepunkt i `UserRestController` som kaller service og svarer med en bruker gitt en ID.

```
GET http://localhost:8080/user/<id>
```

For å verifisere at implementasjonen er korrekt for del 2:
```
mvn test -Doppgave=del2
```

Test endepunktet i Postman.

## Del 3
1. Implementer metoden `insert(user)` i klassen `UserDaoImpl`
2. Implementer metoden `insert(user)` i `UserServiceImpl`
3. Implementer et endepunkt i `UserRestController` som lagrer en ny bruker. Endepunktet skal returnere brukeren med tildelt ID.

```
POST http://localhost:8080/user
```

For å verifisere at implementasjonen er korrekt for del 3:
```
mvn test -Doppgave=del3
```

Test endepunktet i Postman og sjekk at den nye brukeren også returneres vha et av de andre endepunktene.
