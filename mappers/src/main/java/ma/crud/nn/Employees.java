package ma.crud.nn;

import ma.crud.cc.Employee;
import ma.crud.cc.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Employees {


    public Employee getUserById(int id);

    public List<Employee> getUsers(Page page);

    //查询总数据
    public int getCount(Page page);

    public void deleteUser(Employee employee);

    public void addUser(Employee employee);

    public void updateUser(Employee employee);


}
