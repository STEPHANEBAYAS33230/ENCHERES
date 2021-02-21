package fr.eni.encheres.modele.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.Encheres;

public interface EncheresDAO {

	void insert(Encheres enchere) throws BusinessException;

	Encheres EnchereAssocieeAArticle(int id);

	void Encherir(int id, int somme);

	void ajouterEncherisseur(int id, String pseudoCo);

}
