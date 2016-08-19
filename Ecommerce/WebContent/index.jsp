<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'index.jsp' starting page</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="css/index.css" type="text/css"></link>
        <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
        
    </head>
    <body>
        <div class="all">
            <div class="main">
                <div class="mainbg"></div>
                ${result}

                <center>
                    <form id="login" action="servlet/Login" method="post" >

                        <div class="inp" > 
                            <br> 
                            <p style="color: black;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="username" id="username" placeholder="Username"></p>
                            <p style="color: black;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="password" name="password" type="password" placeholder="Password"></p>
                            <img class="ent" onclick="$(login).submit();" width="274" height="100"  src="image/login.png"></img>
                            <br><br>
                            <div> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   Not a member? .Please register <a href="Register.jsp">here</a>!	</div>

                        </div>
                        <!--<img class="ent"  src="image/ener.jpg">-->

                    </form></center>
            </div>
        </div>
    </body>

</html>
