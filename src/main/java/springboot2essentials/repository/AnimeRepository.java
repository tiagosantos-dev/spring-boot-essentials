package springboot2essentials.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot2essentials.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime , Long>{

    List<Anime> findByName(String name);

}
