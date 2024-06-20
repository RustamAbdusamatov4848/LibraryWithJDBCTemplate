package ru.abdusamatov.libraryWithJDBCTemplate.DAO_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.abdusamatov.libraryWithJDBCTemplate.DAO.PersonDAO;
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
public class PersonDAOTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private PersonDAO personDAO;

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
    public void testPersonList() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Person"),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(person));

        List<Person> personList = personDAO.personList();
        assertNotNull(person);
        assertFalse(personList.isEmpty());
        assertEquals(1, personList.size());
        assertEquals(person.getFullName(), personList.get(0).getFullName());
    }

    @Test
    public void testShowPerson() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Person WHERE id=?"),
                any(Object[].class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(person));

        Person foundPerson = personDAO.showPerson(person.getId());
        assertNotNull(person);
        assertEquals(person.getId(), foundPerson.getId());
    }

    @Test
    public void testSavePerson() {
        personDAO.savePerson(person);
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)"),
                        eq(person.getFullName()),
                        eq(person.getYearOfBirth()));
    }

    @Test
    public void testUpdatePerson() {
        personDAO.updatePerson(person.getId(), person);
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?"),
                        eq(person.getFullName()),
                        eq(person.getYearOfBirth()),
                        eq(person.getId()));
    }

    @Test
    public void testDeletePerson() {
        personDAO.deletePerson(person.getId());
        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .update(eq("DELETE FROM Person WHERE id=?"),
                        eq(person.getId()));
    }

    @Test
    public void testGetPersonByFullName() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Person WHERE full_name=?"),
                any(Object[].class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(person));

        Optional<Person> foundPerson = personDAO.getPersonByFullName(person.getFullName());
        assertTrue(foundPerson.isPresent());
        assertEquals(person.getFullName(), foundPerson.get().getFullName());
    }
    @Test
    public void testGetBooksByPersonID() {
        when(jdbcTemplate.query(
                eq("SELECT * FROM Book WHERE person_id = ?"),
                any(Object[].class),
                any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(book));

        List<Book> books = personDAO.getBooksByPersonId(person.getId());
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(1,books.size());
        assertEquals(book.getTitle(),books.get(0).getTitle());
        assertEquals(book.getAuthor(),books.get(0).getAuthor());
        assertEquals(book.getYear(),books.get(0).getYear());
    }
}
