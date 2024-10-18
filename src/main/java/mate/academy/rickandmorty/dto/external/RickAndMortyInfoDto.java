package mate.academy.rickandmorty.dto.external;

public record RickAndMortyInfoDto(
        int count,
        int pages,
        String next,
        String prev
) {
}
