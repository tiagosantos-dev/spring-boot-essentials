package springboot2essentials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import springboot2essentials.domain.Anime;
import springboot2essentials.service.AnimeService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(path = "animes" )
public class AnimeController {


	@Autowired
	private  AnimeService animeService;

	//@Autowired
	//private DateUtil dateUtil;
	
	@GetMapping(path = "list")
	@ResponseBody
	public  ResponseEntity<List<Anime>> list(){
		//System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok().body(this.animeService.getAll());
	}

	@GetMapping(path ="/{id}")
	public ResponseEntity<Anime> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.animeService.findById(id));
	}

	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Anime> save(@RequestBody Anime anime){
		return new ResponseEntity<>(this.animeService.save(anime), HttpStatus.CREATED);

	}


	@DeleteMapping(path ="/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id){
		this.animeService.delete(id);
		return ResponseEntity.noContent().build();

	}


	@PutMapping(path="/{id}")
	public ResponseEntity<Anime> update(@PathVariable Long id ,@RequestBody Anime anime) {
		return ResponseEntity.ok().body(this.animeService.update(anime, id));
	}

}
