package com.softmarket.apisoftmarket.entity;

import com.softmarket.apisoftmarket.dto.Role;
import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(unique = true)
  private Role nombre;

  public Rol(Long id, Role nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Rol() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Role getNombre() {
    return nombre;
  }

  public void setNombre(Role nombre) {
    this.nombre = nombre;
  }
}
