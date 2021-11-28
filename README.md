# WeatherAppT1
Android App for Weather update of searched location.

Search by location name to get current temperature and humidity of that location.

Used api.openweathermap.org API for getting weather data.
Used RequestQueue of Volley library for HTTP request and collected JsonObjectRequest. After that extracted the city name, country name, temperature and humidity. Also converted the temperature from kelvin to centigrade.

Used InputMethodManager to hide keyboard when pressed on the Get Weather Update button so that the output can be seen properly.

Got idea from https://youtu.be/GYimr4OO6Ig
