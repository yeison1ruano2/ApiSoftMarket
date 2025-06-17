package com.softmarket.apisoftmarket.dto;

import com.softmarket.apisoftmarket.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {

  private CompanyResponse company;
  private CustomerResponse customer;
  private NumberingRangeResponse numbering_range;
  private BilingPeriodResponse billing_period;
  private BillResponse bill;
  private List<Object> related_documents = new ArrayList<>();
  private List<ItemResponse> items;
  private List<WithholdingTaxisResponse> withholding_taxes;
  private List<Object> credit_notes = new ArrayList<>();
  private List<Object> debit_notes = new ArrayList<>();

  public BillResponse getBill() {
    return bill;
  }

  public void setBill(BillResponse bill) {
    this.bill = bill;
  }

  public BilingPeriodResponse getBilling_period() {
    return billing_period;
  }

  public void setBilling_period(BilingPeriodResponse billing_period) {
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
    return items;
  }

  public void setItems(List<ItemResponse> item) {
    this.items = item;
  }

  public NumberingRangeResponse getNumbering_range() {
    return numbering_range;
  }

  public void setNumbering_range(NumberingRangeResponse numbering_range) {
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
