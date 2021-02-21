package fr.eni.encheres.controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bll.ArticlesVendusManager;
import fr.eni.encheres.modele.bll.UtilisateurManager;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.ErreurEnregistrementUtilisateur;
import fr.eni.encheres.modele.bo.Utilisateur;



/**
 * Servlet implementation class Connexion
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("messageAproposCpte", "");
		///recup cookie
		String valeur1=null;
		String valeur2=null;
		Cookie[] cookies = request.getCookies();
		for(int i=0; i < cookies.length; i++) {
			Cookie MonCookie = cookies[i];
			if (MonCookie.getName().equals("pseudo")) {
		valeur1 = cookies[i].getValue(); 
	}
			if (MonCookie.getName().equals("mdp")) {
				 valeur2 = cookies[i].getValue();
			}
	 }
		request.getSession().setAttribute("pseudo",valeur1);
		request.getSession().setAttribute("mdp",valeur2);
		
		//**************
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
		rd.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getSession().setAttribute("messageAproposCpte", "");
			///
			String pseudo=request.getParameter("identifiant");
			String mdp=request.getParameter("mdp");
			if ((request.getParameter("sesouvenir"))!=null) {
			Cookie cookie=new Cookie("pseudo",pseudo);
			response.addCookie(cookie);
			cookie.setMaxAge(60*60*24);
			Cookie cookie1=new Cookie("mdp",mdp);
			response.addCookie(cookie1);
			cookie1.setMaxAge(60*60*24);
			} else {Cookie cookie=new Cookie("pseudo",null);
			response.addCookie(cookie);
			cookie.setMaxAge(60*60*24);
			Cookie cookie1=new Cookie("mdp",null);
			response.addCookie(cookie1);
			cookie1.setMaxAge(60*60*24);
				
			}
			// pour connecter l'utilisateur à son compte
			//mise en session mot passe et pseudo
			request.getSession().setAttribute("pseudoC",request.getParameter("identifiant"));
			request.getSession().setAttribute("mdp",request.getParameter("mdp"));
			//****************************************
			UtilisateurManager umg=new UtilisateurManager();
			Utilisateur utilisateur=umg.seconnecteUtilisateur(pseudo,mdp);
			//************ RECUPERER LE PSEUDO ET ID DS utilisateur.getPeudo()
			int idR=-2;
			System.out.println(utilisateur.getPseudo());
			if (utilisateur.getPseudo()!=null) {
			int index=(utilisateur.getPseudo()).indexOf("*");
			if (index>-1) {
			String pseudoR=(utilisateur.getPseudo()).substring(index+1);
			String idchaineR=(utilisateur.getPseudo()).substring(0,index);
			idR=Integer.valueOf(idchaineR);
			utilisateur.setPseudo(pseudoR);}
			}
			
			System.out.println("new method"+utilisateur.getPseudo()+"id"+idR);
			//*****************************************
			//UtilisateurManager.seconnecter(pseudo,mdp);//voir l utilisateur peut se connecter
			//Boolean var=ErreurEnregistrementUtilisateur.getRec();
			//String var1=ErreurEnregistrementUtilisateur.getEmail();//ici id en faite 
			
			if (idR>-1 && (utilisateur.getPseudo()!=null)) {
				 request.getSession().setAttribute("messageconnected", "Connected");
				 request.getSession().setAttribute("idConnecté",idR);
				 String pseudoC=utilisateur.getPseudo();
				 
				 System.out.println("le pseudo du connecté est"+pseudoC+idR);
				String nomC=utilisateur.getNom();
					String prenomC=utilisateur.getPrenom();
					String emailC=utilisateur.getEmail();
					String telephoneC=utilisateur.getTel();
					String rueC=utilisateur.getRue();
					String codePostalC=utilisateur.getPostal();
					String villeC=utilisateur.getVille();
					String mdp1=utilisateur.getMdp();
					int creditC=utilisateur.getCredit();
					Boolean adminC=utilisateur.getAdmin();
					String idC=(""+idR);
					
					// variable mise en session de l'utilisateur connecté 
					request.getSession().setAttribute("idC", idC); // L ID DU CONNECTE
					// ET "pseudo" deja fait plus haut request.getSession().setAttribute("pseudo",request.getParameter("identifiant"));
					request.getSession().setAttribute("nomC", nomC);
					request.getSession().setAttribute("prenomC", prenomC);
					request.getSession().setAttribute("emailC", emailC);
					request.getSession().setAttribute("telephoneC", telephoneC);
					request.getSession().setAttribute("rueC", rueC);
					request.getSession().setAttribute("codePostalC", codePostalC);    //("codePostalC",Integer.parseInt(codePostalC));
					request.getSession().setAttribute("mdpC", mdp1);
					request.getSession().setAttribute("villeC", villeC);
					request.getSession().setAttribute("creditC", creditC);
					request.getSession().setAttribute("adminC", adminC);
					
					// fin mise en session 
					
					
					///////////////////////************************************** test 
					ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
					List<ArticleVendu> listeArticlesVendus=null;

					String html=" ";
					
			try {
						
						listeArticlesVendus = articleVenduManager.selectionner();
						ArticlesVendusManager avm = new ArticlesVendusManager();
						String pseudoA;
						
						
						for (ArticleVendu articles : listeArticlesVendus) {
							pseudoA= avm.findVendeurAcId(articles.getId());
							html=html+"<div class='col-lg-6 justify-content-center'><div class='encadrer separearticle'><form action='detailVente' method='post'>";
							html=html+"<br>Article : <input type='submit' value='"+(articles.getNomArticle())+"' name='bouton1'/>";
							html=html+"</form><br> Prix :  "+(articles.getPrixvente())+" POINTS<br><img class='imagecentre' src='"+(articles.getImage())+"' alt='"+(articles.getDescriptionArticle())+"'";
							html=html+"/><br>Date fin d'Enchère: "+(articles.getDatefinenchere())+"<br>Vendeur : <form action='listeEncheresConnecte' method='post'><input type='submit' value='"+(pseudoA)+"' name='boutonVendeur'/><br></form></div></div>";
							
						}
						html=html+"<div class='col-lg-6 justify-content-center' style='visibility: hidden;'><div class='encadrer separearticle'><form action='detailVente' method='post'>";
						html=html+"<br>Article : <input type='submit' value='"+""+"' name='bouton1'/>";
						html=html+"</form><br> Prix :  "+""+" POINTS<br><img class='imagecentre' src='"+""+"' alt='"+""+"'";
						html=html+"/><br>Date fin d'Enchère: "+""+"<br>Vendeur : <form action='listeEncheresConnecte' method='post'><input type='submit' value='"+""+"' name='boutonVendeur'/><br></form></div></div>";
						
						html=html+"<div class='col-lg-6 justify-content-center' style='visibility: hidden;'><div class='encadrer separearticle'><form action='detailVente' method='post'>";
						html=html+"<br>Article : <input type='submit' value='"+""+"' name='bouton1'/>";
						html=html+"</form><br> Prix :  "+""+" POINTS<br><img class='imagecentre' src='"+""+"' alt='"+""+"'";
						html=html+"/><br>Date fin d'Enchère: "+""+"<br>Vendeur : <form action='listeEncheresConnecte' method='post'><input type='submit' value='"+""+"' name='boutonVendeur'/><br></form></div></div>";
						
						html=html+"<div class='col-lg-6 justify-content-center' style='visibility: hidden;'><div class='encadrer separearticle'><form action='detailVente' method='post'>";
						html=html+"<br>Article : <input type='submit' value='"+""+"' name='bouton1'/>";
						html=html+"</form><br> Prix :  "+""+" POINTS<br><img class='imagecentre' src='"+""+"' alt='"+""+"'";
						html=html+"/><br>Date fin d'Enchère: "+""+"<br>Vendeur : <form action='listeEncheresConnecte' method='post'><input type='submit' value='"+""+"' name='boutonVendeur'/><br></form></div></div>";
						
						html=html+"<div class='col-lg-6 justify-content-center' style='visibility: hidden;'><div class='encadrer separearticle'><form action='detailVente' method='post'>";
						html=html+"<br>Article : <input type='submit' value='"+""+"' name='bouton1'/>";
						html=html+"</form><br> Prix :  "+""+" POINTS<br><img class='imagecentre' src='"+""+"' alt='"+""+"'";
						html=html+"/><br>Date fin d'Enchère: "+""+"<br>Vendeur : <form action='listeEncheresConnecte' method='post'><input type='submit' value='"+""+"' name='boutonVendeur'/><br></form></div></div>";
						 
						} catch (BusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.setAttribute("html", html);
				
					///////////////////////////****************************************
				 RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/ListeEncheresConnecte.jsp");
				   rd3.forward(request, response);
				   } 
				
				
				else {
				   request.getSession().setAttribute("messageCpteInconnu", "Ce compte utilisateur est inconnu");
				   RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
				   rd3.forward(request, response);
				   }
			
			
		
	}

}
