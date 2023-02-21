package ma.crud;

import ma.crud.UserService;
import ma.crud.cc.Employee;
import ma.crud.cc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/emps")
    public String getUsers(Map<String, Object> map, Locale locale, Integer pageNo, Integer pageSize) {

        //int a = 1/0;

        Page<Employee> page = new Page<Employee>();
        if (pageNo == null || pageNo == 0){
            page.setPageNo(1);
        }else {
            page.setPageNo(pageNo);
        }

        if (pageSize == null || pageSize == 0){
            page.setPageSize(3);
        }else{
            page.setPageSize(pageSize);
        }

        userService.getCount(page);
        map.put("locale",locale.toString());
        map.put("page", page);
        return "list";
    }



    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String addUserShow(Map<String, Object> map) {
        Map<String, String> genders = new HashMap<String, String>();
        genders.put("1", "男");
        genders.put("0", "女");
        map.put("genders", genders);
        map.put("departments",userService.getDepartment());
        map.put("employee", new Employee());
        return "input";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee, BindingResult result, Map<String, Object> map, @RequestParam("head") MultipartFile head) {

        if (result.getErrorCount() > 0) {
            System.out.println("114514");

            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            Map<String, String> genders = new HashMap<String, String>();
            genders.put("1", "男");
            genders.put("0", "女");

            map.put("departments",userService.getDepartment());
            map.put("genders", genders);
            return "input";
        }

        String fileName = head.getOriginalFilename();
        String fileNames[] = fileName.split("\\.");

        long a = 0;
        synchronized ("a"){
            a = System.currentTimeMillis();
        }

        String uploadName = a +"."+ fileNames[fileNames.length - 1];
        String path ="E:\\Test\\"+uploadName;
        try {
            InputStream inputStream = head.getInputStream();// 获取到文件上传的流
            OutputStream outputStream = new FileOutputStream(path);

            byte[] b = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
                outputStream.flush();
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        employee.setHeadUrl(path);
        userService.addUser(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/headshow")
    public void test(HttpServletResponse response, String path)throws IOException
    {
        if (path !=null && !path.equals(""))
        {
            String[] fileNames = path.split("\\\\");
            response.setHeader("Content-Disposition","attachment;filename="+fileNames[fileNames.length-1]);
            InputStream in = new FileInputStream(path);

            byte[] bytes = new byte[1024*1024*5];
            int length = 0;
            while ((length = in.read(bytes)) != -1)
            {
                response.getOutputStream().write(bytes,0,length);
            }
            in.close();
        }

    }

    @RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){

        Employee employees = userService.getUserById(id);
        String path = employees.getHeadUrl();


        if (path != null && !path.equals("")){
            File file = new File(path);
            file.delete();
        }
        employees.setId(id);
        userService.deleteUser(employees);
        return "redirect:/emps";
    }

    //修改
    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(@Valid Employee employee, BindingResult result, Map<String, Object> map, @RequestParam("head") MultipartFile head) {

        System.out.println("put------------");
        if(result.getErrorCount() > 0){
            System.out.println("11451419191");

            for(FieldError error:result.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            Map<String, String> genders = new HashMap<String, String>();
            genders.put("1", "男");
            genders.put("0", "女");

            map.put("departments",userService.getDepartment());
            map.put("genders", genders);

            return "input";
        }

        Employee employees = userService.getUserById(employee.getId());
        String path = employees.getHeadUrl();

        if (employees.getHeadUrl() == null || employees.equals("")) {


            String fileName = head.getOriginalFilename();
            String fileNames[] = fileName.split("\\.");

            long a = 0;
            synchronized ("a") {
                a = System.currentTimeMillis();
            }

            String uploadName = a +"."+ fileNames[fileNames.length - 1];
            path ="E:\\Test\\"+uploadName;
        }
        try {
            InputStream inputStream = head.getInputStream();// 获取到文件上传的流
            OutputStream outputStream = new FileOutputStream(path);

            byte[] b = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
                outputStream.flush();
            }

            outputStream.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        employee.setHeadUrl(path);
        userService.updateUser(employee);
        System.out.println(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String update(@PathVariable("id") Integer id,Map<String, Object> map){

        Map<String, String> genders = new HashMap<>();
        genders.put("1", "男");
        genders.put("0", "女");
        map.put("genders", genders);
        map.put("departments",userService.getDepartment());
        map.put("employee", userService.getUserById(id));
        return "input";

    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String, Object> map){
        if(id != null){
            map.put("employee", userService.getUserById(id));
        }
    }

}













