package sample.data.jpa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sample.data.jpa.Model.RdvJPA;

/**
 * Classe DAO de la classe RdvJPA qui permet l'acces à des fonctions par son heritage.
 */
@Component
@Repository
public interface RdvDAO extends JpaRepository<RdvJPA, Long> {
    /**
     * fonction qui retrouve un rendez-vous dans la base de donnée quand on lui passe l' idRdv.
     * @param idRdv
     * @return
     */
    public RdvJPA findByIdRdv (Long idRdv);

}
