# learning-jdbc
Wrote this code when learning about Java Database Connectivity during summer internship..

Code written and compiled on Intellij IDEA using Java

For the Java files, I worked on a database that contained two tables

Table 1: New
- kills (int) [Auto-incrementing Index]
- fire (int)
- idk (text)
- chair (text)


DBEditor.java
- Connect to database and requires manual input to insert, delete and update
- Database URL section can be pre-filled in and the corresponding connection test code in main can be commented out
- Simple code I wrote to understand, familiarize and play around with SQL and Java


JDBCListMap.java
- Connect to database and modifies database from data structure input
- Implemented insert and delete function
- Insert fucntion takes in a List of Map<String,Object> data structure and the corresponding values will be inserted
- Insert function can be modified based on the names of columns in the table
- Example() function returns a List<Map<String,Object>> object to test insert function


JDBCClass.Java - (SQLClass.Java)
- Connect to database and modifies database from a class object (SQLClass)
- SQLClass can be created using the set() functions
- JDBCClass.Java uses get() functions to obtain values from SQLClass and passes values into insert/delete functions
