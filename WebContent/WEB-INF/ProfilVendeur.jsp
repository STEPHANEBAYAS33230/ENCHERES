<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/profilvendeur.css">
<script src="https://kit.fontawesome.com/0fe1e5906f.js" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon profil</title>
</head>
<body>

<h3>Pseudo: ${ pseudoVendeur }</h3>
<h4>Nom: ${ nomVendeur }</h4>
<h4>Prenom: ${ prenomVendeur }</h4>
<h4><i class="fab fa-mailchimp"></i>  Email: ${ emailVendeur }</h4>
<h4><i class="fas fa-phone-volume"></i>  Telephone: ${ telephoneVendeur }</h4>
<h4><i class="fas fa-road"></i>  Rue: ${ rueVendeur }</h4>
<h4><i class="fas fa-mail-bulk"></i>  Code Postal: ${ codePostalVendeur }</h4>
<h4><i class="fas fa-city"></i>  Ville: ${ villeVendeur }</h4>
<!-- affichage info : pseudo, nom, prénom, email, téléphone, rue, CP, ville
bouton modifier vers page monProfilModifierSupprimer-->
<div class="inputboutondiv"><a class="inputbouton" href="">RETOUR</a></div>

</body>
</html>