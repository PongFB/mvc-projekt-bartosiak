<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Widok szczegółowy pojedynczego samochodu --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>${car.brand.name} ${car.model}</h2>

<ul>
    <li><strong>ID oferty:</strong> ${car.id}</li>
    <li><strong>Marka:</strong> ${car.brand.name} (${car.brand.country})</li>
    <li><strong>Model:</strong> ${car.model}</li>
    <li><strong>Rok produkcji:</strong> ${car.year}</li>
    <li><strong>Cena:</strong> ${car.price} PLN</li>
    <li><strong>Status:</strong> ${car.available ? 'Dostępny w salonie' : 'Sprzedany'}</li>
</ul>

<p><a href="${pageContext.request.contextPath}/cars">&larr; Powrót do listy samochodów</a></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>