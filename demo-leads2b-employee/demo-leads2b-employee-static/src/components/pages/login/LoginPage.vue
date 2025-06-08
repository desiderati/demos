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
<template>
    <div class="col-md-5 mx-auto">
        <div class="card card-body bg-light lead">
            <div class="logo mb-3">
                <div class="col-md-12 text-center">
                    <h1>Login</h1>
                </div>
            </div>
            <form @submit.prevent="handleSubmit">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" v-model="username" id="username" name="username" class="form-control"
                           :class="{ 'is-invalid': submitted && !username }"/>
                    <div v-show="submitted && !username" class="invalid-feedback">Username is required</div>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" v-model="password" id="password" name="password" class="form-control"
                           :class="{ 'is-invalid': submitted && !password }"/>
                    <div v-show="submitted && !password" class="invalid-feedback">Password is required</div>
                </div>
                <div class="form-group">
                    <button class=" btn btn-block btn-login btn-primary" :disabled="loading">Login</button>
                    <img v-show="loading" alt="Loading..."
                         src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA=="/>
                </div>
                <div v-if="error" class="alert alert-danger">{{error}}</div>
            </form>
        </div>
    </div>
</template>

<script>
    import {router} from '../../common';
    import {employeeService} from '../../services';

    export default {
        data() {
            return {
                username: '',
                password: '',
                submitted: false,
                loading: false,
                returnUrl: '',
                error: ''
            }
        },

        created() {
            employeeService.logout();
            this.returnUrl = this.$route.query.returnUrl || '/';
        },

        methods: {
            handleSubmit() {
                this.submitted = true;
                const {username, password} = this;
                if (!(username && password)) {
                    return;
                }

                this.loading = true;
                employeeService.login(username, password)
                    .then(
                        () => router.push(this.returnUrl),
                        error => {
                            this.error = error;
                            this.loading = false;
                        }
                    );
            }
        }
    };
</script>
