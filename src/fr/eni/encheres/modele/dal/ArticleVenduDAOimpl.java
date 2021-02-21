package fr.eni.encheres.modele.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.modele.bo.ArticleVendu;
import fr.eni.encheres.modele.bo.Retraits;
import fr.eni.encheres.modele.bo.Utilisateur;


public class ArticleVenduDAOimpl implements ArticleVenduDAO {
	
	
	// requete SQL pour insï¿½rer l'article dans la BDD
	private static final String INSERT_ARTICLE="insert into ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres, "
			+ "prix_initial,prix_vente,no_utilisateur,categorie ) values(?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ARTICLE="SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres <= getdate() AND date_fin_encheres>=getdate()";

	private static final String FIND_BY_ID_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	
	private static final String MAX ="SELECT MAX(no_article) as max FROM ARTICLES_VENDUS";

	
	
	@Override
	public void insert(ArticleVendu articleVendu) throws BusinessException {
			/*if(articleVendu==null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
				throw businessException;
			}*/
		
			try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
			{
				try
				{
					cnx.setAutoCommit(false);
					PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, articleVendu.getNomArticle());
					pstmt.setString(2, articleVendu.getDescriptionArticle());
					pstmt.setDate(3, java.sql.Date.valueOf(articleVendu.getDatedebutenchere()));
					pstmt.setDate(4, java.sql.Date.valueOf(articleVendu.getDatefinenchere()));
					pstmt.setInt(5, articleVendu.getPrixinitial());
					pstmt.setInt(6, articleVendu.getPrixinitial());
					pstmt.setInt(7, articleVendu.getNumeroutilisateur());
					pstmt.setString(8, articleVendu.getCategorie());
					
					
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
	public List<ArticleVendu> select() {
		List<ArticleVendu> listeArticlessvendus = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant=new ArticleVendu();
			while(rs.next())
			{
					articleVenduCourant = articleVenduBuilder(rs);
					listeArticlessvendus.add(articleVenduCourant);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		//	BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_REPAS_ECHEC);
			//throw businessException;
		}
		return listeArticlessvendus;
		
	}

	private ArticleVendu articleVenduBuilder(ResultSet rs) throws SQLException  {
		// crï¿½ation d'un article d'aprï¿½s ce qu'on rï¿½cupï¿½re en BDD
		ArticleVendu articleVenduCourant;
		articleVenduCourant=new ArticleVendu();
		articleVenduCourant.setId(rs.getInt("no_article"));
		articleVenduCourant.setDatedebutenchere(rs.getDate("date_debut_encheres").toLocalDate());
		articleVenduCourant.setDatefinenchere(rs.getDate("date_fin_encheres").toLocalDate());
		articleVenduCourant.setCategorie(rs.getString("categorie"));
		articleVenduCourant.setDescriptionArticle(rs.getString("description"));
		articleVenduCourant.setNomArticle(rs.getString("nom_article"));
		articleVenduCourant.setNumeroutilisateur(rs.getInt("no_utilisateur"));
		articleVenduCourant.setPrixinitial(rs.getInt("prix_initial"));
		articleVenduCourant.setPrixvente(rs.getInt("prix_vente"));
		
		return articleVenduCourant;
	}

	@Override 
	public ArticleVendu findById(int Id) throws BusinessException {
		
	
		ArticleVendu articleVendu = new ArticleVendu();
	
	
		try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
		{// rï¿½cupï¿½ration en BDD et crï¿½ation de l'article
				PreparedStatement pstmt = cnx.prepareStatement(FIND_BY_ID_ARTICLE);
				pstmt.setInt(1, Id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
                {
					
				articleVendu = articleVenduBuilder(rs);
				
                }
			
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//throw e;
			}
		
		return articleVendu;
		
	}

	@Override
	public int maxArticle() {
		
		int max= 0; 
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(MAX);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
					max = rs.getInt("max");;
			}
		//	System.out.println(max  + " dans DAOimpl");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		//	BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_REPAS_ECHEC);
			//throw businessException;
		}
		return max;
		
	}

	@Override
	public String findVendeurAcId(int Id) {//obtnir le pseudo du vendeur en fonction de l'id de l'article vendu
		String pseudo=" ";
		
		try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
		{// rï¿½cupï¿½ration en BDD et crï¿½ation de l'article
				PreparedStatement pstmt = cnx.prepareStatement("SELECT pseudo FROM ARTICLES_VENDUS av Inner join UTILISATEURS u ON av.no_utilisateur=u.no_utilisateur WHERE av.no_article=" + Id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
                {
				pseudo = rs.getString("pseudo");
                }
				pstmt.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				//throw e;
			}
		return pseudo;
	}
	
	@Override
	public List<ArticleVendu> selectAAfficher(String categorie, String filtre) {

		List<ArticleVendu> listeAAfficher = new ArrayList<ArticleVendu>();
		String categorieMin=categorie.toLowerCase();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
		
		if (categorie.equals("Toutes")){
			// on affiche les articles de toutes les catï¿½gories
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres <= getdate() AND date_fin_encheres>=getdate() AND nom_article LIKE '%" +filtre + "%'");
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant=new ArticleVendu();
			while(rs.next())
			{
				articleVenduCourant = articleVenduBuilder(rs);
				listeAAfficher.add(articleVenduCourant);
			}
		}
		else {
			//On affiche uniquement les articles d'une catï¿½gorie donnï¿½e
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE date_debut_encheres <= getdate() AND date_fin_encheres>=getdate() AND categorie='"+categorieMin +"' AND nom_article LIKE '%"+filtre+"%'");
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu articleVenduCourant=new ArticleVendu();
			while(rs.next())
			{
				articleVenduCourant = articleVenduBuilder(rs);
				listeAAfficher.add(articleVenduCourant);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		//	BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_REPAS_ECHEC);
			//throw businessException;
		}
		return listeAAfficher;
		
		
	}

	@Override
	public List<ArticleVendu> selectAAfficherEncheres(String categorie, String choix, String filtre, int id) throws SQLException {
		String categorieMin=categorie.toLowerCase();
		List<ArticleVendu> liste=null;
		int tps;
		
		if(choix.equals("1")) {
		tps=0; //en cours 0
		liste=SelectionnerCatDate(tps, id, filtre, categorieMin);
		}
		else if (choix.equals("10"))	{
			tps=-1;
			liste=SelectionnerCatDate(tps, id, filtre, categorieMin);
			}
		else 	{
			// si l'enchï¿½res est terminï¿½es tps=1
			tps=1;
			liste=SelectionnerCatDate(tps, id, filtre, categorieMin);
			}	
		return liste;
	}

	private List<ArticleVendu> SelectionnerCatDate(int tps, int id, String filtre, String categorie) throws SQLException {
		
		String date="";
		List<ArticleVendu> listeAAfficherEncheres = new ArrayList<ArticleVendu>();
		if (tps==0) {
			date =" date_debut_encheres <= getdate() AND date_fin_encheres>=getdate() ";
		}
		else if (tps == -1) {
			date="date_debut_encheres> getdate()";
		}
		else  {
			date="date_fin_encheres<getdate()";
			
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
		if (categorie.equals("toutes")){
			// on affiche les articles de toutes les catï¿½gories
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE "+ date+" AND nom_article LIKE '%"+filtre +"%' AND no_utilisateur="+id);
			ResultSet rs = pstmt.executeQuery();
			ArticleVendu articleVenduCourant=new ArticleVendu();
			while(rs.next())
			{
				articleVenduCourant = articleVenduBuilder(rs);
				listeAAfficherEncheres.add(articleVenduCourant);
			}
		}
		else {
			//On affiche uniquement les articles d'une catï¿½gorie donnï¿½e
			PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE  "+ date+"  AND categorie='"+categorie +"' AND nom_article LIKE '%"+filtre+"%' AND no_utilisateur=" +id);
			ResultSet rs = pstmt.executeQuery();
			
			ArticleVendu articleVenduCourant=new ArticleVendu();
			while(rs.next())
			{
				articleVenduCourant = articleVenduBuilder(rs);
				listeAAfficherEncheres.add(articleVenduCourant);
				}
			}
		}	
		
		catch(Exception e)
		{
			e.printStackTrace();
			//	BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.LECTURE_REPAS_ECHEC);
			//throw businessException;
		}	
		
		return listeAAfficherEncheres;
	}

	
	@Override
	public void Encherir(int id, int somme) {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection()){
			//faire la requete
			PreparedStatement pstmt=cnx.prepareStatement("UPDATE ARTICLES_VENDUS SET prix_vente=" + somme +" WHERE no_article='"+id+"'");
			
			
			//execute requete
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			/// si erreur
			e.printStackTrace();
			
		}
		
		
		
	}

	@Override
	public String findTelVendeurAcId(int id) {//obtnir le téléphone du vendeur en fonction de l'id de l'article vendu
			String tel=" ";
			
			try(Connection cnx = ConnectionProvider.getConnection()) // on crï¿½e la connexion
			{// rï¿½cupï¿½ration en BDD et crï¿½ation de l'article
					PreparedStatement pstmt = cnx.prepareStatement("SELECT telephone FROM ARTICLES_VENDUS av Inner join UTILISATEURS u ON av.no_utilisateur=u.no_utilisateur WHERE av.no_article=" + id);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next())
	                {
					tel = rs.getString("telephone");
	                }
					pstmt.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					//throw e;
				}
			return tel;
		}
	}

	




	



