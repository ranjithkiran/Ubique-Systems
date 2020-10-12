import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class Movie {
	public String name;
	public int year;
	public int rating;

	public Movie(String name, int year, int rating){
	this.name = name;
	this.year = year;
	this.rating = rating;
	}
}


@Configuration
@Import(MovieRepository.class)
class Config {
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
		return new JdbcTemplate(ds);
	}
}

@Repository
public class MovieRepository {

	@Autowired
	private JdbcTemplate template;

	@PostConstruct
	public void createTable() {
	template.execute("CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
	}

	public void createMovie(String name, int year, int rating) {
		string query = insert into movies(name, year, rating);
		template,update(query);
	}

	public List<Movie> findMoviesByName(String likeName) {
		string query = “select * from movies where name like ?”;
		return template.query(query,new Object[]{“%”+likeName+”%”},new BeanPropertyRowMapper<>(Movie.Class));
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
		config.register(Config.class);
		config.refresh();
		MovieRepository repository = config.getBean(MovieRepository.class);

		repository.createMovie("Some movie", 1974, 3);
		repository.createMovie("Some other movie", 1993, 2);

		List<Movie> movies = repository.findMoviesByName("Some%");
		for(Movie movie : movies){
			System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
		}
	}
}

