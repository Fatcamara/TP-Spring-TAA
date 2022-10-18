package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sample.data.jpa.Model.ProfJPA;

/**
 * Classe ProfDAO qui herite de JpaRepository,qui contient des méthodes implementées.
 */
@Component
@Repository
public interface ProfDAO extends JpaRepository<ProfJPA, Long> {
    /**
     * fonction qui retrouve chaque prof par son email.
     * @param email
     * @return
     */
    public ProfJPA findByEmail(String email);

    /**
     * fonction qui retrouve chaque prof quand on lui passe son id.
     * @param idProf
     * @return
     */
    ProfJPA findByid(long idProf);
}





