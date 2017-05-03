# Rentals

# Considerations
- I've opted for an H2 in-memory database for the sake of expediency, more than anything. JDBI provided a fairly pain-free
integration and allowed me to focus on the application itself. The database set up occurs when the application starts and
is hard coded for convenience/demonstration only.
- Normally I would keep business logic out of REST resource classes, preferring to delegate to a service. I started to do that
here but felt that I was going against the grain somewhat with how Dropwizard organises things.
- The testing is fairly rudimentary and not reflective of how I like to test day to day.
- I've used Java Dates but am conscious of their limitations and would have chosen something like Joda Time, given more time.
- I've used ints to represent money for simplicity only.
- I've assumed that there are limitless supplies of each film. Obviously not realistic.
- I've kept error handling and defensive code to a minimum. I've not implemented any health checks.
 Again, not what I would do under normal development conditions.

How to start the Rentals application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/rentals-0.0.1.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Rentals
---
There are only five films that are initialised by RentalsApplication#run on start up.

You can get a list of available films and customers with a get request:

http://localhost:8080/films
http://localhost:8080/customers

Rent one or more films by making a http post request (e.g. using a Chrome app such as Postman) with the customer's name in the
path after /rentals/ and a map of films/days
in the request body:

E.g.
http://localhost:8080/rentals/Bob
{
    "Horror" : 5,
    "Romcom" : 7
}

Return one or more films by making a http put request with the customer's name in the path after /rentals/ and a json list of rentals
in the request body (only the 'customer' and 'film' fields are necessary here)'':

E.g.
http://localhost:8080/rentals/Bob
[
    {
      "customer": "Bob",
      "film": "Horror",
      "due": "2017-04-28",
      "returned": null,
      "days": 7,
      "price": 200
    }
]