package fr.ujm.tse.Scream.Controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	private static String framework = "embedded";
	private static String protocol = "jdbc:derby:";
	private static String str;
	private static Scanner sc1;
	private static String userName;
	private static Scanner sc2;
	private static String pass;
	private static Scanner sc3;
	private static String dbName;
	private static Scanner sc4;
	
	public static void databse() {
		System.out.println("Êtes vous déjà inscrit ? (o/n)");
		sc1 = new Scanner(System.in);
		do 
		{
			 str = sc1.nextLine(); 
			 switch (str) 
			 {
				case "o":
				    connectDatabase();
			            break;
				case "n":
				    createDatabase();
			            break;
				default:
				    System.out.println("Êtes vous déjà inscrit ? (o/n)");
			        str=""; // si le str est incorrect, on le force à vide
			  }
			 
		} while( str.isEmpty());  // tant que coup est vide on bouble (donc on redemande)
				
	}

	private static void createDatabase() {
		System.out.println("Quel nom souhaitez vous donner à votre base de données ?");
		sc4 = new Scanner(System.in);
		dbName = sc4.nextLine();
		System.out.println("Veuillez renseigner un nom d'utilisateur :");
		sc2 = new Scanner(System.in);
		userName = sc2.nextLine();
		System.out.println("Maintenant un mot de passe :");
		sc3 = new Scanner(System.in);
		pass = sc3.nextLine();
		
		Connection conn = null;
	    ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
	    PreparedStatement psInsert;
	    PreparedStatement psUpdate;
	    Statement s;
	    ResultSet rs = null;
	    
	    try {
	         conn = DriverManager.getConnection(protocol + dbName+";create=true ;user="+userName+" ;password="+pass);
	         System.out.println(dbName+ " créée avec succès !");
	         System.out.println("Notez bien vos identifiants : ");
	         System.out.println("Nom d'utililsateur : "+userName);
	         System.out.println("Mot de passe : "+pass);

	         // We want to control transactions manually. Autocommit is on by
	         // default in JDBC.
	         conn.setAutoCommit(false);
	         if (framework.equals("embedded"))
	         {
	             try
	             {
	                 // the shutdown=true attribute shuts down Derby
	                 DriverManager.getConnection("jdbc:derby:;shutdown=true");

	                 // To shut down a specific database only, but keep the
	                 // engine running (for example for connecting to other
	                 // databases), specify a database in the connection URL:
	                 //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
	             }
	             catch (SQLException se)
	             {
	                 if (( (se.getErrorCode() == 50000)
	                         && ("XJ015".equals(se.getSQLState()) ))) {
	                     // we got the expected exception
	                     System.out.println("Derby shut down normally");
	                     // Note that for single database shutdown, the expected
	                     // SQL state is "08006", and the error code is 45000.
	                 } else {
	                     // if the error code or SQLState is different, we have
	                     // an unexpected exception (shutdown failed)
	                     System.err.println("Derby did not shut down normally");
	                     printSQLException(se);
	                 }
	             }
	         }
		} 
	    catch (SQLException sqle)
	     {
	         printSQLException(sqle);
	     } finally {
	         // release all open resources to avoid unnecessary memory usage

	         // ResultSet
	         try {
	             if (rs != null) {
	                 rs.close();
	                 rs = null;
	             }
	         } catch (SQLException sqle) {
	             printSQLException(sqle);
	         }

	         // Statements and PreparedStatements
	         int i = 0;
	         while (!statements.isEmpty()) {
	             // PreparedStatement extend Statement
	             Statement st = (Statement)statements.remove(i);
	             try {
	                 if (st != null) {
	                     st.close();
	                     st = null;
	                 }
	             } catch (SQLException sqle) {
	                 printSQLException(sqle);
	             }
	         }

	         //Connection
	         try {
	             if (conn != null) {
	                 conn.close();
	                 conn = null;
	             }
	         } catch (SQLException sqle) {
	             printSQLException(sqle);
	         }
	     }
		
	}

	private static void connectDatabase() {
		System.out.println("Indiquez le nom de votre base de données :");
		sc4 = new Scanner(System.in);
		dbName = sc4.nextLine();
		System.out.println("Veuillez renseigner votre nom d'utilisateur :");
		sc2 = new Scanner(System.in);
		userName = sc2.nextLine();
		System.out.println("Maintenant votre mot de passe :");
		sc3 = new Scanner(System.in);
		pass = sc3.nextLine();
		
		Connection conn = null;
	    ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
	    PreparedStatement psInsert;
	    PreparedStatement psUpdate;
	    Statement s;
	    ResultSet rs = null;
	    
	    try {
	         conn = DriverManager.getConnection(protocol + dbName+";user="+userName+" ;password="+pass);
	         System.out.println("Connexion à " + dbName+ " réussie");

	         // We want to control transactions manually. Autocommit is on by
	         // default in JDBC.
	         conn.setAutoCommit(false);
	         if (framework.equals("embedded"))
	         {
	             try
	             {
	                 // the shutdown=true attribute shuts down Derby
	                 DriverManager.getConnection("jdbc:derby:;shutdown=true");

	                 // To shut down a specific database only, but keep the
	                 // engine running (for example for connecting to other
	                 // databases), specify a database in the connection URL:
	                 //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
	             }
	             catch (SQLException se)
	             {
	                 if (( (se.getErrorCode() == 50000)
	                         && ("XJ015".equals(se.getSQLState()) ))) {
	                     // we got the expected exception
	                     System.out.println("Derby shut down normally");
	                     // Note that for single database shutdown, the expected
	                     // SQL state is "08006", and the error code is 45000.
	                 } else {
	                     // if the error code or SQLState is different, we have
	                     // an unexpected exception (shutdown failed)
	                     System.err.println("Derby did not shut down normally");
	                     printSQLException(se);
	                 }
	             }
	         }
		} 
	    catch (SQLException sqle)
	     {
	         printSQLException(sqle);
	     } finally {
	         // release all open resources to avoid unnecessary memory usage

	         // ResultSet
	         try {
	             if (rs != null) {
	                 rs.close();
	                 rs = null;
	             }
	         } catch (SQLException sqle) {
	             printSQLException(sqle);
	         }

	         // Statements and PreparedStatements
	         int i = 0;
	         while (!statements.isEmpty()) {
	             // PreparedStatement extend Statement
	             Statement st = (Statement)statements.remove(i);
	             try {
	                 if (st != null) {
	                     st.close();
	                     st = null;
	                 }
	             } catch (SQLException sqle) {
	                 printSQLException(sqle);
	             }
	         }

	         //Connection
	         try {
	             if (conn != null) {
	                 conn.close();
	                 conn = null;
	             }
	         } catch (SQLException sqle) {
	             printSQLException(sqle);
	         }
	     }
	}
	

	 /**
	  * Prints details of an SQLException chain to <code>System.err</code>.
	  * Details included are SQL State, Error code, Exception message.
	  *
	  * @param e the SQLException from which to print details.
	  */
	 public static void printSQLException(SQLException e)
	 {
	     // Unwraps the entire exception chain to unveil the real cause of the
	     // Exception.
	     while (e != null)
	     {
	         System.err.println("\n----- SQLException -----");
	         System.err.println("  SQL State:  " + e.getSQLState());
	         System.err.println("  Error Code: " + e.getErrorCode());
	         System.err.println("  Message:    " + e.getMessage());
	         // for stack traces, refer to derby.log or uncomment this:
	         //e.printStackTrace(System.err);
	         e = e.getNextException();
	     }
	 }

}
