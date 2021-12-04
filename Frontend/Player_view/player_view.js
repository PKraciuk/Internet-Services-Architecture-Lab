import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName, setTextNode} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    fetchAndDisplayPlayer();
});

function fetchAndDisplayPlayer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayer(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + getParameterByName('club') + '/players/'
        + getParameterByName('player'), true);
    xhttp.send();
}

function displayPlayer(player) {
    setTextNode('Name', player.name);
    setTextNode('Age', player.age);
    setTextNode('Height', player.height);
    setTextNode('Position', player.position);
    setTextNode('Value', player.value);
    setTextNode('Club', player.club);
}




