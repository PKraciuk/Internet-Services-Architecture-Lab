import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayClub();
    fetchAndDisplayPlayers();
});

/**
 * Fetches all clubs and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayPlayers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayPlayers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + getParameterByName('club') + '/players', true);
    xhttp.send();
}

function displayPlayers(players) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    players.players.forEach(player => {
        tableBody.appendChild(createTableRow(player));
    })
}


function createTableRow(player) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(player.name));
    tr.appendChild(createLinkCell('view','../player_view/player_view.html?club='
        + getParameterByName('club') + '&player=' + player.id));
    tr.appendChild(createLinkCell('edit', '../player_edit/player_edit.html?club='
        + getParameterByName('club') + '&player=' + player.id));
    tr.appendChild(createButtonCell('delete', () => deletePlayer(player)));
    return tr;
}

function deletePlayer(player) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayPlayers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/players/' 
     + player.id, true);
    xhttp.send();
}


/**
 * Fetches single club and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayClub() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClub(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + getParameterByName('club'), true);
    xhttp.send();
}


function displayClub(club) {
    setTextNode('Name', club.name);
    setTextNode('Creation Year', club.creationYear);  
    setTextNode('Color', club.mainColor); 
    setTextNode('Budget', club.budget);
}
document.getElementById("addPlayer").onclick = function (){
    location.href = '../player_add/player_add.html?club=' + getParameterByName('club');
}
