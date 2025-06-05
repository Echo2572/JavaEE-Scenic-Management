<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>景区旅游服务平台</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
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
            display: flex;
            flex-wrap: wrap;
        }
        h1 {
            color: #333;
            margin:0 auto;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px 30px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
            border: 2px solid transparent;
            margin-bottom:0;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Scenic Service Platform</h1>
    <div style="margin:0 auto;">
    	<a href="Login.jsp" class="btn">登录</a>
    	<a href="Register.jsp" class="btn">注册</a>
    </div>
</div>
</body>
</html>