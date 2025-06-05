<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>天气信息</title>
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
        .weather-card {
            padding: 20px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f4f4f4;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .weather-card img {
            width: 50px;
            height: 50px;
        }
    </style>
    <script>
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            var scenicName = urlParams.get('scenicName');
            var url = 'http://localhost:8888/ScenicServicePlatform/rest/thirdService/getWeather?spot=' + encodeURIComponent(scenicName);

            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    var weatherContainer = document.getElementById('weatherContainer');
                    weatherContainer.innerHTML = ''; // Clear previous content
                    
                    var scenicNameElement = document.getElementById('scenicName');
                    scenicNameElement.textContent = scenicName+"的景区天气信息";

                    for (var i = 0; i < data.length; i++) {
                        var weather = data[i];
                        var weatherCard = document.createElement('div');
                        weatherCard.className = 'weather-card';

                        weatherCard.innerHTML = 
                            '<h4>' + weather.date + '</h4>' +
                            '<p>' + weather.description + '</p>' +
                            '<p>Temperature: ' + weather.temperature + '</p>';

                        weatherContainer.appendChild(weatherCard);
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
        <div id="weatherContainer"></div>
    </div>
</body>
</html>
