package ru.abdusamatov.libraryWithJDBCTemplate.Controllers_Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.abdusamatov.libraryWithJDBCTemplate.DAO.PersonDAO;
import ru.abdusamatov.libraryWithJDBCTemplate.config.SpringConfig;
import ru.abdusamatov.libraryWithJDBCTemplate.controllers.PeopleController;
import ru.abdusamatov.libraryWithJDBCTemplate.models.Person;
import ru.abdusamatov.libraryWithJDBCTemplate.util.PersonValidator;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class PeopleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonDAO personDAO;
    @MockBean
    private PersonValidator personValidator;
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setId(1);
        person.setFullName("Bob Collins");
        person.setYearOfBirth(1997);
    }

    @Test
    public void testIndex() throws Exception {
        when(personDAO.personList()).thenReturn(Arrays.asList(person));

        mockMvc.perform(get("/people"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("people"))
                .andExpect(view().name("people/index"));
    }

    @Test
    public void testShow() throws Exception {
        when(personDAO.showPerson(1)).thenReturn(person);
        when(personDAO.getBooksByPersonId(1)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("people/show"));
    }

    @Test
    public void testNewPerson() throws Exception {
        mockMvc.perform(get("/people/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("people/new"));
    }

    @Test
    public void testCreatePerson() throws Exception {
        when(personValidator.supports(Person.class)).thenReturn(true);
        Mockito.doNothing().when(personValidator).validate(Mockito.any(), Mockito.any());

        mockMvc.perform(post("/people")
                        .param("fullName", "Bob Collins")
                        .param("yearOfBirth", "1997"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people"));
    }

    @Test
    public void testEditPerson() throws Exception {
        when(personDAO.showPerson(1)).thenReturn(person);

        mockMvc.perform(get("/people/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("person"))
                .andExpect(view().name("people/edit"));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        mockMvc.perform(patch("/people/1")
                        .param("fullName", "John Doe")
                        .param("yearOfBirth", "1990"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people"));
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/people/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/people"));
    }
}
