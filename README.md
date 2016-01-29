Introduction
============

This is a school project (www.codecool.com) for learning the basics of Java and XML. It can create Person and Movie
objects and is able to represent them in XML format.


Structure
====================================

The application has four classes. Apart from the Movie and Person classes there are Tools and MovieManager classes
and two enumaration: Genre and Gender.
The Person class has the following attributes: firstname, lastname, gender and two flags (boolean type property) 
if the current person has already won the Oscar and if the current person has already won the Golden Globe.
The Movie class has the following attributes: title, genre, duration (in minutes), rate and a list of 
actresses/actors (called cast) who are Persons.
Both Movie and Person classes have a public method toXMLString(), which creates an xml like String from the 
properties set in the current object and returns with it.
Tools class among other methods used by the other classes has a countMoviesPerPerson and a getMovieTitles method.
In the MovieManager class is the main method which instantiate some Persons and Movies and write them out in a XML
file.


Contact
========================================

If you have problems, questions, ideas or suggestions, please contact me on csaszarhunor@gmail.com
