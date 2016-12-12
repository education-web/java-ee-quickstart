<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<title>Comics</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css"
      integrity="sha384-2hfp1SzUoho7/TsGGGDaFdsuuDL0LX2hnUp6VkX3CUQ2K4K+xjboZdsXyp4oUHZj" crossorigin="anonymous">
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/comics">
        <div class="form-group row">
            <label for="title" class="col-xs-2 col-form-label">Comics title</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" id="title" name="title" placeholder="Enter title">
            </div>
        </div>
        <div class="form-group row">
            <label for="publisher" class="col-xs-2 col-form-label">Comics publisher</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" id="publisher" name="publisher" placeholder="Enter publisher">
            </div>
        </div>
        <div class="form-group row">
            <label for="author" class="col-xs-2 col-form-label">Comics author</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" id="author" name="author" placeholder="Enter author">
            </div>
        </div>
        <div class="form-group row">
            <label for="number" class="col-xs-2 col-form-label">Comics number</label>
            <div class="col-xs-10">
                <input type="number" min="0"
                       class="form-control" id="number" name="number" placeholder="Enter number">
            </div>
        </div>
        <div class="form-group row">
            <label for="price" class="col-xs-2 col-form-label">Comics price</label>
            <div class="col-xs-10">
                <input type="number" min="0" step="0.01"
                       class="form-control" id="price" name="price" placeholder="Enter price">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/js/bootstrap.min.js"
        integrity="sha384-VjEeINv9OSwtWFLAtmc4JCtEJXXBub00gtSnszmspDLCtC0I4z4nqz7rEFbIZLLU"
        crossorigin="anonymous"></script>
