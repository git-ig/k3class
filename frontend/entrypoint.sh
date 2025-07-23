#!/bin/sh
set -e

find /usr/share/nginx/html/static/js/ -name "*.js" -exec sed -i 's|http://localhost:8080|https://api.dock.ink|g' {} \;
find /usr/share/nginx/html/static/js/ -name "*.js" -exec sed -i 's|localhost:8080|api.dock.ink|g' {} \;

echo "API URL replacement completed. Starting nginx..."
exec nginx -g "daemon off;"