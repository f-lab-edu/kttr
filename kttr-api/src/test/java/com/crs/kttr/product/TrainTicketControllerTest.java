package com.crs.kttr.product;

import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.controller.TrainTicketController;
import com.crs.kttr.product.ticket.controller.dto.TrainTicketRegisterRequest;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = TrainTicketController.class)
public class TrainTicketControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TrainTicketService service;

  private MockMvc mvc;

  @BeforeEach
  void setUp(WebApplicationContext applicationContext) {
    mvc =
      MockMvcBuilders.webAppContextSetup(applicationContext)
        .build();
  }

  @Test
  @DisplayName("기차 등록 API 호출 테스트")
  public void registerTrainTicket() throws Exception {
    final TrainTicket mockTicket = new TrainTicket("티켓A", 10);

    when(service.register(Mockito.any(TrainTicketRegisterRequest.class)))
      .thenReturn(mockTicket);

    final TrainTicketRegisterRequest request = new TrainTicketRegisterRequest("티켓A", 10);

    this.mvc.perform(post("/api/v1/ticket")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        .with(csrf())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").value(Boolean.TRUE))
    ;
  }
}
