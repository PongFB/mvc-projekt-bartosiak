<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Strona potwierdzenia zapisu samochodu (wzorzec PRG) odczytująca atrybuty sesji --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Zapisano samochód</h2>

<p>Ostatnio dodany pojazd:
    <strong>${empty sessionScope.lastCar ? 'brak danych w sesji' : sessionScope.lastCar}</strong>
</p>

<p>W tej sesji dodano:
    <strong>${empty sessionScope.addedCount ? 0 : sessionScope.addedCount}</strong>
    ${sessionScope.addedCount == 1 ? 'samochód' : (sessionScope.addedCount >= 2 && sessionScope.addedCount <= 4 ? 'samochody' : 'samochodów')}.
</p>

<p><a href="${pageContext.request.contextPath}/cars/add">Dodaj kolejny samochód</a></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>