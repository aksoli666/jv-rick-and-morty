package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character management", description = "Endpoints for managing characters")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(
            summary = "Get random character",
            description = "Retrieve character details using a random ID"
    )
    @GetMapping("/random")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(
            summary = "Get all characters by part of the name",
            description = "Retrieve character catalog by part of the name and ignore case"
    )
    @GetMapping("/by-part-name")
    public List<Character> getCharactersByNameContaining(String name) {
        return characterService.getCharactersByNameContainingIgnoreCase(name);
    }
}
