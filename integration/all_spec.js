var frisby = require('frisby');

var baseUrl = 'http://localhost:8080';

var jsonTrue = {json: true}; // frisby sadly requires this all the time

var HttpStatus = {
    OK: 200,
    NOT_FOUND: 404,
    CREATED: 201,
    BAD_REQUEST: 400
};


frisby.create('After start, todo list should be empty')
    .get(baseUrl + '/todoitems')
    .expectStatus(HttpStatus.OK)
    .expectJSON([])
    .expectJSONLength(0)
    .toss();

frisby.create('Trying to access nonexistent item should result in an error')
    .get(baseUrl + '/todoitems/424242')
    .expectStatus(HttpStatus.NOT_FOUND)
    .toss();

frisby.create('Creating new todo item')
    .post(baseUrl + '/todoitems', {
        description: 'Some description here',
        importance: true
    }, jsonTrue)
    .expectStatus(HttpStatus.CREATED)
    .expectJSON({
        description: 'Some description here',
        importance: true
    })
    .expectJSONTypes({
        id: Number
    })
    .afterJSON(function (json) {
        frisby.create('Then retrieving newly created item')
            .get(baseUrl + '/todoitems/' + json.id)
            .expectStatus(HttpStatus.OK)
            .expectJSON({
                description: 'Some description here',
                importance: true
            })
            .toss();
    })
    .toss();

frisby.create('Creating new todo item')
    .post(baseUrl + '/todoitems', {
        description: 'Some description here',
        importance: true
    }, jsonTrue)
    .expectStatus(HttpStatus.CREATED)
    .expectJSON({
        description: 'Some description here',
        importance: true
    })
    .expectJSONTypes({
        id: Number
    })
    .afterJSON(function (json) {
        frisby.create('Then updating it with valid properties')
            .put(baseUrl + '/todoitems/' + json.id, {
                description: 'Updated',
                importance: true
            }, jsonTrue)
            .expectStatus(HttpStatus.OK)
            .expectJSON({
                description: 'Updated',
                importance: true
            })
            .afterJSON(function (json) {
                frisby.create('Should return updated item')
                    .get(baseUrl + '/todoitems/' + json.id)
                    .expectStatus(HttpStatus.OK)
                    .expectJSON({
                        description: 'Updated',
                        importance: true
                    })
                    .toss()

            })
            .toss();
    })
    .toss();


frisby.create('Trying to post invalid (here empty) json should result in an error')
    .post(baseUrl + '/todoitems', {}, jsonTrue)
    .expectStatus(HttpStatus.BAD_REQUEST)
    .toss();

frisby.create('Trying to update nonexistent item should result in an error')
    .put(baseUrl + '/todoitems/424242', {
        description: 'Updated',
        importance: true
    }, jsonTrue)
    .expectStatus(HttpStatus.NOT_FOUND)
    .toss();


frisby.create('Creating new todo item')
    .post(baseUrl + '/todoitems', {
        description: 'Some description here',
        importance: true
    }, jsonTrue)
    .expectStatus(HttpStatus.CREATED)
    .expectJSON({
        description: 'Some description here',
        importance: true
    })
    .expectJSONTypes({
        id: Number
    })
    .afterJSON(function (json) {
        frisby.create('Then trying to update it with wrong props')
            .put(baseUrl + '/todoitems/' + json.id, {}, jsonTrue)
            .expectStatus(HttpStatus.BAD_REQUEST)
            .toss()

    })
    .toss();

frisby.create('Creating new todo item')
    .post(baseUrl + '/todoitems', {
        description: 'Some description here',
        importance: true
    }, jsonTrue)
    .expectStatus(HttpStatus.CREATED)
    .expectJSON({
        description: 'Some description here',
        importance: true
    })
    .expectJSONTypes({
        id: Number
    })
    .afterJSON(function (json) {
        frisby.create('Then deleting it')
            .delete(baseUrl + '/todoitems/' + json.id)
            .expectStatus(HttpStatus.OK)
            .after(function () {
                frisby.create('Should make it disappear')
                    .get(baseUrl + '/todoitems/' + json.id)
                    .expectStatus(HttpStatus.NOT_FOUND)
                    .toss()

            })
            .toss()

    })
    .toss();

frisby.create('Trying to delete nonexistent item should result in an error')
    .delete(baseUrl + '/todoitems/424242')
    .expectStatus(HttpStatus.NOT_FOUND)
    .toss();
