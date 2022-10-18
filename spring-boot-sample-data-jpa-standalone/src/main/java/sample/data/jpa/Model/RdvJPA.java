package sample.data.jpa.Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe entité de la table RdvJPA de la base de données qui contient 4 colonnes:
 * idRdv, date, time id_prof, id_Etudiant
 */
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RdvJPA implements Serializable {

    private Long idRdv;
    private Date date;
    private Date time;
    private ProfJPA prof;

    private EtdJPA etudiant;

    /**
     * Constructeur qui construit un rendez-vous à avec une date, une heure, un prof et un etudiant.
     * @param date
     * @param time
     * @param prof
     * @param etudiant
     */
    public RdvJPA (Date date, Date time, ProfJPA prof, EtdJPA etudiant) {

        this.date= date;
        this.etudiant= etudiant;
        this.prof = prof;
        this.time = time;
    }

    /**
     * Constructeur vide de la classe RdvJPA
     */
    public RdvJPA() {
    }

    /**
     * getter qui accède à un long, l'idRdv generé automatiquement.
     * @return idRdv
     */
    @Id
    @GeneratedValue
    public Long getIdRdv() {
        return idRdv;
    }

    /**
     * Setter qui prend un long en paramètre.
     * @param id
     */
    public void setIdRdv(Long id) {
        this.idRdv = id;
    }

    /**
     * Getter de date
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter ,prend en paramètre une date.
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter qui accède au temps.
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Setter qui prend time de type date en paramètre
     * @param time
     */

    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Getter, mapping de la relation de type ManyToOne de la classe RdvJPA vers la classe ProfJPA.
     * @return un prof
     */
    @ManyToOne
    public ProfJPA getProf() {
        return prof;
    }

    /**
     * Setter qui prend un prof en paramètre.
     * @param prof
     */
    public void setProf(ProfJPA prof) {
        this.prof = prof;
    }

    /**
     * Getter,mapping de la relation de type ManyToOne de la classe RdvJPA vers la classe EtdJPA.
     * @return un etudiant
     */
    @ManyToOne
    public EtdJPA getEtudiant() {
        return etudiant;
    }

    /**
     * Setter qui prend en paramètre etudiant.
     * @param etudiant
     */
    public void setEtudiant(EtdJPA etudiant) {
        this.etudiant = etudiant;
    }

}
