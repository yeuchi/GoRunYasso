# GoRunYasso
Exercise on Github action and Golang service.

There are 8 sample steps data collected with RunYasso800 mobile app. \
They are available as text files run1-8.txt

<img width="200" src="https://user-images.githubusercontent.com/1282659/93022761-7c7f7b00-f5b0-11ea-9801-1961aff5f637.png">

### GET
Get a default run dataset (1-8) on localhost. \
http://127.0.0.1:8080/run/1 \
<img width="600" src="https://user-images.githubusercontent.com/1282659/93022762-7d181180-f5b0-11ea-9632-1746d8f91932.png">

### POST

Android Kotlin app, RetrofitEx, is created to invoke RESTful operatior: GET / POST. \
POST body message is composed of a json string; derived from the data class Run. \
<img width="400" alt="data" src="https://user-images.githubusercontent.com/1282659/98486036-7be61800-21e0-11eb-99a2-870c1159ccfe.png">

With Fiddler, we can see the traffic onRoute to GoRunYasso service.
<img width="600" alt="fiddler" src="https://user-images.githubusercontent.com/1282659/98486038-7ee10880-21e0-11eb-86ba-8574f3e7a603.png">

In PostRunHandler, we can see the Request.Body has received the Run payload.
<img width="800" alt="received" src="https://user-images.githubusercontent.com/1282659/98486039-7ee10880-21e0-11eb-88c4-6d70cc4397b5.png">


# References

1. Go Web Examples : Routing (using gorilla/mux) \
https://gowebexamples.com/routes-using-gorilla-mux/

2. How to Parse a JSON Request Body in Go by Alex Edwards, 21st October 2019 \
https://www.alexedwards.net/blog/how-to-properly-parse-a-json-request-body

