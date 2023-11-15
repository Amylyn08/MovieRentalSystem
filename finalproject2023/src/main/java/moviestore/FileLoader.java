package moviestore;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;

public class FileLoader implements IDatabase {


    public List<Movie> loadMovies() throws IOException{
        Path pMovie = Paths.get("..\\..\\CSVdata\\Movies.csv");
        Path pDvd = Paths.get("..\\..\\CSVdata\\DVD.csv");
        Path pDigital = Paths.get("..\\..\\CSVdata\\DigitalMovie.csv");
        List<String> linesMovie = Files.readAllLines(pMovie);
        List<String> linesDVD = Files.readAllLines(pDvd);
        List<String> linesDigital = Files.readAllLines(pDigital);
        List<Movie> loadMovies = new ArrayList<Movie>();
        for(int i = 0; i < linesMovie.size(); i++){
            String[] dataMovie = linesMovie.get(i).split(",");
            String[] dataDigital = linesDigital.get(i).split(",");
            loadMovies.add(new DigitalMovie(dataMovie[1], dataMovie[2], Integer.parseInt(dataMovie[3]), dataMovie[4], Double.parseDouble(dataMovie[5]), Double.parseDouble(dataMovie[6]), Double.parseDouble(dataMovie[7]), Integer.parseInt(dataDigital[1]), Integer.parseInt(dataDigital[2])));
        }
        return loadMovies;
    }
}
