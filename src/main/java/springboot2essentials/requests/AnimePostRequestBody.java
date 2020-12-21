package springboot2essentials.requests;

import java.io.Serializable;


public class AnimePostRequestBody implements Serializable {
    private static final long serialVersionUID = 1L;
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
