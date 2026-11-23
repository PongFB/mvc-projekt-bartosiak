<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Strona startowa aplikacji wykorzystująca wspólny układ i wyrażenia EL --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Witaj w Salonie Samochodowym</h2>
<p>System zarządzania katalogiem pojazdów i ofertą sprzedaży w architekturze MVC.</p>

<h3>Informacje z obiektów predefiniowanych EL</h3>
<ul>
    <li>Twoja przeglądarka (<code>header</code>): ${header['User-Agent']}</li>
    <li>Kontekst aplikacji (<code>pageContext</code>): ${pageContext.request.contextPath}</li>
    <li>Ostatnio dodany samochód (<code>sessionScope</code>): ${empty sessionScope.lastCar ? 'brak w tej sesji' : sessionScope.lastCar}</li>
    <li>Przykład operatorów EL: 2 + 3 = ${2 + 3}, 10 &gt; 3 to ${10 gt 3}</li>
</ul>

<p><a href="${pageContext.request.contextPath}/cars/add">Przejdź do formularza dodawania samochodu &rarr;</a></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>