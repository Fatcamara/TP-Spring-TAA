package sample.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sample.data.jpa.domain.ProfJPA;
import sample.data.jpa.service.ProfDAO;

/**
 Classe controller de la classe entité ProfJPA, qui permet l'implementation des requetes de creation,
 * suppression, modification, et affichage des prof dans la base de données(bdd)
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
    @RequestMapping("/create")
        @ResponseBody
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
            return "User successfully created with id = " + userTeacherId;
        }

    /**
     * Delete/ delete --> Supprime un prof dans la table userjpa de la bdd dès qu'on lui passe un id.
     * @param id
     * @return un message indiquant la suppression du prof associé à l'Id.
     */
        @RequestMapping("/delete")
        @ResponseBody
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
        @RequestMapping("/get-by-email")
        @ResponseBody
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
            return "The userTeacher is: " + UserTeacherName +" and id is : "+UserTeacherId+" ";
        }

    /**
     * PUT /update  --> met à jour l'email et le nom d'un prof dans quand on lui trouve par son id.
     * @param id
     * @param email
     * @param name
     * @return mis à jour de userstudent reussi si reussite, sinon retourne erreur.
     */
        @RequestMapping("/update")
        @ResponseBody
        public String update(@RequestParam long id, String email, String name) {
            try {
                ProfJPA prof = profD.findByid(id);
                prof.setEmail(email);
                prof.setName(name);
                profD.save(prof);
            }
            catch (Exception ex) {
                return "Error updating the user: " + ex.toString();
            }
            return "UserTeacher successfully updated!";
        }

    }

