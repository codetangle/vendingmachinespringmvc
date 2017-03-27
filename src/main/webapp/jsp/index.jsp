<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <link href="${pageContext.request.contextPath}/css/style.css"
              rel="stylesheet">
    </head>
    <body>
        <div id="main-container" class="container">
            <div class="row">
            </div>
            <div class="row">
                <div class="col-md-12 center-block site-logo">
                    <img src="assets/tvend_logo.svg" class="img-responsive center-block">
                </div><!-- End col div -->
                <hr>
            </div> <!-- End row div -->
            <hr/>
            <ul class="list-group" id="error-messages"></ul>
            <!-- Template for the items displayed to the user -->
            <div id="item-template" style="display: none;">
                        
            </div>

            <div class="row">
                <div class="col-sm-8">
                    <div id="vending-options">
                        <c:forEach var="item" items="${items}">
                        <a href="?name=<c:out value="${item.name}" escapeXml="true"/>">
                            <div class="item-container col-md-4 col-sm-6">
                                <div class="panel panel-default">
                                    <div class="item panel-body">
                                        <div class="item-id col-md-12"></div>
                                        <div class="text-center">
                                            <div class="item-name col-md-12"><c:out value="${item.name}"/></div>
                                            <div class="item-price col-md-12"><c:out value="${item.price}"/></div>
                                            <div class="item-quantity col-md-12">Quantity: <c:out value="${item.quantity}"/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="col-md-12 text-center">
                        <h3>Total $ In</h3>
                    </div>
                    <div id="balance" class="col-md-8 col-md-offset-2 machine-input" style="border: 1px solid black;">${balance}</div>
                    <div class="col-md-6 center-block machine-button">
                        <a href="vendingMachine/addChange?amount=1">
                            <img id="add-dollar" src="assets/add_dollar_button.svg">
                        </a>
                    </div>
                    <div class="col-md-6 center-block machine-button">
                        <a href="vendingMachine/addChange?amount=0.25">
                            <img id="add-quarter" src="assets/add_quarter_button.svg">
                        </a>
                    </div>
                    <div class="col-md-6 center-block machine-button">
                        <a href="vendingMachine/addChange?amount=0.10">
                            <img id="add-dime" src="assets/add_dime_button.svg">
                        </a>
                    </div>
                    <div class="col-md-6 center-block machine-button">
                        <a href="vendingMachine/addChange?amount=0.05">
                            <img id="add-nickle" src="assets/add_nickle_button.svg">
                        </a>
                    </div>

                    <hr>

                    <div class="col-md-12 text-center">
                        <h3>Messages</h3>
                        <h3><c:out value="${webServiceMessage}"/></h3>
                        <div id="messages" class="col-md-8 col-md-offset-2 machine-input" style="border: 1px solid black;"><c:out value="${currentMessage}"/></div>
                        <div class="col-md-12 text-center machine-button">
                            <a href="vendingMachine/vendItem/<c:out value="${itemName}"/>"/>
                                <img id="make-purchase-button" src="assets/make_purchase_button.svg">
                            </a>
                        </div>
                    </div>
                    <hr>
                    <div class="col-md-12 text-center">
                        <h3>Change</h3>
                        <div id="change-box" class="col-md-8 col-md-offset-2 machine-input" style="border: 1px solid black;">
                            <marquee id="change">Collect: ${change.quarter} Quarters, ${change.dime} Dimes, ${change.nickel} Nickel, ${change.penny} Pennies</marquee>
                        </div>
                    </div>
                    <div class="col-md-12 text-center machine-button">
                        <a href="vendingMachine/getChange"/>
                            <img id="change-return-button" src="assets/get_change_button.svg">
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

