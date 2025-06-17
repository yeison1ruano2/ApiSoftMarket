package com.softmarket.apisoftmarket.entity;

import java.util.List;

public class ItemResponse {

  private String scheme_id;
  private String note;
  private String code_reference;
  private String name;
  private Integer quantity;
  private String discount_rate;
  private String discount;
  private String gross_value;
  private String tax_rate;
  private String taxable_amount;
  private String tax_amount;
  private String price;
  private Integer is_excluded;
  private UnitMeasure unit_measure;
  private StandardCode standard_code;
  private TributeResponse tribute;
  private Integer total;
  private List<WithholdingTaxisResponse> withholding_taxes;
  private String mandate;

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getScheme_id() {
    return scheme_id;
  }

  public void setScheme_id(String scheme_id) {
    this.scheme_id = scheme_id;
  }

  public String getMandate() {
    return mandate;
  }

  public void setMandate(String mandate) {
    this.mandate = mandate;
  }

  public String getCode_reference() {
    return code_reference;
  }

  public void setCode_reference(String code_reference) {
    this.code_reference = code_reference;
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

  public String getGross_value() {
    return gross_value;
  }

  public void setGross_value(String gross_value) {
    this.gross_value = gross_value;
  }

  public Integer getIs_excluded() {
    return is_excluded;
  }

  public void setIs_excluded(Integer is_excluded) {
    this.is_excluded = is_excluded;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public StandardCode getStandard_code() {
    return standard_code;
  }

  public void setStandard_code(StandardCode standard_code) {
    this.standard_code = standard_code;
  }

  public String getTax_amount() {
    return tax_amount;
  }

  public void setTax_amount(String tax_amount) {
    this.tax_amount = tax_amount;
  }

  public String getTax_rate() {
    return tax_rate;
  }

  public void setTax_rate(String tax_rate) {
    this.tax_rate = tax_rate;
  }

  public String getTaxable_amount() {
    return taxable_amount;
  }

  public void setTaxable_amount(String taxable_amount) {
    this.taxable_amount = taxable_amount;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public TributeResponse getTribute() {
    return tribute;
  }

  public void setTribute(TributeResponse tribute) {
    this.tribute = tribute;
  }

  public UnitMeasure getUnit_measure() {
    return unit_measure;
  }

  public void setUnit_measure(UnitMeasure unit_measure) {
    this.unit_measure = unit_measure;
  }

  public List<WithholdingTaxisResponse> getWithholding_taxes() {
    return withholding_taxes;
  }

  public void setWithholding_taxes(List<WithholdingTaxisResponse> withholding_taxes) {
    this.withholding_taxes = withholding_taxes;
  }
}
