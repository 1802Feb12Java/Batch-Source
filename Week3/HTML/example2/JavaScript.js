//event listener creation for click
window.onload = function () {
    console.log('in onload');
    document.getElementById('pokemonSubmit').addEventListener('click', getPokemon, false);
};

function loadPokemon(pokemon) {
    document.getElementById('name').innerHTML = pokemon.name;
};

function getPokemon() {
    console.log('hello');
    var pokemonId = document.getElementById('pokeId').value;

    //1. Open XHR
    var xhr = new XMLHttpRequest();

    //2. Function to handle readystatechange
    xhr.onreadystatechange = function () {
        console.log('roll tide');
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            var pokemon = JSON.parse(xhr.responseText);
            loadPokemon(pokemon);
        }
    }

    //3. Open request
    xhr.open('GET', 'https://pokeapi.co/api/v2/pokemon/' + pokemonId, true);

    //4. Send request
    xhr.send();
};