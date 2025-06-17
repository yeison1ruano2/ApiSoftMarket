package com.softmarket.apisoftmarket.dto;

import java.util.List;

public class FacturaRequest {

  private Integer numbering_range_id;
  private String reference_code;
  private String observation;
  private String payment_form;
  private String payment_due_date;
  private String payment_method_code;
  private BillingPeriodRequest billing_period;
  private CustomerRequest customer;
  private List<ItemRequest> items;

  

  public BillingPeriodRequest getBilling_period() {
    return billing_period;
  }

  public void setBilling_period(BillingPeriodRequest billing_period) {
    this.billing_period = billing_period;
  }

  public CustomerRequest getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerRequest customer) {
    this.customer = customer;
  }

  public List<ItemRequest> getItems() {
    return items;
  }

  public void setItems(List<ItemRequest> items) {
    this.items = items;
  }

  public Integer getNumbering_range_id() {
    return numbering_range_id;
  }

  public void setNumbering_range_id(Integer numbering_range_id) {
    this.numbering_range_id = numbering_range_id;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public String getPayment_due_date() {
    return payment_due_date;
  }

  public void setPayment_due_date(String payment_due_date) {
    this.payment_due_date = payment_due_date;
  }

  public String getPayment_form() {
    return payment_form;
  }

  public void setPayment_form(String payment_form) {
    this.payment_form = payment_form;
  }

  public String getPayment_method_code() {
    return payment_method_code;
  }

  public void setPayment_method_code(String payment_method_code) {
    this.payment_method_code = payment_method_code;
  }

  public String getReference_code() {
    return reference_code;
  }

  public void setReference_code(String reference_code) {
    this.reference_code = reference_code;
  }
}
