package com.example.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.EmployeeDAO;
import com.example.entity.Employee;


@Controller
@RequestMapping
public class EmployeeController 
{

	@Autowired
	private EmployeeDAO employeeDAO;

	@RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
	//If you are sending data back to client then there should be @ResponseBody annotation 
	@ResponseBody
	public String listAllEmployeesController(ModelMap map) throws JsonGenerationException, JsonMappingException, IOException
	{
		employeeDAO.getAllEmployees();
		return new ObjectMapper().writeValueAsString(employeeDAO.getAllEmployees().toString());
		//return "Success";
	}
	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	//If you are expecting json data from the client then there should be @ResponseBody annotation
	//If you are sending data back to client then there should be @ResponseBody annotation 
	@ResponseBody
	public String addEmployeeController(@RequestBody String employee) throws JsonParseException, JsonMappingException, IOException
	{
		Employee empobj = new ObjectMapper().readValue(employee, Employee.class);
		employeeDAO.addEmployee(empobj);
		return "Success";
	}
	@RequestMapping(value = "/delEmp/{employeeId}" ,  method = RequestMethod.DELETE)
	//If you are sending data back to client then there should be @ResponseBody annotation 
	@ResponseBody
	public String deleteEmplyeeController(@PathVariable("employeeId") Integer employeeId)
	{
		employeeDAO.deleteEmployee(employeeId);
		return "Success";
	}

}