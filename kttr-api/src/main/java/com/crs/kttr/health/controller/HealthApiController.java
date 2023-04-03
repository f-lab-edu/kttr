package com.crs.kttr.health.controller;

import com.crs.kttr.global.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthApiController {
  @GetMapping
  public Response<String> health() {
    return Response.okk("OK");
  }
}