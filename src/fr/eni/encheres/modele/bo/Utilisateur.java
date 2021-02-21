package fr.eni.encheres.modele.bo;



public class Utilisateur {
	private  String pseudo;
	private  String nom;
	private  String prenom;
	private  String email;
	private  String tel;
	private  String rue;
	private  String ville;
	private  String postal;
	private  String mdp;
	private  int credit;
	private  boolean admin;
	
	public Utilisateur() {
		// TODO Auto-generated constructor stub
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue, String ville,
			String postal, String mdp, int credit, boolean admin) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.ville = ville;
		this.postal = postal;
		this.mdp = mdp;
		this.credit = 50;
		this.admin = admin;
	}
	public  String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public  String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public  String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public  String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public  String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public  int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	public  boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "Utilisateur [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel="
				+ tel + ", rue=" + rue + ", ville=" + ville + ", postal=" + postal + ", mdp=" + mdp + ", credit="
				+ credit + ", admin=" + admin + "]";
	}
	
}
	