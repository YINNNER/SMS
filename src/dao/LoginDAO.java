package dao;

import dbutils.DBUtils;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private DBUtils dBUtils = null;

    public LoginDAO() {
        dBUtils = new DBUtils();
    }

    public boolean register(List<Object> values){
        // 增
        List<Object> params = new ArrayList<Object>();
        params.add("username");
        params.add("password");

        boolean flag = dBUtils.add("user_table", params, values);
        if (flag) {
            System.out.println("注册成功");
        }
        else
            System.out.println("注册失败");

        return flag;
    }

    public User checkLogin(List<Object> values){
        User user = new User();

        List<Object> params = new ArrayList<Object>();
        params.add("username");
        params.add("password");

        try {
            user = dBUtils.queryOneRef("user_table", params, values, User.class);
            System.out.print(user);
            System.out.println("利用反射查询单条记录成功");
        } catch (Exception e) {
            System.out.println("利用反射查询单条记录失败");
            e.printStackTrace();
        }

        return user;
    }
}
