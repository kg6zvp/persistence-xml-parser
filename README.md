# Persistence.xml Parser

A Cleanroom persistence.xml file parser using only the standard library

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

## Examples

```java
import java.util.List;
import javax.persistence.spi.PersistenceUnitInfo;
import eisiges.persistence_xml_parser.PersistenceXmlParser;
...
URI persistenceXmlUri = .....
List<PersistenceUnitInfo> info = new PersistenceXmlParser().parse(persistenceXmlUri);
```

## Getting started

1. Add persistence-xml-parser to your project

Maven:
```xml
		<dependency>
			<groupId>es.eisig</groupId>
			<artifactId>persistence-xml-parser</artifactId>
			<version>1.0.0-SNAPSHOT</version>
		</dependency>
```

Gradle:
```groovy
repositories {
	mavenCentral()
}

dependencies {
	implementation 'es.eisig:persistence-xml-parser:1.0.0-SNAPSHOT'
}
```
