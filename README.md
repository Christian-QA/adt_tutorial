# ADT Tutorial

A Play based weather website that requires a bit of love. 

The backend `WeatherGen` mimics a backend service that returns the forecast and
a status. This backend service is notoriously flaky and fails 50% of the time!

Do not touch the `WeatherGen`, everything else is free game. The code is written poorly so refactor away!

## Tasks
1. The backend API only manages 4 different types of weather: `"Sunny", "Rainy", "Snowy", "Cloudy"`. 
   Using Sum types (or pattern), simplify the possible weather types throughout our frontend service
   so we don't have to manage `strings`. This will save us from introducing string related bugs like typo, 
   spelling mistakes and so on. Call your new Sum type `Forecast`
   
2. The backend API can return these error codes: `OK, NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR, SERVICE_UNAVAILABLE`.
Using a Sum or Product type (or pattern), simplify the possible error codes we need to handle. This will save us from bugs where we mistype 
   an error code. Call your new Product type `HttpError`
   
3. [Optional] Using a hybrid approach, can you think of a way to combine `Forecast` and `HttpError`?
4. We really should not be showing the user our exceptions, can you think of a way to 
refactor the exceptions from the code and display a user-friendly error page?
