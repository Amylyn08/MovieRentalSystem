package moviestore.loader;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void createConnection(String user, String password) {
        try {
            this.conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",
                    user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void load() throws LoaderFailedException {
        String dvdQuery = "SELECT title FROM Movies";
        try {
            PreparedStatement query = this.conn.prepareStatement(dvdQuery);
            ResultSet rs = query.executeQuery();
            rs.next();
            System.out.println(rs.getString(1));
        } catch (SQLException e) {
            throw new LoaderFailedException(e);
        }
    }

    public List<Movie> loadMovies() throws LoaderFailedException {
        try {
            List<Movie> movies = new ArrayList<Movie>();
            String dvdQuery = "SELECT * FROM Movies INNER JOIN DVDs USING(movieID)";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(dvdQuery);
            while (rs.next()) {
                movies.add(new DVD(
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getString("summary"),
                        rs.getDouble("additionOfRatings"),
                        rs.getInt("numRatings"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("movieURL")));
            }
            System.out.println("loaded");

            String digitalQuery = "SELECT * FROM Movies INNER JOIN DigitalMovies USING(movieID)";
            Statement stmt2 = this.conn.createStatement();
            rs = stmt2.executeQuery(digitalQuery);
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

    public List<Customer> loadCustomers() throws LoaderFailedException {
        try {
            List<Customer> customers = new ArrayList<Customer>();
            String getCustomers = "SELECT * FROM Moviestore_Customers";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(getCustomers);
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getString("customerName"),
                        rs.getInt("points")));
            }

            return (customers);
        } catch (SQLException e) {
            throw new LoaderFailedException(e);
        }
    }
}
