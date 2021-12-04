import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayClubs();
});


function fetchAndDisplayClubs() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayClubs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs', true);
    xhttp.send();
}


function displayClubs(clubs) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    clubs.clubs.forEach(club => {
        tableBody.appendChild(createTableRow(club));
    })
}

function createTableRow(club) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(club.name));
    tr.appendChild(createLinkCell('view', '../club_view/club_view.html?club=' + club.name));
    tr.appendChild(createLinkCell('edit', '../club_edit/club_edit.html?club=' + club.name));
    tr.appendChild(createButtonCell('delete', () => deleteClub(club.name)));
    return tr;
}

function deleteClub(club) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayClubs();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/clubs/' + club, true);
    xhttp.send();
    fetchAndDisplayClubs();
}

document.getElementById("addClub").onclick = function (){
    location.href = "../club_add/club_add.html";
}
