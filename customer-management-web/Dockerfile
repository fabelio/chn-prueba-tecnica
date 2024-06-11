FROM node:alpine AS builder
WORKDIR /app
COPY . .
RUN npm install && npm run build
FROM nginx:alpine
COPY --from=builder /app/dist/customer-management-web/browser/* /usr/share/nginx/html/
#COPY /nginx.confi /etc/nginx/conf.d/default.conf