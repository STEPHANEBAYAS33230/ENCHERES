package fr.eni.encheres.modele.bo;

import java.time.LocalDate;

public class ArticleVendu {
    private int id;
	private String nomArticle;
	private String descriptionArticle; 
	private LocalDate datedebutenchere;
	private LocalDate datefinenchere;
	private int prixinitial;
	private int prixvente;
	private int numeroutilisateur;
	private String categorie;
	private String image;
	
	
	public ArticleVendu(int id, String nomArticle, String descriptionArticle, LocalDate datedebutenchere,
			LocalDate datefinenchere, int prixinitial, int prixvente, int numeroutilisateur, String categorie, String image) {
		super();
		this.id = id;
		this.nomArticle = nomArticle;
		this.descriptionArticle = descriptionArticle;
		this.datedebutenchere = datedebutenchere;
		this.datefinenchere = datefinenchere;
		this.prixinitial = prixinitial;
		this.prixvente = prixvente;
		this.numeroutilisateur = numeroutilisateur;
		this.categorie = categorie;
		this.image=image;
		
	}
	public ArticleVendu() {
		// TODO Auto-generated constructor stub
	}
	
	public String getImage() {
		return "pouet.jpg";
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescriptionArticle() {
		return descriptionArticle;
	}
	public void setDescriptionArticle(String descriptionArticle) {
		this.descriptionArticle = descriptionArticle;
	}
	public LocalDate getDatedebutenchere() {
		return datedebutenchere;
	}
	public void setDatedebutenchere(LocalDate datedebutenchere) {
		this.datedebutenchere = datedebutenchere;
	}
	public LocalDate getDatefinenchere() {
		return datefinenchere;
	}
	public void setDatefinenchere(LocalDate datefinenchere) {
		this.datefinenchere = datefinenchere;
	}
	public int getPrixinitial() {
		return prixinitial;
	}
	public void setPrixinitial(int prixinitial) {
		this.prixinitial = prixinitial;
	}
	public int getPrixvente() {
		return prixvente;
	}
	public void setPrixvente(int prixvente) {
		this.prixvente = prixvente;
	}
	public int getNumeroutilisateur() {
		return numeroutilisateur;
	}
	public void setNumeroutilisateur(int numeroutilisateur) {
		this.numeroutilisateur = numeroutilisateur;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [id=" + id + ", nomArticle=" + nomArticle + ", descriptionArticle=" + descriptionArticle
				+ ", datedebutenchere=" + datedebutenchere + ", datefinenchere=" + datefinenchere + ", prixinitial="
				+ prixinitial + ", prixvente=" + prixvente + ", numeroutilisateur=" + numeroutilisateur + ", categorie="
				+ categorie + "]";
	}
	
	
}
