//event listener creation for click
window.onload = function () {
    console.log('in onload');
    document.getElementById('pokemonSubmit').addEventListener('click', getPokemon, false);
    document.getElementById('artistSubmit').addEventListener('click', searchArtist, false);
};

//set name header text to name value of JS object
function loadPokemon(pokemon) {
    document.getElementById('name').innerHTML = pokemon.name;
};

function getPokemon() {
    console.log('hello');
    //value from text field
    var pokemonId = document.getElementById('pokeId').value;

    //1. Open XHR
    var xhr = new XMLHttpRequest();

    //2. Function to handle readystatechange
    xhr.onreadystatechange = function () {
        console.log('roll tide');
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText); //prints JSON text to console
            //parse JSON to JS object
            var pokemon = JSON.parse(xhr.responseText);
            loadPokemon(pokemon);
        }
    }

    //3. Open request
    xhr.open('GET', 'https://pokeapi.co/api/v2/pokemon/' + pokemonId, true);

    //4. Send request
    xhr.send();
};

function searchArtist() {
    var artistNameToSearch = document.getElementById('artistToSearch').value;
    var xhr = new XMLHttpRequest();
    //Spotify API requires change every hour
    var token = "BQAWN-tsPNECtVTaBh0sI6_yyepIHTNwDzU_6Iup0UbaNk250i-GgQS9kyOHl-8EvBKe1CU_DHmN0Ccg8KbFD4S62_UHHuN2fDegeJ4nWj-aRO5LQPifmUniO_pzBWpvHJalViFaKhgU";

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            var jsobjectfromjson = JSON.parse(xhr.responseText);
            displayArtist(jsobjectfromjson);
        }
    }

    xhr.open('GET', 'https://api.spotify.com/v1/search?q=' + artistNameToSearch + "&type=artist", true);

    xhr.setRequestHeader('Authorization', 'Bearer ' + token);

    xhr.send();
}

function displayArtist(parsedJson) {
    var img = document.getElementById('artistImage');
    img.src = parsedJson.artists.items[0].images[0].url;
    img.width = parsedJson.artists.items[0].images[0].width;
    img.height = parsedJson.artists.items[0].images[0].height;

    var form = document.getElementById('artistForm');
    form.action = parsedJson.artists.items[0].external_urls.spotify;
    var link = document.getElementById('artistLink');
    link.value = parsedJson.artists.items[0].name;
}