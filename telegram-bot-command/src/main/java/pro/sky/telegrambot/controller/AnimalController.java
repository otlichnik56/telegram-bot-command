package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Animal;
import pro.sky.telegrambot.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }


    @Operation(
            summary = "Вывести весь список животных определенного вида",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные животные определенного вида",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Animal.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Animals"
    )
    @GetMapping("{kindOfAnimal}")
    public List<Animal> findFacultyStudent(@Parameter(description = "Введите вид животного на английском языке", example = "dog") @PathVariable String kindOfAnimal) {
        return animalService.getAllAnimalsKids(kindOfAnimal);
    }


    @Operation(
            summary = "Найти животное по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденное животное",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Animal.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Animals"
    )
    @GetMapping("{id}")
    public Animal findAnimalById(@Parameter(description = "Введите ID животного", example = "15") @PathVariable Long id) {
        return animalService.getAnimal(id);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создание новой записи животного",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            ),
            tags = "Animals"
    )
    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактирование записи животного",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            ),
            tags = "Animals"
    )
    @PutMapping
    public Animal editAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Удаление записи животного",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Animal.class)
                    )
            ),
            tags = "Animals"
    )
    @DeleteMapping("id")
    public ResponseEntity deleteAnimal(@RequestParam Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }

}
