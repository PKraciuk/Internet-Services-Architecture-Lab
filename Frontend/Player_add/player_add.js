import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const addForm = document.getElementById('addForm');
    addForm.addEventListener('submit', event => addPlayerAction(event));

});

function addPlayerAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/players/', true);

    const request = {
        'name': document.getElementById('name').value,
        'club': getParameterByName('club'),
        'position': document.getElementById('position').value,
        'age': parseInt(document.getElementById('age').value),
        'height': parseInt(document.getElementById('height').value),
        'value': parseInt(document.getElementById('value').value)
    };
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}


