package sample.data.jpa.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.DTO.RdvDTO;
import sample.data.jpa.domain.EtdJPA;
import sample.data.jpa.domain.ProfJPA;
import sample.data.jpa.domain.RdvJPA;
import sample.data.jpa.service.EtdDAO;
import sample.data.jpa.service.ProfDAO;
import sample.data.jpa.service.RdvDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe controller de la classe entité RdvJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des rendez-vous entre etudiants et profs dans la base de données(bdd).
 *
 */
@Controller
@RequestMapping("/rdvcontroller")
public class RdvController {
    @Autowired
    RdvDAO rdvD;
    @Autowired
    ProfDAO profdao;
    @Autowired
    EtdDAO etudao;

    /**
     * POST/create --> cree un rendez-vous dans la table rdvjpa de la bdd à travers l'objet DTO, un id etudiant et un id prof.
     * @param rdvdto
     * @return la date de rendez-vous si celui ci a bien été crée, sinon donne "erreur".
     */
     @PostMapping(value = "/create", consumes = {"application/json"})
     @ResponseBody
     public String create(@RequestBody RdvDTO rdvdto) {
     String rdvdate="";
     try {
     Date dateRdv = new SimpleDateFormat("yyyy-MM-dd").parse(rdvdto.getDate());
     Date timeRdv = new SimpleDateFormat("HH:mm:ss").parse(rdvdto.getTime());
     ProfJPA prof = profdao.findByid(rdvdto.getIdProf());
     EtdJPA student = etudao.findByid(rdvdto.getIdEtd());
     RdvJPA rdv = new RdvJPA (dateRdv,timeRdv,prof,student);
     rdvD.save(rdv);
     rdvdate = String.valueOf(rdvdto.getDate());
     }
     catch (Exception ex) {
     return "Error creating the user: " + ex.toString();
     }
     return "Appointement successfully created with date = " +rdvdate;
     }

    /**
     * Delete/delete --> supprime un rendez-vous quand on lui passe l'idRdv en parametre.
     * @param idRdv
     * @return que le rendez vous a bien été supprimé , sinon on a une erreur.
     */
        @RequestMapping(value = "/delete")
        @ResponseBody
        public String delete(@RequestParam Long idRdv) {
            try {
                RdvJPA rdvs = rdvD.findByIdRdv(idRdv);
                rdvD.delete(rdvs);
            }
            catch (Exception ex) {
                return "Error deleting the appointement:" + ex.toString();
            }
            return "Appointement successfully deleted!";
        }

        /**
         * GET /get-by-idRdv --> Trouve et affiche les id du prof et de l'etudiant associés
         * à l'id rendez-vous  qu'on lui passe en paramètre.
         */
        @RequestMapping("/get-by-idRdv")
        @ResponseBody
        public String getByIdRdv(@RequestParam Long idRdv) {
            String RdvIdprof = "";
            String RdvIdstudent = "";
            try {
                RdvJPA rdvs = rdvD.findByIdRdv(idRdv);
                RdvIdprof = String.valueOf(rdvs.getProf().getId());
                RdvIdstudent = String.valueOf(rdvs.getEtudiant().getId());
            }
            catch (Exception ex) {
                return "Appointement not found";
            }
            return "The Teacher's Id linked to the rendez-vous is: " +RdvIdprof+" with the student's Id =" +RdvIdstudent;
        }

    /**
     * PUT /update  --> modifie la date et l'heure d'un rendez-vous dans la bdd quand on lui
     * retrouve par son id(idRdv). Il prend l'id Rendez-vous et l'objet DTO pour la date et l'heure.
     * @param idRdv
     * @param rdvdto
     * @return Rendez-vous mis à jour si reussite, sinon retourne une exception(erreur).
     */

      @RequestMapping (value = "/update", consumes = {"application/json"})
      @ResponseBody
        public String update(@RequestParam Long idRdv, @RequestBody RdvDTO rdvdto) {
            try {
                RdvJPA rdvs =  rdvD.findByIdRdv(idRdv);
                Date dateRdv = new SimpleDateFormat( "yyyy-MM-dd").parse(rdvdto.getDate());
                Date timeRdv = new SimpleDateFormat("HH:mm:ss").parse(rdvdto.getTime());
                rdvs.setDate(dateRdv);
                rdvs.setTime(timeRdv);
                rdvD.save(rdvs);
            }
            catch (Exception ex) {
                return "Error updating the appointement: " + ex.toString();
            }
            return "Appointement successfully updated!";
        }


    }