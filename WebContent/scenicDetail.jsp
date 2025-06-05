<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>景区详细信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            display: flex;
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .left-section {
            flex: 2;
            margin-right: 20px;
        }
        .right-section {
            flex: 1;
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin-top: 50px;
        }
        .left-section img, .left-section video {
            width: 100%;
            height: auto;
            border-radius: 10px;
        }
        h2 {
            margin-top: 0;
        }
        p {
            line-height: 1.6;
            margin: 20px 0;
        }
        input#visitTime {
            margin: 15px 0;
        }
        .right-section h3, .right-section h4 {
            margin: 0;
            margin-bottom: 10px;
        }
        .card {
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f4f4f4;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .card form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .card form input[type="submit"] {
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .card form input[type="submit"]:hover {
            background-color: #218838;
        }
        .link-button {
            display: inline-block;
            padding: 10px 15px;
            margin: 5px 0;
            color: white;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            font-size: 16px;
        }
        .link-button:hover {
            background-color: #0056b3;
        }
        video {
            margin-top: 30px;
        }
    </style>
    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('reservationSuccess') === 'true') {
                alert('预约成功！');
            }
        };
    </script>
</head>
<body>
    <div class="container">
        <div class="left-section">
            <h2>${scenic.name}</h2>
            <img src="${scenic.image}" alt="${scenic.name}">
            <video controls src="${scenic.video}">视频</video>
            <p>${scenic.description}</p>
        </div>

        <div class="right-section">
            <div class="card">
                <h4>预约</h4>
                <form action="MakeReservationServlet" method="post">
                    <input type="hidden" name="scenicId" value="${scenic.id}">
                    <label for="visitTime">选择参观时间:</label>
                    <input type="datetime-local" id="visitTime" name="visitTime" required>
                    <input type="submit" value="预约">
                </form>
            </div>

            <div class="card">
                <h4>检票</h4>
                <a class="link-button" href="showReservation">去检票</a>
            </div>

            <div class="card">
                <h4>排队情况</h4>
                <a class="link-button" href="QueueInfoServlet?scenicId=${scenic.id}">查看排队情况</a>
            </div>

            <div class="card">
                <h4>服务站情况</h4>
                <a class="link-button" href="ServiceInfoServlet?scenicId=${scenic.id}">查看服务站详情</a>
            </div>

            <div class="card">
                <h4>第三方评价信息</h4>
                <a class="link-button" href="weatherInfo.jsp?scenicName=${scenic.name}">查询天气信息</a>
                <a class="link-button" href="routeInfo.jsp?scenicName=${scenic.name}">查看交通路线</a>
            </div>
        </div>
    </div>
</body>
</html>
