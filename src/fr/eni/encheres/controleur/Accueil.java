package fr.eni.encheres.controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bll.ArticlesVendusManager;
import fr.eni.encheres.modele.bo.ArticleVendu;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recherche des Articles vendus (dont les enchères sont toujours en cours)
		ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
		List<ArticleVendu> listeArticlesVendus=null;
		
		String html=" ";
		
		try {
			
			listeArticlesVendus = articleVenduManager.selectionner();
			ArticlesVendusManager avm = new ArticlesVendusManager();
			String pseudoA;
			for (ArticleVendu articles : listeArticlesVendus) {
				pseudoA= avm.findVendeurAcId(articles.getId());
				html=html+"<div class='col-lg-6 justify-content-center '><div class='encadrer separearticle'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
				html=html+"<input type='hidden' value='"+(articles.getId())+"' name='id'/><input type='submit' value='Article: "+(articles.getNomArticle())+"' formaction='detailVenteAccueil' name='bouton1'/>";
				html=html+"</form><br> Prix :  "+(articles.getPrixvente())+" POINTS<br><img class='imagecentre' src='"+(articles.getImage())+"' alt='"+(articles.getDescriptionArticle())+"'";
				html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+(articles.getDatefinenchere())+"<br><p>Vendeur : "+(pseudoA)+"<p><br></form></div></div>";
				
			}
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
		
		
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//System.out.println(html);
		
		request.setAttribute("html",html);
		
		
		//Transfert de l'affichage Ã  la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);

    }

	
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
		List<ArticleVendu> listeArticlesVendus=null;
		/*  choix catégorie
		 * ou choix d'aprs mot dans nom
		 * 
		 */

		//Recherche des Articles vendus (dont les enchères sont toujours en cours)
		List<ArticleVendu> listeAAfficher=null;
		String categorie="Toutes";
		String filtre="";
		String bouton1=request.getParameter("bouton1");
		
		if("Rechercher".equals(bouton1)){
			filtre=request.getParameter("filtre");
			categorie=request.getParameter("categorie");
			
		}
		
		String html="";
		
	//	System.out.println("Catégorie" + categorie);
try {
			int index=-1;
			listeArticlesVendus = articleVenduManager.selectionner();
			ArticlesVendusManager avm = new ArticlesVendusManager();
			String pseudoA;
			if (filtre.equals("")) {filtre="TOUSLESARTICLES";}
		//	System.out.println("filtre"+ filtre);
			for (ArticleVendu articles : listeArticlesVendus) {
				if(filtre!="TOUSLESARTICLES") {index=((articles.getNomArticle()).toUpperCase()).indexOf(filtre.toUpperCase());}
				//System.out.println(((articles.getNomArticle()).toUpperCase()).indexOf( (filtre.toUpperCase())));
				//System.out.println(filtre.toUpperCase());
				if ( (categorie.equals(articles.getCategorie())) || (categorie.equals("Toutes")) ) {
				
					if (filtre=="TOUSLESARTICLES" || index>-1) {
					pseudoA= avm.findVendeurAcId(articles.getId());
					html=html+"<div class='col-lg-6 justify-content-center'><div class='encadrer separearticle'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
					html=html+"<input type='hidden' value='"+(articles.getId())+"' name='id'/><input type='submit' value='Article: "+(articles.getNomArticle())+"' formaction='detailVenteAccueil' name='bouton1'/>";
					html=html+"</form><br> Prix :  "+(articles.getPrixvente())+" POINTS<br><img class='imagecentre' src='"+(articles.getImage())+"' alt='"+(articles.getDescriptionArticle())+"'";
					html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+(articles.getDatefinenchere())+"<br><p>Vendeur : "+(pseudoA)+"<p><br></form></div></div>";
					}
				
				}
				
				
			}
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteAccueil' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVenteAccueil' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("html", html);

	
	//Transfert de l'affichage Ã  la JSP
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
	rd.forward(request, response); 
	
	
	}
	
	

}
