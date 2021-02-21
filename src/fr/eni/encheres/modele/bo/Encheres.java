package fr.eni.encheres.modele.bo;

import java.time.LocalDate;


public class Encheres {
	
	private int numeroutilisateur;
	private int numeroarticle; 
	private LocalDate dateenchere;
	private int montantenchere;
	private int prixMax;
	private String pseudoDernierEncherisseur;
	
	public Encheres() {
	}

	

	public Encheres(int numeroutilisateur, int numeroarticle, LocalDate dateenchere, int montantenchere, int prixMax,
			String pseudoDernierEncherisseur) {
		this.numeroutilisateur = numeroutilisateur;
		this.numeroarticle = numeroarticle;
		this.dateenchere = dateenchere;
		this.montantenchere = montantenchere;
		this.prixMax = prixMax;
		this.pseudoDernierEncherisseur = pseudoDernierEncherisseur;
	}



	public String getPseudoDernierEncherisseur() {
		return pseudoDernierEncherisseur;
	}



	public void setPseudoDernierEncherisseur(String pseudoDernierEncherisseur) {
		this.pseudoDernierEncherisseur = pseudoDernierEncherisseur;
	}



	public int getPrixMax() {
		return prixMax;
	}


	public void setPrixMax(int prixMax) {
		this.prixMax = prixMax;
	}



	public int getNumeroutilisateur() {
		return numeroutilisateur;
	}

	public void setNumeroutilisateur(int numeroutilisateur) {
		this.numeroutilisateur = numeroutilisateur;
	}

	public int getNumeroarticle() {
		return numeroarticle;
	}

	public void setNumeroarticle(int numeroarticle) {
		this.numeroarticle = numeroarticle;
	}

	public LocalDate getDateenchere() {
		return dateenchere;
	}

	public void setDateenchere(LocalDate dateenchere) {
		this.dateenchere = dateenchere;
	}

	public int getMontantenchere() {
		return montantenchere;
	}

	public void setMontantenchere(int montantenchere) {
		this.montantenchere = montantenchere;
	}

	
}