package fr.eni.encheres.modele.dal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Utilisateur;

public interface ArticleVenduDAO {

	void insert(ArticleVendu articleVendu) throws BusinessException;

	List<ArticleVendu> select();

	ArticleVendu findById(int Id) throws BusinessException;

	int maxArticle();

	String findVendeurAcId(int Id);

	List<ArticleVendu> selectAAfficher(String categorie, String filtre);

	List<ArticleVendu> selectAAfficherEncheres(String categorie, String choix, String filtre, int id) throws SQLException;

	void Encherir(int id, int somme);

	String findTelVendeurAcId(int id);

	

}
