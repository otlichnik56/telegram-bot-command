package pro.sky.telegrambot.controller;

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

    @GetMapping("{kindOfAnimal}")
    public List<Animal> findFacultyStudent(@PathVariable String kindOfAnimal) {
        return animalService.getAllAnimalsKids(kindOfAnimal);
    }

    @GetMapping("{id}")
    public Animal findAnimalById(@PathVariable Long id) {
        return animalService.getAnimal(id);
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }

    @PostMapping
    public Animal editAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }

    @DeleteMapping("id")
    public ResponseEntity deleteAnimal(@RequestParam Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }

}
