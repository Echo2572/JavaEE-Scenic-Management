<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Failed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #d9534f;
        }
        p {
            color: #d9534f;
        }
        a {
            display: inline-block;
            padding: 10px 15px;
            margin-top: 20px;
            color: #fff;
            background-color: #007bff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Failed</h1>
        <%
            String error = request.getParameter("error");
            if ("UsernameAlreadyExists".equals(error)) {
                out.println("<p>The username is already taken. Please choose another one.</p>");
            } else {
                out.println("<p>Registration failed. Please try again.</p>");
            }
        %>
        <a href="Register.jsp">Go back to registration page</a>
    </div>
</body>
</html>
