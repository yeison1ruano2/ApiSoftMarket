package com.softmarket.apisoftmarket.dto;

import com.softmarket.apisoftmarket.entity.WithholdingTax;

import java.util.List;

public class ItemRequest {

  private String code_reference;
  private String name;
  private int quantity;
  private int discount_rate;
  private int price;
  private String tax_rate;
  private int unit_measure_id;
  private int standard_code_id;
  private int is_excluded;
  private int tribute_id;


  private List<WithholdingTax> withholding_taxes;

  public String getCode_reference() {
    return code_reference;
  }

  public void setCode_reference(String code_reference) {
    this.code_reference = code_reference;
  }

  public int getDiscount_rate() {
    return discount_rate;
  }

  public void setDiscount_rate(int discount_rate) {
    this.discount_rate = discount_rate;
  }

  public int getIs_excluded() {
    return is_excluded;
  }

  public void setIs_excluded(int is_excluded) {
    this.is_excluded = is_excluded;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getStandard_code_id() {
    return standard_code_id;
  }

  public void setStandard_code_id(int standard_code_id) {
    this.standard_code_id = standard_code_id;
  }

  public String getTax_rate() {
    return tax_rate;
  }

  public void setTax_rate(String tax_rate) {
    this.tax_rate = tax_rate;
  }

  public int getTribute_id() {
    return tribute_id;
  }

  public void setTribute_id(int tribute_id) {
    this.tribute_id = tribute_id;
  }

  public int getUnit_measure_id() {
    return unit_measure_id;
  }

  public void setUnit_measure_id(int unit_measure_id) {
    this.unit_measure_id = unit_measure_id;
  }

  public List<WithholdingTax> getWithholding_taxes() {
    return withholding_taxes;
  }

  public void setWithholding_taxes(List<WithholdingTax> withholding_taxes) {
    this.withholding_taxes = withholding_taxes;
  }
}
