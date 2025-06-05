<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="entity.User" %>
<%@ page import="util.SimpleEncryption" %>
<jsp:useBean id="user" class="entity.User" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>编辑个人信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"] {
            width: 100%;
            padding: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .form-group input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 3px;
        }
        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>编辑个人信息</h1>
        <% if("1".equals(request.getParameter("error"))){ %>
        <p style="color: red;">修改失败！</p>
        <% } %>
        <form action="UpdateUserInfoServlet" method="post">
            <div class="form-group">
                <label for="username">用户名:</label>
                <input type="text" id="username" name="username" value="<c:out value='${user.getUsername()}'/>" readonly>
            </div>
            <div class="form-group">
                <label for="email">邮箱:</label>
                <input type="email" id="email" name="email" value="<c:out value='${user.getEmail()}'/>">
            </div>
            <div class="form-group">
                <label for="password">密码:</label>
                	<%
                        String decryptedPassword = SimpleEncryption.decrypt(user.getPassword());
                		request.setAttribute("decryptedPassword", decryptedPassword);
                    %>
				<input type="text" id="password" name="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" title="密码必须是8位以上的字母和数字组合" value="${decryptedPassword}"/>
            </div>
            <div class="form-group">
                <input type="submit" value="保存更改">
            </div>
        </form>
    </div>
</body>
</html>
