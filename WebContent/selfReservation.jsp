<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="entity.User" %>
<%@ page import="entity.Reservation" %>
<jsp:useBean id="user" class="entity.User" scope="session" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>本人预约信息</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    h1 {
        color: #333;
    }
    table {
        border-collapse: collapse;
        width: 100%;
        margin-top: 20px;
    }
    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f2f2f2;
    }
    img {
        max-width: 200px;
    }
</style>
<script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if ('${checkSuccess}' === 'true') {
                alert('检票入园成功！');
            }
        };
    </script>
</head>
<body>
    <h1>本人预约信息</h1>
    您的用户名为：<c:out value="${user.username}"/>
    
    <h2>预约信息:</h2>
    
    <table>
        <thead>
            <tr>
                <th>景区名称</th>
                <th>景区描述</th>
                <th>参观时间</th>
                <th>状态</th>
                <th>门票码</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <%-- 使用EL表达式获取预约列表并遍历 --%>
            <c:forEach items="${reservationList}" var="reservation">
                <tr>
                    <td>${reservation.scenic.name}</td>
                    <td>${reservation.scenic.description}</td>
                    <td>${reservation.visitTime}</td>
                    <td>${reservation.status}</td>
                    <td>${reservation.ticketCode}</td>
                    <td>
                        <form action="CheckTicketServlet" method="post">
                            <input type="hidden" name="ticketCode" value="${reservation.ticketCode}"/>
                            <button type="submit">入园检票</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
