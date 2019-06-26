package nytro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


public class AccountDAO {

	public Collection<AccountBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<AccountBean> users = new LinkedList<AccountBean>();
		
		String selectSQL = "SELECT * FROM account ";
		
		//Nel caso avessi voluto imporre un ordine
		if(order!=null && !order.equals(""))
			selectSQL += "ORDER BY " + order;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {				
				AccountBean bean = new AccountBean();
				bean.setUsername(rs.getString("Username"));
				bean.setPassword(rs.getString("Password"));
				bean.setEmail(rs.getString("Email"));
				bean.setEmailRecupero(rs.getString("Email_Recupero"));
				bean.setCellulare(rs.getString("Cellulare"));
				bean.setData(rs.getString("Data"));
				bean.setOra(rs.getString("Ora"));
				bean.setIp(rs.getString("IP"));
				bean.setRuolo(rs.getInt("Ruolo"));
				//bean.setUsername(rs.getString("Img_Profilo"));		per la blob
				
				users.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return users;
	}

	public AccountBean doRetrieveByUsernamePassword(String username, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		AccountBean bean = new AccountBean();
		
		String selectSQL = "SELECT * FROM product WHERE Username = ? AND Password = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			System.out.println("doRetrieveByKey: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setPassword(rs.getString("Password"));
				bean.setEmail(rs.getString("Email"));
				bean.setEmailRecupero(rs.getString("Email_Recupero"));
				bean.setCellulare(rs.getString("Cellulare"));
				bean.setData(rs.getString("Data"));
				bean.setOra(rs.getString("Ora"));
				bean.setIp(rs.getString("IP"));
				bean.setRuolo(rs.getInt("Ruolo"));
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

	public AccountBean doRetrieveByUsername(String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		AccountBean bean = new AccountBean();
		
		String selectSQL = "SELECT * FROM product WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			System.out.println("doRetrieveByKey: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setPassword(rs.getString("Password"));
				bean.setEmail(rs.getString("Email"));
				bean.setEmailRecupero(rs.getString("Email_Recupero"));
				bean.setCellulare(rs.getString("Cellulare"));
				bean.setData(rs.getString("Data"));
				bean.setOra(rs.getString("Ora"));
				bean.setIp(rs.getString("IP"));
				bean.setRuolo(rs.getInt("Ruolo"));
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

	public void doSave(AccountBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "INSERT INTO product(Username, Password, Email, Email_Recupero, Cellulare, Data, Ora, IP, Ruolo, Img_Profilo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getEmailRecupero());
			preparedStatement.setString(5, utente.getCellulare());
			preparedStatement.setString(6, utente.getData());
			preparedStatement.setString(7, utente.getOra());
			preparedStatement.setString(8, utente.getIp());
			preparedStatement.setInt(9, utente.getRuolo());
			preparedStatement.setNull(10, java.sql.Types.BLOB);//blob settata null temporaneamente
			
			System.out.println("doSave: " + preparedStatement.toString());
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
}
