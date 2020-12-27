package springboot2essentials.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import springboot2essentials.domain.Anime;
import springboot2essentials.exception.BadRequestException;
import springboot2essentials.mapper.AnimeMapper;
import springboot2essentials.repository.AnimeRepository;
import springboot2essentials.requests.AnimePostRequestBody;
import springboot2essentials.requests.AnimePutRequestBody;

@Service
public class AnimeService {

	@Autowired
	public AnimeRepository animeRepository ;



	public Page<Anime> getAll(Pageable pageable){
//			System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//			System.out.println(this.listAnimes);
			return this.animeRepository.findAll(pageable);
		
	}

	public List<Anime> getAll( ){
//			System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//			System.out.println(this.listAnimes);
		return this.animeRepository.findAll();

	}

	public List<Anime> findByName(String name){
		return this.animeRepository.findByName(name);
				
	}

	public Anime findByIdOrThrowBadRequestException(Long id){
		return this.animeRepository.findById(id)
		.orElseThrow(()-> new BadRequestException("Not found Anime"));
		

	}

	public Anime save(AnimePostRequestBody animePostRequestBody){
		Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
		return this.animeRepository.save(anime);


	}
	public void delete(Long id){
		animeRepository.delete(findByIdOrThrowBadRequestException(id));
	

	}	

	public Anime update(AnimePutRequestBody animePutRequestBody){
		// CAUSARA UM ERRO CASO NAO ENCONTRE !! 
		Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
		Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
		anime.setId(savedAnime.getId());
		return this.animeRepository.save(anime);
		

	}


	public void converterAnime(Anime anime , AnimePostRequestBody animePostRequestBody){
		anime.setName(anime.getName());

	}



}



