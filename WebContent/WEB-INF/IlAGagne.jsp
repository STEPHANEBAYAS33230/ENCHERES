<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>  ${ pseudoDernierEncherisseur } a gagné.e </h1>



<p> ${ articleVendu.getNomArticle() } </p>
<br>

<p> Description : ${ articleVendu.getDescriptionArticle() } </p>
<br>


<p> Meilleure offre : ${ articleVendu.getPrixvente() } </p>
<br>

<p> Mise à prix : ${ articleVendu.getPrixinitial() } </p>
<br>

<p>Retrait : ${ retrait.getRueretrait() }</p>
<p> ${ retrait.getPostalretrait() }${ retrait.getVilleretrait() }</p>
<br>

<p> Vendeur : ${ pseudo }</p>
<br>

<p> Téléphone : ${ tel }</p>

</body>
</html>