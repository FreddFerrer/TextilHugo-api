package com.fredd.TextilHugo_web.model.dtos.request;

import com.fredd.TextilHugo_web.model.enums.RolEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUsuarioDto {
    @NotEmpty(message = "campo obligatorio")
    @NotNull
    private String username;

    @NotEmpty(message = "campo obligatorio")
    @NotNull
    private String contraseña;

    @Email
    @NotEmpty(message = "campo obligatorio")
    @NotNull
    private String email;

    @NotEmpty(message = "campo obligatorio")
    @NotNull
    private String nombre;

    @NotEmpty(message = "campo obligatorio")
    @NotNull
    private String apellido;
}
