package ma.crud.nn;

import ma.crud.cc.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Departments {

    List<Department> getDepartment();


}
