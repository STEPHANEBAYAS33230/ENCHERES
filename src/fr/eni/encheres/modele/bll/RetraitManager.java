package fr.eni.encheres.modele.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Retraits;
import fr.eni.encheres.modele.dal.ArticleVenduDAO;
import fr.eni.encheres.modele.dal.DAOFactory;
import fr.eni.encheres.modele.dal.RetraitsDAO;



public class RetraitManager {

	
private static RetraitsDAO retraitDAO;
	
	public RetraitManager() {
	
		this.retraitDAO=DAOFactory.getRetraitsDAO();
	}
	
	
	public Retraits ajouterRetrait(int id, String rue, String codepostal, String ville) throws BusinessException {
		
	BusinessException businessException = new BusinessException();
		
		Retraits retrait = null;
		
		if(!businessException.hasErreurs())
		{
			retrait = new Retraits();
			retrait.setId(id); 
			retrait.setRueretrait(rue);;
			retrait.setPostalretrait(codepostal);;
			retrait.setVilleretrait(ville);
			
			this.retraitDAO.insert(retrait);
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
		return retrait;
		
	}


	
	public static Retraits findByIdR(int id) throws BusinessException {
		return retraitDAO.findByIdR(id);
	}

}
