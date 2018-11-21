package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class UserFreelance {

    @Id
    @GeneratedValue
    private long id;
    @Column(length = 45)
    private String username;
    @Column(length = 45)
    private String fullName;
    @Column(length = 45)
    private String email;
    @Column(length = 45)
    private String birthdate;
    @Column(length = 11)
    private long rate;
    @Column(length = 45)
    private String password;
    @Column(length = 45)
    private String phone;
    @Column(length = 10)
    private double priceForHour;
    @Column(length = 1)
    private long completeJobs;
    @Column(length = 250)
    private String bios;
    @Column(length = 200)
    private String descriptionProfession;
    @Column(length = 45)
    private String state;
    @Column(length = 45)
    private String country;
    @Column(length = 45)
    private String municipality;
    @Column(length = 45)
    private long idTypeUser;
    private byte[] avatar;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }


    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public double getPriceForHour() {
        return priceForHour;
    }

    public void setPriceForHour(double priceForHour) {
        this.priceForHour = priceForHour;
    }


    public long getCompleteJobs() {
        return completeJobs;
    }

    public void setCompleteJobs(long completeJobs) {
        this.completeJobs = completeJobs;
    }


    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }


    public String getDescriptionProfession() {
        return descriptionProfession;
    }

    public void setDescriptionProfession(String descriptionProfession) {
        this.descriptionProfession = descriptionProfession;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public long getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(long idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

}
