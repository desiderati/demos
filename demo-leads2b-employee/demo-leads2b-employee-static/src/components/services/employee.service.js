/*
 * Copyright (c) 2025 - Felipe Desiderati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import {authorization} from '../common';

const apiUrl = require('../environment.js').apiUrl;

export const employeeService = {
    login,
    logout,
    create,
    update,
    deleteEmployee,
    findAll
};

function login(username, password) {
    const requestOptions = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username, password})
    };

    return fetch(apiUrl + '/auth', requestOptions)
        .then(handleResponse)
        .then(employee => {
            if (employee) {
                employee["authdata"] = window.btoa(username + ':' + password);
                localStorage.setItem('loggedUser', JSON.stringify(employee));
            }
            return employee;
        });
}

function logout() {
    localStorage.removeItem('loggedUser');
}

function create(employee) {
    const headers = authorization();
    headers['Content-Type'] = 'application/json';

    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(employee)
    };

    return fetch(apiUrl + '/employees', requestOptions).then(handleResponse);
}

function update(employee) {
    const headers = authorization();
    headers['Content-Type'] = 'application/json';

    const requestOptions = {
        method: 'PUT',
        headers: headers,
        body: JSON.stringify(employee)
    };

    return fetch(apiUrl + '/employees/' + employee._id, requestOptions).then(handleResponse);
}

function deleteEmployee(id) {
    const requestOptions = {
        method: 'DELETE',
        headers: authorization()
    };

    return fetch(apiUrl + '/employees/' + id, requestOptions).then(handleResponse);
}

function findAll() {
    const requestOptions = {
        method: 'GET',
        headers: authorization()
    };

    return fetch(apiUrl + '/employees', requestOptions).then(handleResponse);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // Force a logout!
                logout();
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}
