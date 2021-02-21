package fr.eni.encheres.modele.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Utilisateur;
import fr.eni.encheres.modele.dal.ArticleVenduDAO;
import fr.eni.encheres.modele.dal.DAOFactory;

public class ArticlesVendusManager {

	
private static ArticleVenduDAO articleVenduDAO;
	

	public ArticlesVendusManager() {
	
		this.articleVenduDAO=DAOFactory.getArticleVenduDAO();
	}

	
	public ArticleVendu ajouterArticle(String article, String description, LocalDate debut, LocalDate fin, int prixInit,
			String categorie, int idC) {
		
		BusinessException businessException = new BusinessException();
	
		ArticleVendu articleVendu = null;
		
		if(!businessException.hasErreurs())
		{
			articleVendu = new ArticleVendu();
			articleVendu.setDatedebutenchere(debut);
			articleVendu.setDatefinenchere(fin);
			articleVendu.setCategorie(categorie);
			articleVendu.setNomArticle(article);
			articleVendu.setDescriptionArticle(description);
			articleVendu.setPrixinitial(prixInit);
			articleVendu.setNumeroutilisateur(idC);
			
			try {
				this.articleVenduDAO.insert(articleVendu);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				throw businessException;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return articleVendu;
	}

	public List<ArticleVendu> selectionner() throws BusinessException {
		// TODO Auto-generated method stub
		return  this.articleVenduDAO.select();
	}

	public static ArticleVendu findById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return  articleVenduDAO.findById(id);
	}

	public int maxArticle() {
		// TODO Auto-generated method stub
		return articleVenduDAO.maxArticle();
	}


	public String findVendeurAcId(int id) {
		// TODO Auto-generated method stub
		return articleVenduDAO.findVendeurAcId(id);
	}



	public List<ArticleVendu> selectionnerAAfficher(String categorie, String filtre) {
		// TODO Auto-generated method stub
		return this.articleVenduDAO.selectAAfficher(categorie,filtre);
	}


	public List<ArticleVendu> selectAAfficherEncheres(String categorie, String choix, String filtre, int id) throws SQLException {
		// TODO Auto-generated method stub
		return this.articleVenduDAO.selectAAfficherEncheres(categorie,choix,filtre,id);
	}


	public void Encherir(int id, int somme) {
		this.articleVenduDAO.Encherir(id,somme);
		
		
	}


	public String findTelVendeurAcId(int id) {
		// TODO Auto-generated method stub
		return articleVenduDAO.findTelVendeurAcId(id);
	}

}
