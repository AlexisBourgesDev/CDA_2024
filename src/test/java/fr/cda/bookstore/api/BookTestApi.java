package fr.cda.bookstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cda.bookstore.metier.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BookTestApi {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books");

        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();

        mockMvc.perform(requestBuilder).andExpect(resultStatus);
    }

    @Test
    public void testGetParTitre() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/books/search?titre=L1");

        ResultMatcher resultStatus = MockMvcResultMatchers.status().isOk();

        String bodyContent = mockMvc.perform(requestBuilder).andExpect(resultStatus).andReturn().getResponse().getContentAsString();

        Book book = objectMapper.readValue(bodyContent, Book.class);

        Assertions.assertEquals("L1", book.getTitre());
    }

}
