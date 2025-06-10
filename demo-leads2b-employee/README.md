# Leads2B Employee Management System

A full-stack sample application demonstrating a complete employee management system with CRUD operations. This project
showcases a modern web application architecture using:

- **Frontend**: Vue.js with Vuex for state management
- **Backend**: Node.js with Express.js
- **Database**: MongoDB

## Project Overview

This project is structured as a multi-module application with the following components:

1. **MongoDB Module** (`demo-leads2b-employee-mongodb`):
    - Provides the database layer for storing employee information
    - Includes MongoDB and Mongo Express (web-based admin interface)

2. **Server Module** (`demo-leads2b-employee-server`):
    - Node.js/Express backend API
    - Handles CRUD operations for employee data
    - Connects to MongoDB for data persistence

3. **Static Module** (`demo-leads2b-employee-static`):
    - Vue.js frontend application
    - Provides a user interface for managing employees
    - Communicates with the backend API

## System Architecture

```
┌────────────────────┐      ┌────────────────────┐      ┌────────────────────┐
│                    │      │                    │      │                    │
│  Vue.js Frontend   │──────▶  Node.js Backend   │──────▶  MongoDB Database  │
│    (Port 8080)     │      │    (Port 3000)     │      │    (Port 27017)    │
│                    │      │                    │      │                    │
└────────────────────┘      └────────────────────┘      └────────────────────┘
                                                                  │
                                                                  │
                                                         ┌────────▼──────────┐
                                                         │                   │
                                                         │   Mongo Express   │
                                                         │    (Port 8081)    │
                                                         │                   │
                                                         └───────────────────┘
```

## Prerequisites

* Node v12.9.1
* NPM v6.10.2
* Apache Maven 3.6.0
* Docker and Docker Compose (for running MongoDB and containerized deployment)

## Getting Started

To run the complete application, you need to set up and run each component in the following order:

1. **Start the MongoDB database**:
    - [MongoDB Setup Instructions](demo-leads2b-employee-mongodb/README.md)

2. **Start the Node.js backend server**:
    - [Server Setup Instructions](demo-leads2b-employee-server/README.md)

3. **Start the Vue.js frontend application**:
    - [Frontend Setup Instructions](demo-leads2b-employee-static/README.md)

## Default Login Credentials

Once the application is running, you can access it at http://localhost:8080 with the following credentials:

```
Username: admin
Password: 123456
```

## Features

- User authentication
- Employee listing with search and filter capabilities
- Create, read, update, and delete employee records
- Responsive design for desktop and mobile devices

## Development

Each module can be developed independently. Refer to the individual module README files for specific development
instructions.

## License

This project is licensed under the MIT License.
