package nytro.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class VideogiocoDAO {

	public Collection<VideogiocoBean> doRetrieveAll(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL += " WHERE ISIN = ?";
//			selectSQL +=" WHERE ISIN='"+isin+"'";
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY " + order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			if(isin!=null && !isin.equals(""))
				preparedStatement.setString(1, isin);
			
			System.out.println("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = new VideogiocoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				
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

	public Collection<VideogiocoBean> doRetrieveAllLibreria(String username, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, ha_nella_libreria WHERE Username = ? AND Codice=Videogioco";
		
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY " + order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);			
			
			System.out.println("doRetrieveAllLibreria: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				/*	N.B. La query produce una tabella con pi� colonne di quelle che effettivamente andiamo ad utilizzare, siamo interessati
				 * 			solo a quelle che riguardano i videogiochi!*/
				VideogiocoBean bean = new VideogiocoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				
				videogiochi.add(bean);				
			}
			connection.commit();
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
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, a_pagamento WHERE videogioco.Codice=a_pagamento.Codice ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL += " WHERE ISIN = ?";
		//	selectSQL +=" AND ISIN='"+isin+"'";		
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals(""))
			selectSQL += " ORDER BY " + order;
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			if(isin!=null && !isin.equals(""))
				preparedStatement.setString(1, isin);
			
			System.out.println("doRetrieveAllPagamento: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoPagamentoBean bean = new VideogiocoPagamentoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
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
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, demo WHERE videogioco.Codice=demo.Codice ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL +=" AND ISIN='"+isin+"'";
		
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
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setImg(rs.getBinaryStream("Img"));
				
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
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, free_to_play WHERE videogioco.Codice=free_to_play.Codice ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL +=" AND ISIN='"+isin+"'";
		
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
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setImg(rs.getBinaryStream("Img"));
				
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
		
		String selectSQL = "SELECT * FROM videogioco WHERE Codice = ? ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL +=" AND ISIN='"+isin+"'";
		
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
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
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

	public void doSaveVideogiocoPagamento(VideogiocoPagamentoBean bean, String genere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "CALL inserisci_a_pagamento(?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getTitolo());
			preparedStatement.setInt(4, bean.getPEGI());
			preparedStatement.setNull(5, java.sql.Types.BLOB); 
			preparedStatement.setString(6, genere); 
			preparedStatement.setFloat(7, bean.getPrezzo());		
			
			System.out.println("doSaveVideogiocoPagamento: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doSaveVideogiocoDemo(VideogiocoDemoBean bean, String genere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "CALL inserisci_demo(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getTitolo());
			preparedStatement.setInt(4, bean.getPEGI());
			preparedStatement.setNull(5, java.sql.Types.BLOB); 	
			preparedStatement.setString(6, genere); 
			preparedStatement.setInt(7, bean.getCodiceVideogiocoPrincipale());					
			preparedStatement.setInt(8, bean.getDurata());
			
			System.out.println("doSaveVideogiocoDemo: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doSaveVideogiocoFreeToPlay(VideogiocoFreeToPlayBean bean, String genere) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "CALL inserisci_ftp(?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getTitolo());
			preparedStatement.setInt(4, bean.getPEGI());
			preparedStatement.setNull(5, java.sql.Types.BLOB); 	
			preparedStatement.setString(6, genere); 
			preparedStatement.setString(7, bean.getModalitaDiGioco());
			
			System.out.println("doSaveVideogiocoFreeToPlay: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
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
		
		String updateSQL = "UPDATE videogioco SET  ISIN = ?, Data_Rilascio = ?, Data_Rimozione = ?, Titolo = ?, Voto_Medio = ?, PEGI = ?, Img = ?, Trailer = ? WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
						
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getDataRimozione());
			preparedStatement.setString(4, bean.getTitolo());
			preparedStatement.setFloat(5, bean.getVotoMedio());
			preparedStatement.setInt(6, bean.getPEGI());
			preparedStatement.setBlob(7, bean.getImg());
			preparedStatement.setString(8, bean.getTrailer());
			preparedStatement.setInt(9, bean.getCodice());	
			
			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}

	public void doDeleteVideogioco(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "UPDATE videogioco SET Data_Rimozione = current_date() WHERE codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, bean.getCodice());
			
			System.out.println("doDeleteVideogioco: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
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
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
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
			
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
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
	
	public boolean doDeleteFromLibreria(String username, int codiceVideogiocoDaCancellare) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result=0;
		
		String deleteSQL="DELETE FROM ha_nella_libreria WHERE Username = ? AND Videogioco = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, codiceVideogiocoDaCancellare);
			
			System.out.println("doDeleteFromLibreria: " + preparedStatement.toString());
			
			result=preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
	}
	
	public boolean doSaveToLibreria(String username, int codiceVideogiocoDaInserire) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result=0;
		
		String selectSQL = "CALL inserisci_libreria(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, codiceVideogiocoDaInserire);
			
			System.out.println("doSaveToLibreria: " + preparedStatement.toString());
			result=preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
	}
	
	public VideogiocoBean doRetrieveDetailedByCodice(int codice) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;		
		
		String selectSQL1 = "SELECT DISTINCT * FROM videogioco, a_pagamento WHERE videogioco.Codice=a_pagamento.Codice AND videogioco.Codice=?";
		String selectSQL2 = "SELECT DISTINCT * FROM videogioco, demo WHERE videogioco.Codice=demo.Codice AND videogioco.Codice=?";
		String selectSQL3 = "SELECT DISTINCT * FROM videogioco, free_to_play WHERE videogioco.Codice=free_to_play.Codice AND videogioco.Codice=?";
		
		VideogiocoPagamentoBean bean1 = new VideogiocoPagamentoBean();
		VideogiocoDemoBean bean2 = new VideogiocoDemoBean();
		VideogiocoFreeToPlayBean bean3 = new VideogiocoFreeToPlayBean();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement1 = connection.prepareStatement(selectSQL1);
			preparedStatement2 = connection.prepareStatement(selectSQL2);
			preparedStatement3 = connection.prepareStatement(selectSQL3);
			
			preparedStatement1.setInt(1, codice);
			preparedStatement2.setInt(1, codice);
			preparedStatement3.setInt(1, codice);
			
			System.out.println("doRetrieveDetailedByCodice: " + preparedStatement1.toString()+"\n\t"+preparedStatement2.toString()+"\n\t"+preparedStatement3.toString());
			
			ResultSet rs1 = preparedStatement1.executeQuery();
			ResultSet rs2 = preparedStatement2.executeQuery();
			ResultSet rs3 = preparedStatement3.executeQuery();
			
			while(rs1.next()) {
				bean1.setCodice(rs1.getInt("Codice"));
				bean1.setISIN(rs1.getString("ISIN"));
				bean1.setDataRimozione(rs1.getString("Data_Rimozione"));
				bean1.setDataRilascio(rs1.getString("Data_Rilascio"));
				bean1.setTitolo(rs1.getString("Titolo"));
				bean1.setVotoMedio(rs1.getFloat("Voto_medio"));
				bean1.setPEGI(rs1.getInt("PEGI"));
				bean1.setImg(rs1.getBinaryStream("Img"));		
				bean1.setPrezzo(rs1.getFloat("Prezzo"));
				bean1.setCopieVendute(rs1.getInt("Copie_Vendute"));
				bean1.setTrailer(rs1.getString("Trailer"));
			}
			
			while(rs2.next()) {
				bean2.setCodice(rs2.getInt("Codice"));
				bean2.setISIN(rs2.getString("ISIN"));
				bean2.setDataRimozione(rs2.getString("Data_Rimozione"));
				bean2.setDataRilascio(rs2.getString("Data_Rilascio"));
				bean2.setTitolo(rs2.getString("Titolo"));
				bean2.setVotoMedio(rs2.getFloat("Voto_medio"));
				bean2.setPEGI(rs2.getInt("PEGI"));
				bean2.setImg(rs2.getBinaryStream("Img"));	
				bean2.setCodiceVideogiocoPrincipale(rs2.getInt("Videogioco_Principale"));
				bean2.setDurata(rs2.getInt("Durata"));
				bean2.setTrailer(rs2.getString("Trailer"));
			}
			
			while(rs3.next()) {
				bean3.setCodice(rs3.getInt("Codice"));
				bean3.setISIN(rs3.getString("ISIN"));
				bean3.setDataRimozione(rs3.getString("Data_Rimozione"));
				bean3.setDataRilascio(rs3.getString("Data_Rilascio"));
				bean3.setTitolo(rs3.getString("Titolo"));
				bean3.setVotoMedio(rs3.getFloat("Voto_medio"));
				bean3.setPEGI(rs3.getInt("PEGI"));
				bean3.setImg(rs3.getBinaryStream("Img"));
				bean3.setModalitaDiGioco(rs3.getString("Modalita_Di_Gioco"));
				bean3.setTrailer(rs3.getString("Trailer"));
			}
			connection.commit();
		} finally {
			try {
				if(preparedStatement1!=null)
					preparedStatement1.close();
				if(preparedStatement2!=null)
					preparedStatement2.close();
				if(preparedStatement3!=null)
					preparedStatement3.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		//Solo uno dei tre avr� ISIN != da null, gli altri due lo avranno NULL
		if(bean1.getISIN()!=null)
			return bean1;
		else if(bean2.getISIN()!=null)
			return bean2;
		else if(bean3.getISIN()!=null)
			return bean3;
		
		return null;
	}
	
	public boolean doRetrieveAppartenenzaAllaLibreria(int codice, String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int n=0;
		
		String selectSQL = "SELECT COUNT(*) FROM ha_nella_libreria WHERE Videogioco = ? AND Username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, username);
			
			System.out.println("doRetrieveAppartenenzaAllaLibreria: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				n=rs.getInt(1);
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println(n);
		return n==0;
	}
	
	public AccountBean doRetrieveFromLibreria(int codice, String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM ha_nella_libreria WHERE Videogioco = ? AND Username=?";
		AccountBean bean = new AccountBean();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, username);
			
			System.out.println("doRetrieveFromLibreria: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				bean.setUsername(rs.getString("Username"));
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
	
	public boolean doRetrieveAppartenenzaAgliAcquisti(int codice, String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int n=0;
		
		String selectSQL = "SELECT COUNT(*) FROM acquista WHERE Videogioco = ? AND Username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, username);
			
			System.out.println("doRetrieveAppartenenzaAgliAcquisti: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				n=rs.getInt(1);
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println(n);
		return n==0;
	}
	
	public void doAcquisto(List<VideogiocoPagamentoBean> carrello, AccountBean account, String cartaDiPagamento) throws SQLException {
		
		for(VideogiocoPagamentoBean x : carrello) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	
			String selectSQL = "CALL acquista_videogioco(?, ?, ?, ?, ?)";
			
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, account.getUsername());
				preparedStatement.setInt(2, x.getCodice());
				preparedStatement.setString(3, cartaDiPagamento);
				preparedStatement.setFloat(4, x.getPrezzo());
				preparedStatement.setString(5, account.getIp());
				
				System.out.println("doAcquisto: " + preparedStatement.toString());
				
				preparedStatement.executeQuery();

			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			
		}
		
		return ;
	}

	public static List<String> doGetGenere(VideogiocoBean videogiocoBean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<String> generi = new ArrayList<String>();

		String selectSQL = "SELECT Nome FROM genere WHERE Videogioco = ? ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, videogiocoBean.getCodice());
			
			System.out.println("doGetGenere: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				generi.add(rs.getString("Nome"));
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return generi;
	}
	
	public void doUploadImage(VideogiocoBean bean) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "UPDATE videogioco SET Img = ? WHERE ISIN = ? AND data_rilascio = ? AND titolo = ? AND PEGI = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			 if (bean.getImg() != null) {
	                // fetches input stream of the upload file for the blob column
	                preparedStatement.setBlob(1, bean.getImg());
	                preparedStatement.setString(2, bean.getISIN());
	                preparedStatement.setString(3, bean.getDataRilascio());
	                preparedStatement.setString(4, bean.getTitolo());
	                preparedStatement.setInt(5, bean.getPEGI());
	            }		
			
			System.out.println("doUploadImage: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	public void doUploadImageByCodice(VideogiocoBean bean) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "UPDATE videogioco SET Img = ? WHERE Codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			 if (bean.getImg() != null) {
	                // fetches input stream of the upload file for the blob column
	                preparedStatement.setBlob(1, bean.getImg());
	                preparedStatement.setInt(2, bean.getCodice());
	            }		
			
			System.out.println("doUploadImageByCodice: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	public byte[] doDisplayImage(int codice) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		byte[] image = null;
				
		String insertSQL = "SELECT Img FROM Videogioco WHERE Codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, codice);
			
			System.out.println("doDisplayImage: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				image = rs.getBytes("Img");
			}							
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return image;	
	}
	
	public Collection<VideogiocoBean> doRetrievePiuAcquistati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT * FROM a_pagamento ORDER BY a_pagamento.copie_vendute DESC LIMIT ?";
			
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuAcquistati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveDetailedByCodice(rs.getInt("codice"));
				if(bean.getDataRimozione() == null)
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
	
	public Collection<VideogiocoBean> doRetrievePiuVotati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT * FROM piattaforma_videogiochi_tsw.videogioco ORDER BY Voto_Medio DESC LIMIT ?";
			
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuVotati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveDetailedByCodice(rs.getInt("codice"));
				if(bean.getDataRimozione() == null)
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT videogioco, SUM(ha_nella_libreria.Ore_Di_Gioco) AS Ore_Di_Gioco from ha_nella_libreria GROUP BY ha_nella_libreria.Videogioco ORDER BY Ore_Di_Gioco DESC LIMIT ?";
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuGiocati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveDetailedByCodice(rs.getInt("videogioco"));
				
				if(bean.getDataRimozione() == null)
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
	
	public List<VideogiocoBean> doRetrieveByTitolo(String against) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		List<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT * FROM videogioco";	
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveByTitolo: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("Titolo").toLowerCase().contains(against.toLowerCase())) {
					VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"),"");
					videogiochi.add(bean);	
				}			
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println("\tTerminato: doRetrieveByTitolo");
		return videogiochi;
	
	}

	public void doUpdatePagamento(VideogiocoPagamentoBean tmp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String updateSQL = "UPDATE a_pagamento SET Prezzo = ? WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
						
			preparedStatement.setFloat(1, tmp.getPrezzo());
			preparedStatement.setInt(2, tmp.getCodice());
			
			System.out.println("doUpdatePagamento: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}
	
	public void doUpdateDemo(VideogiocoDemoBean tmp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String updateSQL = "UPDATE demo SET Durata = ? WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
						
			preparedStatement.setFloat(1, tmp.getDurata());
			preparedStatement.setInt(2, tmp.getCodice());
			
			System.out.println("doUpdateDemo: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}
	
	public void doInsertGenere(String genere, VideogiocoBean tmp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "INSERT INTO genere VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
						
			preparedStatement.setString(1, genere);
			preparedStatement.setInt(2, tmp.getCodice());
			
			System.out.println("doInsertGenere: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perch� auto-commit � false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}
	
	public VideogiocoBean doRetrievePiuGiocatoFemmine() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_fem()";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrievePiuGiocatoFemmine: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public VideogiocoBean doRetrievePiuGiocatoGenerless() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatori, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco, ha_nella_libreria, giocatore WHERE videogioco.codice = ha_nella_libreria.videogioco AND ha_nella_libreria.username = giocatore.username AND giocatore.genere is null GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT 1";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrievePiuGiocatoGenerless: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public VideogiocoBean doRetrievePiuGiocatoMaschi() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_mas()";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrievePiuGiocatoMaschi: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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

	public String doRetrieveCasaByCodice(int codice) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String nome = null;
		
		String selectSQL = "SELECT Nome_Casa_Editrice FROM azienda, videogioco WHERE codice = ? AND videogioco.ISIN = azienda.ISIN ";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			
			System.out.println("doRetrieveCasaByCodice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				nome = rs.getString("Nome_Casa_Editrice");
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return nome;
	}

	
	public ArrayList<VideogiocoBean> doRetrievePerAnnoDiRimozione(String annoRimozione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<VideogiocoBean> videogiochiRimossiInAnno = new ArrayList<VideogiocoBean>();
		
		String selectSQL = "SELECT * FROM videogioco WHERE Data_Rimozione LIKE ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, annoRimozione);
			
			System.out.println("doRetrievePerAnnoDiRimozione: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochiRimossiInAnno.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochiRimossiInAnno;
	}
	
	public VideogiocoBean doRetrieveVideogiocoPiuGiocatoDa(int min, int max) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_eta(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, min);
			preparedStatement.setInt(2, max);
			
			System.out.println("doRetrieveVideogiocoPiuGiocatoDa: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("codice"), "");
			}
			connection.commit();
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiFemmine(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'f' GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			
			System.out.println("doRetrievePiuGiocatiFemmineCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiMaschi(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'm' GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			System.out.println("doRetrievePiuGiocatiMaschi: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiGenderless(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere is null GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			System.out.println("doRetrievePiuGiocatiGenderless: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiFemmineCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'f' and videogioco.ISIN= ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			
			System.out.println("doRetrievePiuGiocatiFemmineCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiMaschiCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'm' and videogioco.ISIN = ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			System.out.println("doRetrievePiuGiocatiMaschiCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatiGenderlessCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere is null and videogioco.ISIN = ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			System.out.println("doRetrievePiuGiocatiGenderlessCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
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
	
	public Collection<VideogiocoBean> doRetrievePiuVotatiCasaEditrice(int numeroVideogiochi, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT * FROM piattaforma_videogiochi_tsw.videogioco where isin=? ORDER BY Voto_Medio DESC LIMIT ?";
			
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, numeroVideogiochi);
			
			System.out.println("doRetrievePiuVotatiCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveDetailedByCodice(rs.getInt("codice"));
				if(bean.getDataRimozione() == null)
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
	
	public Collection<VideogiocoBean> doRetrievePiuGiocatoInGeneraleCasaEditrice(int numeroVideogiochi, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<VideogiocoBean>();
		
		String selectSQL = "SELECT *, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE videogioco.ISIN = ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, numeroVideogiochi);
			
			System.out.println("doRetrievePiuGiocatoInGeneraleCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveDetailedByCodice(rs.getInt("codice"));
				if(bean.getDataRimozione() == null)
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
	
}
