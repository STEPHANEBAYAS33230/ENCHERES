<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/monprofil.css">
<script src="https://kit.fontawesome.com/0fe1e5906f.js" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon profil</title>
</head>
<body>

<h3>Pseudo: ${ pseudoC }</h3>
<h4>Nom: ${ nomC }</h4>
<h4>Prenom: ${ prenomC }</h4>
<h4><i class="fab fa-mailchimp"></i>  Email: ${ emailC }</h4>
<h4><i class="fas fa-phone-volume"></i>  Telephone: ${ telephoneC }</h4>
<h4><i class="fas fa-road"></i>  Rue: ${ rueC }</h4>
<h4><i class="fas fa-mail-bulk"></i>  Code Postal: ${ codePostalC }</h4>
<h4><i class="fas fa-city"></i>  Ville: ${ villeC }</h4>
<!-- affichage info : pseudo, nom, prénom, email, téléphone, rue, CP, ville
bouton modifier vers page monProfilModifierSupprimer-->
<div class="inputboutondiv">
<a class="inputbouton" href="monProfilModifierSupprimer">Modifier</a>
</div>
</body>
</html>