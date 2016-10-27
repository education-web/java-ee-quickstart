<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.kpi.ip31.jee.gunawardana.ComicsStoreServiceLocator" %>
<!DOCTYPE html>
<title>Comics</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
      integrity="sha384-2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
<div class="container">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <td>id</td>
                <td>title</td>
                <td>publisher</td>
                <td>author</td>
                <td>number</td>
                <td>price</td>
            </tr>
            <tbody>
            <c:forEach var="comics" items="${ComicsStoreServiceLocator.getComicsRepository().findAll()}">
            <tr>
                <td><c:out value="${comics.id}"/></td>
                <td><c:out value="${comics.title}"/></td>
                <td><c:out value="${comics.publisher}"/></td>
                <td><c:out value="${comics.author}"/></td>
                <td><c:out value="${comics.number}"/></td>
                <td><c:out value="${comics.price}"/></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <button type="submit" class="btn btn-primary"
            onclick="location.href = '${pageContext.request.contextPath}/new-comics.jsp'">
        Add comics
    </button>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"
        integrity="sha384-VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU"
        crossorigin="anonymous"></script>