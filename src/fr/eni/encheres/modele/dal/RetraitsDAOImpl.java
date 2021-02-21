package fr.eni.encheres.modele.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.Retraits;

public class RetraitsDAOImpl implements RetraitsDAO {
	
	private static final String INSERT_RETRAIT="insert into RETRAITS(no_article, rue, code_postal, ville ) values(?,?,?,?)";
	


	@Override
	public void insert(Retraits retrait) throws BusinessException {
		
		
		 
			/*if(retrait==null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_NULL);
				throw businessException;
			}*/
		
			try(Connection cnx = ConnectionProvider.getConnection()) // on crée la connexion
			{
				try
				{
					cnx.setAutoCommit(false);
					PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1,retrait.getId());
					pstmt.setString(2, retrait.getRueretrait());
					pstmt.setString(3, retrait.getPostalretrait());
					pstmt.setString(4, retrait.getVilleretrait());
				
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
	public Retraits findByIdR(int Id) throws BusinessException {

		Retraits retrait= new Retraits();
		try(Connection cnx = ConnectionProvider.getConnection()) // on crée la connexion
		{// récupération en BDD et création du retrait
				PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM RETRAITS WHERE no_article=?");
				pstmt.setInt(1,Id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
                {
					
				retrait = retraitBuilder(rs);
				
                }
				pstmt.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//throw e;
			}
		
		return retrait;
		
	}
	

private Retraits retraitBuilder(ResultSet rs) throws SQLException {
	//constructeur de retrait d'après un resultSet
	Retraits retrait;
	retrait=new Retraits();
	retrait.setId(rs.getInt("no_article"));
	retrait.setRueretrait(rs.getString("rue"));
	retrait.setPostalretrait(rs.getString("code_postal"));
	retrait.setVilleretrait(rs.getString("ville"));
	
	return retrait;
}




}


