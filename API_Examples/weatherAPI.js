document.getElementById("submitButton").addEventListener("click", function () {
  var city = document.getElementById("userInput").value;
  var req = new XMLHttpRequest();

  req.open("GET", "http://api.openweathermap.org/data/2.5/weather?zip=" + city + "&units=imperial&appid=fa03b979b02f25445266fb7281b54f01", true);
  req.send(null);

  req.addEventListener('load', function () {
    if (req.status >= 200 && req.status < 400) {

      var responseDataObject = JSON.parse(req.responseText);
      console.log(responseDataObject);
      var location = JSON.stringify(responseDataObject["name"]) + ", " + JSON.stringify(responseDataObject["sys"]["country"]);
      var temperature = "Current Temperature: " + JSON.stringify(responseDataObject["main"]["temp"]) + " \u00B0F";
      var humidity = "Humidity: " + JSON.stringify(responseDataObject["main"]["humidity"]) + "%";
      var wind = "Wind Speed: " + JSON.stringify(responseDataObject["wind"]["speed"]) + " MPH";

      document.getElementById('city').textContent = location;
      document.getElementById('current').textContent = temperature;
      document.getElementById('humidity').textContent = humidity;
      document.getElementById('wind').textContent = wind;
      document.getElementById('responseArea').textContent = JSON.stringify(responseDataObject);

      while (document.getElementById('getResponse').firstChild) {
        document.getElementById('getResponse').removeChild(document.getElementById('getResponse').firstChild);
      }

      for (var p in responseDataObject) {
        var listItem = document.createElement("li")
        listItem.textContent = p + ": " + JSON.stringify(responseDataObject[p]);
        document.getElementById('getResponse').appendChild(listItem);
      }
      document.getElementById("responseArea").textContent = JSON.stringify(responseDataObject);

    } else {
      console.log("Error in network request: " + request.statusText);
    }
  });
  event.preventDefault();
});

