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
const Employee = require('../models/employee.model.js');

exports.authenticate = (req, res, next) => {
    if (!req.body.username || !req.body.password) {
        return res.status(400).send({
            message: "Credentials can not be empty!"
        });
    }

    Employee.authenticate(req.body.username, req.body.password)
        .then(employee => {
            if (!employee) {
                return res.status(401).send({message: 'Username or password is incorrect!'});
            }

            // Just to create the authorization header to test with Postman.
            //const btoa = require('btoa');
            //console.log('Basic ' + btoa(employee.username + ':' + employee.password));

            res.send(employee);
        })
        // Here I've delegated to the error handler!
        .catch(err => next(err));
};