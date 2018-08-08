package canoo.app.proj.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import canoo.app.proj.dao.BookRepository;
import canoo.app.proj.entities.Book;

/**
 * 
 * @author David Sossavi
 * 
 *         Implements businness by calling the Data Access Object
 *
 */
@Service
public class BusinessImpl implements Ibusiness {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public List<Book> searchBook(String title) {
		return bookRepository.searchBook(title);
	}

	@Override
	public List<Book> findAndSortAllBook() {
		return bookRepository.findAndSortAllBook();
	}

}
