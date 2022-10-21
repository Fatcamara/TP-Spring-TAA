# TP-Spring-TAA

Utilisation de L’API :

Connexion à une base de données, run le « SampleDataJpaApplication ».
Ensuite, tester les requetes soit via swagger, postman ou les formulaires.
Swagger → http://localhost:8080/swagger-ui.html

V-Exemples de requêtes

Les requêtes sont à tester dans Postman Swagger et via les formulaires(Front).

Précision : Les requetes sont toutes mappées sur (POST/@PostMapping), en lieu
et place du @GetMapping, @PutMapping, @DeleteMapping. Cela pour ne pas
avoir à dupliquer les méthodes pour Postman et pour le formulaire(front).

-Création d’un rendez-vous
(POST)-→localhost:8080/rdvcontroller/createRdv?date=2022-10-
10&time=13:20:30&idProf=37&idEtd=36

-Suppression d’un rendez-vous
(POST)-→ localhost:8080/rdvcontroller/delete?idRdv=176

-Retrouver un rendez-vous par son identifiant
(POST)-→ localhost:8080/rdvcontroller/get-by-idRdv?idRdv=183

-Modification d’un rendez-vous
(POST)-→ localhost:8080/rdvcontroller/updaterdv?idRdv=66&date=2022-10-
26&time=13:20:30

-Création d’un étudiant
(POST) → localhost:8080/etdcontroller/create?
name=Mamaman&email=maman@yahoo.fr&anneeScol=2022&filiere=Geomorpho

-Création d’un professeur
POST)-→ localhost:8080/profcontroller/create
name="Siddy"&email="sidy@yahoo.fr"&matiere="economie"

-Suppression d’un étudiant
(POST)-→ localhost:8080/etdcontroller/delete?id=185

-Suppression d’un professeur
(POST)-→ localhost:8080/profcontroller/delete?id=160

-Retrouver un étudiant par son adresse email
(POST)-→ localhost:8080/etdcontroller/get-by-email?email=gnamantoure@y.fr

-Retrouver un étudiant par son adresse email
(POST)-→ localhost:8080/profcontroller/get-by-email?email=sidy@yahoo.fr

-Modification d’un étudiant
(POST)-→ localhost:8080/etdcontroller/update?
id=36&email=mamatoure@fanta&name=MANIGBEModification d’un professeur

(POST)-→ localhost:8080/profcontroller/update?
id=37&email=sidymamoudou@yahoo&name=siddy diallo

VI-Front-end

-Création d’un rendez-vous → http://localhost:8080/CreateRdv.html

-Modification d’un rendez-vous → http://localhost:8080/UpdateRdv.html

-Suppression d’un rendez-vous → http://localhost:8080/DeleteRdvz.html

-Retrouver un rendez-vous par son identifiant → http://localhost:8080/GetRdvById.html

-Création d’un étudiant → http://localhost:8080/CreateStudent.html

-Suppression d’un étudiant → http://localhost:8080/DeleteStudent.html

-Retrouver un étudiant par son adresse email --> http://localhost:8080/GetStudentByemail.html

-Création d’un professeur → localhost:8080/CreateTeacher.html

-Suppression d’un professeur → http://localhost:8080/DeleteTeacher.html

-Modification d’un professeur → http://localhost:8080/UpdateTeacher

