package springboot2essentials.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import springboot2essentials.domain.Anime;

@Service
public class AnimeService {
	private static List<Anime> listAnimes ;

	static{
		listAnimes = new ArrayList<>(List.of(new Anime(1L, "Drangon Ball"), new Anime(2L, "Naruto" )));


	}

	public List<Anime> getAll(){
//			System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//			System.out.println(this.listAnimes);
			return 	listAnimes;
		
	}

	public Anime findById(Long id){
		return listAnimes.stream().filter(element-> element.getId().equals(id))
					.findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found Anime"));

	}

	public Anime save(Anime anime){
		anime.setId(Math.abs(ThreadLocalRandom.current().nextLong()));
		listAnimes.add(anime);
		return anime;

	}
	public Boolean delete(Long id){
		Anime animeExcluido =findById(id);
		return listAnimes.remove(animeExcluido);
	

	}	

	public Anime update(Anime anime, Long id){
		delete(id);
		return save(anime);

	}




}



