#!/bin/sh -e

echo "Replacing API_URL..."
sed -i -- "s/\${API_URL}/${API_URL}/g" /usr/share/nginx/html/environment.js

echo "Starting Web Server..."
exec nginx -g 'daemon off;'
