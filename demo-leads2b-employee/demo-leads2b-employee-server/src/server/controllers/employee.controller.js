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

const Employee = require('../models/employee.model.js');

exports.create = (req, res) => {
    if (!req.body.username || !req.body.password || !req.body.name || !req.body.jobTitle) {
        return res.status(400).send({
            message: "Employee (Username, Password, Name and Job Title) can not be empty!"
        });
    }

    Employee.usernameWasAlreadyTaken(null, req.body.username)
        .then(usernameWasAlreadyTaken => {

            if (usernameWasAlreadyTaken) {
                return res.status(409).send({
                    message: "Username '" + req.body.username + "' was already taken!"
                });
            }

            // Just to guarantee that _id was not passed!
            delete req.body['_id'];

            // I should encrypt the password, but I've preferred to maintain this way for simplicity.
            // In a real implementation, I would never opt for this approach.
            const employee = new Employee(Object.assign({}, req.body));
            employee.save()
                .then(employee => {
                    res.send(employee);
                })
                .catch(err => {
                    // I could let to the error handler, but I've preffered to show a custom message.
                    res.status(500).send({
                        message: err.message || "Some error occurred while creating the employee."
                    });
                });
        })

};

exports.findAll = (req, res) => {
    Employee.find()
        .then(employees => {
            res.send(employees.map((employee) => {
                employee.password = '******';
                return employee;
            }));
        })
        .catch(err => {
            // I could let to the error handler, but I've preffered to show a custom message.
            res.status(500).send({
                message: err.message || "Some error occurred while retrieving the employees."
            });
        });
};

exports.findOne = (req, res) => {
    /** @namespace req.params.employeeId */
    Employee.findById(req.params.employeeId)
        .then(employee => {
            if (!employee) {
                return res.status(404).send({
                    message: "Employee not found with id " + req.params.employeeId
                });
            }
            employee.password = '******';
            res.send(employee);
        })
        .catch(err => {
            // I could let to the error handler, but I've preffered to show a custom message.
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Employee not found with id " + req.params.employeeId
                });
            }

            return res.status(500).send({
                message: "Error retrieving employee with id " + req.params.employeeId
            });
        });
};

exports.update = (req, res) => {
    if (!req.body.username || !req.body.name || !req.body.jobTitle) {
        return res.status(400).send({
            message: "Employee (Username, Name and Job Title) can not be empty!"
        });
    }

    Employee.usernameWasAlreadyTaken(req.params.employeeId, req.body.username)
        .then(usernameWasAlreadyTaken => {

            if (usernameWasAlreadyTaken) {
                return res.status(409).send({
                    message: "Username '" + req.body.username + "' was already taken!"
                });
            }

            // Just to guarantee that password will not be reseted to empty!
            if (!req.body.password) {
                delete req.body['password'];
            }

            // I should encrypt the password, but I've preferred to maintain this way for simplicity.
            // In a real implementation, I would never opt for this approach.
            const newEmployee = Object.assign({}, req.body);
            Employee.findByIdAndUpdate(req.params.employeeId, newEmployee, {lean: true})
                .then(employee => {
                    if (!employee) {
                        return res.status(404).send({
                            message: "Employee not found with id " + req.params.employeeId
                        });
                    }

                    // Employee will hold the old information :(
                    newEmployee.password = '******';
                    res.send(Object.assign(employee, newEmployee));
                })
                .catch(err => {
                    // I could let to the error handler, but I've preffered to show a custom message.
                    if (err.kind === 'ObjectId') {
                        return res.status(404).send({
                            message: "Employee not found with id " + req.params.employeeId
                        });
                    }

                    return res.status(500).send({
                        message: "Error updating employee with id " + req.params.employeeId
                    });
                });
        })
};

exports.delete = (req, res) => {
    Employee.findByIdAndRemove(req.params.employeeId)
        .then(employee => {
            if (!employee) {
                return res.status(404).send({
                    message: "Employee not found with id " + req.params.employeeId
                });
            }
            res.send({message: "Employee deleted successfully!"});
        })
        .catch(err => {
            // I could let to the error handler, but I've preffered to show a custom message.
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Employee not found with id " + req.params.employeeId
                });
            }

            return res.status(500).send({
                message: "Could not delete employee with id " + req.params.employeeId
            });
        });
};
