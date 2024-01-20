package com.fredd.TextilHugo_web.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {
    private String estado;
    private String mensaje;

    public CustomErrorResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }
}
