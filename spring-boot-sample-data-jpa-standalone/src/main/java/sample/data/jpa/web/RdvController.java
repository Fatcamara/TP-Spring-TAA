package sample.data.jpa.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.DTO.RdvDTO;
import sample.data.jpa.Model.EtdJPA;
import sample.data.jpa.Model.ProfJPA;
import sample.data.jpa.Model.RdvJPA;
import sample.data.jpa.service.EtdDAO;
import sample.data.jpa.service.ProfDAO;
import sample.data.jpa.service.RdvDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe controller de la classe entité RdvJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des rendez-vous entre etudiants et profs dans la base de données(bdd).
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

    @PostMapping("/createRdv")
    @ResponseBody
    @ApiOperation(value="Creation d'un rendez-vous avec une date, une heure, un prof et un étudiant dans la base de données.",
            notes ="Cette méthode crée un rendez-vous avec une date, un temps, un Id prof et un Id étudiant dans la base de données.",
            response= RdvJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Creation reussie d'un rendez-vous "),
            @ApiResponse(code=400, message = "Creation echouée d'un rendez-vous ") })

    public String createRdv(@ModelAttribute RdvDTO rdvdto) {
        String RendezVousDate;
        String RendezVousTime;

        try {
            Date dateRdv = new SimpleDateFormat("yyyy-MM-dd").parse(rdvdto.getDate());
            Date timeRdv = new SimpleDateFormat("HH:mm:ss").parse(rdvdto.getTime());
            ProfJPA prof = profdao.findByid(rdvdto.getIdProf());
            EtdJPA student = etudao.findByid(rdvdto.getIdEtd());
            RdvJPA rdv = new RdvJPA(dateRdv, timeRdv, prof, student);
            rdvD.save(rdv);
            RendezVousDate = String.valueOf(rdvdto.getDate());
            RendezVousTime = String.valueOf(rdvdto.getTime());

        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Appointement successfully created with date = " + RendezVousDate +" and time ="+RendezVousTime;
    }

    /**
     * Delete/delete --> supprime un rendez-vous quand on lui passe l'idRdv en parametre.
     * @param idRdv
     * @return que le rendez vous a bien été supprimé , sinon on a une erreur.
     */
    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value="Suppression d'un rendez-vous dans la base de données à partir de son Id(idRdv).",
            notes ="Cette méthode supprime un rendez-vous grace à son identifiant dans la base de données.",
            response= RdvJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Suppression reussie d'un rendez-vous"),
            @ApiResponse(code=400, message ="Suppression echoueé d'un rendez-vous")})
    public String delete(@RequestParam long idRdv) {
        try {
            RdvJPA rdvs = rdvD.findByIdRdv(idRdv);
            rdvD.delete(rdvs);
        } catch (Exception ex) {
            return "Error deleting the appointement:" + ex.toString();
        }
        return "Appointement successfully deleted!";
    }

    /**
     * GET /get-by-idRdv --> Trouve et affiche les id du prof et de l'etudiant associés
     * à l'id rendez-vous  qu'on lui passe en paramètre.
     */
    @PostMapping("/get-by-idRdv")
    @ResponseBody
    @ApiOperation(value="retrouver un rendez-vous à partir de son ID dans la base de données.",
            notes ="Cette méthode retrouve un rendez-vous par son identifiant,dans la base de données.",
            response= RdvJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Ce rendez-vous a bien été retrouvé dans la base de données"),
            @ApiResponse(code=400, message ="Ce Rendez-vous 'nexiste pas dans la base de données")})
    public String getByIdRdv(@RequestParam Long idRdv) {
        String RdvIdprof = "";
        String RdvIdstudent = "";
        String RdvProfName = "";
        String RdvStudentName = "";
        try {
            RdvJPA rdvs = rdvD.findByIdRdv(idRdv);
            RdvIdprof = String.valueOf(rdvs.getProf().getId());
            RdvIdstudent = String.valueOf(rdvs.getEtudiant().getId());
            RdvProfName = String.valueOf(rdvs.getProf().getName());
            RdvStudentName = String.valueOf(rdvs.getEtudiant().getName());
        } catch (Exception ex) {
            return "Appointement not found";
        }
        return "The Teacher associated to the rendez-vous is: " + RdvProfName + " with Id =" + RdvIdprof + " " +
                "and the student is " + RdvStudentName + " with Id =" + RdvIdstudent;
    }

    /**
     * PUT /update  --> modifie la date et l'heure d'un rendez-vous dans la bdd quand on lui
     * retrouve par son id(idRdv). Il prend l'id Rendez-vous et l'objet DTO pour la date et l'heure.
     * @param idRdv
     * @param rdvdto
     * @return Rendez-vous mis à jour si reussite, sinon retourne une exception(erreur).
     */

    @PostMapping("/updaterdv")
    @ResponseBody
    @ApiOperation(value="modification via un formulaire de la date et l'heure d'un rendez-vous à partir de son Id dans la base de données.",
            notes ="Cette méthode met à jour l'heure et le date d'un rendez-vous dans la base de données.",
            response= RdvJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Mis à jour reussi d'un Rendez-vous "),
            @ApiResponse(code=400, message = "Echec du mis à jour d'un rendez-vous") })
    public String updateRdv(@RequestParam long idRdv, @ModelAttribute RdvDTO rdvdto) {
        try {
            RdvJPA rdvs = rdvD.findByIdRdv(idRdv);
            Date dateRdv = new SimpleDateFormat("yyyy-MM-dd").parse(rdvdto.getDate());
            Date timeRdv = new SimpleDateFormat("HH:mm:ss").parse(rdvdto.getTime());
            rdvs.setDate(dateRdv);
            rdvs.setTime(timeRdv);
            rdvD.save(rdvs);
        } catch (Exception ex) {
            return "Error updating the appointement: " + ex.toString();
        }
        return "Appointement successfully updated!";
    }
}