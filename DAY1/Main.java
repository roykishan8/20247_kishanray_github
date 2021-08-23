
import jobtracker.model.User;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import jobtracker.model.Task;
public class Main {

	public static void main(String[] args) {
		JobTrackerDao jt = new JobTrackerDaoImpl();
		Connection conn = new MyConnectionImpl().connectToMySQL();
		
		
		try {
			/*Task t=new Task();
			t.setTaskId(5);
			t.setOwnerId(5);
			t.setName("kroy");
			t.setStatus("old");
			System.out.println(jt.addTask(t));*/
			
			/*User u=new User();
			u.setUserId(1);
			u.setUsername("kishan");
			u.setEmail("roykishan8@gmail.com");
			System.out.println(jt.addUser(u));*/
			/*DatabaseMetaData dbm=conn.getMetaData();
			System.out.println(dbm.getDatabaseMajorVersion());
			System.out.println(dbm.getDatabaseMinorVersion());*/
			
			System.out.println(jt.getAllTask());
			System.out.println("task with new status"+jt.getAllTaskByStatus("new"));
			System.out.println("task with old status"+jt.getAllTaskByStatus("old"));
			
			System.out.println("task with owner id 2"+jt.getAllTaskByOwner(2));
			
			System.out.println("updating task status of taskid 4 : "+ jt.updateTaskStatus(3, "old"));
			System.out.println("task with new status"+jt.getAllTaskByStatus("old"));
			
			System.out.println("priority set for task id 5: "+jt.updateTaskPriority(5, "low"));
			System.out.println("notes update for task id 5: "+jt.updateTaskNotes(5, "good project"));
			System.out.println("bookmarked update for task id 5: "+jt.updateTaskBookmark(5, "yes"));
			
			System.out.println("assigning new task owner: "+jt.assignTaskUser(5, 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
