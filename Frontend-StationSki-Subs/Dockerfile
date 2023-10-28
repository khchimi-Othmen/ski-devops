# Use an official Node.js runtime as the base image
FROM node:14

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the rest of your app's source code to the container
COPY . .

# Expose the port your Angular app will run on
EXPOSE 4200

# Start your Angular app
CMD ["npm", "start"]
