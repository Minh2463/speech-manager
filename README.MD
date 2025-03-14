## Prerequisites
- Java 17 (https://www.oracle.com/java/technologies/downloads/#java17)
- Gradle 8.8
- PostgreSQL database
- Run using IntelliJ

## API

### Create A New Speech
`POST /speech/create`
* Create a new speech
#### Request
    POST /speech/create
#### Request Body 
    {
        "author": "Minh Dao",
        "speechName": "Car 123",
        "content": "Intro to car mechanic",
        "date": "03/25/2025",
        "keywords": ["car", "engine", "intro"]
    }

#### Response
    Speech created successfully. ID is {newUserID}
------
### Search All Speeches
`GET /speech/search/all`
* Get all speeches
#### Request
    GET /speech/search/all

#### Response
    [
        {
            "id": 1,
            "date": "03/25/2025",
            "speechName": "Game 123",
            "author": "Dat Vu",
            "content": "Intro to game mechanic",
            "keywords": [
                "plane",
                "game video",
                "intro"
            ]
        },
        {
            "id": 2,
            "date": "03/20/2025",
            "speechName": "Car 123",
            "author": "Minh Dao",
            "content": "Intro to car mechanic",
            "keywords": [
                "car",
                "b12",
                "intro",
                "engine"
            ]
        }
    ]
------
### Search Using Speech Name
`GET /speech/search/name`
* Get speeches from a given name parameter. The search supports **partial matching**, results will include entries where the `speechName` contains the given value.
#### Query Parameters
- `name` (String, required): Filters results by the speech's name (**partial match allowed**).
#### Request
    GET /speech/search/name?name={speech-name}
### Response
    [
        {
            "id": 2,
            "date": "03/20/2025",
            "speechName": "Car 123",
            "author": "Minh Dao",
            "content": "Intro to car mechanic",
            "keywords": [
                "car",
                "b12",
                "intro",
                "engine"
            ]
        }
    ]   
------
### Search Using Keyword or Author Name
`GET /speech/search/extra`
* Get speeches from one or both given parameter(s). The search supports **partial matching**, results will include entries where the `author` contains the given value.
#### Query Parameters 
- `author` (string, optional): Filters results by the author's name (**partial match allowed**). 
- `keyword` (string, optional): Filters results by the keyword set by author.
#### Request
    GET /speech/search/name?author=[author-name]&keyword={keyword}

### Response
    [
        {
            "id": 2,
            "date": "03/20/2025",
            "speechName": "Car 123",
            "author": "Minh Dao",
            "content": "Intro to car mechanic",
            "keywords": [
                "car",
                "b12",
                "intro",
                "engine"
            ]
        }
    ]
------
### Search Using Speech ID
`GET /speech/search/id`
* Get speeches from a given ID parameter.
#### Query Parameters
- `id` (int, required): Filters results by the speech's ID.
#### Request
    GET /speech/search/id?id={search-id}
### Response
    {
        "id": 2,
        "date": "03/20/2025",
        "speechName": "Car 123",
        "author": "Minh Dao",
        "content": "Intro to car mechanic",
        "keywords": [
            "car",
            "b12",
            "intro",
            "engine"
        ]
    }
------
### Edit a Speech
`PUT /speech/edit`
* Updates content or metadata of an existing speech. **Only the fields that need to be edited should be provided** in the request body.
#### Query Parameters
- `id` (int, required): ID of the speech that will be edited.

#### Request
    PUT /speech/edit?id={edit-id}

#### Request Body
All fields are **optional**. Only provide the fields that need updating.

    {
        "author": "Gs Minh",
        "speechName": "KTV 123",
        "content": "Intro to car mechanic",
        "date": "03/25/2025",
        "keywords": ["car", "engine", "intro"]
    }

#### Response
New information of updated speech will be response

    {
        "id": 1,
        "date": "03/25/2025",
        "speechName": "KTV 123",
        "author": "Gs Minh",
        "content": "Intro to game mechanic",
        "keywords": [
            "car",
            "engine",
            "intro"
        ]
    }
------
### Delete a Speech
`DELETE /speech/delete`
#### Query Parameters
- `id` (int, required): ID of the speech that will be deleted.

#### Request
    DELETE /speech/delete?id={edit-id}

#### Response
    Speech deleted successfully
