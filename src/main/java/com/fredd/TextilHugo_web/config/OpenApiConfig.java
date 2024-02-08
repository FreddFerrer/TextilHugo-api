package com.fredd.TextilHugo_web.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Textil Hugo API - Freddy Ferreira",
                description = "API RESTful diseñada para la implementación de un e-commerce de indumentaria masculina y femenina, satisfaciendo los requerimientos esenciales de este tipo de plataformas.\n" +
                        "\n**Funcionalidades principales:**\n" +
                        "- Listado de productos por categorías.\n" +
                        "- Realización de pedidos con múltiples productos y pago a través de Mercado Pago.\n" +
                        "- Administración interna de productos, categorías y pedidos.\n",
                contact = @Contact(name = "Freddy Ferreira", email = "ferreirafreddy39@gmail.com"),
                version = "1.0.0"),
        //externalDocs = @ExternalDocumentation(description = "Repositorio del proyecto", url = "https://github.com/JuanDouCore/EcommerceApi"),
        tags = {
                @Tag(name = "Autenticacion", description = "API para la gestion de autenticaciones"),
                @Tag(name = "Productos", description = "API para la vistas y busquedas de categorias y productos"),
                @Tag(name = "Inventario", description = "API para la gestion integral de las categorias de productos"),
        }
)

@SecurityScheme(
        name = "Bearer Authentication",
        description = "Token JWT que permite la autorizacion al acceso a los recursos. Este puede ser solicitado al iniciar sesion desde el endpoint de autentitacion.",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
