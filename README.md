# Rentals

# Considerations
- How to represent the film inventory - file or db? Sql or NoSql?
- How to represent the three film types - New release, regular and oldie.

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
