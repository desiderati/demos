FROM node:12-alpine as build-stage
WORKDIR /static
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /static/dist/ /usr/share/nginx/html/

# To avoid the problem with HTML5 History Mode.
# See: https://router.vuejs.org/guide/essentials/history-mode.html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
