package springboot2essentials.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springboot2essentials.domain.Anime;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Test for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Test to save Anime in the Repository")
    public void saveAnime_whenSuccessul(){
        Anime animeTobeSaved = createAnime();

        Anime animeSaved = animeRepository.save(animeTobeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeTobeSaved.getName());

    }

    @Test
    @DisplayName("Test to update Anime in the repository")
    public void updatAnime_whenSuccesful(){
        Anime animeCreated = createAnime();
        //salvo com o nome de tiago Animado;
        Anime animeSaved = animeRepository.save(animeCreated);

        animeSaved.setName("Mastruz com leite");
        //Alterando nome para mastruz com leite e salvando
        Anime animeUpdate = animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdate).isNotNull();
        Assertions.assertThat(animeUpdate.getId()).isNotNull();
        Assertions.assertThat(animeUpdate.getName()).isNotNull();
        Assertions.assertThat(animeUpdate.getId()).isEqualTo(animeSaved.getId());
        Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeSaved.getName());

    }

    @Test
    @DisplayName("Delete anime")
    public void deleteAnime_whenSuccessful(){

        Anime anime = createAnime();
        Anime animeSaved = animeRepository.save(anime);
        animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional).isEmpty();


    }

    @Test
    @DisplayName("Find by name")
    public void findByName_whenSucessful(){
        Anime anime  = createAnime();
        anime.setName("Cavaleiros do zoodiaco");
        animeRepository.save(anime);

        List<Anime> listAnimeFound = animeRepository.findByName(anime.getName());

        Assertions.assertThat(listAnimeFound).isNotEmpty();
        Assertions.assertThat(listAnimeFound).contains(anime);


    }

    @Test
    @DisplayName("Not found by name when for null")
    public void notfindByName_whenOfNull(){


        List<Anime> listAnimeFound = animeRepository.findByName("esse Anime nao existe");

        Assertions.assertThat(listAnimeFound).isEmpty();



    }
    private Anime createAnime(){
        return new Anime("Tiago Animado");
    }


}