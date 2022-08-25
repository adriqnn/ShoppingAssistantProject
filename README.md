# ShoppingAssistantProject
Project Structure:
Small Project with Spring Framework and Spring Security.
Using Thymeleaf and Bootstrap for server-side rendering.
Has Rest API services and some front-end with javascript and fetch requests to the db through the api.
Database backend uses MySQL and Spring Data/Hibernate.
The project has ~93% classes and ~77% lines coverage by 114 Unit and Integration tests.
The project has Data Validation using custom annotations and spring boot validation for the Binding Models.
There is Error-Handling for the services and repository information and an error.html for wrong url requests.
The project has one Interceptor that is used to log html requests to the server.
There is a scheduler - using chron that re-rolls the active daily discounts each day at 00:00.
Using ModelMapper to map and transform classes for easier information transfer.
There us one Spring Event that is used for the projects functionality.

Project Functionality:
Project has two roles - Admin and User with generated pictures.
Admin/Users can access control panel.
Control panel has options to change username, email, password, first name and picture.
Users can access shopping list to create lists to help them with their shopping needs.
Users have access to stores page that shows in which store there are discounted product categories for the week.
Users are able also to use randomly generated daily discounts in the stores that the application works with.
The Admin can see registered users.
The Admin has the ability to add stores to the application.
The Admin must control the discounted pricing categories in stores each week by manually changing them.
The Admin can re-roll currently active discounts at any point.
