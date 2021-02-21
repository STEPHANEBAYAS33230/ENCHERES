<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<script src="https://kit.fontawesome.com/aa652281aa.js" crossorigin="anonymous"></script>
<!-- ^^fontansome -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/connexion.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yusei+Magic&display=swap" rel="stylesheet">
</head>
<body>


<!--  Logo en haut à gauche 
formulaire -- identifiant, mdp
2 boutons 'Connexion' (avec case à cocher 'se souvenir de moi' & 'mot de passe oublié' 'Créer un compte'
 -->
 <div class="container">
 
        <div class="row">
            <!--logo+nom du site(ENI ENCHERES)-->
            <div class="col-lg-12 justify-content-left" id="logotitre">
            	<div><h2><a href="accueil"><img id="imagelogo" src="image/eniecole.png"  alt="logo ENI" /></a>ENI-Enchères</h2></div>
            </div>
        </div>
        <div class="row">
           <div class="col-lg-12 justify-content-center" id="formulaire">
            	 <form action="connexion" method="POST">
            	 <div>${ connexionObl }</div>
 				<label for="identifiant">Pseudo: </label> 
				<input id="identifiant" type="text" name="identifiant" size="30" placeholder="pseudo?" maxlength="30" value="${ pseudo }"/><br>
 				 <label for="mdp">Mot de passe: </label> 
				<input id="mdp" type="password" name="mdp" size="30" maxlength="30" placeholder="mot de passe?" value="${ mdp }"/><br>
            </div>
            
        </div>
        <div class="row">
            
            <div class="col-lg-6 justify-content-right" id="boutonconnexion" >
            	<input class="inputbouton" id="boutonconnexion" type="submit" value="CONNEXION" name="bouton"/>
            </div>
            <div class="col-lg-6 justify-content-left" >
            	<div id="alignersouvenir">
            	<input id="sesouvenir" type="checkbox" value="sesouvenirdemoi" name="sesouvenir"/> Se souvenir de moi<br>
				<a href="accueil" id="motpasse">Mot de passe oublié</a><br> <!-- PAGE MOT DE PASSE OUBLIE NON CREE -->
				 </form>
				 </div>
            </div>
            <div class="col-lg-12 justify-content-center" id="creerComptebouton" >
            	<a class="inputbouton" href="inscription" id="creerCompte" > Créer un compte</a>
            </div>
        </div>
        
</div><!-- class container -->



 
 
</body>
</html>