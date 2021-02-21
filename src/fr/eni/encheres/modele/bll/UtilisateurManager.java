package fr.eni.encheres.modele.bll;

import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.ErreurEnregistrementUtilisateur;
import fr.eni.encheres.modele.bo.Utilisateur;
import fr.eni.encheres.modele.dal.ArticleVenduDAO;
import fr.eni.encheres.modele.dal.DAOFactory;
import fr.eni.encheres.modele.dal.UtilisateurDAO;

public class UtilisateurManager {
	
private static UtilisateurDAO utilisateurDAO;
	

	public UtilisateurManager() {
	
		this.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	
	
	public int verifMotPasse(String motPasse,String confirmMp) {
		//verif motpasse/confirm motde passe
		if (motPasse.length()<8) {return 1;}
		if ( (motPasse.equals((motPasse.toLowerCase()))) || (motPasse.equals((motPasse.toUpperCase())))) {return 2;}
		if (motPasse.equals(confirmMp)) {return 4;}
		return 3;
		
	}
	
	public int verifMotPasseNouveau(String mpActuel,String mp,String confirmMp) {
		//verif motpasse/confirm motde passe
		
		if ( (mp.equals((mp.toLowerCase()))) || (mp.equals((mp.toUpperCase())))) {return 2;}
		if (mp.equals(confirmMp)) {return 4;}
		return 3;
		
	}
	
	public  void enregistrerUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,String rue,String codepostal,String ville,String mp) {
		// appel de la DAL
		boolean var=true;
		Utilisateur utilisateur= new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,0,var);
		DAOFactory.getUtilisateurDAO().enregistrerUtilisateur(utilisateur);
		
	}
	

	public void miseAjourUtilisateur(int idC, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codepostal, String ville, String mp, int creditC, Boolean adminC) {
		pseudo=(String.valueOf(idC))+"*"+pseudo;
		Utilisateur utilisateur= new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,creditC,adminC);
		DAOFactory.getUtilisateurDAO().miseAjourUtilisateur(utilisateur);
		
	}

	public static void supprimerMonCompte(int idC, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codepostal, String ville, String mp, int creditC, Boolean adminC) {
		pseudo=(String.valueOf(idC))+"*"+pseudo;
		Utilisateur utilisateur= new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,creditC,adminC);
		DAOFactory.getUtilisateurDAO().supprimerMonCompteUtilisateur(utilisateur);
		
	}

	public Utilisateur findUtilisateur(String boutonVendeur) {
		// TODO Auto-generated method stub private static final String FIND_BY_ID_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
		
		return this.utilisateurDAO.findUtilisateur(boutonVendeur);
	}


	public void Encherir(String pseudo, int creditC) {
		// TODO Auto-generated method stub
		this.utilisateurDAO.encherir(pseudo,creditC);
		
	}


	public Utilisateur seconnecteUtilisateur(String pseudo, String mdp) {
		// TODO Auto-generated method stub
		return this.utilisateurDAO.seconnecteUtilisateur(pseudo,mdp);
	}
	
	
	
	
}
