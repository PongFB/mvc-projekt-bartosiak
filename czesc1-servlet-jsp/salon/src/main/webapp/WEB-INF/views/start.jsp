<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Widok formularza zapytania o jazdę próbną obsługiwany przez StartServlet --%>
<%@ include file="/WEB-INF/views/fragments/header.jspf" %>

<h2>Zapytanie o jazdę próbną</h2>
<p>${requestScope.statusMessage}</p>

<form action="${pageContext.request.contextPath}/start" method="post">
    <label>Twoje imię
        <input type="text" name="clientName" value="${param.clientName}" required>
    </label>
    <label>Interesujący Cię model auta
        <input type="text" name="carModel" value="${param.carModel}" placeholder="np. Ferrari 296 GTB" required>
    </label>
    <button type="submit">Wyślij zgłoszenie</button>
</form>

<%@ include file="/WEB-INF/views/fragments/footer.jspf" %>