package sample.data.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe entité étudiant qui herite de la classe UserJPA.Elle s'occupe de construire les étudiants et
 * aussi de mapper la relation oneToMany entre la classe etudiant et la classe rendez-vous.
 */
@Entity
public class EtdJPA extends UserJPA implements Serializable {

     private int anneeScol;
    private String filiere;

    /**
     * Constructeur vide de la classe EtdJPA
     */
    public EtdJPA (){
        super();
    }

    /**
     * Constructeur de la classe EtdJPA qui prend 2 paramètres
     * @param anneeScol
     * @param filiere
     */
    public EtdJPA ( int anneeScol, String filiere) {
        this.anneeScol= anneeScol;
        this.filiere = filiere;
    }

    /**
     * Constructeur supplementaire de la classe qui regroupe les paramètres herités et les paramètres de la classe.
     * @param name
     * @param email
     * @param anneeScol
     * @param filiere
     */
    public EtdJPA(String name, String email, int anneeScol, String filiere) {
        super(name, email);
        this.anneeScol = anneeScol;
        this.filiere = filiere;
    }

    /**
     * Getter qui permet d'acceder à l'annee scolaire.
     * @return anneeScol
     */
    public int getAnneeScol() {
        return anneeScol;
    }

    /**
     * Setter qui prend un int en paramètre.
     * @param anneeScol
     */
    public void setAnneeScol(int anneeScol ){
        this.anneeScol = anneeScol;
    }

    /**
     * Getter qui accède au filière.
     * @return filiere
     */
    public String getFiliere() {
        return filiere;
    }

    /**
     * Setter qui prend en paramètre un string.
     * @param filiere
     */
    public void setFiliere(String filiere ){
        this.filiere = filiere;
    }

    public   List <RdvJPA> rendezvous = new ArrayList<RdvJPA>();

    /**
     * mapping de la relation @oneToMany entre la EtdJPA et la classe RdvJPA.
     * @return une liste de rendez-vous <<rendezvous>>
     */
    @OneToMany (mappedBy = "etudiant", cascade = CascadeType.PERSIST)
    public List<RdvJPA> getRendezvous() {
        return rendezvous;
    }

    /**
     * Setter qui prend l'Arraylist "rendezvous" en paramètre.
     * @param rendezvous
     */
    public void setRendezvous(List<RdvJPA> rendezvous) {
        this.rendezvous = rendezvous;
    }
}
