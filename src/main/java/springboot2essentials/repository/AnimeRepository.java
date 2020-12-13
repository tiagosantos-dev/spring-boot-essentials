package springboot2essentials.repository;

import java.util.List;

import springboot2essentials.domain.Anime;

public interface AnimeRepository{

    public List<Anime> getAll();

    public Anime findById();


}
