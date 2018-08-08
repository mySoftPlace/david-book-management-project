package canoo.app.proj.business;

import java.util.List;

import canoo.app.proj.entities.Book;

/**
 * 
 * @author David Sossavi
 * 
 *         Interface for bussiness method
 *
 */
public interface Ibusiness {

	public Book saveBook(Book book);

	public Book updateBook(Book book);

	public void deleteBook(Long id);

	public List<Book> searchBook(String title);

	public List<Book> findAndSortAllBook();
}
