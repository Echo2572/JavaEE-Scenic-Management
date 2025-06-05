<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            padding: 20px;
        }
        .container {
            max-width: 400px;
            margin: 100px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
        }

        .login-form input[type="text"],
        .login-form input[type="password"],
        .login-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .login-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .login-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .forgot-password {
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>用户登录</h1>
        <% if("1".equals(request.getParameter("error"))){ %>
        <p style="color: red;">用户名或密码错误！</p>
        <% } %>
        
        <% if("1".equals(request.getParameter("reset"))){ %>
        <p style="color: red;">重置密码成功！</p>
        <% } %>

        <div class="login-form">
            <form action="LoginServlet" method="post">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" required><br><br>
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required><br><br>
                <input type="submit" value="登录">
            </form>
        </div>
        <div class="forgot-password">
            <a href="forgotPassword.jsp">忘记密码？</a>
        </div>
    </div>
</body>
</html>
