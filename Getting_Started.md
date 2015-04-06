## 1 - Running Finantial Faces from WAR file ##
  * 1.1 - get the war file from [here](http://finantial-faces.googlecode.com/svn/trunk/FinantialFaces/WAR/FinantialFaces.war)
  * 1.2 - chose a database dumb: if you want clean database only with necessary data to run the app take [this](http://code.google.com/p/finantial-faces/source/browse/trunk/FinantialFaces/Sql%20dumps/clean-db.sql) but if you want a database with some data to make some queries and generate some reports get [this one](http://code.google.com/p/finantial-faces/source/browse/trunk/FinantialFaces/Sql%20dumps/full-db.sql) and restore it in mysql(let the option "target schema:original schema" selected)
  * 1.3 - deploy the war file in a Java EE 6 server compilant like glassfish (tested with glassfish 3.0)
  * 1.4 - acess the system at http://localhost:8080/FinantialFaces
  * 1.5 - login with login:admin pass:admin


## 2 - Running from source ##
  * 2.1 - checkout Finantial Faces from http://finantial-faces.googlecode.com/svn/trunk/FinantialFaces
  * 2.2 - if your IDE claims for missing jars you can find them in the libs folder under CinemaWeb/lib
  * 2.3 - create the database as in steps 1.2 and 1.3
  * 2.4 - create a data source named finantial\_ds and point to the database created in early steps(you can create it in your application server or in you favorite IDE - eg. Netbeans will claim for missing data source and you can resolve this with right click in the project name).
  * 2.5 - configure a Java EE 6 server compilant to run the project
  * 2.6 - clean & build and run the project

