#!/bin/sh
set -e

echo "Replacing API URLs in JS files..."

find /usr/share/nginx/html/static/js/ -name "*.js" -exec sed -i \
  -e 's|http://localhost:8080/|https://api.dock.ink/|g' \
  -e 's|http://localhost:8080|https://api.dock.ink|g' \
  -e 's|localhost:8080/|api.dock.ink/|g' \
  -e 's|localhost:8080|api.dock.ink|g' \
  -e 's|REACT_APP_API_BASE_URL":"http://localhost:8080/"|REACT_APP_API_BASE_URL":"https://api.dock.ink"|g' \
  {} \;

echo "API URL replacement completed. Starting nginx..."
exec nginx -g "daemon off;"




# #!/bin/sh
# set -e

# find /usr/share/nginx/html/static/js/ -name "*.js" -exec sed -i 's|http://localhost:8080/|https://api.dock.ink|g' {} \;
# find /usr/share/nginx/html/static/js/ -name "*.js" -exec sed -i 's|localhost:8080|api.dock.ink|g' {} \;

# echo "API URL replacement completed. Starting nginx..."
# exec nginx -g "daemon off;"