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
import fr.eni.encheres.modele.bo.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("messageCreationCpte", "");
		request.getSession().setAttribute("messageAproposCpte", "");
		request.getSession().setAttribute("messageCpteInconnu", "");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo=request.getParameter("pseudo");
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String email=request.getParameter("email");
		String telephone=request.getParameter("telephone");
		String rue=request.getParameter("rue");
		String codepostal=request.getParameter("codepostal");
		String ville=request.getParameter("ville");
		String mp=request.getParameter("mp");
		String confirmMp=request.getParameter("confirmMp");
		String bouton =request.getParameter("bouton");
		String message="";
		request.getSession().setAttribute("message", message);
		String message1="";
		request.getSession().setAttribute("message1", message1);
		String message2="";
		request.getSession().setAttribute("message2", message2);
		String message3="";
		request.getSession().setAttribute("message3", message3);
		//****************
		   if (bouton.equals("creer")) {
			   UtilisateurManager utilisateurManager=new UtilisateurManager();
				int verifMotPasse=utilisateurManager.verifMotPasse(mp,confirmMp);
				// switch pour verifier le mot de passe ds Uilisateurmanager case1/2/3retour à inscription-case4->bdd
				
				switch(verifMotPasse) {
				case 1:  message="mot de passe trop court(minimum 8 caractères)";
				request.getSession().setAttribute("messageMdpCourt", message);
						 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
						   rd.forward(request, response);
						   break;
				case 2:  message1="Le mot de passe doit contenir des majuscules et minuscules";
				request.getSession().setAttribute("messageMdpMajMin", message1);
				 RequestDispatcher rd1 = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
				   rd1.forward(request, response);
				   break;
				case 3:  message2="Erreur de saisie du mot de passe/confimation mot de passe";
				request.getSession().setAttribute("messageErreurMdp", message2);
				 RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
				   rd2.forward(request, response);
				   break;
				case 4:  message3="compte en cours de création";
				request.getSession().setAttribute("messageCreationCpte", message3);
				 
				// envoi pour enregesitrer en bdd et direction en page 
				utilisateurManager.enregistrerUtilisateur(pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp);
				
				
				
				// request renvoi à acceuil(non connecté)pour que l'utilidateur se connecte apres si il veut
				if (ErreurEnregistrementUtilisateur.getRec()) {
					
				 request.getSession().setAttribute("messageAproposCpte", "Merci-Votre compte a bien été créé.");///remettre message a acceuil
				 RequestDispatcher rd3 = request.getRequestDispatcher("accueil");
				   rd3.forward(request, response);} 
				
				
				else {System.out.println("NON CONNECTER");
				   request.getSession().setAttribute("messageAproposCpte", "PROBLEME ENREGISTREMENT EN BASE DE DONNEES");
				   RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
				   rd3.forward(request, response);
				   }
				   break;
						 
				}}
		   
	 }
}
