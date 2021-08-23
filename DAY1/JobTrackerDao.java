import java.util.*;
import jobtracker.model.*;
import java.sql.SQLException;
import jobtracker.model.Task;
public interface JobTrackerDao {
	public boolean addTask(Task task) throws SQLException;
	public boolean addUser(User task) throws SQLException;
	public List<Task> getAllTask() throws SQLException;
	public List<Task> getAllTaskByStatus(String status) throws SQLException;
	public List<Task> getAllTaskByOwner(int ownerid) throws SQLException;
	public int updateTaskStatus(int taskid,String status) throws SQLException;
	public int updateTaskPriority(int taskid,String priority) throws SQLException;
	public int updateTaskNotes(int taskid,String notes) throws SQLException;
	public int updateTaskBookmark(int taskid,String bookmarks) throws SQLException;
	public int assignTaskUser(int taskid,int ownerid) throws SQLException;

}
