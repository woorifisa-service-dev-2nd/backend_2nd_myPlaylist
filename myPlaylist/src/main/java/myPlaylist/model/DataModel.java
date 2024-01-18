package myPlaylist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataModel {
	static PreparedStatement pstmt = null;
	
	public static PreparedStatement addMusicToPlaylist(Connection conn, int user_id, String play_list_name, String title, String artist_name) {
		try {
			pstmt = conn.prepareStatement(
					  "Insert into play_list(play_list, music_id) "
						  		+ "values ( (select play_list_id from play_lists where user_id = ? and play_list_name = ?), "
						  		+ "(select music.music_id from  "
						  		+ "music join artist on music.artist_id = artist.artist_id "
						  		+ "where music.title = ? and artist.artist_name = ?))"

					);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, play_list_name );
			pstmt.setString(3,  title);
			pstmt.setString(4,  artist_name);
		}
		catch (SQLException e) {
			
		}
		
		

		return pstmt;
		
	}
	public static PreparedStatement deleteMusicFromPlaylist(Connection conn, int user_id, String play_list_name, String title, String artist_name) {
		try {
			pstmt = conn.prepareStatement(
					"DELETE FROM user_playlist "
					   		+ "where play_list_id = "
					   		+ "(select play_list_id from play_lists where user_id = ? and play_list_name = ?) "
					   		+ "and music_id = (select music.music_id from music join artist on "
					   		+ "music.artist_id = artist.artist_id where music.title = ? and artist.artist_name = ?)"

					);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, play_list_name );
			pstmt.setString(3,  title);
			pstmt.setString(4,  artist_name);
		}
		catch (SQLException e) {
			
		}
		
		

		return pstmt;
		   
		 
		
		

	}
	public static PreparedStatement deleteUserPlayList(Connection conn, int deletePlayListId) {
		try {
			pstmt = conn.prepareStatement("DELETE FROM user_playlist WHERE play_list_id = ?");
			pstmt.setInt(1, deletePlayListId);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
	public static PreparedStatement deletePlayLists(Connection conn, int deletePlayListId) {
		try {
			pstmt = conn.prepareStatement("DELETE FROM play_lists WHERE play_list_id = ?");
			pstmt.setInt(1, deletePlayListId);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
	public static PreparedStatement deleteMusicInPlayList(Connection conn, int deletePlayListId, int deleteMusicId) {
		pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM user_playlist WHERE play_list_id = ? AND music_id = ?");
			pstmt.setInt(1, deletePlayListId);
			pstmt.setInt(2, deleteMusicId);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
	static public PreparedStatement getCurrentPlayListNumberQuery(Connection conn, int user_id) {
		try {
			pstmt = conn.prepareStatement("select count(*) from play_lists where user_id = ?");
			pstmt.setInt(1, user_id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
	static public PreparedStatement createPlayListQuery(Connection conn, int user_id, String play_list_name, int play_list_no) {
		try {
			pstmt = conn.prepareStatement("INSERT INTO play_lists (user_id, play_list_name, play_list_no) values (?, ?, ?)");
			pstmt.setInt(1, user_id);
			pstmt.setString(2, play_list_name);
			pstmt.setInt(3, play_list_no);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
	static public PreparedStatement login(Connection conn, String id) {
		try {
			pstmt = conn.prepareStatement("SELECT user_id, id, pw FROM user WHERE id=?");
			pstmt.setString(1, id);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return pstmt;
	}
}
