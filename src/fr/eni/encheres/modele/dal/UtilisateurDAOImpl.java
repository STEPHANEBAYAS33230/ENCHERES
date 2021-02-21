package fr.eni.encheres.modele.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.modele.dal.ConnectionProvider;
import fr.eni.encheres.modele.bo.ErreurEnregistrementUtilisateur;
import fr.eni.encheres.modele.bo.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

	@Override
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		//1recup connexion ---> pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,credit,var
			Boolean validRec=false;
				try {
					//2 faire la requete
					Connection cnx=ConnectionProvider.getConnection();
					PreparedStatement pstmt=cnx.prepareStatement("INSERT INTO UTILISATEURS values(?,?,?,?,?,?,?,?,?,?,?)");
					pstmt.setString(1,utilisateur.getPseudo());
					pstmt.setString(2,utilisateur.getNom());
					pstmt.setString(3,utilisateur.getPrenom());
					pstmt.setString(4,utilisateur.getEmail());
					pstmt.setString(5,utilisateur.getTel());
					pstmt.setString(6,utilisateur.getRue());
					pstmt.setString(7,utilisateur.getVille());
					pstmt.setString(8,utilisateur.getPostal());
					pstmt.setString(9,utilisateur.getMdp());
					pstmt.setInt(10,utilisateur.getCredit());
					pstmt.setBoolean(11,utilisateur.getAdmin());
					
					//3 execut larequete
					pstmt.executeUpdate(); /// executequery pour select executeupdate insert delete update
					validRec=true;
					cnx.close();
					
					
					
					
				} catch (SQLException e) {
					/// si erreur
					e.printStackTrace();
					validRec=false;
				}
				//voir si enregistrer ou pas en bdd
				ErreurEnregistrementUtilisateur erreurEnregistrementUtilisateur=new ErreurEnregistrementUtilisateur(utilisateur.getEmail(),validRec);
				
				erreurEnregistrementUtilisateur.setEmail(utilisateur.getEmail()); //email ici mais aussi peut etre l'id ds seconnecterUtilisateur(Utilisateur utilisateur)
				erreurEnregistrementUtilisateur.setRec(validRec);
		
	}

	
	@Override
	public void miseAjourUtilisateur(Utilisateur utilisateur) {
		// recuperer le id avec le pseudo et le mot de passe
		
		int index=(utilisateur.getPseudo()).indexOf("*");
		String pseudo=(utilisateur.getPseudo()).substring(index+1);
		String idchaine=(utilisateur.getPseudo()).substring(0,index);
		int id=Integer.valueOf(idchaine);
		
		utilisateur.setPseudo(pseudo);
		//1recup connexion ---> pseudo,nom,prenom,email,telephone,rue,codepostal,ville,mp,credit,var
		Boolean validRec=false;
		
			try {
				//2 faire la requete
				Connection cnx=ConnectionProvider.getConnection();
				PreparedStatement pstmt=cnx.prepareStatement("UPDATE UTILISATEURS SET pseudo = ? , nom = ? , prenom = ? , email = ? , telephone = ? , rue =  ? , code_postal = ? , ville = ? , mot_de_passe = ? , credit = ? , administrateur = ? "+"WHERE no_utilisateur ='"+id+"'");
				pstmt.setString(1,utilisateur.getPseudo());
				pstmt.setString(2,utilisateur.getNom());
				pstmt.setString(3,utilisateur.getPrenom());
				pstmt.setString(4,utilisateur.getEmail());
				pstmt.setString(5,utilisateur.getTel());
				pstmt.setString(6,utilisateur.getRue());
				pstmt.setString(7,utilisateur.getVille());
				pstmt.setString(8,utilisateur.getPostal());
				pstmt.setString(9,utilisateur.getMdp());
				pstmt.setInt(10,utilisateur.getCredit());
				pstmt.setBoolean(11,utilisateur.getAdmin());
				
				//3 execut larequete
				pstmt.executeUpdate(); /// executequery pour select executeupdate insert delete update
				validRec=true;
				
				cnx.close();
				
				
				
				
			} catch (SQLException e) {System.out.println("ON EST DAOIMPL EN ERREUR");
				/// si erreur
				e.printStackTrace();
				validRec=false;
			}
		
		
		
	}

	@Override
	public void supprimerMonCompteUtilisateur(Utilisateur utilisateur) {
		// recuperer le id avec le pseudo et le mot de passe
				int index=(utilisateur.getPseudo()).indexOf("*");
				String pseudo=(utilisateur.getPseudo()).substring(index+1);
				String idchaine=(utilisateur.getPseudo()).substring(0,index);
				int id=Integer.valueOf(idchaine);
				//connection a la bdd
				Boolean validRec=false;
					try {
						
						// SUPPRESSION DES ENCHERES *****************************************
						Connection cnx=ConnectionProvider.getConnection();
						PreparedStatement pstmt3=cnx.prepareStatement("DELETE FROM ENCHERES WHERE no_utilisateur='"+idchaine+"'");
						//3 execut larequete
						pstmt3.executeUpdate(); /// executequery pour select executeupdate insert delete update
						System.out.println("SUPPRESSION ENCHERES OK------SUCESS");
						
						//// EFFACER LES ARTICLES
						PreparedStatement pstmt=cnx.prepareStatement("DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur='"+idchaine+"'");
						//3 execut larequete
						pstmt.executeUpdate(); /// executequery pour select executeupdate insert delete update
						validRec=true;
						System.out.println("SUPPRESSION ARTICLE OK------SUCESS");
						cnx.close();
					
						
						
						
						//SUPPRESSION DU COMPTE********************
						//2 faire la requete
						Connection cnx1=ConnectionProvider.getConnection();
						PreparedStatement pstmt1=cnx1.prepareStatement("DELETE FROM UTILISATEURS WHERE no_utilisateur='"+idchaine+"' AND pseudo='"+pseudo+"'");
						
						
						//3 execut larequete
						pstmt1.executeUpdate(); /// executequery pour select executeupdate insert delete update
						validRec=true;
						System.out.println("SUPPRESSION COMPTE OK------SUCESS");
						cnx.close();
						
						
						
						
					} catch (SQLException e) {
						/// si erreur
						System.out.println("ERREUR SUPPRESSION COMPTE");
						e.printStackTrace();
						validRec=false;
					}
	}

	@Override
	public Utilisateur findUtilisateur(String boutonVendeur) {
		Utilisateur utilisateur = new Utilisateur();
		//*************************************************
		try {
			Connection cnx1 = ConnectionProvider.getConnection();
			String pseudo=boutonVendeur;
			String Selection1 = "SELECT *FROM UTILISATEURS WHERE pseudo='"+pseudo+"'";
			PreparedStatement pstmt1=cnx1.prepareStatement(Selection1);
			ResultSet rs1=pstmt1.executeQuery();
			int no_utilisateurC=0;
			String pseudoC="";
			String nomC="";
			String prenomC="";
			String emailC="";
			String telephoneC="";
			String rueC="";
			String code_postalC="";
			String villeC="";
			String mot_de_passeC="";
			int creditC=0;
			boolean administrateurC=false;
			System.out.println("point1");
			while (rs1.next()) {no_utilisateurC=rs1.getInt("no_utilisateur");
			pseudoC=rs1.getString("pseudo");
			nomC= rs1.getString("nom");
			prenomC=rs1.getString("prenom");
			emailC=rs1.getString("email");
			telephoneC=rs1.getString("telephone");
			rueC=rs1.getString("rue");
			code_postalC=rs1.getString("code_postal");
			 villeC=rs1.getString("ville");
			 mot_de_passeC=rs1.getString("mot_de_passe");
			 creditC=rs1.getInt("credit");
			 administrateurC=rs1.getBoolean("administrateur");
			}
			
			
			utilisateur.setPseudo(pseudoC);
			utilisateur.setNom(nomC);
			utilisateur.setPrenom(prenomC);
			utilisateur.setEmail(emailC);
			utilisateur.setTel(telephoneC);
			utilisateur.setRue(rueC);
			utilisateur.setPostal(code_postalC);
			utilisateur.setVille(villeC);
			utilisateur.setMdp(" ");
			utilisateur.setCredit(creditC);
			utilisateur.setAdmin(administrateurC);
			String id=String.valueOf(no_utilisateurC);
			
			
			
			
			//******************
			cnx1.close();
		} catch (SQLException e) { System.out.println("ERREUR LECTURE EN BDD");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//******************

		
		// TODO Auto-generated method stub
		return utilisateur;
	}

	@Override
	public void encherir(String pseudo, int creditC) {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection()){
			//faire la requete
		
			PreparedStatement pstmt=cnx.prepareStatement("UPDATE UTILISATEURS SET credit='" + creditC +"' WHERE pseudo='"+pseudo+"'");

		
			//execute requete
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
		/// si erreur
		e.printStackTrace();
		
			
		}
		
		
	
		
	}

	@Override
	public Utilisateur seconnecteUtilisateur(String pseudo, String mdp) {
		Utilisateur utilisateur = new Utilisateur();
		try {
			Connection cnx1 = ConnectionProvider.getConnection();
			
			
			String Selection1 = "SELECT *FROM UTILISATEURS WHERE pseudo='"+pseudo+"' AND mot_de_passe='"+mdp+"'";
			PreparedStatement pstmt1=cnx1.prepareStatement(Selection1);
			ResultSet rs1=pstmt1.executeQuery();
			int no_utilisateurC=0;
			String pseudoC="";
			String nomC="";
			String prenomC="";
			String emailC="";
			String telephoneC="";
			String rueC="";
			String code_postalC="";
			String villeC="";
			String mot_de_passeC="";
			int creditC=0;
			boolean administrateurC=false;
		
			while (rs1.next()) {no_utilisateurC=rs1.getInt("no_utilisateur");
			pseudoC=rs1.getString("pseudo");
			nomC= rs1.getString("nom");
			prenomC=rs1.getString("prenom");
			emailC=rs1.getString("email");
			telephoneC=rs1.getString("telephone");
			rueC=rs1.getString("rue");
			code_postalC=rs1.getString("code_postal");
			 villeC=rs1.getString("ville");
			 mot_de_passeC=rs1.getString("mot_de_passe");
			 creditC=rs1.getInt("credit");
			 administrateurC=rs1.getBoolean("administrateur");
			}
			
			if (pseudo.equals(pseudoC) && mdp.equals(mot_de_passeC)) {
				String id=String.valueOf(no_utilisateurC);
			utilisateur.setPseudo(id+"*"+pseudoC);
			utilisateur.setNom(nomC);
			utilisateur.setPrenom(prenomC);
			utilisateur.setEmail(emailC);
			utilisateur.setTel(telephoneC);
			utilisateur.setRue(rueC);
			utilisateur.setPostal(code_postalC);
			utilisateur.setVille(villeC);
			utilisateur.setMdp(mot_de_passeC);
			utilisateur.setCredit(creditC);
			utilisateur.setAdmin(administrateurC);
			
			
				} else
			{
				
				
			System.out.println("mot de passe incorrect ou email inconnu");
				
			}
			
			//******************
			cnx1.close();
		} catch (SQLException e) { System.out.println("ERREUR LECTURE EN BDD");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//******************
	
		
		
		
		// TODO Auto-generated method stub
		return utilisateur;
	}


	
	
}


