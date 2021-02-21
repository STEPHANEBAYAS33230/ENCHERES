package fr.eni.encheres.modele.dal;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.Retraits;

public interface RetraitsDAO {

	void insert(Retraits retrait) throws BusinessException;

	Retraits findByIdR(int Id) throws BusinessException;

}
