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
		
		int id = Integer.valueOf(request.getParameter("id"));//on recupère id de l'article pour récupérer l'enchère associée.

		System.out.println( " l'id de l'objet dont on veut le détail est ( dans le doPost) " + id );
		
		Encheres enchereCourante= new Encheres();
		enchereCourante=em.EnchereAssocieeAArticle(id); //récupère l'enchère avec Id de l'article
		
		
		//recupération de l'article en cours
		ArticleVendu articleVendu=null;
		try {
			articleVendu = avm.findById(id);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Utilisateur userC= new Utilisateur();
		//Récupération de l'utilisateur en Session
		if(request.getSession().getAttribute("pseudoC")!=null) {
			userC = um.findUtilisateur(request.getSession().getAttribute("pseudoC").toString());
			System.out.println("l'utilisateur connecté est " + userC );
		}
		String pseudoCo = userC.getPseudo();
	
		String redirection="";
		String bouton=request.getParameter("bouton");
		String enchere=request.getParameter("enchere");
		System.out.println("l'enchere est de :" + enchere);
		if("Encherir".equals(bouton)) { // si bouton==encherir on est dans detailVente don soit on redirige vers Connexion soit on enchérir et retourne à ListeEncheresConnecté
			String connexionObl="Il faut se connecter pour pouvoir enchérir";
			String creditInsuf="vous n'avez pas un crédit suffisant pour encherir ainsi";
			Object pseudoC = request.getSession().getAttribute("pseudoC");
			if(pseudoC!=null){// si connecté 
				
				redirection = Encherir(request, avm, em, um, id, enchereCourante, articleVendu, userC, pseudoCo,
						redirection, enchere, creditInsuf);
				
			}
			
			else {// s'il n'est pas connecté il ne peut pas enchérir
				redirection="/WEB-INF/Connexion.jsp";				
				request.setAttribute("connexionObl", connexionObl);
			}
			
			 
		}// fin du if bouton==enchérir
		
		
		else {//on veut toutes les infos dans DetailVente
			
			Retraits retrait=null;
			String pseudo = null;
			String telVendeur=null;
			try {
				retrait=rm.findByIdR(id); //retrait lié à cet article
				pseudo=avm.findVendeurAcId(id); //pseudo du vendeur
				telVendeur=avm.findTelVendeurAcId(id);//obtenir numéro de téléphone du vendeur 
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
		
		if(articleVendu.getDatefinenchere().isBefore(LocalDate.now())) {// si date a expiré alors on dirige vers une autre page

			String lastEncherisseur=enchereCourante.getPseudoDernierEncherisseur();
			System.out.println("pseudo du dernier Enchérisseur " +  lastEncherisseur);
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
		
		int creditC=Integer.valueOf(request.getSession().getAttribute("creditC").toString()); // crédit du connecté
		int somme = Integer.valueOf(enchere);
		String lastEncherisseur=enchereCourante.getPseudoDernierEncherisseur();
		int exPrix=enchereCourante.getPrixMax();
		
		//fonction qui enchérie
		if(somme  > exPrix && somme<=creditC) {
			//change le prix de l'enchere
			enchereCourante.setPrixMax(somme);
			em.Encherir(id,somme);//change en BDD à l'aide de l'id de l'article et ajoute la nouvelle somme
			
				///////////////////******************* pas d'erreur avant ça ********************//////////////////////// 	
			//ajoute le nom de l'encherisseur
			enchereCourante.setPseudoDernierEncherisseur(pseudoCo);
			em.ajoutEncherisseur(id,pseudoCo);//ajoute en BDD à l'aide de l'id de l'article
		///////////////////******************* pas d'erreur après ça ********************//////////////////////// 	
			
			//change la valeur de vente de l'article
			articleVendu.setPrixvente(somme);
			avm.Encherir(id,somme);
			
			// change les crédit dispo de l'utilisateur connecté
			creditC = creditC - somme;
			request.getSession().setAttribute("creditC", creditC); //change en session
			userC.setCredit(creditC);//change dans l'objet
			um.Encherir(pseudoCo,creditC);
			
			// change le crédit du vendeur 
			Utilisateur vendeur;
			String pseudoVendeur = avm.findVendeurAcId(id);//on obtient le pseudo du vnedeur
			vendeur=um.findUtilisateur(pseudoVendeur);
			//ajout seulement ce qui est en plus.
			int ajout=articleVendu.getPrixvente()-exPrix; //ce qu'il a gagné lors de cette enchère
			int newCredit =vendeur.getCredit()+ajout; //nouveau crédit du vendeur
			
			vendeur.setCredit(newCredit);//ajoute dans l'objet
			um.Encherir(vendeur.getPseudo(), newCredit);
			
			//*************************
			
			//ajoute credit au dernier enchérisseur
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
