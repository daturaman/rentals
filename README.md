# Rentals

# Considerations
- I've opted for an H2 in-memory database for the sake of expediency, more than anything. JDBI provided a fairly pain-free
integration and allowed me to focus on the application itself. The database set up occurs when the application starts and
is hard coded for convenience/demonstration only.
- I'm a strong advocate of testing and even TDD, but I've kept the tests simple to save time. Under other circumstances
 I may have opted for parameterised tests, requirements based functional testing etc.
- I've used Java Dates but am conscious of their limitations and would have chosen something like Joda Time, given more time.
- I've used ints to represent money for simplicity only.
- I've assumed that there are limitless supplies of each film.
- I've kept error handling and defensive code to a minimum. I've not implemented any health checks.
 Again, not what I would do for production ready code.

#https://nbsoftsolutions.com/blog/getting-started-with-dropwizard-testing.html
#http://www.dropwizard.io/1.1.0/docs/manual/jdbi.html
#http://jdbi.org/sql_object_api_queries/

How to start the Rentals application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/rentals-0.0.1.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Rentals
---
There are only five films and four customers (Bob, Sue, Pam and Jim) that are initialised when the application starts up.

Rent one or more films by making a post request (using a Chrome app such as Postman) with the customer's name in the
path after /rentals/ and a map of films/days
in the request body:

E.g.
http://localhost/rentals/Bob
{
    "Horror" : 5,
    "Romcom" : 7
}

Return one or more films by making a put request with the customer's name in the path after /rentals/ and a list of rentals
in the request body:
