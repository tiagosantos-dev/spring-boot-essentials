package springboot2essentials.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import springboot2essentials.domain.Anime;

import java.util.List;

public class SpringClient {

    public static void main(String[] args){

        //PODE ACEITAR OUTRO METODO PARA PEGAR O CORPO, E RETORNARA UM, ANIME (.getBody()) NO FINAL
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8081/animes/1", Anime.class );
        System.out.println(entity.toString());


        Anime animeId1 = new RestTemplate().getForObject("http://localhost:8081/animes/1", Anime.class);
        System.out.println(animeId1.toString());


        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange(
                "http://localhost:8081/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Anime>>() {});

        System.out.println(exchange.getBody().toString());
        //OUTRA FORMA DE TRAZER TODOS, SO QUE DENTRO DE UM ARRAY E USANDO CAST

        Anime[]  arrAnimes = new RestTemplate().getForObject("http://localhost:8081/animes/all", Anime[].class);

       System.out.println(arrAnimes[1]);

       //UTILIZANDO METODO POST

       Anime anime = new Anime();
       anime.setName("TEUKU NAVARA");

       Anime animeSaved = new RestTemplate().postForObject("http://localhost:8081/animes", anime, Anime.class);
       System.out.println(animeSaved.toString());


       Anime anime2 = new Anime();
       anime2.setName("isoxlsk");

       ResponseEntity<Anime>  animeSaved2 = new  RestTemplate().exchange(
               "http://localhost:8081/animes",
               HttpMethod.POST,
               new HttpEntity<>(anime2, SpringClient.getHeader()), Anime.class);

       System.out.println(animeSaved);

       //METODO POST

        Anime animeUpdate = new Anime();
            animeUpdate.setId(3L);
            animeUpdate. setName("Deu a louca em todo mundo");

       ResponseEntity<Void> animeUpdateResponse = new RestTemplate().exchange(
               "http://localhost:8081/animes",
               HttpMethod.PUT,
               new HttpEntity<>(animeUpdate, SpringClient.getHeader()),
               Void.class
               );


       System.out.println("RECEBIDO "+animeUpdate);


       //METODO DELETE


        ResponseEntity<Void> animeExcluido = new RestTemplate().exchange(
                "http://localhost:8081/animes/3",
                HttpMethod.DELETE,
               null,
                Void.class
        );

        System.out.print(animeExcluido);
    }



    public static HttpHeaders getHeader(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return header;
    }
}
