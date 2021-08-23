

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import jobtracker.model.Task;
import jobtracker.model.User;
import java.util.*;

import com.mysql.jdbc.ResultSetMetaData;


public class JobTrackerDaoImpl implements JobTrackerDao {
	@Override
	public boolean addTask(Task task) throws SQLException{
		boolean added = false;
		String sql="INSERT INTO Task(taskid,ownerid,name,status) VALUES(?,?,?,?)";
		
		Connection conn = new MyConnectionImpl().connectToMySQL();
		//PreparedStatement pst=conn.prepareStatement(sql);
		//String sql="INSERT INTO Task VALUES"
		//String sql = "INSERT INTO Task VALUES(3,2,'SQL Training','learning JDBC connectivity','new','high','we have to use JDBC driver',true,'2021-08-20 10:32:30','2021-08-20 10:32:30')";
		//Statement stmt =  conn.createStatement();
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, task.getTaskId());
		pst.setInt(2, task.getOwnerId());
		pst.setString(3, task.getName());
		pst.setString(4,task.getStatus());
		
		//int r = stmt.executeUpdate(sql);
		int r = pst.executeUpdate();
		//stmt.close();
		pst.close();
		conn.close();
		if (r > 0)
			added = true;
		return added;
	}
	public boolean addUser(User task) throws SQLException{
		boolean added = false;
		String sql="INSERT INTO user(userid,username,email) VALUES(?,?,?)";
		Connection conn = new MyConnectionImpl().connectToMySQL();
		
		PreparedStatement pst=conn.prepareStatement(sql);
		
		pst.setInt(1,task.getUserId() );
		pst.setString(2,task.getUsername());
		pst.setString(3,task.getEmail());
	
		int r = pst.executeUpdate();
		pst.close();
		conn.close();
		if (r > 0)
			added = true;
		return added;
	}
	
	public List<Task> getAllTask() throws SQLException{
		List<Task> tasklist=new ArrayList<>();
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="Select * from task";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet res=pst.executeQuery();
		
		ResultSetMetaData rsm=(ResultSetMetaData) res.getMetaData();
		System.out.println(rsm.getColumnCount());
		//Task task=new Task();
		while(res.next()) {
			Task task=new Task();
			task.setTaskId(res.getInt("taskId"));
			task.setName(res.getString("name"));
			task.setOwnerId(res.getInt("ownerId"));
			task.setIsBookmarked(res.getString("isbookmarked"));
			task.setStatus(res.getString("status"));
			task.setNotes(res.getString("notes"));
			task.setCreatedOn(res.getDate("createdon"));
			task.setPriority(res.getString("priority"));
			task.setDescription(res.getString("description"));
			task.setStatusChangedOn(res.getDate("statuschangedon"));
			tasklist.add(task);			
		}
		
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return tasklist;
	}
	
	public List<Task> getAllTaskByStatus(String status) throws SQLException{
		List<Task> tasklist=new ArrayList<>();
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="Select * from task where status=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, status);
		ResultSet res=pst.executeQuery();
		
		
		
		while(res.next()) {
			Task task=new Task();
			task.setTaskId(res.getInt("taskId"));
			task.setName(res.getString("name"));
			task.setOwnerId(res.getInt("ownerId"));
			task.setIsBookmarked(res.getString("isbookmarked"));
			task.setStatus(res.getString("status"));
			task.setNotes(res.getString("notes"));
			task.setCreatedOn(res.getDate("createdon"));
			task.setPriority(res.getString("priority"));
			task.setDescription(res.getString("description"));
			task.setStatusChangedOn(res.getDate("statuschangedon"));
			tasklist.add(task);			
		}
		
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return tasklist;
	
		
	}
	public List<Task> getAllTaskByOwner(int ownerid) throws SQLException{
		List<Task> tasklist=new ArrayList<>();
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="Select * from task where ownerid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, ownerid);
		ResultSet res=pst.executeQuery();
		
		
		
		while(res.next()) {
			Task task=new Task();
			task.setTaskId(res.getInt("taskId"));
			task.setName(res.getString("name"));
			task.setOwnerId(res.getInt("ownerId"));
			task.setIsBookmarked(res.getString("isbookmarked"));
			task.setStatus(res.getString("status"));
			task.setNotes(res.getString("notes"));
			task.setCreatedOn(res.getDate("createdon"));
			task.setPriority(res.getString("priority"));
			task.setDescription(res.getString("description"));
			task.setStatusChangedOn(res.getDate("statuschangedon"));
			tasklist.add(task);			
		}
		
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return tasklist;
	
		
	}
	public int updateTaskStatus(int taskid,String status) throws SQLException{
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="update task set status= ? where taskid = ?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, status);
		pst.setInt(2, taskid);
		pst.executeUpdate();
		System.out.println("status updated");
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return 0;
		
	}
	
	public int updateTaskPriority(int taskid,String priority) throws SQLException{
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="update task set priority=? where taskid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, priority);
		pst.setInt(2, taskid);
		pst.executeUpdate();
		System.out.println("priority updated");
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return 0;
	}
	
	public int updateTaskNotes(int taskid,String notes) throws SQLException{
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="update task set notes=? where taskid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, notes);
		pst.setInt(2, taskid);
		pst.executeUpdate();
		System.out.println("notes updated");
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return 0;
	}
	
	public int updateTaskBookmark(int taskid,String bookmarks) throws SQLException{
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="update task set isbookmarked=? where taskid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, bookmarks);
		pst.setInt(2, taskid);
		pst.executeUpdate();
		System.out.println("bookmarks updated");
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return 0;
	}
	public int assignTaskUser(int taskid,int ownerid) throws SQLException{
		Connection conn = new MyConnectionImpl().connectToMySQL();
		String sql="update task set ownerid=? where taskid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setInt(1, ownerid);
		pst.setInt(2, taskid);
		pst.executeUpdate();
		System.out.println("task owner changed");
		pst.close();
		conn.close();//after connection is closed resultset data vanished
		return 0;
	}
	

}
