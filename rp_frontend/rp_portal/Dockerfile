# Stage 1: Build the React application
FROM node:lts-buster

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json
COPY package.json /app
COPY package-lock.json /app

# Install the dependencies
RUN npm ci

# Copy the rest of the application code
COPY . /app

EXPOSE 3000

CMD ["npm", "start"]