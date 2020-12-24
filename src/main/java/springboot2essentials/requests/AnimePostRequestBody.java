package springboot2essentials.requests;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;


public class AnimePostRequestBody implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Campo nao pode ser nulo")
    private  String name ;


    public AnimePostRequestBody(){

    }

    AnimePostRequestBody(String name){
        this.name = name;


    }

    public String getName(){
        return name;

    }

    public void setName(String name){
        this.name = name;

    }

    
}
