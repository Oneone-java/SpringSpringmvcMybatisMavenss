package ma.crud;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test test = new Test();
        test.cs();

    }

    public void cs() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        //java 通过路由链接的mysql                                 //只有主节点ip能进行操作 其他ip只能查询
       Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.31.193:7001/tests","root","123123Qq#");

        //java 直接链接集群  ?roundRobinLoadBalance=true 表示主节点挂了 会使其他节点来成为
     //  gggg Connection connection = DriverManager.getConnection("jdbc:mysql:loadbalance://192.168.1.226:3306,192.168.1.227:3306,192.168.1.230:3306/a?roundRobinLoadBalance=true","root","123123Qq#");

        String sql =   "select * from testss";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt("id"));
        }


    }
}
