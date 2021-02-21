<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://kit.fontawesome.com/aa652281aa.js" crossorigin="anonymous"></script>
<!-- ^^fontansome -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/inscription.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Yusei+Magic&display=swap" rel="stylesheet">
<title>Inscription</title>
</head>
<body>
<div class="container">
<!-- ligne logo+eni enchere -->
        <div class="row">
            <div class="col-12 justify-content-left" id="logotitre">
            	<div><h2><a href="accueil"><img id="imagelogo" src="image/eniecole.png"  alt="logo ENI" /></a>ENI-Enchères</h2></div>
            </div>
        </div>
 <!-- fin ligne logo+eni enchere-->
        <div class="row">           
            <div class="col-lg-12 justify-content-center realigne1">
            	<div id="titremonprofil">
                <h2>Inscription <i class="fas fa-user-alt"></i></h2>
                </div>          
            </div>
        </div>
<!-- fin titre inscriptio -->        
		<div class="row">           
            <div class="col-lg-6 justify-content-center">
            	<form action="inscription" method="post">
					<label for="pseudo">VOTRE PSEUDO:</label>
					<input id="peudo" type="text" name="pseudo" placeholder="pseudo ?" required maxlength="30" minlength="3" size="20"/><br>

					<label for="prenom">VOTRE PRENOM:</label>
					<input id="prenom" type="text" name="prenom"  placeholder="prenom?" required maxlength="30" minlength="2" size="20"/><br>

					<label for="telephone">VOTRE TELEPHONE:</label>
					<input id="telephone" type="tel" name="telephone"  placeholder="telephone?" required size="20"/><br>

					<label for="codepostal">VOTRE CODE POSTAL:</label>
					<input id="codepostal" type="number" name="codepostal" min="01000" max="99999"  placeholder="cp ?" required/><br>

					
					<label for="mp">VOTRE MOT DE PASSE:</label>
					<input id="mp" type="password" name="mp"  value=null placeholder="mot de passe?" maxlength="30" minlength="8" size="20" required/><br>
					<p>${ messageMdpCourt} ${ messageMdpMajMin}</p>          
            </div>
            <!-- *************premier bloc form************************* --> 
        	<div class="col-lg-6 justify-content-center">
            	<label for="nom">VOTRE NOM:</label>
				<input id="nom" type="text" name="nom"  placeholder="nom?" required maxlength="30" minlength="3" size="20"/><br>

				<label for="email">VOTRE EMAIL:</label>
				<input id="email" type="email" name="email"  placeholder="email?" required maxlength="20" size="20"/><br>

				<label for="rue">VOTRE RUE:</label>
				<input id="rue" type="text" name="rue"  placeholder="rue?" required maxlength="30" size="20" required/><br>
				
				<label for="ville">VOTRE VILLE:</label>
				<input id="ville" type="text" name="ville"  value="" placeholder="ville?" maxlength="30" minlength="3" size="20" required/><br>
				
				
				<label for="confirmMp">CONFIRMATION: </label>
				<input id="confirmMp" type="password" name="confirmMp"  placeholder="mot de passe?"  maxlength="30" minlength="8" size="20" required/><br>
				<p>${ messageErreurMdp } ${ messageCreationCpte } ${ messageCpteInconnu}</p>            
            </div>
        
        </div><!-- fin row -->
        	<div class="row">           
            <div class="col-lg-12 justify-content-between realigne">
            	<div class="inputboutondiv">
            		<input class="inputbouton" type="submit" value="creer" name="bouton"/>
 					</form>
 					 <a class="inputbouton" href="accueil">Annuler</a>
 				</div>   
            </div>
        </div>
        
        
        
        
</div> <!-- div classcontainer*************************************************** -->


<!-- pseudo, nom, prénom, email, téléphone, rue, code postal, ville, mdp, confirmation mdp -> pour créer formulaire (DB)
2 boutons 'créer' 'annuler'
erreurs 
si impossible (ou annuler) dans page d'accueil   
Si création ok redirige sur la page d'accueil avec 'connecté'-->



</body>
</html>