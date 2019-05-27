# learning-jdbc
Wrote this code when learning about Java Database Connectivity during summer internship..

Code written and compiled on Intellij IDEA using Java


DBEditor.java
- Connect to database and requires manual input to insert, delete and update
- Database URL section can be pre-filled in and the corresponding connection testing code in main can be commented out
- Simple code I wrote to understand, familiarize and play around with SQL and Java

JDBCListMap.java
- Connect to database and modifies database from data structure input
- First commit only finished insert function..
- Insert fucntion takes in a List of Map<String,Object> data structure and the corresponding values will be inserted
- Insert function can be modified based on the names of columns in the table
  - I created a table named "new" in my test database with columns "kills", "chair", "fire" and "idk" 
  - "kills" column is auto-incrementing and used as index 
- Example() function returns a List<Map<String,Object>> object to test insert function
- Recycled the commit() function from DBEditor.java so executing SQL requires manual confirmation each time..
  - will change to executeBatch() without manual confirmation for faster execution of large SQL commands 

