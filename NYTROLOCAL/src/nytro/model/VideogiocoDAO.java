package nytro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class VideogiocoDAO {

	public Collection<VideogiocoBean> doRetrieveAll(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco WHERE ISIN='"+isin+"' ";
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY " + order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = new VideogiocoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getDouble("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				//bean.setIMMAGINE DEL VIDEOGIOCO(rs.getString("Img_Profilo"));		per la blob
				
				videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}

	public Collection<VideogiocoBean> doRetrieveAllPagamento(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, a_pagamento WHERE videogioco.Codice=a_pagamento.Codice AND ISIN ='"+isin+"' ";
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY " + order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAllPagamento: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoPagamentoBean bean = new VideogiocoPagamentoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getDouble("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				//bean.setIMMAGINE DEL VIDEOGIOCO(rs.getString("Img_Profilo"));		per la blob
				bean.setPrezzo(rs.getDouble("Prezzo"));
				bean.setCopieVendute(rs.getInt("Copie_Vendute"));
				
				videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	public Collection<VideogiocoBean> doRetrieveAllDemo(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, demo WHERE videogioco.Codice=demo.Codice AND ISIN ='"+isin+"' ";
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY "+order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAllDemo: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoDemoBean bean = new VideogiocoDemoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getDouble("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				//bean.setIMMAGINE DEL VIDEOGIOCO(rs.getString("Img_Profilo"));		per la blob
				
				bean.setCodiceVideogiocoPrincipale(rs.getInt("Videogioco_Principale"));
				bean.setDurata(rs.getInt("Durata"));
				
				videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	public Collection<VideogiocoBean> doRetrieveAllFreeToPlay(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, free_to_play WHERE videogioco.Codice=free_to_play.Codice AND ISIN ='"+isin+"' ";
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY "+order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAllFreeToPlay: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoFreeToPlayBean bean = new VideogiocoFreeToPlayBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getDouble("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				//bean.setIMMAGINE DEL VIDEOGIOCO(rs.getString("Img_Profilo"));		per la blob
				
				bean.setModalitaDiGioco(rs.getString("Modalita_Di_Gioco"));
				
				videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}

	public VideogiocoBean doRetrieveByCodice(int codice, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = new VideogiocoBean();
		
		String selectSQL = "SELECT * FROM videogioco WHERE Codice = ? AND ISIN ='"+isin+"' ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			
			System.out.println("doRetrieveByCodice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getDouble("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				//bean.setIMMAGINE DEL VIDEOGIOCO(rs.getString("Img_Profilo"));		per la blob
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	public void doSaveVideogioco(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "INSERT INTO videogioco(Codice, ISIN, Data_Rilascio, Data_Rimozione, Titolo, Voto_Medio, PEGI, Img) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, bean.getCodice());
			preparedStatement.setString(2, bean.getISIN());
			preparedStatement.setString(3, bean.getDataRilascio());
			preparedStatement.setString(4, bean.getDataRimozione());
			preparedStatement.setString(5, bean.getTitolo());
			preparedStatement.setDouble(6, bean.getVotoMedio());
			preparedStatement.setInt(7, bean.getPEGI());
			preparedStatement.setNull(8, java.sql.Types.BLOB);						//blob settata null temporaneamente
			
			System.out.println("doSaveVideogioco: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perchè auto-commit è false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doUpdate(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE videogioco SET  ISIN = ?, Data_Rilascio = ?, Data_Rimozione = ?, Titolo = ?, Voto_Medio = ?, PEGI = ?, Img = ? WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
						
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getDataRimozione());
			preparedStatement.setString(4, bean.getTitolo());
			preparedStatement.setDouble(5, bean.getVotoMedio());
			preparedStatement.setInt(6, bean.getPEGI());
			preparedStatement.setNull(7, java.sql.Types.BLOB);		//Aggiornare con blob			preparedStatement.setInt(8, bean.getCodice());
			
			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perchè auto-commit è false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}

	public boolean doDelete(VideogiocoBean bean, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		
		int result=0;
		
		String deleteSQL="DELETE FROM videogioco WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, bean.getCodice());
			
			System.out.println("doDelete: " + preparedStatement.toString());
			result=preparedStatement.executeUpdate();
			connection.commit();													//Perchè auto-commit è false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
		//Per rimuovere il videogioco in una delle sue possibili specializzazioni (che non conosco)
		try {
			String deleteSQL1="DELETE FROM a_pagamento WHERE Codice = ?";
			String deleteSQL2="DELETE FROM free_to_play WHERE Codice = ?";			
			String deleteSQL3="DELETE FROM demo WHERE Codice = ?";
			
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement1 = connection.prepareStatement(deleteSQL1);
			preparedStatement2 = connection.prepareStatement(deleteSQL2);
			preparedStatement3 = connection.prepareStatement(deleteSQL3);
			
			preparedStatement1.setInt(1, bean.getCodice());
			preparedStatement2.setInt(1, bean.getCodice());
			preparedStatement3.setInt(1, bean.getCodice());
			
			System.out.println("doDelete: " + preparedStatement1.toString());
			System.out.println("doDelete: " + preparedStatement2.toString());
			System.out.println("doDelete: " + preparedStatement3.toString());
			
			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			preparedStatement3.executeUpdate();
			
			connection.commit();													//Perchè auto-commit è false in DriverManagerConnectionPool
			
		}catch(SQLException e){
			;
		}finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
	}
}
