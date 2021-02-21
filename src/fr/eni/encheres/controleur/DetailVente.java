package fr.eni.encheres.controleur;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bll.ArticlesVendusManager;
import fr.eni.encheres.modele.bll.EncheresManager;
import fr.eni.encheres.modele.bll.RetraitManager;
import fr.eni.encheres.modele.bll.UtilisateurManager;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Encheres;
import fr.eni.encheres.modele.bo.Retraits;
import fr.eni.encheres.modele.bo.Utilisateur;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/detailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVente.jsp");
		rd.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticlesVendusManager avm = new ArticlesVendusManager();
		RetraitManager rm= new RetraitManager();
		EncheresManager em = new EncheresManager();
		UtilisateurManager um = new UtilisateurManager();
		
		int id = Integer.valueOf(request.getParameter("id"));//on recup�re id de l'article pour r�cup�rer l'ench�re associ�e.

		System.out.println( " l'id de l'objet dont on veut le d�tail est ( dans le doPost) " + id );
		
		Encheres enchereCourante= new Encheres();
		enchereCourante=em.EnchereAssocieeAArticle(id); //r�cup�re l'ench�re avec Id de l'article
		
		
		//recup�ration de l'article en cours
		ArticleVendu articleVendu=null;
		try {
			articleVendu = avm.findById(id);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Utilisateur userC= new Utilisateur();
		//R�cup�ration de l'utilisateur en Session
		if(request.getSession().getAttribute("pseudoC")!=null) {
			userC = um.findUtilisateur(request.getSession().getAttribute("pseudoC").toString());
			System.out.println("l'utilisateur connect� est " + userC );
		}
		String pseudoCo = userC.getPseudo();
	
		String redirection="";
		String bouton=request.getParameter("bouton");
		String enchere=request.getParameter("enchere");
		System.out.println("l'enchere est de :" + enchere);
		if("Encherir".equals(bouton)) { // si bouton==encherir on est dans detailVente don soit on redirige vers Connexion soit on ench�rir et retourne � ListeEncheresConnect�
			String connexionObl="Il faut se connecter pour pouvoir ench�rir";
			String creditInsuf="vous n'avez pas un cr�dit suffisant pour encherir ainsi";
			Object pseudoC = request.getSession().getAttribute("pseudoC");
			if(pseudoC!=null){// si connect� 
				
				redirection = Encherir(request, avm, em, um, id, enchereCourante, articleVendu, userC, pseudoCo,
						redirection, enchere, creditInsuf);
				
			}
			
			else {// s'il n'est pas connect� il ne peut pas ench�rir
				redirection="/WEB-INF/Connexion.jsp";				
				request.setAttribute("connexionObl", connexionObl);
			}
			
			 
		}// fin du if bouton==ench�rir
		
		
		else {//on veut toutes les infos dans DetailVente
			
			Retraits retrait=null;
			String pseudo = null;
			String telVendeur=null;
			try {
				retrait=rm.findByIdR(id); //retrait li� � cet article
				pseudo=avm.findVendeurAcId(id); //pseudo du vendeur
				telVendeur=avm.findTelVendeurAcId(id);//obtenir num�ro de t�l�phone du vendeur 
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("retrait", retrait);
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("tel", telVendeur);
			
			redirection="/WEB-INF/DetailVente.jsp";
		}
		
		if(articleVendu.getDatefinenchere().isBefore(LocalDate.now())) {// si date a expir� alors on dirige vers une autre page

			String lastEncherisseur=enchereCourante.getPseudoDernierEncherisseur();
			System.out.println("pseudo du dernier Ench�risseur " +  lastEncherisseur);
			if(lastEncherisseur.equals(pseudoCo)) {
			redirection="/WEB-INF/VousAvezGagne.jsp";
		}
			else if(lastEncherisseur.equals("pseudodelamortquituetquonnetrouverajamais")) {
				lastEncherisseur="Personne n'";
				request.setAttribute("pseudoDernierEncherisseur", lastEncherisseur);
				redirection="/WEB-INF/IlAGagne.jsp";
			}
				
			else {
				request.setAttribute("pseudoDernierEncherisseur", lastEncherisseur);
				redirection="/WEB-INF/IlAGagne.jsp";
			}	}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirection);
		rd.forward(request, response);
		
	}


	private String Encherir(HttpServletRequest request, ArticlesVendusManager avm, EncheresManager em,
			UtilisateurManager um, int id, Encheres enchereCourante, ArticleVendu articleVendu, Utilisateur userC,
			String pseudoCo, String redirection, String enchere, String creditInsuf) {
		
		int creditC=Integer.valueOf(request.getSession().getAttribute("creditC").toString()); // cr�dit du connect�
		int somme = Integer.valueOf(enchere);
		String lastEncherisseur=enchereCourante.getPseudoDernierEncherisseur();
		int exPrix=enchereCourante.getPrixMax();
		
		//fonction qui ench�rie
		if(somme  > exPrix && somme<=creditC) {
			//change le prix de l'enchere
			enchereCourante.setPrixMax(somme);
			em.Encherir(id,somme);//change en BDD � l'aide de l'id de l'article et ajoute la nouvelle somme
			
				///////////////////******************* pas d'erreur avant �a ********************//////////////////////// 	
			//ajoute le nom de l'encherisseur
			enchereCourante.setPseudoDernierEncherisseur(pseudoCo);
			em.ajoutEncherisseur(id,pseudoCo);//ajoute en BDD � l'aide de l'id de l'article
		///////////////////******************* pas d'erreur apr�s �a ********************//////////////////////// 	
			
			//change la valeur de vente de l'article
			articleVendu.setPrixvente(somme);
			avm.Encherir(id,somme);
			
			// change les cr�dit dispo de l'utilisateur connect�
			creditC = creditC - somme;
			request.getSession().setAttribute("creditC", creditC); //change en session
			userC.setCredit(creditC);//change dans l'objet
			um.Encherir(pseudoCo,creditC);
			
			// change le cr�dit du vendeur 
			Utilisateur vendeur;
			String pseudoVendeur = avm.findVendeurAcId(id);//on obtient le pseudo du vnedeur
			vendeur=um.findUtilisateur(pseudoVendeur);
			//ajout seulement ce qui est en plus.
			int ajout=articleVendu.getPrixvente()-exPrix; //ce qu'il a gagn� lors de cette ench�re
			int newCredit =vendeur.getCredit()+ajout; //nouveau cr�dit du vendeur
			
			vendeur.setCredit(newCredit);//ajoute dans l'objet
			um.Encherir(vendeur.getPseudo(), newCredit);
			
			//*************************
			
			//ajoute credit au dernier ench�risseur
			um.Encherir(lastEncherisseur, exPrix);
			
			redirection="/WEB-INF/ListeEncheresConnecte.jsp";
		}
		else if (somme>creditC) {
			request.setAttribute("creditInsuf", creditInsuf);
			redirection="/WEB-INF/ListeEncheresConnecte.jsp";				
			
			
		}
		return redirection;
	}
	
}
