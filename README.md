**Converter website using spring boot**
**backEnd**
main dependencies:<br>
 -JPA(hibernate)<br>
 -Thymeleaf <br>
 -PostgreSQL<br>
 **frontEnd**<br>
 -javaScript(ajax)<br>

**run process**
after spring boot context gets ready *Listener* gets xml data from *http://www.cbr.ru/scripts/XML_daily.asp* and saves to database(if data is NOT same by date).
converter.html sends request (using ajax) to **/api/calculate**,
and the server returns calculated result.<br>
*Future features*<br>
-security<br>
-history of converts<br>
