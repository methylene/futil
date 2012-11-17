futil
=====

Some classes I found myself repeating across several projects. Most of this stuff is related to JSF.

Install maven artifact like this:

	mvn clean install

then use it in your pom.xml

	<dependency>
		<groupId>org.meth4j.futil</groupId>
		<artifactId>futil</artifactId>
		<version>0.3.3</version>
	</dependency>

[futil-square-validator](https://github.com/methylene/futil-square-validator) is an example project.

### How can it make your life easier?

*	~~Reliably validate input fields, not only when they are non-empty. FacesValidator will not trigger on empty onput, one of the worst "features" of JSF imo. One way out is to use a FacesConverter instead. To make this easy, the abstract class [ValidatingConverter](https://github.com/methylene/futil/blob/master/src/main/java/org/meth4j/futil/ValidatingConverter.java) can be extended~~ UDPATE: ValidatingConverter is now a javax.faces.validator.Validator, which will validate empty fields too. For this, javax.faces.VALIDATE_EMPTY_FIELDS must be set to true. This feature was not available before JSF 2.0

*	~~[Validating an input field using value from another input field](https://github.com/methylene/futil-square-validator/blob/master/src/main/java/some/group/SideValidator.java) This is a standard JSF feature (using binding and f:attribute tag) and ValidatingConverter makes this especially easy for the case that there is only one attribute per input. The name of the attribute must be org.meth4j.futil.attribute~~

*	~~Internationalised validation error messages. Simply extend ValidatingConverter and throw an appropriate org.meth4j.futil.ValidatorException. The base name of your message resource bundle must be [org.meth4j.futil.messages](https://github.com/methylene/futil-square-validator/blob/master/src/main/resources/org/meth4j/futil/messages_en.properties), unless you put an optional config file /META-INF/services/org.meth4j.futil.properties in your classpath. This file then has to define a propertiy `bundle_name`, see [org.meth4j.futil.properties](https://github.com/methylene/futil-square-validator/blob/master/src/main/resources/META-INF/services/org.meth4j.futil.properties)~~

*	Flash messages that can be [used](https://github.com/methylene/futil-square-validator/blob/master/src/main/java/some/group/SquareBean.java) anywhere, in ManagedBeans or servlets. To use this feature a [JSF PhaseListener](https://github.com/methylene/futil/blob/master/src/main/java/org/meth4j/futil/MessageFilter.java) must be registered in [faces-config.xml](https://github.com/methylene/futil-square-validator/blob/master/src/main/webapp/WEB-INF/faces-config.xml)

*	Reduce FacesMessage construction boilerplate. Use static methods in class [Message](https://github.com/methylene/futil/blob/master/src/main/java/org/meth4j/futil/Message.java).

*	~~In the xhtml: Easily find out if validation has failed for a particular input field. This can be used to change the css class for that field, for instance. example [here](https://github.com/methylene/futil-square-validator/blob/master/src/main/webapp/WEB-INF/tags/some/group/inputText.xhtml)~~

*	Enforces use of enums for resource bundle message keys

*	Load classpath resources as strings or properties objects, in one line, and without checked exceptions. See [ResourceLoader](https://github.com/methylene/futil/blob/master/src/main/java/org/meth4j/futil/ResourceLoader.java)

*	Provides the convenient interface [IKey](https://github.com/methylene/futil/blob/master/src/main/java/org/meth4j/futil/IKey.java) which represents key-label pairs

*	Convert a bunch of IKeys (think enum constants) into a bunch of select items quickly, see this in action [here](https://github.com/methylene/futil-square-validator/blob/master/src/main/java/some/group/ContentsOf.java)
