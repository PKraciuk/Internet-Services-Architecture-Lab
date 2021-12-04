import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayClub();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/clubs', true);

    const request = {
        'name': document.getElementById('name').value,
        'CreationYear': parseint(document.getElementById('creation_year').value),
        'MainColor': parseInt(document.getElementById('color').value),
        'Budget': parseint(document.getElementById('budget').value),
        
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
 document.getElementsByName("Add").onclick = function () {
     location.href = '../club_list/club_list.html';
 }