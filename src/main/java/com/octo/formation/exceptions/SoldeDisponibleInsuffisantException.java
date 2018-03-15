package com.octo.formation.exceptions;

public class SoldeDisponibleInsuffisantException extends Exception {

  private static final long serialVersionUID = 1L;
  private String keyMessage;

  public SoldeDisponibleInsuffisantException() {
  }

  public SoldeDisponibleInsuffisantException(String message, String keyMessage) {
    super(message);
    this.keyMessage = keyMessage;
  }

  public String getKeyMessage() {
    return keyMessage;
  }

  public void setKeyMessage(String keyMessage) {
    this.keyMessage = keyMessage;
  }
}
