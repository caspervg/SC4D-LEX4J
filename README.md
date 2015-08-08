SC4Devotion-LEX4J
======================

This Java library provides methods and beans to communicate with the SC4Devotion Lot Exchange API. It is currently compatible with the v4 version of the LEX API, provided you use a version higher than v4.0.0.

### Installation
Installation is easy using the Maven Central Repository. Just add the following snippet to your ```pom.xml```
```xml
<dependencies>
  ...
  <dependency>
      <groupId>net.caspervg.lex4j</groupId>
      <artifactId>LEX4J</artifactId>
      <version>4.1.0</version>
  </dependency>
  ...
</dependencies>
```

If you run the tests, make sure to rename auth_example.properties to auth.properties and fill in a username.

### Changelog
##### v0.5
* Initial release

##### v0.6
* Improved documentation for the search filters

##### v0.7
* Switched to v2 LEX API
* Add support for new search query

##### v0.7.5
* Improved handling of incorrect search filter parameters, will now throw a FilterParameterException if you supply an incorrect parameter type
* Further improved documentation for the search filters
* Fixed a bug that caused downloads in the Lot bean to always stay at 0

##### v0.8
* Further improved search filter documentation

##### v0.9
* Switched to v3 LEX API
* Add support new search dependency results
* Add support for full urls for images, etc.

##### v1.0
* Added generic methods that can return any Class extending one of the LEX API beans. This is useful if you want to add extra functionality to for example the User class, but still want easy JSON deserialisation.

##### v1.1
* Made fields in all beans ```protected``` instead of ```private``` so that overriding classes can edit them easily, as there are no setters.

##### v4.0.0
* Undo generic method support. Seldom used, rarely useful.
* Use Jackson instead of GSON for (de)serialization. GSON didn't natively support the API v4 datetime format (ISO 8601).
* Add support for LEX API v4 and the "extra info" parameters for dependencies, comments, votes and users

##### v4.1.0
* Add support for recursive dependencies. Allows flatting them as well using the new DependencyList#asSet() and #asList() methods

### Documentation
Documentation (Javadoc) is available at http://caspervg.net/public/sc4d-lex4j

### Contribute
Feel free to contribute ideas, suggestions and code.