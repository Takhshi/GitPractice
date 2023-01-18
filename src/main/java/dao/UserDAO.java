package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllUser;
import dto.User;
import dto.UserDel;
import util.GenerateHashedPw;
import util.GenerateSalt;

public class UserDAO {

	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerUser(User user) {
		String sql = "INSERT INTO useraccount VALUES(default, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		// ランダムなソルトの取得(今回は32桁で実装)
		String salt = GenerateSalt.getSalt(32);
		
		// 取得したソルトを使って平文PWをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(user.getPassword(), salt);
		
		System.out.println("生成されたソルト："+salt);
		System.out.println("生成されたハッシュ値："+hashedPw);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, user.getName());
			pstmt.setInt(2, user.getAge());
			pstmt.setInt(3, user.getGender());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getMail());
			pstmt.setString(6, salt);
			pstmt.setString(7, hashedPw);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	
	// メールアドレスを元にソルトを取得
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM useraccount WHERE mail = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<AllUser> selectAllUser() {		
		List<AllUser> result = new ArrayList<>();

		String sql = "SELECT * FROM useraccount;";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			try (ResultSet rs = pstmt.executeQuery()){
				while(rs.next()) {
					String name = rs.getString("name");
					int age = rs.getInt("age");
					int gender = rs.getInt("gender");
					String gen = gender==0?"男":"女";
					String phone = rs.getString("phone");
					String mail = rs.getString("mail");
					
					AllUser alluser = new AllUser(name, age, gender, gen, phone, mail);

					result.add(alluser);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int deleteUser(UserDel userdel) {
		String sql = "DELETE FROM useraccount WHERE mail = ?";

		int result = 0;
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){

			pstmt.setString(1, userdel.getMail());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件削除しました。");
		}
		return result;
	}
	
	// ログイン処理
	public static User login(String mail, String hashedPw) {
		String sql = "SELECT * FROM useraccount WHERE mail = ? AND password = ?";
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					int gender = rs.getInt("gender");
					String gen = rs.getString("gen");
					String phone = rs.getString("phone");
					String salt = rs.getString("salt");
					String createdAt = rs.getString("created_at");
					
					return new User(id, name, age, gender, gen, phone, mail, salt, null, null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

