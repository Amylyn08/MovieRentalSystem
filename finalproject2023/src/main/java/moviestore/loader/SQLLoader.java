package moviestore.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import moviestore.products.*;
import moviestore.Customer;
import moviestore.exceptions.LoaderFailedException;

public class SQLLoader implements IDatabase {
    private Connection conn;

    public SQLLoader(String user, String password) {
        createConnection(user, password);
    }

    /* method to close connection */
    public void Close() throws SQLException {
        if (this.conn != null)
            this.conn.close();
    }

    /**
     * this method establishes the connection to the database
     * 
     * @param {String} - represents the username
     * @param {String} - represents the password
     */
    public void createConnection(String user, String password) {
        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                    user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method loads all the movies from the database through 2 queries: 1 for
     * digital and 1 for DVDs
     */
    public List<Movie> loadMovies() throws LoaderFailedException {
        try {
            CallableStatement dvd = this.conn.prepareCall("{call loading.getPhysicalMovies(?)}");
            dvd.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            dvd.execute();
            ResultSet rs = (ResultSet) dvd.getObject(1);
            List<Movie> movies = new ArrayList<Movie>();
            while (rs.next()) {
                movies.add(new DVD(
                        rs.getString("TITLE"),
                        rs.getString("GENRE"),
                        rs.getInt("DURATION"),
                        rs.getString("summary"),
                        rs.getDouble("additionOfRatings"),
                        rs.getInt("numRatings"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("movieURL")));
            }

            CallableStatement digital = this.conn.prepareCall("{call loading.getDigitalMovies(?)}");
            digital.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            digital.execute();
            rs = (ResultSet) digital.getObject(1);
            while (rs.next()) {
                movies.add(new DigitalMovie(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getString("summary"),
                        rs.getDouble("additionOfRatings"),
                        rs.getInt("numRatings"),
                        rs.getDouble("price"),
                        rs.getInt("fileSize"),
                        rs.getInt("stock"),
                        rs.getString("movieURL")));
            }

            return (movies);
        } catch (SQLException e) {
            throw new LoaderFailedException(e);
        }
    }

    /**
     * this method loads all customers from the database using a query
     */
    public List<Customer> loadCustomers() throws LoaderFailedException {
        try {
            CallableStatement cs = this.conn.prepareCall("{call loading.getCustomers(?)}");
            cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            List<Customer> customers = new ArrayList<Customer>();
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getString("customerName"),
                        rs.getInt("points")));
            }
            return customers;

        } catch (SQLException e) {
            throw new LoaderFailedException(e);
        }
    }
}
