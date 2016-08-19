<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'OrderList.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
        <link rel="stylesheet" href="css/index.css" type="text/css">

        <script type="text/javascript">
            $(document).ready(function() {
                $.ajax({
                    url: "servlet/QueryOrder",
                    success: function(data) {
                        $("#databox").html(data);
                    }
                });

            });
            function set(id) {
                $.ajax({
                    url: "servlet/SetOrder",
                    data: {
                        id: id
                    },
                    success: function(data) {
                        alert(data);
                        window.location.reload();
                    }
                });

            }

            function deleteO(id) {
                if (confirm("Are you sure you want to Delete this Gadget from the Cart?")) {
                    $.ajax({
                        url: "servlet/DeleteO",
                        data: {
                            id: id
                        },
                        success: function(data) {
                            alert(data);
                            if (data == "Gadget Deleted from cart!!!")
                                $("#" + id).remove();
                        }
                    });
                }
                return false;
            }
        </script>
        <!--
<link rel="stylesheet" type="text/css" href="styles.css">
        -->

    </head>

    <body>
        <div class="all">
            <h1 style="float: right; margin-right: 20px;" ><a href="index.jsp" style=" color: white;">Logout</a></h1>
            <h1 style="float: right;  margin-right: 50px;" ><a href="MyIndex.html" style=" color: white;" >Home</a></h1>
            <div class="mainbg"></div>
            <div id="databox">

            </div>
        </div>


    </body>
</html>
