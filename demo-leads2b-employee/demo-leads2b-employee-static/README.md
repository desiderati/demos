# Leads2B Employee Static Module (Frontend)

This module provides the frontend user interface for the Leads2B Employee Management System. It's a Vue.js application
that allows users to interact with the employee data through a modern, responsive web interface.

## Overview

The static module:

- Provides a user-friendly interface for employee management
- Implements authentication and authorization
- Communicates with the backend API to perform CRUD operations
- Uses Vue.js with Vuex for state management

## Prerequisites

* Node v12.9.1
* NPM v6.10.2
* Backend API running (see [Server Module](../demo-leads2b-employee-server/README.md))
* Docker and Docker Compose (for containerized deployment)

## Features

- User authentication
- Employee listing with search and filter capabilities
- Create, read, update, and delete employee records
- Responsive design for desktop and mobile devices
- Form validation

## Project Structure

The frontend follows Vue.js best practices with the following structure:

- `src/components/common/` - Reusable Vue components
- `src/components/pages/` - Page components
- `src/components/services/` - API communication services

## Development Setup

### Project Installation

```bash
# Clone the repository (if not already done)
git clone https://github.com/desiderati/demos.git

# Navigate to the project directory
cd demos/demo-leads2b-employee/demo-leads2b-employee-static

# Install dependencies
npm install
```

### Development Commands

```bash
# Serve with hot-reload for development
npm run serve

# Build for production
npm run build

# Lint and fix files
npm run lint
```

## Building for Production

### Building Docker Image

```bash
# Build the Docker image
docker build -t desiderati/leads2b-employee-static:latest .

# Push to Docker Hub (optional)
docker push desiderati/leads2b-employee-static:latest
```

## Deployment

### Using Docker Compose

```bash
# Start the frontend with Docker Compose
docker-compose up -d && docker-compose logs -f
```

### Using Docker Directly

```bash
# Run the Docker container
docker run -it -p 8080:80 --rm -e API_URL=<YOUR_IP_ADDRESS>:3000 desiderati/leads2b-employee-static:latest
```

Replace `<YOUR_IP_ADDRESS>` with the IP address of your backend server.

## Configuration

The frontend can be configured using environment variables:

- `API_URL` - Backend API URL (default: http://localhost:3000)

## Accessing the Application

Once running, the application is accessible at:

```
http://localhost:8080
```

### Default Login Credentials

```
Username: admin
Password: 123456
```

## Integration with Other Modules

- This module communicates with the [Server Module](../demo-leads2b-employee-server/README.md) to perform CRUD
  operations
- The server in turn communicates with the [MongoDB Module](../demo-leads2b-employee-mongodb/README.md) for data
  persistence

## Browser Compatibility

The application is compatible with:

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)
