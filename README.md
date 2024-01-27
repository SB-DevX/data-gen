Spring Boot Project For Creating Json Data
--- 
- Generates data based on json schema provided.
- Generates data in batches using virtual threads.
- Size of a batch is 1000(**default size**). Can be changed by overwriting the property `data.file.gen.batch.size` in `application.properties` file.
- Sample Request for calling the `POST` endpoint `http://localhost:8080/json/data/generate`:
- ```json
  {
    "schema": {
        "$schema": "http://json-schema.org/draft-07/schema#",
        "type": "object",
        "properties": {
            "firstName": {
                "type": "string"
            },
            "lastName": {
                "type": "string"
            },
            "status": {
                "type": "boolean"
            },
            "favColor": {
                "enum": [
                    "red",
                    "amber",
                    "green",
                    "orange",
                    "blue"
                ]
            },
            "Address": {
                "type": "object",
                "properties": {
                    "line1": {
                        "type": "string"
                    },
                    "line2": {
                        "type": "string"
                    }
                }
            }
        }
    },
    "noOfTimes": 10,
    "outputFilePath": "D:\\data\\__generated__\\file.json",
    "genType": "FILE_GEN"
    }
  ```