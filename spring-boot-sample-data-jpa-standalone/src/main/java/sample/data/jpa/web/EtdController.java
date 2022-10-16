package sample.data.jpa.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.domain.EtdJPA;
import sample.data.jpa.service.EtdDAO;

/**
 * Classe controller de la classe entité EtdJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des etudiants dans la base de données(bdd)
 */
@Controller
@RequestMapping("/etdcontroller")
public class EtdController {

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

        @RequestMapping("/create")
        @ResponseBody
        @ApiOperation(value="Inscrire un élève avec son nom, son prénom et son adresse mail.",
                notes ="Cette méthode inscrit un élève avec son nom, prénom et son adresse mail en lui attribuant un identifiant dans la base de donnée.",
                response= EtdJPA.class)
        @ApiResponses(value ={
                @ApiResponse(code=201, message="L'élève a bien été inscrit en base de données"),
                @ApiResponse(code=400, message = "L'élève n'a pas été enregistré") })
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
            return "User successfully created with name = " + userStudentname;
        }

    /**
     * Delete/ delete --> Supprime un étudiant de la bdd dès qu'on lui passe son id.
     * @param id
     * @return un message indiquant la suppression de l'etudiant associé à l'id.
     */

    //@RequestMapping("/delete")
    @RequestMapping(value = "DeleteUser.html/",method = RequestMethod.DELETE)
    @ResponseBody
        public String delete( @ModelAttribute Long id) {
            try {
                etdD.findById(id);
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
    @RequestMapping("/get-by-email")
        @ResponseBody
        public String getByEmail(@RequestParam String email) {
            String studentId = "";
            try {
                EtdJPA etd = etdD.findByEmail(email);
                studentId = String.valueOf(etd.getId());
            }
            catch (Exception ex) {
                return "User not found";
            }
            return "The StudentUser id is: " + studentId;
        }

    /**
     * PUT /update  --> met à jour l'email et le nom d'un étudiant dans la bdd quand on le trouve par son id.
     * @param id
     * @param email
     * @param name
     * @return mis à jour de userstudent reussi, sinon retourne erreur.
     */
        @RequestMapping("/update")
        @ResponseBody
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

