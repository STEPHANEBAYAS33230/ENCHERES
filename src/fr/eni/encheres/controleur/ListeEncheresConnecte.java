package fr.eni.encheres.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bll.ArticlesVendusManager;
import fr.eni.encheres.modele.bll.UtilisateurManager;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Utilisateur;


/**
 * Servlet implementation class ListeEncheresConnecte
 */
@WebServlet("/listeEncheresConnecte")
public class ListeEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
		List<ArticleVendu> listeArticlesVendus=null;

		String html=" ";
		
try {
			
			listeArticlesVendus = articleVenduManager.selectionner();
			ArticlesVendusManager avm = new ArticlesVendusManager();
			String pseudoA;
			
			
			for (ArticleVendu articles : listeArticlesVendus) {
				
				pseudoA= avm.findVendeurAcId(articles.getId());
				html=html+"<div class='col-lg-6 justify-content-center '><div class='encadrer separearticle'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
				html=html+"<input type='hidden' value='"+(articles.getId())+"' name='id'/><input type='submit' value='Article: "+(articles.getNomArticle())+"' formaction='detailVente' name='bouton1'/>";
				html=html+"</form><br> Prix :  "+(articles.getPrixvente())+" POINTS<br><img class='imagecentre' src='"+(articles.getImage())+"' alt='"+(articles.getDescriptionArticle())+"'";
				html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+(articles.getDatefinenchere())+"<br><p>Vendeur : "+(pseudoA)+"<p><br></form></div></div>";
				
			}
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteA' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>"; } catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("html", html);
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ListeEncheresConnecte.jsp");
		rd.forward(request, response);	
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recherche des Articles vendus (dont les ench�res sont toujours en cours)
		

		String html="";
		ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
		List<ArticleVendu> listeArticlesVendus=null;
		String radio = request.getParameter("radio");
		String enchere = request.getParameter("enchere");
		String vente = request.getParameter("vente");
		String achat=request.getParameter("enchere");
		String categorie=request.getParameter("categorie");
		String filtre=request.getParameter("filtre");
		String choix="";
		
		// stephane
		String bouton1=request.getParameter("bouton1");
		String boutonVendeur=request.getParameter("boutonVendeur");
		String redirection="";//page de redirection vers laquelle on forward
		
		//*****************************************************************fin stephane
		//Utilisateur user=new Utilisateur("toto", "titi", "tata", "adresseMail", "0987654321","rue", "ville", "code postal", " mdp",  100,  true);
		int id= Integer.valueOf((String)request.getSession().getAttribute("idC")); // L ID DU CONNECTE; //id de l'utilisateur courant
		System.out.println("dans ListeEnchere.java l'id du perso courant est " + id);
		
		
		
		if("rechercher".equals(bouton1)){// Si on recherche 
			redirection="/WEB-INF/ListeEncheresConnecte.jsp";
			if (radio.equals("Achats"))
				// si on s�lectionne les achats
			{
				choix=achat;
				
				if(achat.equals("EncheresOuvertes")){
					listeArticlesVendus=articleVenduManager.selectionnerAAfficher(categorie, filtre);
				}
				else{
					System.out.println("on verra plus tard il faut que les ench�res soient impl�ment�es");	
				}
			}
			else {// Ventes de l'utilisateur courant (r�cup�rer utilisateur courant)
				choix=vente;
				System.out.println("dans liste encheres servlet le choix de vente est "+choix);
				try {
					listeArticlesVendus = articleVenduManager.selectAAfficherEncheres(categorie, choix, filtre, id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
//		listeArticlesVendus = articleVenduManager.selectionner();
			ArticlesVendusManager avm = new ArticlesVendusManager();
			String pseudoA;

			for (ArticleVendu articles : listeArticlesVendus) {
				
				pseudoA= avm.findVendeurAcId(articles.getId());
				html=html+"<div class='col-lg-6 justify-content-center '><div class='encadrer separearticle'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
				html=html+"<input type='hidden' value='"+(articles.getId())+"' name='id'/><input type='submit' value='Article: "+(articles.getNomArticle())+"' formaction='detailVente' name='bouton1'/>";
				html=html+"</form><br> Prix :  "+(articles.getPrixvente())+" POINTS<br><img class='imagecentre' src='"+(articles.getImage())+"' alt='"+(articles.getDescriptionArticle())+"'";
				html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+(articles.getDatefinenchere())+"<br><p>Vendeur : "+(pseudoA)+"<p><br></form></div></div>";
				
			}
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVente' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>";
			
			html=html+"<div class='col-lg-6 justify-content-center separearticle' style='visibility: hidden;'><div class='encadrer' style='width=90%;'><form action='accueil' method='POST'><form action='detailVenteA' method='post'>";
			html=html+"<input type='hidden' value='"+" "+"' name='id'/><input type='submit' value='Article: "+" "+"' formaction='detailVente' name='bouton1'/>";
			html=html+"</form><br> Prix :  "+" "+" POINTS<br><img class='imagecentre' src='"+" "+"' alt='"+" "+"'";
			html=html+"class='imagecentre' /><br>Date fin d'Enchère: "+" "+"<br><p>Vendeur : "+("  ")+"<p><br></form></div></div>"; }
		
request.setAttribute("html", html);
		
		
		request.setAttribute("listeEncheresConnecte", listeArticlesVendus);	
		//******************************************
		
		if ("Deconnexion".equals(bouton1)) { 
			request.getSession().setAttribute("idC", null);
			request.getSession().setAttribute("pseudoC", null);
			request.getSession().setAttribute("nomC", null);
			request.getSession().setAttribute("prenomC", null);
			request.getSession().setAttribute("emailC", null);
			request.getSession().setAttribute("telephoneC", null);
			request.getSession().setAttribute("rueC", null);
			request.getSession().setAttribute("codePostalC", null);    //("codePostalC",Integer.parseInt(codePostalC));
			request.getSession().setAttribute("mdpC", null);
			request.getSession().setAttribute("villeC", null);
			request.getSession().setAttribute("adminC", null);
			request.getSession().setAttribute("creditC", null);
			redirection="deconnection";
		}
		if ("Mon Profil".equals(bouton1)) {
			 redirection="/monProfil";
		}
		if("mes Encheres".equals(bouton1)){
			 redirection="/WEB-INF/ListeEncheresConnecte.jsp";
		}
		if ("Vendre Un Article".equals(bouton1)) {
			 redirection="/WEB-INF/VendreArticle.jsp";
		}

		//******************************************
		if(redirection=="" && boutonVendeur!=null){
			// Si on clique sur le bouton du vendeur
			//ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
			UtilisateurManager umg=new UtilisateurManager();
			Utilisateur vendeur=umg.findUtilisateur(boutonVendeur); //BOUTONVENDEUR EST LE PSEUDO CLICKER SUR LISTE ENCHERE 
			
			
			request.getSession().setAttribute("pseudoVendeur", vendeur.getPseudo());
			request.getSession().setAttribute("nomVendeur", vendeur.getNom());
			request.getSession().setAttribute("prenomVendeur", vendeur.getPrenom());
			request.getSession().setAttribute("emailVendeur", vendeur.getEmail());
			request.getSession().setAttribute("telephoneVendeur",vendeur.getTel());
			request.getSession().setAttribute("rueVendeur", vendeur.getRue());
			request.getSession().setAttribute("codePostalVendeur", vendeur.getPostal());    
			request.getSession().setAttribute("mdpVendeur", null);
			request.getSession().setAttribute("villeVendeur",vendeur.getVille());
			request.getSession().setAttribute("adminVendeur", vendeur.getAdmin());
			request.getSession().setAttribute("creditVendeur", vendeur.getCredit());
			redirection="profilVendeur";
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirection);
		rd.forward(request, response);	
		}
		}


