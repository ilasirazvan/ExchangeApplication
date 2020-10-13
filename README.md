# ExchangeApplication

Known problems for the project:

1) No Tests were included in the first version (Unit, Integration)
2) The REST calls that returns the JSON object for exchange rates accepts one path variable and one querry parameter. The problem is that the XML files generate like:
https://www.bnro.ro/nbrfxrates.xml are only for provided currencies (RON - EUR, RON - USD, RON - YEN). With the given xml you can't do a check EUR - USD.
  This can be addressed by creating a database model with all exchange rates.
3) For the caching part ( NOT IMPLEMENTED ) we can use an in-mem database that would contain the exchange rates. The rates will be filled in the table at the first call to the URL
  After all other calls on the REST endpoint will point to this table. This way the URL can be down.
4) Glassfish 5.1 and Tomcat 9.0.39 were used for testing the endpoints. Enpoints were tested from Browser as well as Postman.
5) Create a separate method to access https links that provide the XML files. Also be aware that we might need xjc to compute the Java classes from XML schemas.
6) One solution for caching is to use Guava from Google.
