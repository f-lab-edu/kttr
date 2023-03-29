package com.crs.kttr.member;

import com.crs.kttr.member.application.MemberRegisterService;
import com.crs.kttr.member.command.RegisterMemberCommand;
import com.crs.kttr.member.interfaces.RegisterMemberApiController;
import com.crs.kttr.member.interfaces.dto.MemberDto;
import com.crs.kttr.member.interfaces.dto.MemberRegisterRequest;
import com.crs.kttr.member.model.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

@WebMvcTest(value = RegisterMemberApiController.class)
class RegisterMemberApiControllerTest {

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MemberRegisterService service;

  private MockMvc mvc;

  @BeforeEach
  void setUp(WebApplicationContext applicationContext) {
    mvc =
      MockMvcBuilders.webAppContextSetup(applicationContext)
//        .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
        .build();
  }

  @Test
  public void registerMember() throws Exception {
    final Member mockMember = new Member("signInId@test.com", "encryted-password", "signInId@test.com", "010-1234-1234");

    when(service.register(Mockito.any(RegisterMemberCommand.class)))
      .thenReturn(new MemberDto(mockMember));


    final MemberRegisterRequest request = new MemberRegisterRequest("signInId@test.com", "passwrod", "signInId@test.com", "010-1234-1234");

    this.mvc.perform(post("/api/v1/guest/member")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))
        .with(csrf())
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.signInId").value("signInId@test.com"))
    ;
  }
}