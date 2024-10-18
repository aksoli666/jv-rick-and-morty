package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record RickAndMortyDataDto(
        RickAndMortyInfoDto info,
        List<RickAndMortyResultsDto> results
) {
}
