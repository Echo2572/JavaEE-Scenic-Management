<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="entity.User" %>
<%@ page import="util.SimpleEncryption" %>
<jsp:useBean id="user" class="entity.User" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>个人信息</title>
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
        .profile-pic {
            display: block;
            margin: 0 auto 20px;
            width: 150px;
            height: 150px;
            border-radius: 50%;
            border: 2px solid #ddd;
            object-fit: cover;
        }
        .info {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .info-item {
            display: flex;
            justify-content: space-between;
            width: 30%;
            max-width: 400px;
            margin-bottom: 10px;
        }
        .info label {
            font-weight: bold;
        }
        .info p {
            margin: 0;
            color: #555;
            margin-left: 10px;
        }
        .edit-button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .edit-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>个人信息</h1>
        <img src="images/userPic.png" alt="Profile Picture" class="profile-pic">
        <div class="info">
            <div class="info-item">
                <label for="username">用户名:</label>
                <p id="username"><c:out value="${user.getUsername()}"/></p>
            </div>
            <div class="info-item">
                <label for="email">邮箱:</label>
                <p id="email"><c:out value="${user.getEmail()}"/></p>
            </div>
            <div class="info-item">
                <label for="password">密码:</label>
                <p id="password">
                    <%
                        String decryptedPassword = SimpleEncryption.decrypt(user.getPassword());
                        out.print(decryptedPassword);
                    %>
                </p>
            </div>
            <a href="editUserInfo.jsp" class="edit-button">编辑信息</a>
        </div>
    </div>
</body>
</html>
