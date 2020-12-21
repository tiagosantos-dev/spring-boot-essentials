package springboot2essentials.requests;


import java.io.Serializable;


public class AnimePutRequestBody  implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public AnimePutRequestBody(){

    }

    AnimePutRequestBody(Long id , String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    


}
