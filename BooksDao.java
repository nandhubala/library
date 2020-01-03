package com.library.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.model.Books;

import java.sql.Connection;

public class BooksDao {
	public static Connection connectTODB() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addBooks(Books book) {

		try {
			Connection con = connectTODB();
			PreparedStatement stmt = con.prepareStatement("insert into Books values(?,?,?,?");
			stmt.setInt(1, book.getBookId());
			stmt.setString(2, book.getBookName());
			stmt.setString(3, book.getBookAuthor());
			stmt.setDouble(4, book.getBookPrice());
			int affectedRows = stmt.executeUpdate();
			System.out.println("affectedRows=" + affectedRows);
			ResultSet rs = stmt.executeQuery("select * from Books");
			while (rs.next())
				System.out.println(
						rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getDouble(4));
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

