#!/usr/bin/env node
/*
 * Copyright (c) 2019 - Felipe Desiderati
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
const mongoose = require('mongoose');

const EmployeeSchema = mongoose.Schema({
    username: String,
    password: String,
    name: String,
    jobTitle: String
}, {
    timestamps: true
});

// The model here also acts as a Service because the Mongoose.
const Employee = mongoose.model('Employee', EmployeeSchema);
module.exports = Employee;

// The right implementation would be to have an Authentication Service separated from
// the Employee Service, but as this is a simple example, I've preferred to follow this way.
module.exports.authenticate = (username, password) => {

    // I've need to use promises here because Mongoose does not support sync queries.
    return new Promise(function (resolve, reject) {
        findByUsername(username)
            .then(employee => {
                if (!!employee && employee.password === password) {
                    resolve(employee);
                } else {
                    resolve(null);
                }
            })
            .catch(err => {
                console.log(err);
                reject(err);
            });
    });
};

module.exports.usernameWasAlreadyTaken = (id, username) => {
    // I've need to use promises here because Mongoose does not support sync queries.
    return new Promise(function (resolve, reject) {
        findByUsername(username)
            .then(employee => {
                resolve(!!employee && (id === null || id !== employee._id));
            })
            .catch(err => {
                console.log(err);
                reject(err);
            });
    });
};

function findByUsername(username) {
    // I've need to use promises here because Mongoose does not support sync queries.
    return new Promise(function (resolve, reject) {
        Employee.find({username: username})
            .then(employee => {
                if (!!employee && employee.length > 0) { // Always be one!
                    resolve(employee[0]);
                } else {
                    resolve(null);
                }
            })
            .catch(err => {
                reject(err);
            });
    });
}