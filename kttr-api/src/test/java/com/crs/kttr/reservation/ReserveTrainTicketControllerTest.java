package com.crs.kttr.reservation;

import com.crs.kttr.product.ticket.application.TrainTicketService;
import com.crs.kttr.product.ticket.controller.TrainTicketController;
import com.crs.kttr.product.ticket.controller.dto.TrainTicketRegisterRequest;
import com.crs.kttr.product.ticket.model.TrainTicket;
import com.crs.kttr.reservation.application.TicketReservationService;
import com.crs.kttr.reservation.controller.ReserveTrainTicketController;
import com.crs.kttr.reservation.controller.dto.TrainTicketReserveRequest;
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

@WebMvcTest(value = ReserveTrainTicketController.class)
public class ReserveTrainTicketControllerTest {
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TicketReservationService service;

  private MockMvc mvc;

  @BeforeEach
  void setUp(WebApplicationContext applicationContext) {
    mvc =
      MockMvcBuilders.webAppContextSetup(applicationContext)
        .build();
  }

  @Test
  @DisplayName("기차 예매 API 호출 테스트")
  public void reserve() throws Exception {
    when(service.reserve(Mockito.anyLong(), Mockito.anyLong()))
      .thenReturn("uuid");

    final TrainTicketReserveRequest request = new TrainTicketReserveRequest(1L, 1L);

    this.mvc.perform(post("/api/v1/reserve")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        .with(csrf())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data").value("uuid"))
    ;
  }



}
