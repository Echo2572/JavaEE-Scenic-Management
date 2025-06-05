<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
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

        .reset-password-form input[type="password"],
        .reset-password-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .reset-password-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .reset-password-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>重置密码</h1>
        <% if("1".equals(request.getParameter("error"))){ %>
        <p style="color: red;">两次输入的密码不同，请重新输入！</p>
        <% } %>
        
        <% if("2".equals(request.getParameter("error"))){ %>
        <p style="color: red;">无效验证，请重试！</p>
        <% } %>
        
        <div class="reset-password-form">
            <form action="ResetPasswordServlet" method="post">
            	<input type="hidden" name="token" value="<%= request.getParameter("token") %>">
                <label for="password">新密码：</label>
                <input type="password" id="password" name="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" title="密码必须是8位以上的字母和数字组合" required><br><br>
                <label for="confirm_password">确认密码：</label>
                <input type="password" id="confirm_password" name="confirm_password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" title="密码必须是8位以上的字母和数字组合" required><br><br>
                <input type="submit" value="重置密码">
            </form>
        </div>
    </div>
</body>
</html>
