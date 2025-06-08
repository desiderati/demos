<!--
  - Copyright (c) 2025 - Felipe Desiderati
  -
  - Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
  - associated documentation files (the "Software"), to deal in the Software without restriction,
  - including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
  - and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
  - subject to the following conditions:
  -
  - The above copyright notice and this permission notice shall be included in all copies or substantial
  - portions of the Software.
  -
  - THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
  - LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
  - IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
  - WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
  - SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  -->
<!--suppress ES6ModulesDependencies, JSUnresolvedVariable-->
<template>
    <div class="col-md-10 mx-auto">
        <div class="row">
            <div class="col-md-10">
                <h1>Hi {{loggedUser.name}}!</h1>
            </div>
            <div class="col-md-2">(<router-link to="/login">Logout</router-link>)
            </div>
        </div>

        <div class="card card-body bg-light lead">Employee Registration Form</div>

        <div v-if="error" class="alert alert-danger">{{error}}</div>

        <form class="form-horizontal my-3" @submit.prevent="handleSubmit">
            <input type="hidden" v-model="_id" id="id" name="id"/>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label text-right" for="username">Username</label>
                <div class="col-sm-5">
                    <input class="form-control" type="text" id="username" name="username" title="Username"
                           v-model="username" :class="{ 'is-invalid': submitted && !username }"/>
                    <div v-show="submitted && !username" class="invalid-feedback">Username is required</div>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label text-right" for="password">Password</label>
                <div class="col-sm-5">
                    <input class="form-control" type="password" id="password" name="password" title="Password"
                           v-model="password" :class="{ 'is-invalid': submitted && !password && !_id }"/>
                    <div v-show="submitted && !password && !_id" class="invalid-feedback">Password is required</div>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label text-right" for="name">Name</label>
                <div class="col-sm-5">
                    <input class="form-control" type="text" id="name" name="name" title="Name"
                           v-model="name" :class="{ 'is-invalid': submitted && !name }"/>
                    <div v-show="submitted && !name" class="invalid-feedback">Name is required</div>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label text-right" for="jobTitle">Job Title</label>
                <div class="col-sm-5">
                    <input class="form-control" type="text" id="jobTitle" name="jobTitle" title="Job Title"
                           v-model="jobTitle" :class="{ 'is-invalid': submitted && !jobTitle }"/>
                    <div v-show="submitted && !jobTitle" class="invalid-feedback">Job Title is required</div>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-5 offset-sm-2">
                    <button class="btn btn-primary" :disabled="loading">Submit</button>
                    &nbsp;
                    <button class="btn" @click="resetAll()">Reset</button>
                    <img v-show="loading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" alt="Loading..."/>
                </div>
            </div>
        </form>

        <div class="card card-body bg-light lead">Employee Listing</div>
        <em v-if="employees.loading">Loading employees...</em>
        <table class="table table-striped" v-if="employees.length">
            <thead>
            <tr>
                <th>Username</th>
                <th>Name</th>
                <th>Job Title</th>
                <th style="width: 20%"></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="employee in employees" :key="employee._id">
                <td class="align-middle">{{employee.username}}</td>
                <td class="align-middle">{{employee.name}}</td>
                <td class="align-middle">{{employee.jobTitle}}</td>
                <td>
                    <button type="button" class="btn btn-sm btn-primary"
                            @click="selectEmployee(employee._id)">Update</button>
                    &nbsp;
                    <button type="button" class="btn btn-sm btn-primary" :disabled="loggedUser._id === employee._id"
                            @click="deleteEmployee(employee._id)">Remove</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {employeeService} from '../../services';
    import {mapFields} from 'vuex-map-fields';

    export default {
        data() {
            return {
                submitted: false,
                loading: false,
                error: '',

                loggedUser: {},
                employees: []
            }
        },

        computed: {
            ...mapFields([
                '_id',
                'username',
                'password',
                'name',
                'jobTitle',
            ]),
        },

        created() {
            this.loggedUser = JSON.parse(localStorage.getItem('loggedUser'));
            this.employees.loading = true;
            this.resetAll();
        },

        methods: {
            resetStatus() {
                this.submitted = false;
                this.loading = false;

            },

            resetAll() {
                this.resetStatus();
                this.error = '';

                this._id = '';
                this.username = '';
                this.password = '';
                this.name = '';
                this.jobTitle = '';

                employeeService.findAll().then(employees => this.employees = employees);
            },

            selectEmployee(id) {
                for (let i = 0; i < this.employees.length; i++) {
                    if (this.employees[i]._id === id) {
                        this._id = this.employees[i]._id;
                        this.username = this.employees[i].username;
                        this.password = '';
                        this.name = this.employees[i].name;
                        this.jobTitle = this.employees[i].jobTitle;
                        break;
                    }
                }
            },

            deleteEmployee(id) {
                employeeService.deleteEmployee(id)
                    .then(
                        () => {
                            employeeService.findAll().then(employees => this.employees = employees);
                        },
                        error => {
                            this.error = error;
                        }
                    );
            },

            handleSubmit() {
                this.submitted = true;
                if (!this.$store.state._id) {
                    const {username, password, name, jobTitle} = this;
                    if (!(username && password && name && jobTitle)) {
                        return;
                    }

                    this.loading = true;
                    employeeService.create(this.$store.state)
                        .then(
                            () => {
                                this.resetAll();
                            },
                            error => {
                                this.resetStatus();
                                this.error = error;
                            }
                        );
                } else {
                    const {username, name, jobTitle} = this;
                    if (!(username && name && jobTitle)) {
                        return;
                    }

                    this.loading = true;
                    employeeService.update(this.$store.state)
                        .then(
                            () => {
                                this.resetAll();
                            },
                            error => {
                                this.resetStatus();
                                this.error = error;
                            }
                        );
                }
            }
        }
    };
</script>
