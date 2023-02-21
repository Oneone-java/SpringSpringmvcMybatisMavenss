package ma.crud.cc;

import java.sql.Date;

//@Entity
public class Employee {

	private Integer id;

	/*@Length(max = 4,min = 1)//被注释的字符串必须在指定范围内
	@NotEmpty//被注释的字符串必须为非空*/
	private String lastName;

	/*@Length(max = 20)
	@Email
	@NotEmpty//被注释的-|-字符串-|-必须为非空*/
	private String email;
	//1 male, 0 female
	//@NotNull
	private Integer gender;

	//@ManyToOne(cascade =CascadeType.REMOVE)
	//private crud.Department department;


	/*@Past//必须是过去的一个日期
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")//日期格式化*/
	private Date birth;

	/*@NotNull
	@NumberFormat(pattern="#,###,###.#")//数值格式化*/
	private Float salary;

	private String headUrl;

	private Department department;

	//@JoinColumn(name="p_id")
	//@ManyToOne
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}


	public Employee(Integer id, String lastName, String email, Integer gender) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "crud.Employee{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", gender=" + gender +
				", birth=" + birth +
				", salary=" + salary +
				", headUrl='" + headUrl + '\'' +
				", department=" + department +
				'}';
	}
}
