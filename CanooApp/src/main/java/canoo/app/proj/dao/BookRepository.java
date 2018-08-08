package canoo.app.proj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import canoo.app.proj.entities.Book;

/**
 * 
 * @author David Sossavi
 * 
 *         The Data access object of the book
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	// @Query(nativeQuery = true, value = "select * from Books b order by
	// b.publication_date, b.title ASC LIMIT 7")
	@Query("from Book b order by b.publicationDate, b.title")
	public List<Book> findAndSortAllBook();

	@Query("from Book b where b.title like %?1%")
	public List<Book> searchBook(String title);
}
