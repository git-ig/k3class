#!/bin/sh
set -e

find /usr/share/nginx/html/static/js/ -name "main.*.js" -exec sed -i 's|REACT_APP_API_BASE_URL|https://api.dock.ink/|g' {} \;

echo "Replacement completed. Starting nginx..."
exec nginx -g "daemon off;"
