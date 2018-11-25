package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OffersProject {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 11)
    private long idOffer;
    @Column(length = 11)
    private long idProject;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(long idOffer) {
        this.idOffer = idOffer;
    }


    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

}
