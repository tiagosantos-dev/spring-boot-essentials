package springboot2essentials.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import springboot2essentials.domain.Anime;
import springboot2essentials.requests.AnimePostRequestBody;
import springboot2essentials.requests.AnimePutRequestBody;


@Mapper(componentModel = "spring")
public interface AnimeMapper {

    AnimeMapper  INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public  Anime toAnime(AnimePostRequestBody animePostRequestBody);

    public  Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
