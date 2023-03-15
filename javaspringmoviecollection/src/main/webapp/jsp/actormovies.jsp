<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Labrada">

<!--  JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	
</head>
<body>

	<div class="container-md mt-5 p-5">
        <div class="text-center mt-3 mb-3 actor-preview">
        <img src="../${image}" style="width:250px;height:350px" class="img-actor"/>
            <h1>${name}</h1>
            <div class="actor-info">
                <div class="m-3">
                    <span class="d-block fs-4">อายุ</span>
                    <span> ${age}</span>
                </div>
                <div class="m-3">
                    <span class="d-block fs-4">เพศ</span>
                    <span> ${gender} </span>
                </div>
             <div class="m-3">
                    <span class="d-block fs-4">วันเดือนปีเกิด</span>
                    <span> ${birthday} </span>
                </div>
               
            </div>
        </div>
  		<div class="my-3 text-center">
            <h3>ผลงานที่แสดง</h3>
        </div>
        <div class="movie-act">
            <div class="moviecard">
                <div class="movcard">
                
                <c:forEach items="${actmovlist}" var="actmov">
                	<a href="../movie_detail/${actmov.movies_id}" class="mov-pic">
                        <img src="../${actmov.posterimage}" class="img-mov" alt="...">
                        <div class="movie-name">
                            <p class="moviename-text">${actmov.moviename}</p>
                        </div>
                        <p class="movtype"><b>ประเภท:</b> ${actmov.category}</p>
                    </a>
                </c:forEach>
                    
        
                </div>
            </div>
        </div>
    </div>
<script src="
https://cdn.jsdelivr.net/npm/vanilla-tilt@1.8.0/dist/vanilla-tilt.min.js
"></script>
<script type="text/javascript">
let eventBox = document.querySelector(".img-actor");

VanillaTilt.init(eventBox);

</script>
</body>
</html>