<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Détail vente</title>
<script src="https://kit.fontawesome.com/aa652281aa.js" crossorigin="anonymous"></script>
<!-- ^^fontansome -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/detailVente.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yusei+Magic&display=swap" rel="stylesheet">
</head>
<body>
<h3 class="inputbouton">Détails article</h3>

	
		
<h4  >${ articleVendu.getNomArticle() }</h4><p> Description : ${ articleVendu.getDescriptionArticle() } </p>

<p> Description : ${ articleVendu.getDescriptionArticle() } </p>

<p> Catégorie : ${ articleVendu.getCategorie() } </p>

<p> Meilleure offre :${ articleVendu.getPrixvente() } Mise à prix : ${ articleVendu.getPrixinitial() }</p>

<p> Fin de l'enchère ${ articleVendu.getDatefinenchere() } </p>
<p>Retrait : ${ retrait.getRueretrait() } ${ retrait.getPostalretrait() }  ${ retrait.getVilleretrait() }</p>

<p>Vendeur : ${ articleVendu.getNumeroutilisateur() }<p>
<form action="detailVente" method="post">
<input type="hidden" value="${ articleVendu.id }" name="id"/>
<label for="enchere" id="mapropo" >Ma proposition :</label> 
<input id ="enchere" id="inputencherir" type="number"  min="${ articleVendu.getPrixvente() }" value="${ articleVendu.getPrixvente() }" name="enchere"/> ${ encherePlus }<br>

<img
    src="${ articleVendu.getImage() } "
    alt="${ articleVendu.getDescriptionArticle() } "
    height="150px"
    width="150px"
/>
<div class="inputboutondiv">
<input class="inputbouton" type="submit" value="Encherir" name="bouton"/>
</div>
</form>



</body>
</html>