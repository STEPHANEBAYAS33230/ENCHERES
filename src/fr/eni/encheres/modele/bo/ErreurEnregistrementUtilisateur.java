package fr.eni.encheres.modele.bo;

public class ErreurEnregistrementUtilisateur {
	private static String email;
	private static Boolean rec;
	
public ErreurEnregistrementUtilisateur() {
	// TODO Auto-generated constructor stub
}

/**
 * @param email
 * @param rec
 */
public ErreurEnregistrementUtilisateur(String email, Boolean rec) {
	super();
	this.email = email;
	this.rec = rec;
}

/**
 * @return the email
 */
public static String getEmail() {
	return email;
}

/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * @return the rec
 */
public static Boolean getRec() {
	return rec;
}

/**
 * @param rec the rec to set
 */
public void setRec(Boolean rec) {
	this.rec = rec;
}

}
