<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ua.kpi.ip31.jee.gunawardana.ComicsStoreServiceLocator" %>
<!DOCTYPE html>
<title>Comics</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css"
      integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">

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

<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"
        integrity="sha384-3ceskX3iaEnIogmQchP8opvBy3Mi7Ce34nWjpBIwVTHfGYWQS9jwHDVRnpKKHJg7"
        crossorigin="anonymous"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/tether/1.3.7/js/tether.min.js"
        integrity="sha384-XTs3FgkjiBgo8qjEjBk0tGmf3wPrWtA6coPfQDfFEY8AnYJwjalXCiosYRBIBZX8"
        crossorigin="anonymous"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"
        integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK"
        crossorigin="anonymous"></script>
