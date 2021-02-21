<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/aa652281aa.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Accueil.css">
<title>Accueil</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yusei+Magic&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body id="bodies">

<div class="container" id="container">
 
        <div class="row" >
            <!--logo+nom du site(ENI ENCHERES)-->
            <div class="col-lg-9 justify-content-left" id="logotitre">
            	<div><h2><img id="imagelogo" src="image/eniecole.png"  alt="logo ENI" />ENI-Enchères</h2></div>
             </div>
             <div class="col-lg-3 justify-content-end" >
            	<a href="connexion" id="sinscrire">S'INSCRIRE-SE CONNECTER</a>
             </div>
        </div>
        
        <div class="row" >
            <!--logo+nom du site(ENI ENCHERES)-->
            <div class="col-lg-12 justify-content-center">
            
            	<div id="listeenchere"><h1>Liste des Enchères</h1></div> ${ message3 }
            	
            </div>
            
        </div>
</div> <!-- fin container -->        
<div class="container" id="container2">
			<div class="row" >
            
            <div class="col-lg-6 justify-content-center">
            		<form action="accueil" method="post">

					<!--   barre des articles -->
		
					

		
					<br>
					<i class="fas fa-search"></i><label for="rechercher"> Filtres :</label>
					<input id="rechercher" name="filtre" size="20" placeholder="Le nom de l'article contient"><br><br>
		
					<label for="categorie">Catégorie :</label>  <!-- Menu déroulant -->


						<SELECT id="categorie" name="categorie" size="1">
						<option>Toutes
						<OPTION>informatique
						<OPTION>vetement
						<OPTION>ameublement
						<OPTION>sport et loisir
						</SELECT>
            </div>
            
            <div class="col-lg-6 justify-content-center">
            	<div id="inputboutondiv" >
            		<label for="bouton1"></label>
            		<input  id="bouton1"  type="submit" value="Rechercher" name="bouton1"/>	
            		</form>
            	</div>
            </div>
           <div class="row">
            
            	${ html }
            
		   </div>
</div> <!-- fin container -->


			
	
	
			
			   
			
			
				
    
				

<!-- Logo - en haut à  droite 's'inscrire , se connecter' 
barre de recherche   
onglet catégorie (info, vetement, ameublement, sport & loisir)
bouton rechcher

2 colonnes avec annonces d'article (nom article, prix, fin enchères, nom vendeur)-->
</body>
</html>