package ma.crud.cc;

import java.util.HashSet;
import java.util.Set;

//@Entity
public class Department {

	private Integer id;
	private String departmentName;
	/*private crud.Employee employee;*/
	private Set<Employee> employee = new HashSet<>();

	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(int id, String string) {
		this.id = id;
		this.departmentName = string;
	}

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	//@OneToMany(mappedBy="department")
	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "crud.Department{" +
				"id=" + id +
				", departmentName='" + departmentName + '\'' +
				'}';
	}
}
