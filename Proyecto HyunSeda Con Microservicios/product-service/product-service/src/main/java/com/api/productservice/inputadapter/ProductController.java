package com.api.productservice.inputadapter;

import com.api.productservice.domain.Product;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ProductModel")
public class ProductController {
    @Autowired
    private IProductService productService;


    @GetMapping
    @Operation(summary = "Obtener todos los productos",
            description = "Obtiene una lista de todos los productos almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class),
                    examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"Aretes\"}, {\"id\": 2, \"name\": \"Ruanas\"}]")))
    public ArrayList<Product> getAllProducts() {
        return this.productService.getProducts();
    }

    @PostMapping
    @Operation(summary = "Guardar un producto.")
    @ApiResponse(responseCode = "200", description = "Producto guardado con éxito.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class),
                    examples = @ExampleObject(value =
                            "{" + "\"id\": 7," + "\"name\": \"Bufanda roja\"," + "\"description\": \"Bufanda de seda con hilos rojos\"," +
                                    "\"price\": 140000.0," + "\"image\": \"tu/ruta\"," + "\"category\": {" + "\"categoryId\": 2," +
                                    "\"name\": \"Bufandas\"" +
                                    "}" + "}")))

    @ApiResponse(responseCode = "422", description = "Nombre del producto no ingresado o formato de precio del producto no válido",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value =
                            "{" +
                                    "\"errors\": [" +
                                    "{" +
                                    "\"code\": \"INVALID_NUMBER\"," +
                                    "\"field\": \"price\"," +
                                    "\"message\": \"El precio del producto es obligatorio y mayor a cero\"" +
                                    "}" +
                                    "]" +
                                    "}")))
    public Product createProduct(@RequestBody Product product) throws ProductDomainException {
        return this.productService.save(product);
    }


    @PutMapping(path = "/{id}")
    @Operation(summary = "Edita un producto.", description = "Edita un producto en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Producto editado con éxito, retorna el producto editado.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class),
                    examples = @ExampleObject(value =
                            "{" + "\"id\": 7," + "\"name\": \"Bufanda roja\"," + "\"description\": \"Bufanda de seda con hilos rojos\"," +
                                    "\"price\": 140000.0," + "\"image\": \"tu/ruta\"," + "\"category\": {" + "\"categoryId\": 2," +
                                    "\"name\": \"Bufandas\"" +
                                    "}" + "}")))
    @ApiResponse(responseCode = "422", description = "Nombre del producto no ingresado o formato de precio del producto no válido",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value =
                            "{" +
                                    "\"errors\": [" +
                                    "{" +
                                    "\"code\": \"INVALID_NUMBER\"," +
                                    "\"field\": \"price\"," +
                                    "\"message\": \"El precio del producto es obligatorio y mayor a cero\"" +
                                    "}" +
                                    "]" +
                                    "}")))
    public Product updateById(@RequestBody Product product,@PathVariable("id") long id)throws ProductDomainException,ResourceNotFoundException {
        return this.productService.updateById(product,id);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Eliminar un producto.",
            description = "Elimina un producto existente de la base de datos por su ID.")
    @ApiResponse(responseCode = "200", description = "Producto eliminada con éxito"
            ,content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Producto a eliminar no encontrada.",
            content = @Content (mediaType = "application/json", examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\"}]}")))
    public void deleteById(@PathVariable("id") long id) throws ResourceNotFoundException{
         productService.deleteById(id);
    }

    @GetMapping(path = "/contarProductosPorCategoria")
    @Operation(summary = "Cuenta los productos que existen por categoria.",
            description = "Retorna un Map<String,Integer> en el cual especifica cuantos productos existen por categoría.")
    @ApiResponse(responseCode = "200", description = "Map recuperado exitosamente."
            ,content = @Content(mediaType = "application/json",examples = @ExampleObject(value = "{\"Ruanas\": \"3\"}")))
    public Map<String, Integer> contarProductosPorCategoria() {
        return this.productService.contarProductosPorCategoria();
    }


    @GetMapping("/byNameMatching/{name}")
    @Operation(summary = "Obtiene los productos que coincidan con el nombre ingresado",
            description = "Obtiene una lista de los productos de los que su nombre coincida con la cadena ingresada. Ejemplo: cadena = a, retornará el producto con nombre Arete, Ruana, Bufanda...")
    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value =
                            "{" + "\"id\": 7," + "\"name\": \"Bufanda roja\"," + "\"description\": \"Bufanda de seda con hilos rojos\"," +
                                    "\"price\": 140000.0," + "\"image\": \"tu/ruta\"," + "\"category\": {" + "\"categoryId\": 2," +
                                    "\"name\": \"Bufandas\"" +
                                    "}" + "}")))
    public List<Product> findByMatchingName(@PathVariable String name) {
        return productService.findByMatchingName(name);
    }

    @GetMapping("/byIdMatching/{id}")
    @Operation(summary = "Obtiene los productos que coincidan con el id ingresado",
            description = "Obtiene una lista de los productos de las que su id coincida con la cadena ingresada. Ejemplo: cadena = 1, retornará los productos con id 1, 10, 11, 12...")
    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value =
                            "{" + "\"id\": 1," + "\"name\": \"Bufanda roja\"," + "\"description\": \"Bufanda de seda con hilos rojos\"," +
                                    "\"price\": 140000.0," + "\"image\": \"tu/ruta\"," + "\"category\": {" + "\"categoryId\": 2," +
                                    "\"name\": \"Bufandas\"" +
                                    "}" + "}")))
    public List<Product> findByMatchingId(@PathVariable String id) {
        return productService.findByMatchingId(id);
    }


    @GetMapping("/byCategoryMatching/{name}")
    @Operation(summary = "Obtiene los productos que coincidan con el nombre de la categoria ingresado",
            description = "Obtiene una lista de los productos de las que el nombre de la categoría al que estan asociados coincida con" +
                    " la cadena ingresada. Ejemplo: cadena = buf, retornará los productos que esten asociados a la categoría bufandas..")
    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value =
                            "{" + "\"id\": 1," + "\"name\": \"Bufanda roja\"," + "\"description\": \"Bufanda de seda con hilos rojos\"," +
                                    "\"price\": 140000.0," + "\"image\": \"tu/ruta\"," + "\"category\": {" + "\"categoryId\": 2," +
                                    "\"name\": \"Bufandas\"" +
                                    "}" + "}")))
    public List<Product> findByMatchingCategory(@PathVariable String name) {
        return productService.findByMatchingCategoryName(name);
    }



}
