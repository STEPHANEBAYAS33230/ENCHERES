package fr.eni.encheres.modele.bll;

import java.time.LocalDate;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Encheres;
import fr.eni.encheres.modele.bo.Utilisateur;
import fr.eni.encheres.modele.dal.ArticleVenduDAO;
import fr.eni.encheres.modele.dal.DAOFactory;
import fr.eni.encheres.modele.dal.EncheresDAO;

public class EncheresManager {
	
	

private static EncheresDAO encheresDAO;
	

	public EncheresManager() {
	
		this.encheresDAO=DAOFactory.getEncheresDAO();
	}

	
	public Encheres ajouterEnchere(int idC, int lastIdArticle, LocalDate fin, int prixInit, int prixInit2) {
	
		
		BusinessException businessException = new BusinessException();
	
		Encheres enchere = null;
		
		if(!businessException.hasErreurs())
		{
			enchere = new Encheres();
			enchere.setNumeroutilisateur(idC);
			enchere.setNumeroarticle(lastIdArticle);
			enchere.setDateenchere(fin);
			enchere.setPrixMax(prixInit2);
			enchere.setMontantenchere(prixInit);
			
			try {
				this.encheresDAO.insert(enchere);
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
		return enchere;
	}


	public Encheres EnchereAssocieeAArticle(int id) {
		// TODO Auto-generated method stub
		return  this.encheresDAO.EnchereAssocieeAArticle(id);
	}


	public void Encherir(int id, int somme) {
		// TODO Auto-generated method stub
		this.encheresDAO.Encherir(id,somme);
		
	}


	public void ajoutEncherisseur(int id, String pseudoCo) {
	this.encheresDAO.ajouterEncherisseur(id, pseudoCo);
		
	}
	


}
