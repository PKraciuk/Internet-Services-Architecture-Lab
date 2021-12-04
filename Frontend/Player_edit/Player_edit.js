import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayPlayer();
});

/**
 * Fetches currently logged club's players and updates edit form.
 */
function fetchAndDisplayPlayer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + getParameterByName('club') + '/players/'
        + getParameterByName('player'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayPlayer();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/players/'
        + getParameterByName('player'), true);

    const request = {
        'name': document.getElementById('name').value,
        'value': parseInt(document.getElementById('value').value),
        'age': parseInt(document.getElementById('age').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

// document.getElementById("Submit").onclick = function(){
//     location.href = "../club_view/club_view.html?player=" + getParameterByName('player');
// }
