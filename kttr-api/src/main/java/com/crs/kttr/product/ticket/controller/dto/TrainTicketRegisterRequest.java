package com.crs.kttr.product.ticket.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrainTicketRegisterRequest {
  @NotBlank(message = "필수 입력 값입니다.")
  private String name;

  @Min(value = 1, message = "1 이상 입력해주세요.")
  private Integer maxQuantity;
}
