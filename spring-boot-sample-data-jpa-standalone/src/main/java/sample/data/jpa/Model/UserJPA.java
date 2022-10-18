package sample.data.jpa.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe entité de la table UserJPA dans la base de données.Elle contient 7 colonnes au total:
 * un dtype pour differencier etudiant et prof, des attributs commumns (id, name,email) et
 * ceux spécifiques à chacune des classes filles qui héritent d'elle(ProfJPA et EtdJPA).
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//(strategy=InheritanceType.TABLE_PER_CLASS )--> si on voulait une table par classe.
public class UserJPA implements Serializable {

   private Long id;

    private String name;

    private String email;

    /**
     * Constructeur vide de la classe UserJPA.
     */
    public UserJPA() {
    }

    /**
     * Constructeur qui construit un user avec deux paramètres de type string.
     * @param name
     * @param email
     */
    public UserJPA(String name, String email) {

        this.name = name;
        this.email = email;
    }

    /**
     * Getter qui accède à l'id de type Long.
     * @return id
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /**
     * Setter de l'id de type Long.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter de nom de type STring.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter de l'atribut name, retourne un nom.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter de l'attribut email de type String.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de l'attribut email de type String.
     * @return
     */
    public String getEmail() {
        return email;
    }

}

