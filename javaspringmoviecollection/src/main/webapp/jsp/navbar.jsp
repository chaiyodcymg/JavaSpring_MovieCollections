<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-lg">
        <!-- Container wrapper -->
        <div class="container">
            <!-- Navbar brand -->
            <a href="/" class="navbar-brand me-5">
                <h1 style="font-family: 'Labrada';"><span>C</span>ollect<span>M</span>ovie</h1>
            </a>

            <!-- Toggle button -->
            <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
                data-mdb-target="#navbarButtonsExample" aria-controls="navbarButtonsExample" aria-expanded="false"
                aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Collapsible wrapper -->
            <div class="collapse navbar-collapse" id="navbarButtonsExample">
                <!-- Left links -->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">หน้าแรก</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="actorlist">นักแสดง</a>
                    </li>
                </ul>
                <!-- Left links -->
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success sub" type="submit">Search</button>
                </form>
                <div class="d-flex align-items-center">
                    <a href="admin/login" class="signin-btn">Login</a>
                </div>

            </div>
            <!-- Collapsible wrapper -->
        </div>
        <!-- Container wrapper -->
    </nav>
    <!-- Navbar -->