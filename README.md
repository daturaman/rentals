# Rentals

# Considerations
- Using db to keep track of points, rentals and have inventory. Keeping it simple so
using simple string names as identifiers for films, customers; no foreign keys, constraints etc

#https://nbsoftsolutions.com/blog/getting-started-with-dropwizard-testing.html
#http://www.dropwizard.io/1.1.0/docs/manual/jdbi.html
#http://jdbi.org/sql_object_api_queries/

How to start the Rentals application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/rentals-0.0.1.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
