package fr.eni.encheres.controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.modele.bll.UtilisateurManager;
import fr.eni.encheres.modele.bo.ErreurEnregistrementUtilisateur;

/**
 * Servlet implementation class MonProfilModifierSupprimer
 */
@WebServlet("/monProfilModifierSupprimer")
public class MonProfilModifierSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//remise a zero des petits messages erreur mot passe a vide
		request.getSession().setAttribute("messageMdpCourt1","");
		request.getSession().setAttribute("messageMdpMajMin1","");
		request.getSession().setAttribute("messageErreurMdp1","");
		request.getSession().setAttribute("messageMpactuel","");
		request.getSession().setAttribute("messageMpactuel", "");
		//envoir a la jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MonProfilModifierSupprimer.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chaine=(String) request.getSession().getAttribute("idC");
		int idC=Integer.parseInt(chaine);
		Boolean adminC=(Boolean) request.getSession().getAttribute("adminC");
		int creditC=(int) request.getSession().getAttribute("creditC");
		String pseudoC=(String) request.getSession().getAttribute("pseudoC");
		///recup formulaire
		String pseudo=request.getParameter("pseudo");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String rue=request.getParameter("rue");
		String codepostal=request.getParameter("codepostal");
		String ville=request.getParameter("ville");
		String mpActuel=request.getParameter("mpActuel");
		String mp=request.getParameter("mp");//nouveau mot passe
		String confirmMp=request.getParameter("confirmMp");//confirm nouveau mot passe
		String bouton =request.getParameter("bouton");
		//*****************
		 
		String redirection="";
		 String var=(String) request.getSession().getAttribute("mdpC");
		 System.out.println(var);
		 System.out.println(mpActuel);
		   if (bouton.equals("Enregistrer") && (mpActuel.equals(var))) {
			  
			   UtilisateurManager utilisateurManager=new UtilisateurManager();
			   int verifMotPasse=utilisateurManager.verifMotPasseNouveau(mpActuel,mp,confirmMp);
			   
			   //*********************
			   String message;
			   String message1;
			   String message2;
			   String message3;
			  
			   switch(verifMotPasse) {
				case 1:  message="mot de passe trop court(minimum 8 caractères)";
				request.getSession().setAttribute("messageMdpCourt1", message);
						 redirection="/WEB-INF/MonProfilModifierSupprimer.jsp";
						   break;
				case 2:  message1="Le mot de passe doit contenir des majuscules et minuscules";
				request.getSession().setAttribute("messageMdpMajMin1", message1);
				redirection="/WEB-INF/MonProfilModifierSupprimer.jsp";
				   break;
				case 3:  message2="Erreur de saisie du mot de passe/confimation mot de passe";
				request.getSession().setAttribute("messageErreurMdp1", message2);
				 redirection="/WEB-INF/MonProfilModifierSupprimer.jsp";
				   break;
				case 4: message3="mise à jour du compte";
				request.getSession().setAttribute("messageMiseAjourCpte", message3);
				
				utilisateurManager.miseAjourUtilisateur(idC,pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,creditC,adminC);
				request.getSession().setAttribute("pseudoC", pseudo);
				request.getSession().setAttribute("nomC", nom);
				request.getSession().setAttribute("prenomC", prenom);
				request.getSession().setAttribute("emailC", email);
				request.getSession().setAttribute("telephoneC", telephone);
				request.getSession().setAttribute("rueC", rue);
				request.getSession().setAttribute("codePostalC", codepostal);    //("codePostalC",Integer.parseInt(codePostalC));
				request.getSession().setAttribute("mdpC", mp);
				request.getSession().setAttribute("villeC", ville);
				
				redirection="/WEB-INF/ListeEncheresConnecte.jsp";
				 break;
				}
			}
			   
			   //*********************
			 //  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/MonProfilModifierSupprimer.jsp");/////////A MODIFIER
			 //  rd.forward(request, response);
			
		   if (bouton.equals("Supprimer mon compte") && (mpActuel.equals(var)) && (pseudo.equals(pseudoC))) {
			   UtilisateurManager.supprimerMonCompte(idC,pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,creditC,adminC);
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
			   redirection="/accueil";
			   
		   } else if(!(mpActuel.equals(var))){request.getSession().setAttribute("messageMpactuel", "mot de passe actuel inconnu");
			   redirection="/WEB-INF/MonProfilModifierSupprimer.jsp";}
		   
		   
		   RequestDispatcher rd5 = request.getRequestDispatcher(redirection);
		   rd5.forward(request, response); 
			   
		   }
	
	
}
	
		
		   
	
