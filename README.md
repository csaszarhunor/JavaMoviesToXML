Introduction
============

This is a school project (www.codecool.com) for learning Java and practicing serialization and networking.


Structure
====================================

The application has an interface Buyable and an abstract class Product. Implemented and extended from these are the 
following main classes: Movie, Game, Book and a forth one, not being the child of any of mentioned, Person.
There are two main methods, one in RentManager the other one in ObjectServer. These two communicate with each other 
through sockets, the ObjectServer having the ServerSocket. The RentManager can send and receive objects to ObjectServer
to serialization and storing in a file. The ObjectServer can recieve and store and read serialized objects and send them 
to RentManager.

The Buyable interface has an abstract method, getPrice.
The Product abstract class has the following attributes: id, title, renter (Person) and getTitle and getRenter methods.

Apart from the inherited attributes the Movie class has the following fields: genre, duration (in minutes), rate, a list 
of Persons (called cast) and price. It's getInvestment method calculates the summary of the cast's salary. 
Apart from the inherited attributes the Game class has the following fields: preOrdered, price and a list 
of Persons (called staff). It's getInvestment method calculates the summary of the staff's salary.
Apart from the inherited attributes the Book class has the author field. It's getInvestment method returns the author's 
salary.
The Person class has the following attributes: firstname, lastname, gender and salary.

The IdGenerator class' generate method creates an ID number and it is called with every instantiation of the Movie, Game 
and Book classes.
In the RentManager class is the main method which instantiate some Persons, Movies, Games and Books.


Contact
========================================

If you have problems, questions, ideas or suggestions, please contact me on csaszarhunor@gmail.com
