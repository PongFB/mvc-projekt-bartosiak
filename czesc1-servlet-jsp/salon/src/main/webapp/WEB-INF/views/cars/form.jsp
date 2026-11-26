<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Formularz dodawania samochodu z wyborem marki przez JSTL c:forEach --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Dodaj samochód do oferty</h2>

<form method="post" action="${pageContext.request.contextPath}/cars/add">
    <label>Marka
        <select name="brandId">
            <option value="">-- wybierz markę --</option>
            <c:forEach items="${brands}" var="b">
                <option value="${b.id}" ${param.brandId == b.id ? 'selected' : ''}>${b.name} (${b.country})</option>
            </c:forEach>
        </select>
    </label>
    <span class="error">${errors.brandId}</span>

    <label>Model
        <input type="text" name="model" value="${param.model}" placeholder="np. 296 GTB">
    </label>
    <span class="error">${errors.model}</span>

    <label>Rok produkcji
        <input type="text" name="year" value="${param.year}" placeholder="np. 2024">
    </label>
    <span class="error">${errors.year}</span>

    <label>Cena (PLN)
        <input type="text" name="price" value="${param.price}" placeholder="np. 1450000">
    </label>
    <span class="error">${errors.price}</span>

    <button type="submit">Zapisz samochód</button>
</form>

<p><a href="${pageContext.request.contextPath}/cars">&larr; Powrót do listy samochodów</a></p>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>