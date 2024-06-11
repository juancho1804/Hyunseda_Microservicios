package com.api.productservice.inputadapter;

import com.api.productservice.domain.Category;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CategoryModel")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    @Operation(summary = "Obtener todas las categorías",
            description = "Obtiene una lista de todas las categorías almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de categorías recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class),
                    examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"Aretes\"}, {\"id\": 2, \"name\": \"Ruanas\"}]")))
    public ArrayList<Category> getCategories() {
        return categoryService.getCategories();
    }



    @PostMapping
    @Operation(summary = "Guardar una categoría",
            description = "Guarda una nueva categoría en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Categoría guardada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class),
                    examples = @ExampleObject(value = "{\"name\": \"Ruanas\"}")))
    @ApiResponse(responseCode = "422", description = "El nombre de la categoría es obligatorio",
    content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"EMPTY_FIELD\", \"field\": \"name\", \"message\": \"El nombre de la categoría es obligatorio\"}]}")))
    public Category save(@RequestBody Category newCategoryModel)throws ProductDomainException {
        return categoryService.save(newCategoryModel);
    }


   @PutMapping(path = "/{id}")
   @Operation(summary = "Edita una categoría.", description = "Edita una categoría en la base de datos.")
   @ApiResponse(responseCode = "200", description = "Categoría editada con éxito, retorna la categoría editada.",
           content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class),
           examples = @ExampleObject(value = "{\"name\": \"Ruanas\"}")))
   @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
   content = @Content (mediaType = "application/json", examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\"}]}")))
   @ApiResponse(responseCode = "422", description = "El nombre de la categoría es obligatorio",
           content = @Content(mediaType = "application/json",
                   examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"EMPTY_FIELD\", \"field\": \"name\", \"message\": \"El nombre de la categoría es obligatorio\"}]}")))
    public Category updateById(@RequestBody Category categoryModel, @PathVariable("id") long id) throws ProductDomainException,ResourceNotFoundException{
        return categoryService.updateById(categoryModel,id);
    }



    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Eliminar una categoría",
            description = "Elimina una categoría existente de la base de datos por su ID.")
    @ApiResponse(responseCode = "200", description = "Categoría eliminada con éxito"
    ,content = @Content(mediaType = "application/json",
    examples = @ExampleObject(value = "true")))
    @ApiResponse(responseCode = "404", description = "Categoría a eliminar no encontrada.",
            content = @Content (mediaType = "application/json", examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\"}]}")))
    public boolean deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if(categoryService.deleteById(id)) {
            System.out.println("Categoria borrada satisfactoriamente");
            return true;
        }
        System.out.println("Error al eliminar la categoria");
        return false;
    }



    @GetMapping(path ="/findByMatchingName/{name}")
    @Operation(summary = "Obtiene las categorías que coincidan con el nombre ingresado",
            description = "Obtiene una lista de las categorias de las que su nombre coincida con la cadena ingresada. Ejemplo: cadena = a, retornará la categoria con nombre Aretes, Ruanas, Bufandas...")
    @ApiResponse(responseCode = "200", description = "Lista de categorías recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"Aretes\"}, {\"id\": 2, \"name\": \"Ruanas\"}]")))
    List<Category> findByMatchingName(@PathVariable String name){
        return categoryService.findByMatchingName(name);
    }


    @GetMapping(path ="/findByMatchingId/{id}")
    @Operation(summary = "Obtiene las categorías que coincidan con el id ingresado",
            description = "Obtiene una lista de las categorias de las que su id coincida con la cadena ingresada. Ejemplo: cadena = 1, retornará las categorias con id 1, 10, 11, 12...")
    @ApiResponse(responseCode = "200", description = "Lista de categorías recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "[{\"id\": 1, \"name\": \"Aretes\"}, {\"id\": 10, \"name\": \"Ruanas\"}]")))
    List<Category> findByMatchingId(@PathVariable String id){
        return categoryService.findByMatchingId(id);
    }


    @GetMapping(path = "/byName/{name}")
    @Operation(summary = "Obtiene la categoría que coincida exactamente con el nombre ingresado",
            description = "Obtiene la categoria de las que su nombre coincida con la cadena ingresada. Ejemplo: cadena = Ruanas, retornará la categoria con nombre Ruanas.")
    @ApiResponse(responseCode = "200", description = "Categoría recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"categoryId\": 2, \"name\": \"Ruanas\"}")))
    public Category findByName(@PathVariable("name") String name) {
        return categoryService.findByName(name);
    }

    @GetMapping(path = "/byId/{id}")


    @Operation(summary = "Obtiene la categoría que coincida exactamente con el id ingresado",
            description = "Obtiene la categoria de las que su id coincida con la cadena ingresada. Ejemplo: cadena = 1, retornará la categoria con id 1.")
    @ApiResponse(responseCode = "200", description = "Categoría recuperada exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"categoryId\": 1, \"name\": \"Bufandas\"}")))
    public Category findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return categoryService.findById(id);
    }
}
