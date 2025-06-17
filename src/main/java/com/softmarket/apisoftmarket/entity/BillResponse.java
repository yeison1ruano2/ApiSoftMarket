package com.softmarket.apisoftmarket.entity;

import com.softmarket.apisoftmarket.dto.OperationTypeResponse;

import java.util.List;

public class BillResponse {

  private Integer id;
  private DocumentResponse document;
  private String number;
  private String reference_code;
  private OperationTypeResponse operation_type;
  private String order_reference;
  private Integer status;
  private Integer send_email;
  private String qr;
  private String cufe;
  private String validated;
  private String discount_rate;
  private String discount;
  private String gross_value;
  private String taxable_amount;
  private String tax_amount;
  private String total;
  private String observation;
  private List<String> errors;
  private String created_at;
  private String payment_due_date;
  private String qr_image;
  private Integer has_claim;
  private Integer is_negotiable_instrument;
  private PaymentFormResponse payment_form;
  private PaymentMethodResponse payment_method;
  private String public_url;

  public String getOrder_reference() {
    return order_reference;
  }

  public void setOrder_reference(String order_reference) {
    this.order_reference = order_reference;
  }

  public OperationTypeResponse getOperation_type() {
    return operation_type;
  }

  public void setOperation_type(OperationTypeResponse operation_type) {
    this.operation_type = operation_type;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getCufe() {
    return cufe;
  }

  public void setCufe(String cufe) {
    this.cufe = cufe;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public String getDiscount_rate() {
    return discount_rate;
  }

  public void setDiscount_rate(String discount_rate) {
    this.discount_rate = discount_rate;
  }

  public DocumentResponse getDocument() {
    return document;
  }

  public void setDocument(DocumentResponse document) {
    this.document = document;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public String getGross_value() {
    return gross_value;
  }

  public void setGross_value(String gross_value) {
    this.gross_value = gross_value;
  }

  public Integer getHas_claim() {
    return has_claim;
  }

  public void setHas_claim(Integer has_claim) {
    this.has_claim = has_claim;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIs_negotiable_instrument() {
    return is_negotiable_instrument;
  }

  public void setIs_negotiable_instrument(Integer is_negotiable_instrument) {
    this.is_negotiable_instrument = is_negotiable_instrument;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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

  public PaymentFormResponse getPayment_form() {
    return payment_form;
  }

  public void setPayment_form(PaymentFormResponse payment_form) {
    this.payment_form = payment_form;
  }

  public PaymentMethodResponse getPayment_method() {
    return payment_method;
  }

  public void setPayment_method(PaymentMethodResponse payment_method) {
    this.payment_method = payment_method;
  }

  public String getPublic_url() {
    return public_url;
  }

  public void setPublic_url(String public_url) {
    this.public_url = public_url;
  }

  public String getQr() {
    return qr;
  }

  public void setQr(String qr) {
    this.qr = qr;
  }

  public String getQr_image() {
    return qr_image;
  }

  public void setQr_image(String qr_image) {
    this.qr_image = qr_image;
  }

  public String getReference_code() {
    return reference_code;
  }

  public void setReference_code(String reference_code) {
    this.reference_code = reference_code;
  }

  public Integer getSend_email() {
    return send_email;
  }

  public void setSend_email(Integer send_email) {
    this.send_email = send_email;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getTax_amount() {
    return tax_amount;
  }

  public void setTax_amount(String tax_amount) {
    this.tax_amount = tax_amount;
  }

  public String getTaxable_amount() {
    return taxable_amount;
  }

  public void setTaxable_amount(String taxable_amount) {
    this.taxable_amount = taxable_amount;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getValidated() {
    return validated;
  }

  public void setValidated(String validated) {
    this.validated = validated;
  }
}
