package sample.data.jpa.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.Model.ProfJPA;
import sample.data.jpa.service.ProfDAO;

/**
 Classe controller de la classe entité ProfJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des prof dans la base de données(BDD)
 */
@Controller
@RequestMapping("/profcontroller")
public class ProfController {
    @Autowired
    private ProfDAO profD;

    /**
     * POST/create --> cree un prof et l'enregistre dans la table userjpa, avec les parametres suivants:
     * @param name
     * @param email
     * @param matiere
     * @return retourne l'id du prof cree si reussite, sinon retourne une exception.
     */
    @PostMapping("/create")
    @ResponseBody
    @ApiOperation(value="Insertion d'un professeur avec son nom, son adresse email et sa matière dans la base de données.",
            notes ="Cette méthode crée un prof avec un identifiant et les attributs cités ci-dessus dans la base de données.",
            response= ProfJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Insertion reussie d'un prof "),
            @ApiResponse(code=400, message = "Insertion echoueé d'un prof ") })

    public String create(@RequestParam String name, String email, String matiere) {
            String userTeacherId = "";
            try {
                ProfJPA prof = new ProfJPA(name, email, matiere);
                profD.save(prof);
                userTeacherId = String.valueOf(prof.getId());
            }
            catch (Exception ex) {
                return "Error creating the user: " + ex.toString();
            }
            return "Teacher successfully created with id = " + userTeacherId;
        }

    /**
     * Delete/ delete --> Supprime un prof dans la table userjpa de la bdd dès qu'on lui passe un id.
     * @param id
     * @return un message indiquant la suppression du prof associé à l'Id.
     */
        @PostMapping("/delete")
        @ResponseBody
        @ApiOperation(value="Suppression d'un professeur dans la base de données à partir de son Id.",
                notes ="Cette méthode supprime un prof grace à son identifiant dans la base de données.",
                response= ProfJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="Suppression reussie d'un prof"),
                @ApiResponse(code=400, message ="Suppression echoueé d'un prof")})

        public String delete(@RequestParam long id) {
            try {
                profD.findByid (id);
                profD.deleteById(id);

            }
            catch (Exception ex) {
                return "Error deleting the user:" + ex.toString();
            }
            return "Teacher successfully deleted!";
        }

        /**
         *GET/get-by-email --> Trouve un prof à travers son email en parametre.
         * @param email
         *@return le nom et l'Id du prof associé à l'email s'il existe, sinon retourne "user not found".
         */
        @PostMapping("/get-by-email")
        @ResponseBody
        @ApiOperation(value="retrouver un prof à partir de  son adresse email dans la base de données.",
                notes ="Cette méthode retrouve un prof par son email, dans la base de données.",
                response= ProfJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="Prof a bien été retrouvé dans la base de données"),
                @ApiResponse(code=400, message = " Prof inexistant pas dans la base de données")})
        public String getByEmail(@RequestParam String email) {
            String UserTeacherName = "";
            String UserTeacherId = "";

            try {
               ProfJPA prof = profD.findByEmail(email);
                UserTeacherName = String.valueOf(prof.getName());
                UserTeacherId = String.valueOf(prof.getId());

            }
            catch (Exception ex) {
                return "userTeacher not found";
            }
            return "The userTeacher is: " + UserTeacherName +" with Id =: "+UserTeacherId+" ";
        }

    /**
     * PUT /update  --> met à jour l'email et le nom d'un prof dans quand on lui trouve par son id.
     * @param id
     * @param email
     * @param name
     * @return mis à jour de userstudent reussi si reussite, sinon retourne erreur.
     */
        @PostMapping("/update")
        @ResponseBody
        @ApiOperation(value="modification du nom,de l'adresse email, de la matière d'un prof à partir de son ID dans la base de données.",
                notes ="Cette méthode met à jour l'email et le nom d'un prof dans la base de données.",
                response= ProfJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="Mis à jour reussi du prof "),
                @ApiResponse(code=400, message = "Echec du mis à jour du prof ") })

        public String update(@RequestParam long id, String email, String name, String matiere) {
            try {
                ProfJPA prof = profD.findByid(id);
                prof.setEmail(email);
                prof.setName(name);
                prof.setMatiere(matiere);
                profD.save(prof);
            }
            catch (Exception ex) {
                return "Error updating the user: " + ex.toString();
            }
            return "UserTeacher successfully updated!";
        }

    }

