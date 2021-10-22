<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="page-header">
    <nav class="navbar navbar-expand-lg justify-content-between">
        <a href="/home" class="navbar-brand main-logo main-logo-smaller">
            Car Parts <span>Store</span>
        </a>
        <div class="d-flex justify-content-around">
            <h4 class="text-light mr-3">${loggedUser.name} ${loggedUser.surname}</h4>
            <div class="circle-div text-center"><i class="fas fa-user icon-user"></i></div>
        </div>
    </nav>
</header>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href="/welcome">
                    <span>Dashboard</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/shoppingCartDetails">
                    <span>Shopping Cart</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/orderHistory">
                    <span>Orders</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/accountInfo">
                    <span>Edit user</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">
                    <span>Logout</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </ul>