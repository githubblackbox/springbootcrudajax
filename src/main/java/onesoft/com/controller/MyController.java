package onesoft.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import onesoft.com.dao.studentDao;
import onesoft.com.model.student;

@Controller
public class MyController {

	@Autowired
	studentDao pd;
	
	@RequestMapping(value="/")
	public String show() {
		return "index";
	}
	
	@RequestMapping(value="/all")
	public ResponseEntity<List<student>> getAllItems()
	{
		System.out.println("GetAll Method");
		return new ResponseEntity<>(pd.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getone/{id}",method=RequestMethod.GET)
	public student get(@PathVariable("id") int id)
	{
		return pd.getIndividualItem(id);
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public ResponseEntity<Object> addDetails(@RequestBody student p)
	{
		String message = null;
		System.out.println("inside insert method");
		if(pd.addItem(p.getName(),p.getCity())>=1){
			message = "Data Saved Successfully";
		}
		else
		{
			message = "Please Check";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> DeleteDetails(@PathVariable("id") int id){
		System.out.println("TO BE DELETED "+id);
		String message = null;
		if(pd.deleteItem(id)>=1){
			message = "Data Removed Successfully";
		} else {
			message = "Please Check";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateDetails(@PathVariable("id") int id, @RequestBody student p){
		System.out.println(p.getName());
		String message = null;
		if(pd.updateItem(id, p.getName(), p.getCity())>=1){
			message = "Data Updated Successfully";
		} else {
			message = "Please Check";
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
