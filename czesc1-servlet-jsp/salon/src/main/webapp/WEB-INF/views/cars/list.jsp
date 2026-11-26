<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Widok listy samochodów z pętlą c:forEach z biblioteki JSTL --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Oferta samochodów</h2>

<table>
    <thead>
    <tr>
        <th>Marka</th>
        <th>Model</th>
        <th>Rok</th>
        <th>Cena (PLN)</th>
        <th>Status</th>
        <th>Akcje</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.brand.name}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
            <td>${car.available ? 'Dostępny' : 'Sprzedany'}</td>
            <td><a href="${pageContext.request.contextPath}/cars/details?id=${car.id}">Szczegóły</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="${pageContext.request.contextPath}/cars/add">Dodaj nowy samochód</a></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>