package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Commentary {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 350)
    private String textCommentary;
    @Column(length = 11)
    private long idUser;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTextCommentary() {
        return textCommentary;
    }

    public void setTextCommentary(String textCommentary) {
        this.textCommentary = textCommentary;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

}
