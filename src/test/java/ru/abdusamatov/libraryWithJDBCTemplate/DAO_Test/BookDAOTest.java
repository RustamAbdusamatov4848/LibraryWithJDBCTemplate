package ru.abdusamatov.libraryWithJDBCTemplate.DAO_Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.abdusamatov.libraryWithJDBCTemplate.DAO.BookDAO;
import ru.abdusamatov.libraryWithJDBCTemplate.models.Book;
import ru.abdusamatov.libraryWithJDBCTemplate.models.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookDAOTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDAO bookDAO;

    private Person person;
    private Book book;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setId(1);
        person.setFullName("Mark Anderson");
        person.setYearOfBirth(1988);

        book = new Book();
        book.setId(1);
        book.setTitle("War and Peace");
        book.setYear(1869);
    }

    @Test
    public void testBookList() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Book"),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(book));

        List<Book> bookList = bookDAO.bookList();
        assertNotNull(book);
        assertFalse(bookList.isEmpty());
        assertEquals(1, bookList.size());
        assertEquals(book.getId(), bookList.get(0).getId());
        assertEquals(book.getTitle(), bookList.get(0).getTitle());
        assertEquals(book.getAuthor(), bookList.get(0).getAuthor());
        assertEquals(book.getYear(), bookList.get(0).getYear());
    }

    @Test
    public void testShowBook() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Book WHERE id=?"),
                any(Object[].class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(book));

        Book foundBook = bookDAO.showBook(book.getId());
        assertNotNull(book);
        assertEquals(book.getId(), foundBook.getId());
    }

    @Test
    public void testSaveBook() {
        bookDAO.saveBook(book);
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)"),
                        eq(book.getTitle()),
                        eq(book.getAuthor()),
                        eq(book.getYear()));
    }

    @Test
    public void testUpdateBook() {
        bookDAO.updateBook(book.getId(), book);
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("UPDATE Book SET title=?, author=?, year=? WHERE id=?"),
                        eq(book.getTitle()),
                        eq(book.getAuthor()),
                        eq(book.getYear()),
                        eq(book.getId()));
    }

    @Test
    public void testDeleteBook() {
        bookDAO.deleteBook(book.getId());
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("DELETE FROM Book WHERE id=?"),
                        eq(book.getId()));
    }

    @Test
    public void testGetBookOwner() {
        when(jdbcTemplate.query(
                eq("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id = ?"),
                any(Object[].class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(person));

        Optional<Person> owner = bookDAO.getBookOwner(book.getId());
        assertTrue(owner.isPresent());
        assertEquals(person.getId(), owner.get().getId());
        assertEquals(person.getFullName(), owner.get().getFullName());
        assertEquals(person.getYearOfBirth(), owner.get().getYearOfBirth());
    }

    @Test
    public void testRelease() {
        bookDAO.release(book.getId());
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("UPDATE Book SET person_id=NULL WHERE id=?"),
                        eq(book.getId()));
    }

    @Test
    public void testAssign() {
        bookDAO.assign(book.getId(), person);
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("UPDATE Book SET person_id=? WHERE id=?"),
                        eq(person.getId()),
                        eq(book.getId()));
    }
}
