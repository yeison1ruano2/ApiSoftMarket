package com.softmarket.apisoftmarket.controller;

import com.softmarket.apisoftmarket.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  private final UserService userService;

  public UsuarioController(UserService userService) {
    this.userService = userService;
  }


}
