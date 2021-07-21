# Installation
After cloning the project go inside its folder and run

` docker compose up `

It will compile the app image and deploy its container in conjunction with a postgres one.

The app uses port 8080

# Endpoints

Interacting with the API can be made using localhost:8080 during development

`localhost:8080/`

### Get all fields
GET request to fields/
`localhost:8080/fields`

### Get a single field using an id parameter
GET request to fields/{id}

`localhost:8080/fields/c0f63e74-n27ef-4931-fgb3-0e770ae9iu45`

### Create an item
POST request to fields/

`localhost:8080/fields/`

### Update an item
PUT request to fields/

`localhost:8080/fields`

### Delete an item using an id parameter
DELETE request to fields/{id}

`localhost:8080/fields/c0f63e74-n27ef-4931-fgb3-0e770ae9iu45`

# Postman
A Postman requests export is available within the project, just import **agro-api.postman_collection.json**

# Considerations 

For most projects I would store the entities with a regular integer/long primary key id field, but for this project let the string ids from the request being used as primary keys just for simplicity.

I worked to generate the requests to the Polygon and WeatherHistory endpoints until the requests worked, and I just got a invalid Api Key error, after this point I used MockServer to mock the request responses.
