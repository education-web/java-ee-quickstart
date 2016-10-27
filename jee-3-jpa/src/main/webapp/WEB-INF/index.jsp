<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<title>Comics</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
      integrity="sha384-2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
<div class="container">
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <td>Title</td>
                <td>Publisher</td>
                <td>Number</td>
                <td>Url</td>
                <td>Price</td>
                <td>Online price</td>
                <td>Superheroes</td>
            </tr>
            <tbody>
            <c:forEach var="comics" items="${requestScope.comicsList}">
            <tr>
                <td><c:out value="${comics.title}"/></td>
                <td><c:out value="${comics.publisher}"/></td>
                <td><c:out value="${comics.number}"/></td>
                <td>
                    <c:if test="${not empty comics.onlineComics}">
                        <c:out value="${comics.onlineComics.url}"/>
                    </c:if>
                </td>
                <td><c:out value="${comics.price}"/></td>
                <td>
                    <c:if test="${not empty comics.onlineComics}">
                        <c:out value="${comics.onlineComics.price}"/>
                    </c:if>
                </td>
                <td><c:out value="${comics.superHeroes}"/></td>
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