 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Login Admin</title>
	<jsp:include page="header_admin.jsp"/>
</head>
<body>

 <div class="container-md mt-5">
        <div class="card login-box shadow-lg">
            <div class="card-body">
                <h1>เข้าสู่ระบบ</h1>
                <form action="/admin/login" method="POST">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">ชื่อผู้ใช้</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" >
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">รหัสผ่าน</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"  >
                    </div>
                    <button type="submit" class="submit-btn" style="width: 100%;">ยืนยัน</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>