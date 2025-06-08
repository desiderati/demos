#!/usr/bin/env node
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

require('rootpath')();

const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const errorHandler = require('./error-handler.js');
const basicAuth = require('./basic-auth.js');

const server = express();

server.use(bodyParser.urlencoded({extended: true})); // application/x-www-form-urlencoded
server.use(bodyParser.json()); // application/json
server.use(cors()); // Enable CORS.
server.use(basicAuth);

// Define the routes.
require('./routes/index.route.js')(server);
require('./routes/auth.route.js')(server);
require('./routes/employee.route.js')(server);

// Defines a global error handler.
server.use(errorHandler);

server.listen(3000, () => {
    console.log("Server is listening on port 3000...");
});

// Configuring the database.
const dbConfig = require('./database.js');
const mongoose = require('mongoose');

mongoose.Promise = global.Promise;

mongoose.connect(dbConfig.url, {
    useNewUrlParser: true
}).then(() => {
    console.log("Successfully connected to the database");
}).catch(err => {
    console.log('Could not connect to the database. Exiting now...', err);
    process.exit();
});

// Create Admin user.
const Employee = require('./models/employee.model.js');

Employee.usernameWasAlreadyTaken(null, 'admin')
    .then(usernameWasAlreadyTaken => {
        if (usernameWasAlreadyTaken) {
            console.log('Admin user already created!');
            return;
        }

        const employee = new Employee({
            username: 'admin',
            // I should encrypt the password, but I've preferred to maintain this way for simplicity.
            // In a real implementation, I would never opt for this approach.
            password: '123456',
            name: 'System Administrator',
            jobTitle: 'System Administrator'
        });

        employee.save()
            .then(employee => {
                console.log('Admin user created: ' + employee);
            })
            .catch(err => {
                console.log('Error while creating Admin user: ' + err);
            });
    });

