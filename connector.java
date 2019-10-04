import java.sql.*;

class Connector {
  private static final String dbUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private static final String username = "root";
  private static final String password = "kelpians";
  Connection myConnection;
  Statement myStatement;

  public Connector() {
    try {
      //get connection
      this.myConnection = DriverManager.getConnection(dbUrl, username, password);
      //create statement obj
      this.myStatement = myConnection.createStatement();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void query(String field, String table) {
    try {
      //create sql statement
      String sql = "Select " + field + " from " + table;
      //execute query
      ResultSet myResultSet=myStatement.executeQuery(sql);
      //output result
      while(myResultSet.next()) {
        System.out.println("Name: " + myResultSet.getString("name"));
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void update(String table, String field, String id, String newValue) {
    try {
      //create sql Statement
      String sql = "update " + table + " set " + field + " = " + newValue + " where id = " + id;
      //countUpdated is number of rows updated
      int countUpdated = myStatement.executeUpdate(sql);
      System.out.println(countUpdated + " records affected.");
      query("*", "fake_data");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
  //TODO Add id / row selection
  public void delete() {
    try {
      String sqlDelete = "delete from fake_data where id = 1";
      System.out.println("The SQL statement is: " + sqlDelete + "\n");  // Echo for debugging
      int countDeleted = myStatement.executeUpdate(sqlDelete);
      System.out.println(countDeleted + " records deleted.\n");
      query("*", "fake_data");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
  //TODO Add id / row selection
  public void insert() {
    try {
      String sqlInsert = "insert into fake_data values (NULL, 'Name_inserted')";
      System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
      int countInserted = myStatement.executeUpdate(sqlInsert);
      System.out.println(countInserted + " records inserted.\n");
      query("*", "fake_data");
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    try {
      Connector connection = new Connector();
      connection.query("*", "fake_data");
      connection.update("fake_data", "name", "3", "'my_new_name'" );
      //connection.insert();
      //connection.delete();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
