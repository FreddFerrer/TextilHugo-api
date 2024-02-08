package com.fredd.TextilHugo_web.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CustomErrorResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String estado;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensaje;

    public CustomErrorResponse(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public CustomErrorResponse(String mensaje, Map<String, String> errors) {
        this.mensaje = mensaje;
        this.errors = errors;
    }

    public CustomErrorResponse(String estado) {
        this.estado = estado;
    }
}
