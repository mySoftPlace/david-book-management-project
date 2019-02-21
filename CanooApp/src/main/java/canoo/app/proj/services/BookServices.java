package canoo.app.proj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import canoo.app.proj.business.Ibusiness;
import canoo.app.proj.entities.Book;

/**
 * 
 * @author David Sossavi
 * 
 *         This class contains the implementation of web services to save,
 *         update, delete, search and list all books
 *
 */
@RestController
public class BookServices {

	@Autowired
	private Ibusiness ibusiness;

	@RequestMapping(value = "/user/savebook", method = RequestMethod.POST)
	public Book saveBook(@RequestBody Book book) {
		return ibusiness.saveBook(book);
	}

	@RequestMapping(value = "/user/updatebook", method = RequestMethod.PUT)
	public Book updateBook(@RequestBody Book book) {
		return ibusiness.updateBook(book);
	}

	@RequestMapping(value = "/admin/deletebook", method = RequestMethod.DELETE)
	public void deleteBook(@RequestParam("bookId") Long id) {
		ibusiness.deleteBook(id);
	}

	@RequestMapping(value = "/user/searchbook", method = RequestMethod.GET)
	public List<Book> searchBook(@RequestParam("booktitle") String title) {
		return ibusiness.searchBook(title);
	}

	@RequestMapping(value = "/user/books", method = RequestMethod.GET)
	public List<Book> findAndSortAllBook() {
		return ibusiness.findAndSortAllBook();
	}
}
