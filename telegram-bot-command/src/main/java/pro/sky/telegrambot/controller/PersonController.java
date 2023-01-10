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
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Operation(
            summary = "Вывести весь список контактов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные контакты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Person.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Persons"
    )
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }


    @Operation(
            summary = "Найти контакт по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный контакт",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Person.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ничего не найдено"
                    )
            },
            tags = "Persons"
    )
    @GetMapping("id")
    public Person findPersonById(@Parameter(description = "Введите ID контакта", example = "15") @RequestParam Long id) {
        return personService.getPerson(id);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Создание новой записи контакт",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Person.class)
                    )
            ),
            tags = "Persons"
    )
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Редактирование записи контакта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Person.class)
                    )
            ),
            tags = "Persons"
    )
    @PutMapping
    public Person editPerson(@RequestBody Person person) {
        return personService.editPerson(person);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Удаление записи контакта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Person.class)
                    )
            ),
            tags = "Persons"
    )
    @DeleteMapping("id")
    public ResponseEntity deletePerson(@RequestParam Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }

}
