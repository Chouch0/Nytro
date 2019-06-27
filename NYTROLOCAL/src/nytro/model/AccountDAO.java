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
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
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
		
		String selectSQL = "SELECT * FROM account WHERE Username = ? AND Password = ?";
		
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
		
		String selectSQL = "SELECT * FROM account WHERE Username = ?";
		
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

	public void doSave(AccountBean account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertSQL = "INSERT INTO account(Username, Password, Email, Email_Recupero, Cellulare, Data, Ora, IP, Ruolo, Img_Profilo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getEmail());
			preparedStatement.setString(4, account.getEmailRecupero());
			preparedStatement.setString(5, account.getCellulare());
			preparedStatement.setString(6, account.getData());
			preparedStatement.setString(7, account.getOra());
			preparedStatement.setString(8, account.getIp());
			preparedStatement.setInt(9, account.getRuolo());
			preparedStatement.setNull(10, java.sql.Types.BLOB);						//blob settata null temporaneamente
			
			System.out.println("doSave: " + preparedStatement.toString());
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

	public void doUpdate(AccountBean account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE account SET  Password = ?, Email = ?, Email_Recupero=?, Cellulare=?, Data=current_date(), Ora=current_time(), IP=?, Ruolo=?, Img_Profilo=? WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, account.getPassword());
			preparedStatement.setString(2, account.getEmail());
			preparedStatement.setString(3, account.getEmailRecupero());
			preparedStatement.setString(4, account.getCellulare());
			preparedStatement.setString(5, account.getIp());
			preparedStatement.setInt(6, account.getRuolo());
			preparedStatement.setNull(7, java.sql.Types.BLOB);
			preparedStatement.setString(8, account.getUsername());
			
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

	public boolean doDelete(AccountBean account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result=0;
		
		String deleteSQL="DELETE FROM account WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, account.getUsername());
			
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
		
		return (result != 0);
	}
}
