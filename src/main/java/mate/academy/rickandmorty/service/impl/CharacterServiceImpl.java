package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        long countCharacters = characterRepository.count();
        if (countCharacters == 0) {
            throw new EntityNotFoundException("No character in DB");
        }
        Long randomId = (long) (Math.random() * countCharacters) + 1;
        return characterRepository.findById(randomId).orElseThrow(()
                -> new EntityNotFoundException("Can`t find random character. "
                + "The search was on this id: " + randomId));
    }

    @Override
    public List<Character> getCharactersByNameContainingIgnoreCase(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }
}
