package fr.eni.encheres.controleur;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.DateTimeSyntax;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jndi.toolkit.dir.LazySearchEnumerationImpl;

import fr.eni.encheres.modele.bll.ArticlesVendusManager;
import fr.eni.encheres.modele.bll.EncheresManager;
import fr.eni.encheres.modele.bll.RetraitManager;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Encheres;
import fr.eni.encheres.BusinessException;

/**
 * Servlet implementation class VendreArticle
 */
@WebServlet("/vendreArticle")
public class VendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/VendreArticle.jsp");
		rd.forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int idC=Integer.valueOf((String) request.getSession().getAttribute("idC")); // L ID DU CONNECTE
		String redirection; // page de redirection 
			
			// recup�ration des diff�rentes donn�es de l'article
			String article=request.getParameter("article");
			String description=request.getParameter("description");
			String categorie=request.getParameter("categorie");
			int prixInit= Integer.valueOf(request.getParameter("prixInit")); //cast en entier
	/*	Recup�ration fichier 
	 * String file=request.getParameter("file");
		
		System.out.println(file);**/
			LocalDate debut=null;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			debut= LocalDate.parse(request.getParameter("debut"),dtf); //cast en LocalDate
			LocalDate fin=null;
			DateTimeFormatter dtff = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			fin= LocalDate.parse(request.getParameter("fin"),dtff);//cast en LocalDate
			String rue=request.getParameter("rue");
			String codepostal= request.getParameter("codepostal");
			String ville=request.getParameter("ville");
			

			String messageVA="";

			String messagePI="";			
			request.getSession().setAttribute("messageVA", messageVA);
			
			if(debut.isAfter(fin)) {
				messageVA="la date de d�but d'une ench�re est ant�rieure � sa date de fin";

				request.getSession().setAttribute("messageVA", messageVA);

				redirection="/WEB-INF/VendreArticle.jsp";
			   }
			else 
				if(prixInit<0) {
					messagePI="la mise de d�part doit ^tre positive";
					request.getSession().setAttribute("messagePI", messagePI);
					
					redirection="/WEB-INF/VendreArticle.jsp";
				   }
				else 
			{
				   
					ArticlesVendusManager articleManager = new ArticlesVendusManager();
					ArticleVendu art=articleManager.ajouterArticle(article, description, debut, fin, prixInit, categorie, idC); // ajout de l'article en BDD
					int lastIdArticle= articleManager.maxArticle();
					RetraitManager retraitManager = new RetraitManager();
					EncheresManager enchereManager = new EncheresManager();
					Encheres enchere=enchereManager.ajouterEnchere(idC, lastIdArticle, fin, prixInit, prixInit);// ajout retrait en BDD
							
					
					//Si tout se passe bien, je vais vers la page de consultation:
					redirection="/WEB-INF/ListeEncheresConnecte.jsp";
					try {
						retraitManager.ajouterRetrait(lastIdArticle, rue, codepostal, ville); //ajout du retrait en BDD
					} catch (BusinessException e) {
						e.printStackTrace();
					}

			}

					
					RequestDispatcher rd = request.getRequestDispatcher(redirection);
					rd.forward(request, response);
					
		}
}
		
