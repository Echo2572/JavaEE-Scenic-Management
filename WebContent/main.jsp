<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="entity.User" %>
<jsp:useBean id="user" class="entity.User" scope="session" />
<html>
<head>
    <title>景区平台</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        a{
        	text-decoration:none;
        }
        header, footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        header a, footer a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }
        .container {
            max-width: 1700px;
            margin: 20px auto;
            padding: 0 20px;       
        }
        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            display:flex;
            flex-wrap:wrap;
        }
        .grid-item {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
            margin-right:20px;
            margin-bottom:20px;
            width:400px;
        }
        .grid-item:hover {
            transform: scale(1.05);
        }
        .grid-item img {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }
        .grid-item h3 {
            margin: 10px;
        }
        .grid-item p {
            margin: 0 10px 10px;
        }
        
        .container .self{
        	color:rgb(200,200,200);
        }
        
        .container .self:hover{
        	color:white;
        }
    </style> 
</head>
<body>
    <header>
        <div class="container">
            <nav>
                <a class="self" href="userInfo.jsp">个人中心</a>
                <a class="self" href="showReservation">预约信息</a>
                <a class="self" href="Login.jsp">退出登录</a>
            </nav>
        </div>
    </header>
    
    <div class="container">
        <p>您的用户名是: <c:out value="${user.getUsername()}" /></p>
        
        <div class="grid-container">
        <c:forEach var="scenic" items="${scenicList}">
            <div class="grid-item">
                <a href="ScenicDetail?id=${scenic.id}">
                    <img src="${scenic.image}" alt="${scenic.name}">
                    <h3>${scenic.name}</h3>
                    <p>${scenic.description}</p>
                </a>
            </div>
        </c:forEach>
    </div>
    </div>
    
    <footer>
        <div class="container">
            <p>联系信息: sunyb2021@mails.jlu.edu.cn | 电话: 150-4932-8940</p>
        </div>
    </footer>
</body>
</html>
