package fr.eni.encheres.modele.bo;

public class Vendeur {
	
	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private String rue;
	private String ville;
	private String postal;
	private String mdp;
	private int credit;
	private boolean admin;
	/**
	 * @param id
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param tel
	 * @param rue
	 * @param ville
	 * @param postal
	 * @param mdp
	 * @param credit
	 * @param admin
	 */
	public Vendeur(int id, String pseudo, String nom, String prenom, String email, String tel, String rue, String ville,
			String postal, String mdp, int credit, boolean admin) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.ville = ville;
		this.postal = postal;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the postal
	 */
	public String getPostal() {
		return postal;
	}
	/**
	 * @param postal the postal to set
	 */
	public void setPostal(String postal) {
		this.postal = postal;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "Vendeur [id=" + id + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", tel=" + tel + ", rue=" + rue + ", ville=" + ville + ", postal=" + postal + ", mdp=" + mdp
				+ ", credit=" + credit + ", admin=" + admin + "]";
	}
	
	public Vendeur() {
		// TODO Auto-generated constructor stub
	}
}
