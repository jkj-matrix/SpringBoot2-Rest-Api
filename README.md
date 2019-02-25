# SpringBoot2

# SpringBoot2 Rest API features:

- H2 in-memory database. With prepopulated table data.
- Spring Data
- Custom Response object
- Custom Exception handling
- Junit

## root$ curl -X GET http://localhost:8080/person/all
``` json
{
    "status": "Success",
    "persons": [
        {
            "id": 1,
            "fullName": "John Paul",
            "gender": "M",
            "dateOfBirth": "1998-01-23"
        },
        {
            "id": 2,
            "fullName": "Mary Sy",
            "gender": "F",
            "dateOfBirth": "2012-01-23"
        },
        {
            "id": 3,
            "fullName": "Ryan Anderson",
            "gender": "M",
            "dateOfBirth": "1985-08-22"
        },
        {
            "id": 4,
            "fullName": "Rod Drew",
            "gender": "F",
            "dateOfBirth": "2009-03-12"
        },
        {
            "id": 5,
            "fullName": "Sally Copper",
            "gender": "M",
            "dateOfBirth": "1985-11-30"
        }
    ],
    "error": null
}
```

## root$ curl -X GET http://localhost:8080/person/1
``` json
{
    "status": "Success",
    "persons": [
        {
            "id": 1,
            "fullName": "John Paul",
            "gender": "M",
            "dateOfBirth": "1998-01-23"
        }
    ],
    "error": null
}
```


#### root$ curl --header "Content-Type: application/json" --request POST --data '{"fullName": "James Bond","gender": "M","dateOfBirth": "1975-03-25"}' http://localhost:8080/person/  
``` json
{
    "status": "Success",
    "persons": [
        {
            "id": 6,
            "fullName": "James Bond",
            "gender": "M",
            "dateOfBirth": "1975-03-25T00:00:00.000+0000"
        }
    ],
    "error": null
}
Verification:
root$ curl -X GET http://localhost:8080/person/6
{
    "status": "Success",
    "persons": [
        {
            "id": 6,
            "fullName": "James Bond",
            "gender": "M",
            "dateOfBirth": "1975-03-25"
        }
    ],
    "error": null
}
```

#### root$ curl --header "Content-Type: application/json" --request PUT --data '{"fullName": "Updated Person","gender": "M","dateOfBirth": "1993-03-30"}' http://localhost:8080/person/1
 ``` json 
{
    "status": "Success",
    "persons": [
        {
            "id": 1,
            "fullName": "Updated Person",
            "gender": "M",
            "dateOfBirth": "1993-03-30T00:00:00.000+0000"
        }
    ],
    "error": null
}
Verification:
root$ curl -X GET http://localhost:8080/person/1
{
    "status": "Success",
    "persons": [
        {
            "id": 1,
            "fullName": "Updated Person",
            "gender": "M",
            "dateOfBirth": "1993-03-30"
        }
    ],
    "error": null
}
```
