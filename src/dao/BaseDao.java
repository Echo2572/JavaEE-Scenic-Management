package dao;

import java.sql.*;

public class BaseDao {
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	System.out.println("加载数据库驱动程序失败");
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/scenicplatform";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

    protected void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
