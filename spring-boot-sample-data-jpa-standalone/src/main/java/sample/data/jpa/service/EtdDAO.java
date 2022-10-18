package sample.data.jpa.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sample.data.jpa.Model.EtdJPA;

/**
 * Classe EtdDAO qui herite de JpaRepository pour implementer certaines méthodes.
 */
@Component
@Repository
public interface EtdDAO extends JpaRepository<EtdJPA, Long> {
   /**
    * fonction qui retrouve chaque étudiant par son email.
    * @param email
    * @return un etudiant
    */
   public EtdJPA findByEmail(String email);

   /**
    *fonction qui retrouve un étudiant present dans la base de données quand on lui passe son id.
    * @param id
    * @return
    */
   public  EtdJPA findByid (Long id);

}


