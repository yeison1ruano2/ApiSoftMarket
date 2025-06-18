package com.softmarket.apisoftmarket.entity;

import com.softmarket.apisoftmarket.dto.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido;
  private String identificacion;
  private String correo;
  private String telefono;
  private Boolean estado;
  private String username;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role roles;

  public Usuario() {
  }

  public Usuario(String nombre, String apellido, String identificacion, String correo, String telefono, String username,
      String password, Role roles, boolean estado) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.identificacion = identificacion;
    this.correo = correo;
    this.telefono = telefono;
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.estado = estado;
  }

  public Role getRoles() {
    return roles;
  }

  public void setRoles(Role roles) {
    this.roles = roles;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.roles.name()));
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean isEstado() {
    return estado;
  }

  public void setEstado(Boolean estado) {
    this.estado = estado;
  }
}
