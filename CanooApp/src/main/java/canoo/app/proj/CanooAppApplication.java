package canoo.app.proj;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import canoo.app.proj.dao.BookRepository;
import canoo.app.proj.entities.Book;

/**
 * 
 * @author David Sossavi
 * 
 *         Start the spring boot application and save some data to the database
 *
 */
@SpringBootApplication
public class CanooAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CanooAppApplication.class, args);
		canoo.app.proj.dao.BookRepository bookRepository = ctx.getBean(BookRepository.class);

		bookRepository.save(new Book("The last hero", "John Garry", 201, new Date()));
		bookRepository.save(new Book("Independence tomorrow", "Fraser James", 196, new Date()));
		bookRepository.save(new Book("My best friend", "Sydney Brandon", 308, new Date()));
		bookRepository.save(new Book("the extraordinary adventure", "Roland Stevens", 228, new Date()));
		bookRepository.save(new Book("The second adventure", "Angela Kahn", 108, new Date()));
		bookRepository.save(new Book("Narrow Path", "Mataos lauter", 122, new Date()));
		bookRepository.save(new Book("demain des l'aube", "Victor Hugo", 93, new Date()));
		bookRepository.save(new Book("L'homme et la mere", "Charles Baudelaire", 115, new Date()));
		bookRepository.save(new Book("The last man", "Ashley Smith", 118, new Date()));
		bookRepository.save(new Book("Santiago shoes", "Alfred Colman", 182, new Date()));
		bookRepository.save(new Book("Le fils du fermier", "Claude Francois", 293, new Date()));
		bookRepository.save(new Book("La vallee de dana", "Melissa theuriau", 185, new Date()));
		bookRepository.findAll().forEach(b -> System.out.println(b.getTitle()));
		
		/*Page<Book> books = bookRepository.findAll(new PageRequest(0, 5));
		books.forEach(b -> System.out.println(b.getTitle()));*/
	}
}
