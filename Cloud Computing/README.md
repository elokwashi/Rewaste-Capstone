# Cloud Computing Path
Utilizing the capabilities of cloud computing and the reliability of GCP, we have built an optimized APIs using Node.js and Express.js, delivering outstanding performance and unlimited scalability. In addition, we utilize MongoDB, a highly flexible and scalable NoSQL database. MongoDB provides a document-oriented data model that aligns with the API development requirements.


## API URL
[Destinations API](https://trash-bfj2j76g2q-et.a.run.app)
## Documentation


### Sign Up

- Method: POST
- Url: /api/v1/auth/signup
- Request Body 
  - name as string
  - email as string, must be unique
  - password as string
- Response

```json
{
  "error": false,
  "message": "User Created"
}
```

### Sign In

- Method: POST
- Url: /api/v1/auth/signin
- Request Body 
  - email as string
  - password as string
- Response

```json
{
    "error": false,
    "message": null,
    "data": {
        "userId": "647f2cdb20447c1c0238964b",
        "name": "yogi1",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0N2YyY2RiMjA0NDdjMWMwMjM4OTY0YiIsImlhdCI6MTY4NjE0NzM1OX0.LaGPkU5l13M_NKox03sayE-Q56qUhhQOOD71MNgk8Rg"
    }
}
```
<br>

### Profile

#### Post Profile
- Method: POST
- Url: /api/v1/users/profile/images
- Headers
  - Content-Type: multipart/form-data
  - Authorization: Bearer token
- Request Body : photo as file, must be a valid image file (png, jpg, jpeg)
- Response 

```json
{
    "error": false,
    "message": null,
    "data": {
        "userId": "647c62fa9286ac93bf8f6f5d",
        "email": "1masdim@gmail.com",
        "imageUrl": "https://storage.googleapis.com/bucket_rest_api/user-img/FHOTzPyWUAYUdl_-1686147653477.jpg",
        "_id": "648092453652810e76f543e8",
        "createdAt": "1686147653659",
        "__v": 0
    }
}
```
#### Get Profile

- Method: GET
- Url: /api/v1/users/profile
- Header: Authorization (Bearer token)

```json
{
    "error": false,
    "message": null,
    "data": {
        "DataProfil": {
            "_id": "647c62fa9286ac93bf8f6f5d",
            "name": "dimas",
            "email": "1masdim@gmail.com",
            "password": "$2b$12$f4.qM.QubPpFfEaawbAUA.Gq8rhM7gzEPUvgz04mSTv30prjFfy1q",
            "createdAt": "1685873402025",
            "imageUrl": "https://storage.googleapis.com/bucket_rest_api/user-img/FHOTzPyWUAYUdl_-1686147653477.jpg",
            "__v": 0
        }
    }
}
```

### Upload Image Article

#### Get Image Article

- Method: GET
- Url: /api/v1/articles/upload
- Header: Authorization (Bearer token)

```json
{
    "error": false,
    "message": null,
    "data": [
        {
            "_id": "647c3fe37ec418ac3b28c4b9",
            "url": "https://storage.googleapis.com/bucket_rest_api/images/download-1685864419539.jpeg",
            "name": "download.jpeg",
            "__v": 0
        }
    ]
}
```

### Article

#### Post Article 

- Method: POST
- Url: /api/v1/articles
- Headers
  - Content-Type: multipart/form-data
  - Authorization: Bearer token
- Request Body 
  - photo as file, must be a valid image file (png, jpg, jpeg)
  - name as string, must be unique
  - description as string
- Response 

```json
{
    "error": false,
    "message": "success"
}
```

#### Get Article

- Method: GET
- Url: /api/v1/articles
- Header: Authorization (Bearer token)

```json
{
    "error": false,
    "message": null,
    "data": [
        {
            "_id": "647c3fe47ec418ac3b28c4bb",
            "imageUrl": "https://storage.googleapis.com/bucket_rest_api/images/download-1685864419539.jpeg",
            "name": "Kucing",
            "description": "Kucing makan",
            "__v": 0
        }
    ]
}
```
