package springboot2essentials.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import springboot2essentials.domain.Anime;
import springboot2essentials.requests.AnimePostRequestBody;
import springboot2essentials.requests.AnimePutRequestBody;
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
	public  ResponseEntity<Page<Anime>> list(Pageable pageable){
		//System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok().body(this.animeService.getAll(pageable));
	}


	@GetMapping(path = "all")
	@ResponseBody
	public  ResponseEntity<List<Anime>> all( ){
		//System.out.println(	dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return ResponseEntity.ok().body(this.animeService.getAll());
	}

	@GetMapping(path ="/{id}")
	@ResponseBody
	public ResponseEntity<Anime> findById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.animeService.findByIdOrThrowBadRequestException(id));
	}

	
	@PostMapping

	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody anime){
		return new ResponseEntity<>(this.animeService.save(anime), HttpStatus.CREATED);

	}


	@DeleteMapping(path ="/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id){
		this.animeService.delete(id);
		return ResponseEntity.noContent().build();

	}


	@PutMapping
	public ResponseEntity<Anime> update(@RequestBody @Valid AnimePutRequestBody animePutRequestBody) {
		return ResponseEntity.ok().body(this.animeService.update(animePutRequestBody));
	}

	@GetMapping
	public ResponseEntity<List<Anime>> findByName(@RequestParam String name){
		List<Anime> listAnime = this.animeService.findByName(name);
		return ResponseEntity.ok().body(listAnime);


	}

}
