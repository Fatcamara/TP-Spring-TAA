package sample.data.jpa.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.Model.EtdJPA;
import sample.data.jpa.service.EtdDAO;

/**
 * Classe controller de la classe entité EtdJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des etudiants dans la base de données(bdd)
 */
@Controller
@RequestMapping("/etdcontroller")
public class EtdController {

    Logger logger = LoggerFactory.getLogger(String.valueOf(EtdController.class));

    @Autowired
    private EtdDAO etdD;

    /**
     *  POST/Create --> creé un étudiant dans la table userjpa de la bdd en lui passant les paramètres suivants:
     * @param name
     * @param email
     * @param anneeScol
     * @param filiere
     * @return le nom de l'étudiant si l'operation d'insertion a bien reussi,sinon retourne erreur.
     */

    @PostMapping("/create")
        @ResponseBody
        @ApiOperation(value="Insertion d'un élève avec son nom, son adresse email, son année scolaire et sa filière.",
                notes ="Cette méthode crée un élève avec un identifiant et les attributs cités ci-dessus dans la base de données.",
                response= EtdJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="Insertion reussie d'un elève "),
                @ApiResponse(code=400, message = "Insertion echoueé d'un l'élève ") })
        public String create(@RequestParam String name, String email, int anneeScol, String filiere) {
            String userStudentname = "";
            try {
                EtdJPA etd = new EtdJPA(name, email, anneeScol,filiere);
                etdD.save(etd);
                userStudentname = String.valueOf(etd.getName());
            }
            catch (Exception ex) {
                return "Error creating the user: " + ex.toString();
            }
            return "UserStudent successfully created with name = " + userStudentname;
        }

    /**
     * Delete/ delete --> Supprime un étudiant de la bdd dès qu'on lui passe son id.
     * @param id
     * @return un message indiquant la suppression de l'etudiant associé à l'id.
     * Elle supprime aussi bien via postman que via le formulaire.
     */

    //@RequestMapping("/delete")
    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value="Suppression d'un élève dans la base de données à partir de son Id.",
            notes ="Cette méthode supprime un élève grace à son identifiant dans la base de données.",
            response= EtdJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="Suppression reussie d'un l'elève "),
            @ApiResponse(code=400, message = "Suppression echoueé d'un l'élève ") })

    public String delete(@RequestParam long id) {
        logger.warn("avant");
            try {

                logger.warn("avant ID" + id);
                etdD.findById(id);
                logger.warn("avant ID" + id);
                etdD.deleteById(id);
            }
            catch (Exception ex) {
                return "Error deleting the user:" + ex.toString();
            }
            return "Student successfully deleted!";
        }

    /**
     * GET/get-by-email --> Trouve un etudiant quand on lui passe l'email en parametre.
     * @param email
     * @return l'Id de l'etudiant associé à l'email s'il existe, sinon retourne "user not found".
     */
    @PostMapping("/get-by-email")
    @ResponseBody
    @ApiOperation(value="retrouver un élève à partir de  son adresse email dans la base de données.",
            notes ="Cette méthode retrouve un élève dès qu'on a son email, dans la base de données.",
            response= EtdJPA.class)
    @ApiResponses(value ={
            @ApiResponse(code=201, message="L'elève a bien été retrouvé dans la base de données"),
            @ApiResponse(code=400, message = " L'elève n'existe pas dans la base de données")})

    public String getByEmail(@RequestParam String email) {
            String studentId = "";
            String studentName = "";
            try {
                EtdJPA etd = etdD.findByEmail(email);
                studentId = String.valueOf(etd.getId());
                studentName = String.valueOf(etd.getName());

            }
            catch (Exception ex) {
                return "User not found";
            }
            return "The StudentUser id is: " + studentId+" and name is "+studentName;
        }

    /**
     * PUT /update  --> met à jour l'email et le nom d'un étudiant dans la bdd quand on le trouve par son id.
     * @param id
     * @param email
     * @param name
     * @return mis à jour de userstudent reussi, sinon retourne erreur.
     */
        @PostMapping("/update")
        @ResponseBody
        @ApiOperation(value="modification du nom,de l'adresse email d'un élève à partir de son ID dans la base de données.",
                notes ="Cette méthode met à jour l'email et le nom d'un élève dans la base de données.",
                response= EtdJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="Mis à jour reussi de l'elève "),
                @ApiResponse(code=400, message = "Echec du mis à jour de l'élève ") })

        public String update( @RequestParam  long id, String email, String name) {
            try {
                EtdJPA etd = etdD.findByid(id);
                etd.setEmail(email);
                etd.setName(name);
                etdD.save(etd);
            }
            catch (Exception ex) {
                return "Error updating the user: " + ex.toString();
            }
            return "UserStudent successfully updated!";
        }

    }

