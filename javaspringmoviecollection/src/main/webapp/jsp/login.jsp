 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Admin</title>
	<jsp:include page="header_admin.jsp"/>
</head>
<body>
<div class="container p-5 my-5  border">
  <h2>Login</h2>
  <form action="/admin/login" method="POST">
    <div class="mb-3 mt-3">
      <label for="email">Username:</label>
      <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" value="admin">
    </div>
    <div class="mb-3">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"  value="admin1234">
    </div>
  <!--   <div class="form-check mb-3">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div> -->
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
<!--   <img src="../img/jaae.png" width="500" height="600"/> -->
</div>
</body>
</html>