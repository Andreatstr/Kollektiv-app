module restclient
{
// Requires for Spring Boot and WebFlux
requires spring.boot;
requires spring.boot.autoconfigure;
requires spring.web;
requires spring.webflux;

// Requires for JUnit testing (only in the test scope, this is optional)
exports restclient;

}