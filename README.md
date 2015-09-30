# SpringFXExample

An example of integrating the Spring framework into a JavaFX Application. The primary aim is for the `FXMLLoader` 
to retrieve the controller instances from the Spring bean factory (aka application context). This is achieved by
setting a controller factory on the `FXMLLoader` which delegates to the bean factory: essentially this is just
`loader.setControllerFactory(applicationContext::getBean)`. Additionally, by setting a custom builder factory on the
loader, the bean factory can be used to generate instances declared in the FXML itself. (This behavior is enabled but not
used in this application.)

Using a dependency injection framework such as Spring to manage the controllers allows for an easy mechanism to let the 
controllers have access to a shared model instance. This in turn means the controllers can observe (bind to) and modify a shared
set of data, allowing for interactivity without coupling the controllers to each other.

The example builds on the standard [Oracle tutorial for `TableView`](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE).

The code is divided into the following packages:

## UI Packages

### table 

Contains the FXML and presenter (controller) class for the `TableView`.

### editor 

Contains the FXML and presenter for editing the current contact, or for entering details for a new contact.

### controls

Contains the FXML and presenter for the "New","Delete", and "Mail" buttons.

### search

Contains the FXML and presenter for the search functionality.

## Other packages

### model

Contains the data representation class (`Contact`), and the application state data model.

### data

Contains a simple data accessor class (no persistence support to keep the example simple).

### fxml

A support class to link Spring to the `FXMLLoader`

### application

The `Application` subclass, application FXML, and the Spring application configuration. Also include a testing
class to demo showing just a portion of the UI (the table).

## Dependencies

The only explicit dependency is on Spring-context:

```xml
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>4.2.0.RELEASE</version>
  	</dependency>

## Links

This example (or a close relative of it) is described in [this blog post](https://www.marshall.edu/genomicjava/2015/09/27/experiments-with-spring-and-javafx/).
