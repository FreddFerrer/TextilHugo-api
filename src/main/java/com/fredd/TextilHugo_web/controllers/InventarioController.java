package com.fredd.TextilHugo_web.controllers;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import com.fredd.TextilHugo_web.exceptions.ResourceNotFoundException;
import com.fredd.TextilHugo_web.model.dtos.InventarioDto;
import com.fredd.TextilHugo_web.model.dtos.ProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.CompraRequestDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateInventarioDto;
import com.fredd.TextilHugo_web.model.dtos.request.CreateProductoDto;
import com.fredd.TextilHugo_web.model.dtos.request.EditInventarioDto;
import com.fredd.TextilHugo_web.model.entities.Inventario;
import com.fredd.TextilHugo_web.model.entities.Compra;
import com.fredd.TextilHugo_web.services.ICompraService;
import com.fredd.TextilHugo_web.services.IinventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final IinventarioService inventoryService;
    private final ICompraService purchaseService;

    @Operation(
            tags = {"Inventario"},
            operationId = "getAllInventario",
            summary = "Obtener todos los productos del inventario",
            description = "Enlista todos los productos del inventario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos disponibles en el inventario", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "404", description = "No hay registros de productos en el inventario", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"No hay registros de inventario en el sistema\", \"mensaje\": \"uri=/api/v1/auth/inventario\" }")
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @GetMapping()
    public ResponseEntity<?> getAllInventories() {
        List<Inventario> inventories = inventoryService.getAllInventory();
        if (inventories == null || inventories.isEmpty()) {
            throw new ResourceNotFoundException("inventario");
        }
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener un producto del inventario por ID",
            description = "Obtiene la información detallada de un producto del inventario específico por su ID",
            tags = {"Inventario"},
            operationId = "getIventarioById",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"producto no fue encontado con: id=''\", \"mensaje\": \"uri=/api/v1/inventario/id\" }")
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")

            }
    )
    @GetMapping("/{inventarioId}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long inventarioId) {

        Optional<InventarioDto> inventarioDto = inventoryService.getInventarioDtoById(inventarioId);

        if (inventarioDto.isEmpty()) {
            throw new ResourceNotFoundException("producto", "id", inventarioId);
        }

        return new ResponseEntity<>(inventarioDto, HttpStatus.OK);

    }

    @Operation(
            tags = {"Inventario"},
            operationId = "createInventario",
            summary = "Agregar un producto al inventario",
            description = "Agrega un nuevo producto al inventario. Si no existe el producto lo crea." +
                    "**IMPORTANTE:** Al poner el producto solo " +
                    " debe poner el id del mismo.",
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Producto a ser agregado al inventario.",
                    content = @Content(schema = @Schema(implementation = CreateInventarioDto.class))

            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto agregado al inventario", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Validación fallida. **EJEMPLO:**", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = """
                                {
                                    "errors": {
                                        "precioUnitario": "El precio unitario no debe ser nulo"
                                    },
                                "mensaje": "uri=/api/v1/inventario"
                                }
                            """)
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @PostMapping()
    public ResponseEntity<?> addInventory(@RequestBody @Valid CreateInventarioDto inventory) {
        Inventario newInventory;
        try {
            newInventory = inventoryService.addInventory(inventory);
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @Operation(
            tags = {"Inventario"},
            operationId = "editInventario",
            summary = "Edita cantidad o precio del producto en el inventario.",
            description = "Edita el precio o cantidad de un producto en el inventario.",
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Producto a ser creado.",
                    content = @Content(schema = @Schema(implementation = EditInventarioDto.class))

            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Precio o Cantidad modificada correctamente", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Validación fallida. **EJEMPLO:**", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = """
                                {
                                    "errors": {
                                        "precioUnitario": "El precio unitario no debe ser nulo."
                                    },
                                "mensaje": "uri=/api/v1/productos"
                                }
                            """)
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @PutMapping("/{inventarioId}")
    public ResponseEntity<?> updateInventory(@RequestBody @Valid EditInventarioDto editInventoryDto, @PathVariable Long inventarioId) {

        try {
            inventoryService.updateInventory(editInventoryDto, inventarioId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("inventario", "id", inventarioId);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(
            tags = {"Inventario"},
            summary = "Eliminar un producto del inventario",
            description = "Elimina un producto por su ID.",
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            parameters = {
                    @Parameter(name = "productoId", description = "ID del producto a eliminar", required = true, in = ParameterIn.PATH, schema = @Schema(implementation = Long.class))
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado del inventario"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"producto no fue encontado con: id=''\", \"mensaje\": \"uri=/api/v1/inventario/id\" }")
                    )),
            }
    )
    @DeleteMapping("/{inventarioId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long inventarioId) {
        try {
            Optional<Inventario> inventoryDelete = inventoryService.getInventarioById(inventarioId);
            if (inventoryDelete.isPresent()) {
                Inventario inventory = inventoryDelete.get();
                inventoryService.deleteInventoryById(inventory.getId());

                return new ResponseEntity<>(inventoryDelete, HttpStatus.NO_CONTENT);
            } else {
                throw new ResourceNotFoundException("inventario", "id", inventarioId);
            }
        } catch (DataAccessException exDt) {
            throw new BadRequestException(exDt.getMessage());
        }
    }

    @Operation(
            tags = {"Inventario"},
            operationId = "compraInventario",
            summary = "Disminuye la disponibilidad de los productos en el inventario.",
            description = "Imapcto de la compra. Disminuye los productos del inventario." +
                    "**IMPORTANTE:** Al poner el producto solo " +
                    " debe poner el id del mismo.",
            security = {@SecurityRequirement(name = "Bearer Authentication")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Producto a ser agregado al inventario.",
                    content = @Content(schema = @Schema(implementation = CreateInventarioDto.class))

            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto agregado al inventario", content = @Content(schema = @Schema(implementation = ProductoDto.class))),
                    @ApiResponse(responseCode = "400", description = "Validación fallida. **EJEMPLO:**", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = """
                                {
                                    "errors": {
                                        "precioUnitario": "El precio unitario no debe ser nulo"
                                    },
                                "mensaje": "uri=/api/v1/inventario"
                                }
                            """)
                    )),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            }
    )
    @PostMapping("/compra")
    public ResponseEntity<?> impactarCompra(@RequestBody @Valid CompraRequestDto compraRequest) {

        try {
            Compra effectedPurchase = purchaseService.impactarCompra(compraRequest);
            return ResponseEntity.ok(effectedPurchase);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e; // Reenvía las excepciones específicas
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()); // Envuelve otras excepciones en BadRequestException
        }
    }
}
