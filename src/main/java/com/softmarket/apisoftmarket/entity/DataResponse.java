package com.softmarket.apisoftmarket.entity;

import com.softmarket.apisoftmarket.dto.BillingPeriodResponse;

import java.util.List;

public class DataResponse {

  private CompanyResponse company;
  private CustomerResponse customer;
  private NumberingRange numbering_range;
  private BillingPeriodResponse billing_period;
  private BillResponse bill;
  private List<Object> related_documents;
  private List<ItemResponse> item;
  private List<WithholdingTaxisResponse> withholding_taxes;
  private List<Object> credit_notes;
  private List<Object> debit_notes;

  public BillResponse getBill() {
    return bill;
  }

  public void setBill(BillResponse bill) {
    this.bill = bill;
  }

  public BillingPeriodResponse getBilling_period() {
    return billing_period;
  }

  public void setBilling_period(BillingPeriodResponse billing_period) {
    this.billing_period = billing_period;
  }

  public CompanyResponse getCompany() {
    return company;
  }

  public void setCompany(CompanyResponse company) {
    this.company = company;
  }

  public List<Object> getCredit_notes() {
    return credit_notes;
  }

  public void setCredit_notes(List<Object> credit_notes) {
    this.credit_notes = credit_notes;
  }

  public CustomerResponse getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerResponse customerResponse) {
    this.customer = customerResponse;
  }

  public List<Object> getDebit_notes() {
    return debit_notes;
  }

  public void setDebit_notes(List<Object> debit_notes) {
    this.debit_notes = debit_notes;
  }

  public List<ItemResponse> getItems() {
    return item;
  }

  public void setItems(List<ItemResponse> item) {
    this.item = item;
  }

  public NumberingRange getNumbering_range() {
    return numbering_range;
  }

  public void setNumbering_range(NumberingRange numbering_range) {
    this.numbering_range = numbering_range;
  }

  public List<Object> getRelated_documents() {
    return related_documents;
  }

  public void setRelated_documents(List<Object> related_documents) {
    this.related_documents = related_documents;
  }

  public List<WithholdingTaxisResponse> getWithholding_taxes() {
    return withholding_taxes;
  }

  public void setWithholding_taxes(List<WithholdingTaxisResponse> withholding_taxes) {
    this.withholding_taxes = withholding_taxes;
  }
}
