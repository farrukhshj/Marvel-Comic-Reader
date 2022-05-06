# Marvel-Comic-Reader
External libraries used :
Picasso - for loading images

retrofit - for network calls

gson - for converting network json response to model classes

Android libraries used :
Livedata
viewmodel
mockito

How the app works:
1. Making a network call to /v1/public/comics/{comicId} to fetch one comic by id 1332
2. md5("$timeStamp$privateKey$apiKey") is used to create hash for making network calls
