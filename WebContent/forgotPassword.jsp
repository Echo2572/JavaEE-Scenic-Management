<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
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

        .forgot-password-form input[type="email"],
        .forgot-password-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .forgot-password-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        .forgot-password-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>忘记密码</h1>
        <% if(request.getParameter("error") != null){ %>
        <p style="color: red;">该电子邮件地址未注册。</p>
        <% } %>

        <div class="forgot-password-form">
            <form action="ForgotPasswordServlet" method="post">
                <label for="email">电子邮件地址：</label>
                <input type=email id="email" name="email" required placeholder="请输入注册时的电子邮件地址"><br><br>
                <input type="submit" value="确认">
            </form>
        </div>
    </div>
</body>
</html>
