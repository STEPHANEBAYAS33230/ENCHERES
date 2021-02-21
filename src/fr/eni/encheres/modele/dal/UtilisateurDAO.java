package fr.eni.encheres.modele.dal;

import fr.eni.encheres.modele.bo.Utilisateur;

public interface UtilisateurDAO {

	public void enregistrerUtilisateur(Utilisateur utilisateur);
	
	public void miseAjourUtilisateur(Utilisateur utilisateur);

	public void supprimerMonCompteUtilisateur(Utilisateur utilisateur);

	public Utilisateur findUtilisateur(String boutonVendeur);

	public void encherir(String pseudo, int creditC);

	Utilisateur seconnecteUtilisateur(String pseudo, String mdp);
	
	
}