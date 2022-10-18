package sample.data.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProfJPA extends UserJPA implements Serializable {

   private String matiere;

    /**
     * Constructeur vide de la classe ProfJPA.
     */
    public ProfJPA (){
        super();
    }

    /**
     * Constructeur de la classe ProfJPA qui prend un string en parametre.
     * @param matiere
     */
    public ProfJPA (String matiere) {
        this.matiere = matiere;
    }

    /**
     *Constructeur qui fait référence à celui de la classe mère UserJPA.Il prend 4 paramètres:
     * @param name
     * @param email
     * @param matiere
     */
    public ProfJPA(String name, String email, String matiere) {
        super(name, email);
        this.matiere = matiere;
    }

    /**
     * Getter qui accède à l'attrinut matière.
     * @return matiere
     */
    public String getMatiere() {
        return matiere;
    }

    /**
     * Setter qui prend un string en paramètre.
     * @param matiere
     */
    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public List <RdvJPA> rendezvous = new ArrayList<RdvJPA>();

    /**
     *  mapping de la relation @oneToMany entre la classe ProfJPA et la classe RdvJPA.
     * @return une liste de rendez-vous <<rendezvous>>
     */
    @OneToMany (mappedBy = "prof", cascade = CascadeType.PERSIST)
    public List<RdvJPA> getRendezvous() {
        return rendezvous;
    }

    /**
     * Setter qui prend la liste "rendezvous" en paramètre.
     * @param rendezvous
     */
    public void setRendezvous(List<RdvJPA> rendezvous) {
        this.rendezvous = rendezvous;
    }

}
