package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommentaryUser {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 11)
    private long idUser;
    @Column(length = 11)
    private long idCommentary;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


    public long getIdCommentary() {
        return idCommentary;
    }

    public void setIdCommentary(long idCommentary) {
        this.idCommentary = idCommentary;
    }

}
