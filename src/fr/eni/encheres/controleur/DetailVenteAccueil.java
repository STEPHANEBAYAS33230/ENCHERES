package fr.eni.encheres.controleur;

import java.io.IOException;

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
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Encheres;
import fr.eni.encheres.modele.bo.Retraits;
import fr.eni.encheres.modele.bo.Utilisateur;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/detailVenteAccueil")
public class DetailVenteAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/DetailVenteAccueil.jsp");
		rd.forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticlesVendusManager avm = new ArticlesVendusManager();
		RetraitManager rm= new RetraitManager();
		EncheresManager em = new EncheresManager();
		
		int id = Integer.valueOf(request.getParameter("id"));//on recup�re id de l'article pour r�cup�rer l'ench�re associ�e.
	
		Encheres enchereCourante= new Encheres();
		// cmt faire pour r�cup�rer l'ench�re qui est sur cet article  ?		
		enchereCourante=em.EnchereAssocieeAArticle(id); //r�cup�re l'ench�re avec Id de l'article
		
		String redirection;
		String bouton=request.getParameter("bouton");
		
		//Sinon on �tait dans Accueil et on veux toutes les infos dans DetailVente
			
			
			Retraits retrait=null;
			String pseudo = null;
			ArticleVendu articleVendu=null;
			
			
			try {
				articleVendu = avm.findById(id);
				retrait=rm.findByIdR(id);
				pseudo=avm.findVendeurAcId(id);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("articleVendu", articleVendu);
			request.setAttribute("retrait", retrait);
			request.setAttribute("pseudo", pseudo);
			
			redirection="/WEB-INF/DetailVenteAccueil.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(redirection);
		rd.forward(request, response);
		
		//	doGet(request, response);
	}
	
}
