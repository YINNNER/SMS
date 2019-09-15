package dbutils;

import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private JdbcUtils test = null;

    public DBUtils() {
        this.test = new JdbcUtils();
    }

    /**
     * 执行sql语句
     * @param sql:sql 语句
     * @param params: 参数名
     * @return flag
     */
    private boolean execute(String sql, List<Object> params) {
        System.out.println(sql);

        boolean flag = false;
        try {
            flag = test.updateByPreparedStatement(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            test.releaseConn();
        }
        return flag;
    }

    /**
     * 组装查询sql语句
     * @param table:table name
     * @param params:where field params name
     * @return sql string
     */
    private String make_query(String table, List<Object> params) {

        test.getConnection();
        StringBuilder sql;
        int size = params.size();
        if (size > 0) {
            sql = new StringBuilder("select * from " + table + " where ");
            sql.append(params.get(0)).append(" = ? ");
            for (int i = 1; i < size; i++) {
                sql.append(" and ").append(params.get(i)).append("  = ? ");
            }
        }
        else
            sql = new StringBuilder("select * from " + table);
        return sql.toString();
    }

    /**
     * 增加数据库表数据
     * @param table:table name
     * @param params:where field params name
     * @param values:where field params value
     * @return flag
     */
    public boolean add(String table, List<Object> params, List<Object> values) {
        boolean flag = false;
        test.getConnection();
        StringBuilder sql = new StringBuilder("insert into " + table + "(");
        int size = params.size();
        for (int i = 0; i < size - 1; i++) {
            sql.append(params.get(i)).append(", ");
        }
        sql.append(params.get(size - 1)).append(") values (");
        for (int i = 0; i < size - 1; i++) {
            sql.append("?, ");
        }
        sql.append("?)");

        return execute(sql.toString(), values);
    }

    /**
     * 更新数据库表数据
     * @param table:table name
     * @param params:where field params name
     * @param values:where field params value
     * @return flag
     */
    public boolean update(String table, List<Object> params, List<Object> values) {

        test.getConnection();
        StringBuilder sql = new StringBuilder("update " + table + " set ");
        int size = params.size();
        for (int i = 0; i < size - 2; i++) {
            sql.append(params.get(i)).append(" = ? , ");
        }
        sql.append(params.get(size - 2)).append(" = ? where ").append(params.get(size - 1)).append(" = ? ");

        return execute(sql.toString(), values);
    }

    /**
     * 删除数据库表数据
     * @param table:table name
     * @param params:where field params name
     * @param values:where field params value
     * @return flag
     */
    public boolean delete(String table, List<Object> params, List<Object> values) {
        test.getConnection();
        StringBuilder sql = new StringBuilder("delete from " + table + " where ");

        int size = params.size();
        if (size > 0) {
            sql.append(params.get(0)).append(" = ? ");
            for (int i = 1; i < size; i++) {
                sql.append(" and ").append(params.get(i)).append("  = ? ");
            }
        }
        return execute(sql.toString(), values);
    }

    public List<Map<String, Object>> querySubSet(String table, List<Object> params, List<Object> values) {
        List<Map<String, Object>> rs = new ArrayList<Map<String, Object>>();
        String sql = make_query(table, params);

        try {
            rs = test.findModeResult(sql, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            test.releaseConn();
        }
        return rs;
    }

    public Map<String, Object> queryOne(String table, List<Object> params, List<Object> values) {
        Map<String, Object> map = new HashMap<String, Object>();

        String sql = make_query(table, params);

        try {
            map = test.findSimpleResult(sql, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            test.releaseConn();
        }
        return map;
    }

    /**
     * 利用反射查询单个数据库表数据
     * @param table:table name
     * @param params:where field params name
     * @param values:where field params value
     * @param cls:class
     * @return flag
     */
    public <T> T queryOneRef(String table, List<Object> params, List<Object> values, Class<T> cls) {
        T object = null;

        String sql = make_query(table, params);

        try {
            object = test.findSimpleRefResult(sql, values, cls);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            test.releaseConn();
        }
        return object;
    }

    /**
     * 利用反射查询多个数据库表数据
     * @param table:table name
     * @param params:where field params name
     * @param values:where field params value
     * @param cls:class
     * @return flag
     */
    public <T> List<T> querySubsetRef(String table, List<Object> params, List<Object> values, Class<T> cls) {
        List<T> list = null;

        String sql = make_query(table, params);

        try {
            list = test.findMoreRefResult(sql, values, cls);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            test.releaseConn();
        }
        return list;
    }

    public static void main(String[] args) throws SQLException {

        DBUtils dBUtils = new DBUtils();

        // 增
        List<Object> params_add = new ArrayList<Object>();
        params_add.add("inst_id");
        params_add.add("inst_name");

        List<Object> values_add = new ArrayList<Object>();
        values_add.add(2);
        values_add.add("经济与管理学院");

        if (dBUtils.add("institute_table", params_add, values_add)) {
            System.out.println("添加成功");
        }
        else
            System.out.println("添加失败");

        // 删
        List<Object> params_delete = new ArrayList<Object>();
        params_delete.add("inst_id");

        List<Object> values_delete = new ArrayList<Object>();
        values_delete.add(2);

        if (dBUtils.delete("institute_table", params_delete, values_delete)) {
            System.out.println("删除成功");
        }
        else
            System.out.println("删除失败");

        // 改
        List<Object> params_update = new ArrayList<Object>();
        params_update.add("inst_name");
        params_update.add("inst_id");

        List<Object> values_update = new ArrayList<Object>();
        values_update.add("国际软件学院");
        values_update.add(1);

        if (dBUtils.update("institute_table", params_update, values_update)) {
            System.out.println("修改成功");
        }
        else
            System.out.println("修改失败");

        // 利用反射查询单条记录
        List<Object> params_query_ref_one = new ArrayList<Object>();
        params_query_ref_one.add("stu_id");

        List<Object> values_query_ref_one = new ArrayList<Object>();
        values_query_ref_one.add("1");

        Student student;
        try {
            student = dBUtils.queryOneRef("student_table", params_query_ref_one, values_query_ref_one, Student.class);
            System.out.print(student);
            System.out.println("利用反射查询单条记录成功");
        } catch (Exception e) {
            System.out.println("利用反射查询单条记录失败");
            e.printStackTrace();
        }

        // 利用反射查询多个记录
        List<Object> params_query_ref_subset = new ArrayList<Object>();
        // params_query_ref_one.add("stu_id");

        List<Object> values_query_ref_subset = new ArrayList<Object>();
        // values_query_ref_one.add("1");

        List<Student> studentList;
        try {
            studentList = dBUtils.querySubsetRef("student_table", params_query_ref_subset, values_query_ref_subset, Student.class);
            System.out.print(studentList);
            System.out.println("利用反射查询多个记录成功");
        } catch (Exception e) {
            System.out.println("利用反射查询多个记录失败");
            e.printStackTrace();
        }

    }

}
