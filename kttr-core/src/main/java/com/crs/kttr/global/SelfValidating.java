package com.crs.kttr.global;

import jakarta.validation.*;

import java.util.Set;

public class SelfValidating<T> {
  private Validator validator;
  public SelfValidating() {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  @SuppressWarnings("unchecked")
  protected void validateSelf() {
    final Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
