package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserProject {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 11)
    private long idUser;
    @Column(length = 11)
    private long idProject;


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


    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

}
