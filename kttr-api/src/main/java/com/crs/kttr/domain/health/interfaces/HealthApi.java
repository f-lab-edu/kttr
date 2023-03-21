package com.crs.kttr.domain.health.interfaces;

import com.crs.kttr.global.dto.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health 체크용 Api.
 *
 * @author Heeyeon.
 */
@RestController
@RequestMapping("/health")
public class HealthApi {
  @GetMapping
  public ResponseEntity<GenericResponse<String>> health(HttpServletRequest req) {
    return GenericResponse.ok("OK");
  }
}
