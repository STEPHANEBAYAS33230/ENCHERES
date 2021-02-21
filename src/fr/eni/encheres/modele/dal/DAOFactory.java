package fr.eni.encheres.modele.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOimpl();
	}
	public static EncheresDAO getEncheresDAO() {
		return new EncheresDAOImpl();
	}
	public static RetraitsDAO getRetraitsDAO() {
		return new RetraitsDAOImpl();
	}
}
