package dao;

import dbutils.DBUtils;
import entity.Class;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private DBUtils dBUtils;

    public ClassDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 获得所有班级信息
     * @return List<Class>
     */
    public List<Class> queryAllClassInfo(){
        List<Class> classList = new ArrayList<>();
        try {
            classList = dBUtils.querySubsetRef("class_table", new ArrayList<>(), new ArrayList<>(), Class.class);
            System.out.print(classList);
            System.out.println("查询所有班级信息成功");
        } catch (Exception e) {
            System.out.println("查询所有班级信息失败");
            e.printStackTrace();
        }

        return classList;
    }

    /**
     * 通过id查找班级
     * @param id：id号
     * @return 班级对象
     */
    public Class queryClassInfoById(int id){
        Class cls = new Class();
        List<Object> params = new ArrayList<Object>();
        params.add("class_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            cls = dBUtils.queryOneRef("class_table", params, values, Class.class);
            System.out.print(cls);
            System.out.println(id + " 班级信息获取成功");
        } catch (Exception e) {
            System.out.println(id + " 班级信息获取失败");
            e.printStackTrace();
        }

        return cls;
    }

    /**
     * 通过专业id获得所有班级信息
     * @param maj_id：专业id号
     * @return List<Class>
     */
    public List<Class> queryAllClassInfoByMaj(int maj_id){
        List<Class> classList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("maj_id");
        List<Object> values = new ArrayList<>();
        values.add(maj_id);

        try {
            classList = dBUtils.querySubsetRef("class_table", params, values, Class.class);
            System.out.print(classList);
            System.out.println("通过专业id获得所有班级信息成功");
        } catch (Exception e) {
            System.out.println("通过专业id获得所有班级信息失败");
            e.printStackTrace();
        }

        return classList;
    }

    /**
     * 通过年级、专业id、班级名获得所有班级信息
     * @param inst_id：年级id号
     * @param maj_id：专业id号
     * @param class_name：班级名
     * @return List<Class>
     */
    public Class queryClassInfoByInstMajName(int inst_id, int maj_id, String class_name){
        Class cls = new Class();
        List<Object> params = new ArrayList<>();
        params.add("inst_id");
        params.add("maj_id");
        params.add("class_name");
        List<Object> values = new ArrayList<>();
        values.add(inst_id);
        values.add(maj_id);
        values.add(class_name);

        try {
            cls = dBUtils.queryOneRef("class_table", params, values, Class.class);
            System.out.print(cls);
            System.out.println("通过年级、专业id、班级名获得班级信息成功");
        } catch (Exception e) {
            System.out.println("通过年级、专业id、班级名获得班级信息失败");
            e.printStackTrace();
        }

        return cls;
    }

    /**
     * 自定义查找满足条件的学生
     * @param params：条件名
     * @param values：值
     * @return list 学生列表
     */
    public List<Class> queryStuInfo(List<Object> params, List<Object> values){
        List<Class> classList = new ArrayList<>();
        try {
            classList = dBUtils.querySubsetRef("student_table", params, values, Class.class);
            System.out.print(classList);
            System.out.println("自定义查询班级信息成功");
        } catch (Exception e) {
            System.out.println("自定义查询班级信息失败");
            e.printStackTrace();
        }

        return classList;
    }

    /**
     * 判断是否已经存在id号或名字相同的班级
     * @param cls: 班级对象
     * @return flag
     */
    public boolean checkClassExist(Class cls) {
        boolean flag = true;
        int id = cls.getClass_id();
        String name = cls.getClass_name();
        Class query_cls = queryClassInfoById(id);
        Class query_cls_name = queryClassInfoByInstMajName(cls.getInst_id(), cls.getMaj_id(), cls.getClass_name());
        if (query_cls == null && query_cls_name == null)
            flag = false;
        return flag;
    }

    /**
     * 添加班级信息
     * @param cls: 班级对象
     * @return flag
     */
    public boolean addClassInfo(Class cls){
        boolean flag = false;
        if (checkClassExist(cls)) {
            System.out.println("存在重复的班级：请检查班级名和id号");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("class_id");
        params.add("class_name");
        params.add("maj_id");
        params.add("inst_id");

        List<Object> values = new ArrayList<Object>();
        values.add(cls.getClass_id());
        values.add(cls.getClass_name());
        values.add(cls.getMaj_id());
        values.add(cls.getInst_id());

        if (dBUtils.add("class_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        }
        else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 通过id删除班级信息
     * @param id：班级id号
     * @return flag
     */
    public boolean deleteClassInfoById(int id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("class_id");

        List<Object> values = new ArrayList<Object>();
        values.add(id);

        if (dBUtils.delete("class_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }

    /**
     * 根据id修改班级信息
     * @param cls: 班级对象
     * @return flag
     */
    public boolean modifyClassInfo(Class cls) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("class_name");
        params.add("maj_id");
        params.add("inst_id");
        params.add("class_id");

        List<Object> values = new ArrayList<Object>();
        values.add(cls.getClass_name());
        values.add(cls.getMaj_id());
        values.add(cls.getInst_id());
        values.add(cls.getClass_id());

        if (dBUtils.update("class_table", params, values)) {
            System.out.println("修改成功");
            flag = true;
        }
        else
            System.out.println("修改失败");
        return flag;
    }

    public static void main(String[] args) {
        ClassDAO classDAO = new ClassDAO();
        classDAO.queryAllClassInfo();
        classDAO.queryAllClassInfoByMaj(1);
        Class cls = new Class(3);
        Class cls_duplicate = new Class(2, "软工一班",1, 1 );
        System.out.println(classDAO.checkClassExist(cls));
        System.out.println(classDAO.checkClassExist(cls_duplicate));

    }
}
