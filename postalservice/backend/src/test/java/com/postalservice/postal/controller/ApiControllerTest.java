package com.postalservice.postal.controller;
import com.postalservice.postal.repo.*;
import com.postalservice.postal.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ApiController.class)
class ApiControllerTest {
  @Autowired MockMvc mvc;
  @MockBean LetterRepository letters;
  @MockBean ParcelRepository parcels;
  @MockBean IdService ids;
  @MockBean QueuePublisher queues;
  @Test void postLetter_enqueuesAndReturnsId() throws Exception {
    when(ids.newId()).thenReturn("id1");
    mvc.perform(post("/api/letter/AT/Alice"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.id").value("id1"))
       .andExpect(jsonPath("$.status").value("WAITING"));
    verify(queues).sendLetter("id1");
  }
}
