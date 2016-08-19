<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>GoodsDetail</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
        <link rel="stylesheet" href="css/index.css" type="text/css"></link>

        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
        <script type="text/javascript">
            function addOrder() {
                $.ajax({url: "servlet/AddOrder",
                    data: {id: $("#id").val(), number: $("#buyNumber").val()},
                    success: function(data) {
                        alert(data);
                        window.location.href = "MyIndex.html";
                    }

                });

            }
        </script>
    </head>

    <body>
        <div class="all">
            <h1 style="float: right; margin-right: 20px;" ><a href="index.jsp" style=" color: white;">Logout</a></h1>
        <h1 style="float: right;  margin-right: 50px;" ><a href="OrderList.jsp" style=" color: white;" target="_Blank">My Cart</a></h1>

            <div  class="mainbg"></div>
            <div style="background-image: url('${good.picture}'); float:left; display: inline-block; background-size: auto 70%; background-position: center; height: 500px; width: 500px; background-repeat: no-repeat;"></div>
            <div style="margin-top: 100px;">
                <strong>Name:</strong>${good.name}<br><br>
                <strong>Detail:</strong>${good.describe}<br><br>
                <strong>Price:</strong>$${good.price}<br><br>
                <strong>${good.number} items remaining.</strong><br><br>
                Quantity <input id="buyNumber"></input>
                <input id="id"  value="${good.id}" type="hidden"/>

                <button id="add" onclick="addOrder()" >Add item(s) to cart</button>
            </div>
        </div>
    </body>
</html>
