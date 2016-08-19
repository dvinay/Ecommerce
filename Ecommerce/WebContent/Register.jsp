<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'Register.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="css/index.css" type="text/css"></link>

        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->

    </head>

    <body>
        <div class="all">
            <div class="mainbg"></div>
            <div class="inp"  style="height: 300px;"> 
                
                <center>
                    <h1>User Sign Up</h1>
                        <form action="servlet/AddUsers" method="post" >
                            <input type="text" id="username" name="username" placeholder="Username" width="200"><br><br>
                            <input id="password" name="password"  type="password" placeholder="Password" width="200"><br><br>
                            <input type="text" id="address" name="address" placeholder="Address"><br><br>
                            <input type="submit" value="Submit">
                        </form>
                </center>
            </div>
        </div>
    </body>
</html>
