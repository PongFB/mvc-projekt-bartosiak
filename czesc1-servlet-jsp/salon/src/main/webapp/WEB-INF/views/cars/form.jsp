<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Formularz dodawania samochodu: wartości z param i błędy walidacji z requestScope.errors --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Dodaj samochód do oferty</h2>

<form method="post" action="${pageContext.request.contextPath}/cars/add">
    <label>Marka
        <input type="text" name="brand" value="${param.brand}" placeholder="np. Ferrari">
    </label>
    <span class="error">${requestScope.errors.brand}</span>

    <label>Model
        <input type="text" name="model" value="${param.model}" placeholder="np. 296 GTB">
    </label>
    <span class="error">${requestScope.errors.model}</span>

    <label>Rok produkcji
        <input type="text" name="year" value="${param.year}" placeholder="np. 2024">
    </label>
    <span class="error">${requestScope.errors.year}</span>

    <label>Cena (PLN)
        <input type="text" name="price" value="${param.price}" placeholder="np. 1450000">
    </label>
    <span class="error">${requestScope.errors.price}</span>

    <button type="submit">Zapisz samochód</button>
</form>

<p>Liczba błędów walidacji: <strong>${empty requestScope.errors ? 0 : requestScope.errors.size()}</strong></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>