package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 45)
    private String name;
    @Column(length = 350)
    private String description;
    @Column(length = 10)
    private double priceMax;
    @Column(length = 10)
    private double priceMin;
    @Column(length = 10)
    private double priceFinal;
    @Column(length = 11)
    private long idStatus;
    @Column(length = 11)
    private long idProgrammingLanguage;
    @Column(length = 11)
    private long idEmployed;
    @Column(length = 11)
    private long idTypePayment;
    private byte[] doc;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }


    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }


    public double getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(double priceFinal) {
        this.priceFinal = priceFinal;
    }


    public long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(long idStatus) {
        this.idStatus = idStatus;
    }


    public long getIdProgrammingLanguage() {
        return idProgrammingLanguage;
    }

    public void setIdProgrammingLanguage(long idProgrammingLanguage) {
        this.idProgrammingLanguage = idProgrammingLanguage;
    }


    public long getIdEmployed() {
        return idEmployed;
    }

    public void setIdEmployed(long idEmployed) {
        this.idEmployed = idEmployed;
    }


    public long getIdTypePayment() {
        return idTypePayment;
    }

    public void setIdTypePayment(long idTypePayment) {
        this.idTypePayment = idTypePayment;
    }


    public byte[] getDoc() {
        return doc;
    }

    public void setDoc(byte[] doc) {
        this.doc = doc;
    }

}
