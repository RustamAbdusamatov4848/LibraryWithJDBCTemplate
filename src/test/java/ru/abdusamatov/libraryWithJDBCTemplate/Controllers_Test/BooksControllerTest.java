package ru.abdusamatov.libraryWithJDBCTemplate.Controllers_Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.abdusamatov.libraryWithJDBCTemplate.DAO.BookDAO;
import ru.abdusamatov.libraryWithJDBCTemplate.DAO.PersonDAO;
import ru.abdusamatov.libraryWithJDBCTemplate.config.SpringConfig;
import ru.abdusamatov.libraryWithJDBCTemplate.models.Book;
import ru.abdusamatov.libraryWithJDBCTemplate.models.Person;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BooksControllerTest.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BooksControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookDAO bookDAO;
    @MockBean
    private PersonDAO personDAO;
    private Book book;
    private Person person;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setYear(2000);

        person = new Person();
        person.setId(1);
        person.setFullName("Bob Collins");
        person.setYearOfBirth(1997);
    }

    @Test
    public void testIndex() throws Exception {
        when(bookDAO.bookList()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("books/index"));

    }

    @Test
    public void testShow() throws Exception {
        when(bookDAO.showBook(1)).thenReturn(book);
        when(bookDAO.getBookOwner(1)).thenReturn(Optional.of(person));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("books/show"));
    }

    @Test
    public void testNewBook() throws Exception {
        mockMvc.perform(get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("books/new"));
    }

    @Test
    public void testCreateBook() throws Exception {
        mockMvc.perform(post("/books")
                        .param("title", "Test Book")
                        .param("author", "Test Author")
                        .param("year", "2000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void testEditBook() throws Exception {
        when(bookDAO.showBook(1)).thenReturn(book);

        mockMvc.perform(get("/books/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"))
                .andExpect(view().name("books/edit"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        mockMvc.perform(patch("/books/1")
                        .param("title", "Updated Book")
                        .param("author", "Updated Author")
                        .param("year", "2020"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    public void testReleaseBook() throws Exception {
        mockMvc.perform(patch("/books/1/release"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/1"));
    }

    @Test
    public void testAssignBook() throws Exception {
        mockMvc.perform(patch("/books/1/assign")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/1"));

    }
}
