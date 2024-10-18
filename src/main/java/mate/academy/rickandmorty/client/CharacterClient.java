package mate.academy.rickandmorty.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyDataDto;
import mate.academy.rickandmorty.dto.external.RickAndMortyResultsDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterClient {
    @Value("${rick.and.morty.url}")
    private String urlRickAndMorty;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void init() {
        if (characterRepository.count() == 0) {
            saveCharactersCatalogFromThirdPartyApi(urlRickAndMorty);
        }
    }

    public void saveCharactersCatalogFromThirdPartyApi(String url) {
        HttpClient client = HttpClient.newHttpClient();
        List<RickAndMortyResultsDto> results = new ArrayList<>();
        try {
            while (url != null) {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );
                RickAndMortyDataDto data = objectMapper.readValue(
                        response.body(),
                        RickAndMortyDataDto.class
                );
                results.addAll(data.results());
                url = data.info().next();
            }
            List<Character> characters = characterMapper.toModelList(results);
            characterRepository.saveAll(characters);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can`t send request to Third-Party API", e);
        }
    }
}
