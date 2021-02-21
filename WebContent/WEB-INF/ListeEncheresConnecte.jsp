<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@  taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ListeEncheres.css">
    <title>Liste des enchères en mode connecté</title>
</head>

<body id="bodies">

    <!--  avant ok -->
    <div class="container" id="container">

        <form action="listeEncheresConnecte" method="post">

            <div class="menu">
                <input id="enchereB" class="bouton-menu" type="submit" name="bouton1" value="mes Encheres">
                <input id="VendreArticleB" class="bouton-menu" type="submit" name="bouton1" value="Vendre Un Article">
                <input id="MonProfil" class="bouton-menu" type="submit" name="bouton1" value="Mon Profil">
                <input id="Deconnexion" class="bouton-menu" type="submit" name="bouton1" value="Deconnexion">
            </div>
        </form>

        <div id="listeenchere">
            <h1>Liste des enchères</h1>
        </div>

        <div class="messsage-co"> ${ pseudoC} ${ messageconnected} ${ messageCreationCpte1 }</div>


    </div><!--  fin container -->

    <div class="container" id="container2">

        <form action="listeEncheresConnecte" method="post">
            <div class="row">

                <div class="col-lg-8">
                    <h2>Filtres :</h2>

                    <input id="rechercher" type="text" name="filtre" size="60" />

<div>
                    <label id="cat" for="categorie">Catégorie :</label> <!-- Menu déroulant -->


                    <SELECT id="categorie" name="categorie" size="1">
                        <option>Toutes </option>
                        <OPTION>Informatique</OPTION>
                        <OPTION>Vetement</OPTION>
                        <OPTION>Ameublement</OPTION>
                        <OPTION>Sport et loisir</OPTION>
                    </SELECT>
</div>

                </div> <!-- fermeture du div filtre/catégorie-->

                <div class="col-lg-4"> </div><!-- div vide- -->

            </div> <!-- fermeture du row-->

            <div class="row">
                <!-- fnoouvelle row achat/vente +  bouton rechercher-->

                <div class="col-lg-4 justify-content-center" >

<div class="gros_bouton" >
                    <input type="radio" id="Achats" name="radio" value="Achats" checked>
                    <label for="Achats">ACHATS</label>
                </div>
</div>


                <div class="col-lg-4 justify-content-center">
<div class="gros_bouton" >
                    <input type="radio" id="Vente" name="radio" value="Mes ventes">
                    <label for="Vente">MES VENTES</label>
                </div>
</div>

                <div class="col-lg-4 justify-content-center">
                    <div> ${ creditInsuf} </div>
                    <div id="inputboutondiv">
                        <label for="bouton1"></label>
                        <input id="bouton1" type="submit" value="rechercher" name="bouton1" />
                    </div>
                </div>

            </div> <!-- fermeture du row-->

            <div class="row">
                <!-- fnoouvelle row, choix achat et choix vente-->


                <div class="col-lg-4 justify-content-center ">
                <div class="bt-radio">
                    <div>     
                               <input type="radio" id="unA" name="vente" value="1" checked>
                               <label for="unA">Mes ventes en cours</label>
                   </div>
                   <div>       
                               <input type="radio" id="deuxA" name="vente" value="10">
                               <label for="deuxA">Ventes non débutées</label>
                   </div>
                   <div>
                       <input type="radio" id="troisA" name="vente" value="100">
                       <label for="troisA">Ventes terminées</label>
                  </div>
               </div>
</div>
                <div class="col-lg-4 justify-content-center">
<div class="bt-radio">
                   <div> 
                      <input type="radio" id="unV" name="enchere" value="EncheresOuvertes" checked>
                      <label for="unV">Enchères ouvertes</label>
                   </div>
                   <div>     
                      <input type="radio" id="deuxV" name="enchere" value="EncheresEnCours">
                      <label for="deuxV">Mes enchères en cours</label>
                   </div>              
                   <div>
                     <input type="radio" id="troisV" name="enchere" value="EncheresRemportees">
                     <label for="troisV">Enchères remportées </label>
                   </div>
               </div>
</div>
               <div class="col-lg-4 justify-content-center"> </div> <!-- vid pour finir ligne -->

            </div> <!-- fermeture du row-->



        </form>
   


    <div class="row">
         <div class="col-lg-12 justify-content-center">

        ${ html }
        </div>


    </div>


    </div><!--  fin container2 -->
</body>

</html>
