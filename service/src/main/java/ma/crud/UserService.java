package ma.crud;

import ma.crud.cc.Department;
import ma.crud.cc.Employee;
import ma.crud.cc.Page;
import ma.crud.nn.Departments;
import ma.crud.nn.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private Employees employees;

	@Autowired
	private Departments departments;
	/*@Transactional
	public List<crud.Employee> queryStudent()
	{
		List<crud.Employee> list= userMapper.queryStudent();
		return list;
	}*/

	@Transactional
	public void getCount(Page<Employee> page)
	{
		int count = employees.getCount(page);
		int p =(page.getPageNo()-1) *page.getPageSize(); //每页多少条数据          //当前第几页
		page.setP(p);
		page.setSum(count);
		page.setList(employees.getUsers(page));

	}

	@Transactional
	public Employee getUserById(int id)
	{
		return employees.getUserById(id);
	}
	@Transactional
	public List<Department> getDepartment()
	{
		return departments.getDepartment();
	}
	@Transactional
	public void addUser(Employee employee)
	{
		employees.addUser(employee);
	}
	//@Transactional
	public void deleteUser(Employee employee)
	{
		employees.deleteUser(employee);
	}
	//@Transactional
	public void updateUser(Employee employee)
	{
		employees.updateUser(employee);
	}


}
