<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>交通路线</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .route-card {
            padding: 20px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f4f4f4;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
    <script>
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var scenicName = urlParams.get('scenicName');
            var url = 'http://localhost:8888/ScenicServicePlatform/rest/thirdService/getRoutes?spot=' + encodeURIComponent(scenicName);

            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    var routeContainer = document.getElementById('routeContainer');
                    routeContainer.innerHTML = ''; // Clear previous content
                    
                    var scenicNameElement = document.getElementById('scenicName');
                    scenicNameElement.textContent = scenicName+"的景区交通路线";

                    for (var i = 0; i < data.length; i++) {
                        var route = data[i];
                        var routeCard = document.createElement('div');
                        routeCard.className = 'route-card';

                        routeCard.innerHTML = 
                            '<h4>' + route.routeName + '</h4>' +
                            '<p>' + route.description + '</p>' +
                            '<p>' + route.duration + '</p>';

                        routeContainer.appendChild(routeCard);
                    }
                }
            };
            xhr.send();
        };
    </script>
</head>
<body>
    <div class="container">
    	<h1 id="scenicName"></h1>
        <div id="routeContainer"></div>
    </div>
</body>
</html>
