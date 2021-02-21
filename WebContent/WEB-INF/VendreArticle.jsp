<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/vendreArticle.css">
<title>vendre un article</title>
</head>
<body>

<h1 class="container-fluid bleu">
 Vendre un article
</h1>


<form action="vendreArticle" method="post" enctype="multipart/form-data">
 <div>
   <label for="file">Sélectionner le fichier à envoyer</label>
   <input type="file" id="file" name="file">
 </div>
</form>



<form action="vendreArticle" method="post">

<label for="article">Article : </label>
<input id="article" type="text" name="article" placeholder="Nom de l'artcile ?" size="20" required/><br>

<label for="description">Description :</label>
<input id="description" type="text" name="description"  placeholder="description de l'article ?"  size="20" required/><br>

<label for="categorie">Catégorie :</label>  <!-- Menu déroulant -->


<SELECT id="categorie" name="categorie" size="1">
<OPTION>informatique
<OPTION>vetement
<OPTION>ameublement
<OPTION>sport et loisir
</SELECT>


<br>


<!-- Bouton uploader photo  + cadre pour photo uploadée-->



<label for="prixInit">Mise à prix :</label>
<input id="prixInit" type="number" name="prixInit" min="0" max="1000"   />${ messagePI}<br><br>

<label for="debut">Date de début des enchères :</label>
<input id="debut" type="date" name="debut"  size="20" required/><br>

<label for="fin">Date de fin des enchères :</label>
<input id="fin" type="date" name="fin"  size="20" required/>${ messageVA}<br>


 
<div class="encadrer"><h2>Retrait</h2>

<label for="rue">Rue :</label>
<input id="rue" type="text" name="rue"  placeholder="rue?" size="20" required/><br>

<label for="codepostal">Code Postal :</label>
<input id="codepostal" type="number" name="codepostal" min="01000" max="99999"  placeholder="code postal?" size="20" required/><br>

<label for="ville">Ville :</label>
<input id="ville" type="text" name="ville"  placeholder="ville?" size="20" required/><br>

</div>

<input type="submit" class="btn btn-success" value="enregistrer" name="bouton"/>


</form>

 <a href="listeEncheresConnecte"><button class="btn btn-success bouton" >ANNULER</button></a>
<!-- logo en haut à gauche 

gros formulaire 
article
description
catégorie (menu déroulant)
Photo de l'article
mise à prix 
date début 
date fin 
cadre retrait -> rue, CP, Ville 
2boutons 'enregistrer' 'annuler' (renvoie à listeEncheresConnecte -->

</body>
</html>