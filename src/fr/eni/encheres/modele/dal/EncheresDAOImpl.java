package fr.eni.encheres.modele.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Encheres;
import fr.eni.encheres.modele.bo.Utilisateur;

public class EncheresDAOImpl implements EncheresDAO {
	

	private static final String INSERT_ENCHERE="insert into ENCHERES(no_utilisateur,no_article, date_enchere,montant_enchere, enchere_max, pseudo_dernier_encherisseur)"
			+ " values(?,?,?,?,?,?)";
	
	private static final String FIND_BY_ID_ENCHERES= "SELECT * FROM ENCHERES WHERE no_article=";

	
	@Override
	public void insert(Encheres enchere) throws BusinessException {
			
			
				try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
				{
					try
					{
						cnx.setAutoCommit(false);
						PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS);
						pstmt.setInt(1, enchere.getNumeroutilisateur());
						pstmt.setInt(2, enchere.getNumeroarticle());
						pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateenchere()));
						pstmt.setInt(4, enchere.getMontantenchere());
						pstmt.setInt(5, enchere.getPrixMax());
						pstmt.setString(6, "pseudodelamortquituequelonnetrouverajamais");
						
						
						pstmt.executeUpdate();
						
						pstmt.close();
						cnx.commit();
					}
					catch(Exception e)
					{
						e.printStackTrace();
						cnx.rollback();
						throw e;
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					BusinessException businessException = new BusinessException();
					//businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
					throw businessException;
				}

			

		
	}


	@Override
	public Encheres EnchereAssocieeAArticle(int id) {
		Encheres enchere = new Encheres();
		try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
		{// rï¿½cupï¿½ration en BDD et crï¿½ation de l'article
				PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM ENCHERES WHERE no_article="+id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
                {
					enchere = enchereBuilder(rs);
                }
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//throw e;
			}
	
		return enchere;
	}
	
	private Encheres enchereBuilder(ResultSet rs) throws SQLException {
		// Création d'une enchère
		Encheres enchere = new Encheres();

		enchere.setNumeroarticle(rs.getInt("no_article"));
		enchere.setNumeroutilisateur(rs.getInt("no_utilisateur"));
		enchere.setDateenchere(rs.getDate("date_enchere").toLocalDate());
		enchere.setMontantenchere(rs.getInt("montant_enchere"));
		enchere.setPrixMax(rs.getInt("enchere_max"));
		enchere.setPseudoDernierEncherisseur(rs.getString("pseudo_dernier_encherisseur"));
		
		return enchere;
	}

	

	@Override
	public void Encherir(int id, int somme) {
	System.out.println("enchérir (enchères DAOimpl) est ok ?");
		try(Connection cnx = ConnectionProvider.getConnection()){
			//faire la requete
			PreparedStatement pstmt=cnx.prepareStatement("UPDATE ENCHERES SET enchere_max=" + somme +" WHERE no_article ='"+id+"'");

		
			//execute requete
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
		/// si erreur
		e.printStackTrace();
			
		}
		
		
	}


	@Override
	public void ajouterEncherisseur(int id, String pseudoCo) {
	
		try(Connection cnx = ConnectionProvider.getConnection()){
			//faire la requete
			PreparedStatement pstmt=cnx.prepareStatement("UPDATE ENCHERES SET pseudo_dernier_encherisseur='" + pseudoCo +"' WHERE no_article ="+id);

		
			//execute requete
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
		/// si erreur
		e.printStackTrace();
			
		}
		
	}

}
