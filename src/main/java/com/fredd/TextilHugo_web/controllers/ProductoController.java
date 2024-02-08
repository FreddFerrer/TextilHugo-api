package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateProductoDto;
import com.fredd.TextilHugo_web.payload.CustomErrorResponse;
import com.fredd.TextilHugo_web.services.IProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService productosService;

    @Operation(
            tags = {"Productos"},
            operationId = "getAllPorductos",
            summary = "Obtener todos los productos",
            description = "Enlista todos los productos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "404", description = "No hay registros de productos en el sistema", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"No hay registros de productos en el sistema\", \"mensaje\": \"uri=/api/v1/auth/productos\" }")
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @GetMapping()
    public ResponseEntity<?> getAllProductos() {
        List<ProductoDto> clothing = productosService.getAllClothings();
        if (clothing == null || clothing.isEmpty()) {
            throw new ResourceNotFoundException("productos");
        }
        return new ResponseEntity<>(clothing, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener un producto por ID",
            description = "Obtiene la información detallada de un producto específico por su ID",
            tags = {"Productos"},
            operationId = "getProductoById",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"producto no fue encontado con: id=''\", \"mensaje\": \"uri=/api/v1/productos/id\" }")
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")

            }
    )
    @GetMapping("/{productoId}")
    public ResponseEntity<?> getProductoById(@Parameter(description = "ID del producto a buscar")@PathVariable Long productoId) {

        Optional<ProductoDto> producto = productosService.getProductoById(productoId);

        if (producto.isEmpty()) {
            throw new ResourceNotFoundException("producto", "id", productoId);
        }

        return new ResponseEntity<>(producto, HttpStatus.OK);

    }

    @Operation(
            tags = {"Productos"},
            operationId = "createProduct",
            summary = "Crear un producto",
            description = "Crea un producto nuevo.",
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Producto a ser creado. **IMPORTANTE:** Los campos 'temporada' y 'talle' <u>no son obligatorios</u>. " +
                            "                    Si desea poner el talle solo" +
                            "                    debe poner el id del mismo.",
                    content = @Content(schema = @Schema(implementation = CreateProductoDto.class))

            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Validación fallida"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @PostMapping()
    public ResponseEntity<?> saveIndumentaria(@RequestBody @Valid CreateProductoDto clothing) {
        ProductoDto newClothing;
        try {
            newClothing = productosService.saveIndumentaria(clothing);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newClothing, HttpStatus.CREATED);
    }

    @PutMapping("{idClothing}")
    public ResponseEntity<?> updateClothing(@RequestBody @Valid ProductoDto clothing, @PathVariable Long idClothing){

        try {
            ProductoDto updatedClothing = productosService.updateIndumentaria(idClothing, clothing);
            return ResponseEntity.ok(updatedClothing);
        } catch (DataAccessException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("{idProducto}")
    public ResponseEntity<?> delete(@PathVariable Long idProducto) {
        try {
            productosService.deleteProductoById(idProducto);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("indumentaria", "id", idProducto);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

}
