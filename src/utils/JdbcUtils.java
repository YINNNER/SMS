package utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JdbcUtils {
    //数据库用户名  
    private static final String USERNAME = "sms_user";
    //数据库密码  
    private static final String PASSWORD = "123456";
    //驱动信息   
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址  
    private static final String URL = "jdbc:mysql://192.168.1.106:3306/sms?characterEncoding=UTF-8";
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public JdbcUtils() {
        try {
            Class.forName(DRIVER);
            System.out.println("数据库连接成功！");

        } catch (Exception ignored) {

        }
    }

    /**
     * 获得数据库的连接
     */
    public void getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 增加、删除、改
     *
     * @param sql:sql语句
     * @param values:where项对应的参数值
     * @return flag:操作是否成功
     * @throws SQLException:
     */
    public boolean updateByPreparedStatement(String sql, List<Object> values) throws SQLException {
        boolean flag = false;
        int result = -1;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (values != null && !values.isEmpty()) {
            for (Object param : values) {
                pstmt.setObject(index++, param);
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0;
        return flag;
    }

    public boolean checkExist(String sql, List<Object> params) throws SQLException {
        boolean flag = false;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty()) {
            for (Object param : params) {
                pstmt.setObject(index++, param);
            }
        }
        resultSet = pstmt.executeQuery();
        if (resultSet.next()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 查询单条记录
     *
     * @param sql:sql 语句
     * @param params:where项对应的参数值
     * @return map:查询结果
     * @throws SQLException:
     */
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (Object param : params) {
                pstmt.setObject(index++, param);
            }
        }
        resultSet = pstmt.executeQuery();//返回查询结果  
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < col_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                //if (cols_value == null) {
                //    cols_value = "";
                //}
                map.put(cols_name, cols_value);
            }
        }
        return map;
    }

    /**
     * 查询多条记录
     *
     * @param sql:sql 语句
     * @param values:where项对应的参数值
     * @return list:查询结果
     * @throws SQLException:
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> values) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (values != null && !values.isEmpty()) {
            for (Object param : values) {
                pstmt.setObject(index++, param);
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                //if (cols_value == null) {
                //    cols_value = "";
                //}
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 通过反射机制查询单条记录
     *
     * @param sql:sql 语句
     * @param values:where项对应的参数值
     * @param cls:类
     * @return resultObject:查询结果
     * @throws Exception:
     */
    public <T> T findSimpleRefResult(String sql, List<Object> values,
                                     Class<T> cls) throws Exception {
        T resultObject = null;
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (values != null && !values.isEmpty()) {
            for (Object param : values) {
                pstmt.setObject(index++, param);
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            //通过反射机制创建一个实例  
            resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                //if (cols_value == null) {
                //    cols_value = "";
                //}
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限  
                field.set(resultObject, cols_value);
            }
        }
        return resultObject;

    }

    /**
     * 通过反射机制查询多条记录
     *
     * @param sql:sql语句
     * @param values:where项对应的参数值
     * @param cls:类
     * @return list:返回结果
     * @throws Exception:
     */
    public <T> List<T> findMoreRefResult(String sql, List<Object> values,
                                         Class<T> cls) throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (values != null && !values.isEmpty()) {
            for (Object param : values) {
                pstmt.setObject(index++, param);
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            //通过反射机制创建一个实例  
            T resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                //if (cols_value == null) {
                //    cols_value = "";
                //}
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限  
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;
    }

    /**
     * 释放数据库连接
     */
    void releaseConn() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
