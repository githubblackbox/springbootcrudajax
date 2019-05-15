package onesoft.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import onesoft.com.model.student;

@Repository
public class studentDao {

	@Autowired
	JdbcTemplate temp;
	
	public List<student> getAll(){
		System.out.println("Reached Get method");
		List<student> li = new ArrayList<student>();
		temp.query("select id,Name,city from student", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				li.add(new student(rs.getInt("id"), rs.getString("name"), rs.getString("city")));
			}
		});
		return li;
	}
	
	public student getIndividualItem(int itemId)
	{
		String query="select * from student where ID=?";
		student p=temp.queryForObject(query, new Object[]{itemId},new BeanPropertyRowMapper<>(student.class));
		return p;
	}
	
	public int addItem(String name, String city) {
		String query="insert into student(name,city)values(?,?)";
		return temp.update(query,name,city);
	}
	
	public int deleteItem(int id)
	{
		String query="delete from student where id=?";
		return temp.update(query,id);
	}

	public int updateItem(int id,String name,String city)
	{
		String query="update student set name='"+name+"', city='"+city+"' where id="+id;
		return temp.update(query);
	}	
}
