package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickAndMortyResultsDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {

    @Mapping(target = "externalId", source = "id")
    Character toModel(RickAndMortyResultsDto result);

    List<Character> toModelList(List<RickAndMortyResultsDto> results);
}
