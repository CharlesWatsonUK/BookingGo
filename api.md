# BookingGo Ride API Docs

Access the API with the URL
`http://localhost:8080/bookinggo/ride`

An example request with parameters
`localhost:8080/bookinggo/ride?pickup=51.470020,-0.454295&dropoff=51.470021,-0.454280&passengers=1`

### Query Parameters
The Ride API takes the following parameters:
* pickup
  * Pickup location for the journey
  * lat, long e.g. 3.410632,-2.157533
* dropoff
  * Drop off location for the journey
  * lat, long e.g. 3.410632,-2.157533

### Responses
##### 200 OK
```
{
    "pickup": "51.470020,-0.454295",
    "dropoff": "51.470021,-0.454280",
    "passengers": 1,
    "rides": [
        {
            "supplier": "Eric's Taxis",
            "carType": "PEOPLE_CARRIER",
            "price": 595839
        },
        {
            "supplier": "Dave's Taxis",
            "carType": "MINIBUS",
            "price": 487853
        },
        {
            "supplier": "Eric's Taxis",
            "carType": "LUXURY_PEOPLE_CARRIER",
            "price": 438375
        },
        {
            "supplier": "Eric's Taxis",
            "carType": "LUXURY",
            "price": 67867
        }
    ]
}
```
##### 400 Bad Request
```
{
    "pickup": "51.470020,-0.454295",
    "dropoff": "51.470021,-0.454280",
    "passengers": 20,
    "message": "Invalid input! Please make sure your location coordinates are properly formed, e.g.: 51.470020,-0.454295  also please ensure your passenger number is between 1 and 16 inclusive."
}
```
