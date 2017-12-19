package fr.ujm.tse.Scream.Controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Scream
 *
 */
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
	private static Connection conn;
	private static ArrayList<Statement> statements = new ArrayList<Statement>();
	private static PreparedStatement psInsert;
	private static PreparedStatement psUpdate;
	private static Statement s;
	private static ResultSet rs;


	public static void databse() {
		System.out.println("�tes vous d�j� inscrit ? (o/n)");
		sc1 = new Scanner(System.in);
		do {
			str = sc1.nextLine();
			switch (str) {
			case "o":
				System.out.println("Indiquez le nom de votre biblioth�que :");
				sc4 = new Scanner(System.in);
				dbName = sc4.nextLine();
				System.out.println("Veuillez renseigner votre nom d'utilisateur :");
				sc2 = new Scanner(System.in);
				userName = sc2.nextLine();
				System.out.println("Maintenant votre mot de passe :");
				sc3 = new Scanner(System.in);
				pass = sc3.nextLine();
				connectDatabase(dbName, userName, pass);
				break;
			case "n":
				System.out.println("Quel nom souhaitez vous donner � votre biblioth�que ?");
				sc4 = new Scanner(System.in);
				dbName = sc4.nextLine();
				System.out.println("Veuillez renseigner un nom d'utilisateur :");
				sc2 = new Scanner(System.in);
				userName = sc2.nextLine();
				System.out.println("Maintenant un mot de passe :");
				sc3 = new Scanner(System.in);
				pass = sc3.nextLine();
				createDatabase(dbName, userName, pass);
				break;
			default:
				System.out.println("�tes vous d�j� inscrit ? (o/n)");
				str = ""; // si le str est incorrect, on le force � vide
			}

		} while (str.isEmpty()); // tant que coup est vide on bouble (donc on
									// redemande)

	}
	
	/**
	 * M�thode permettant de cr�er une base de donn�es
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static Boolean createDatabase(String dbName, String userName, String pass) {
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";create=true ;user=" + userName + " ;password=" + pass);
			System.out.println(dbName + " cr��e avec succ�s !");
			System.out.println("Notez bien vos identifiants : ");
			System.out.println("Nom d'utililsateur : " + userName);
			System.out.println("Mot de passe : " + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			s = conn.createStatement();
			statements.add(s);

			// We create a table...
			s.execute("create table library(id int primary key not null," + " titre varchar(100),"
					+ " auteur varchar(100)," + " ann�e int, etat varchar(100)," + " bookmark int," + " note int,"
					+ " commentaire varchar(500))");
			
			/*
			 * We commit the transaction. Any changes will be persisted to the
			 * database now.
			 */
			conn.commit();
			return true;
		} catch (SQLException sqle) {
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;

	}

	/**
	 * M�thode permettant de se connecter � la base de donn�es
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static Boolean connectDatabase(String dbName, String userName, String pass) {
		conn = null;
		rs = null;

		try {
			conn = DriverManager.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);
			System.out.println("Connexion � " + dbName + " r�ussie");

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			return true;
		} catch (SQLException sqle) {
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
	}

	/**
	 * Prints details of an SQLException chain to <code>System.err</code>.
	 * Details included are SQL State, Error code, Exception message.
	 *
	 * @param e
	 *            the SQLException from which to print details.
	 */
	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

	/**
	 * Permet de se d�connecter de la biblioth�que
	 */
	public static void deconnection() {
		try {
			// the shutdown=true attribute shuts down Derby
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
			// To shut down a specific database only, but keep the
			// engine running (for example for connecting to other
			// databases), specify a database in the connection URL:
			// DriverManager.getConnection("jdbc:derby:" + dbName +
			// ";shutdown=true");
		} catch (SQLException se) {
			if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
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
	
	/**
	 * M�thode permettant de trouver toutes les informations concernant un comics
	 * @param title
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static Boolean selectByTitle(String title, String dbName, String userName, String pass) {
		// On initialise la connexion � null
		conn = null;
		rs = null;
		
		try {
			conn = DriverManager.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			// Commande de SELECT qui affiche une ligne 
			s = conn.createStatement();
			statements.add(s);
			rs = s.executeQuery(
			        "SELECT * FROM library where title = ?");
			while(rs.next()){
				System.out.println(rs.getInt(1)+
						" "+rs.getString(2)+
						" "+rs.getString(3)+
						" "+rs.getInt(4)+
						" "+rs.getString(5)+
						" "+rs.getInt(6)+
						" "+rs.getInt(7)+
						" "+rs.getString(8));
			}
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	
	
	
	/**
	 * Permet de selectionner une ligne
	 * @param id
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static Boolean selectLigne(int id, String dbName, String userName, String pass)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			// Commande de SELECT qui affiche une ligne 
			s = conn.createStatement();
			statements.add(s);
			rs = s.executeQuery(
			        "SELECT * FROM library where id = ?");
			while(rs.next()){
				System.out.println(rs.getInt(1)+
						" "+rs.getString(2)+
						" "+rs.getString(3)+
						" "+rs.getInt(4)+
						" "+rs.getString(5)+
						" "+rs.getInt(6)+
						" "+rs.getInt(7)+
						" "+rs.getString(8));
			}
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		}
	
	/**
	 * M�thode permettant de trouver toutes les informations suivant un auteur donn�
	 * @param author
	 * @param userNames
	 * @param pass
	 * @return
	 */
	public static Boolean selectByAuthor(String author, String userNames, String pass) {
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			// Commande de SELECT qui affiche une ligne 
			s = conn.createStatement();
			statements.add(s);
			rs = s.executeQuery(
			        "SELECT * FROM library where author = ?");
			while(rs.next()){
				System.out.println(rs.getInt(1)+
						" "+rs.getString(2)+
						" "+rs.getString(3)+
						" "+rs.getInt(4)+
						" "+rs.getString(5)+
						" "+rs.getInt(6)+
						" "+rs.getInt(7)+
						" "+rs.getString(8));
			}
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @param id
	 * @param title
	 * @param author
	 * @param year
	 * @param etat
	 * @param bookmark
	 * @param note
	 * @param com
	 * @return
	 */
	public static Boolean insert(String dbName, String userName, String pass, int id, String title, String author, int year, String etat, int bookmark, int note, String com)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			// Commande pour ins�rer des valeurs
			psInsert = conn.prepareStatement("insert into library values (?, ?, ?, ?, ?, ?, ?, ?)");
			statements.add(psInsert);

			psInsert.setInt(1, id);
			psInsert.setString(2, title);
			psInsert.setString(3, author);
			psInsert.setInt(4, year);
			psInsert.setString(5, etat);
			psInsert.setInt(6, bookmark);
			psInsert.setInt(7, note);
			psInsert.setString(8, com);
			
			psInsert.executeUpdate();
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @param id
	 * @param bookmark
	 * @return
	 */
	public static Boolean updateBookmark(String dbName, String userName, String pass, int id, int bookmark)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */

			psUpdate = conn.prepareStatement("update library set bookmark=? where id=?");
			psUpdate.setInt(6, bookmark);
			psUpdate.executeUpdate(); 
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @param id
	 * @param note
	 * @return
	 */
	public static Boolean updateNote(String dbName, String userName, String pass, int id, int note)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */

			psUpdate = conn.prepareStatement("update library set bookmark=? where id=?");
			psUpdate.setInt(7, note);
			psUpdate.executeUpdate(); 
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @param id
	 * @param com
	 * @return
	 */
	public static Boolean updateCom(String dbName, String userName, String pass, int id, String com)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			
			psUpdate = conn.prepareStatement("update library set bookmark=? where id=?");
			psUpdate.setString(8, com);
			psUpdate.executeUpdate(); 
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @param id
	 * @param etat
	 * @return
	 */
	public static Boolean updateEtat(String dbName, String userName, String pass, int id, String etat)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			
			psUpdate = conn.prepareStatement("update library set bookmark=? where id=?");
			psUpdate.setString(5, etat);
			psUpdate.executeUpdate(); 
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param id
	 * @param dbName
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static Boolean deleteLigne(int id, String dbName, String userName, String pass)
	{
		conn = null;
		rs = null;

		try {
			conn = DriverManager
					.getConnection(protocol + dbName + ";user=" + userName + " ;password=" + pass);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);
			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			psUpdate = conn.prepareStatement("delete from library where id=?");
			psUpdate.executeUpdate(); 
			conn.commit();
			return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
		return false;
		
	}
}
