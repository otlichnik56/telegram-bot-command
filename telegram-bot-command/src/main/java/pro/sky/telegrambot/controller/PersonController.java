package pro.sky.telegrambot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("{id}")
    public Person findPersonById(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PostMapping
    public Person editPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @DeleteMapping("id")
    public ResponseEntity deletePerson(@RequestParam Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }

}
