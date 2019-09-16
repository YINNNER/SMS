package dao;

import dbutils.DBUtils;
import entity.Institute;

import java.util.ArrayList;
import java.util.List;

public class InstituteDAO {
    private DBUtils dBUtils;

    public InstituteDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 获得所有学院信息
     * @return 学院列表
     */
    public List<Institute> queryAllInstInfo(){
        List<Institute> instituteList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        List<Object> values = new ArrayList<Object>();
        try {
            instituteList = dBUtils.querySubsetRef("institute_table", params, values, Institute.class);
            System.out.print(instituteList);
            System.out.println("获得所有学院信息成功");
        } catch (Exception e) {
            System.out.println("获得所有学院信息失败");
            e.printStackTrace();
        }

        return instituteList;
    }

    /**
     * 通过id查找学院
     * @param id：id号
     * @return 学院对象
     */
    public Institute queryInstInfoById(int id){
        Institute institute = new Institute();
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            institute = dBUtils.queryOneRef("institute_table", params, values, Institute.class);
            System.out.print(institute);
            System.out.println(id + " 通过id查找学院成功");
        } catch (Exception e) {
            System.out.println(id + " 通过id查找学院失败");
            e.printStackTrace();
        }

        return institute;
    }

    /**
     * 判断是否已经存在相同的学院
     * @param institute: 学院对象
     * @return flag
     */
    public boolean checkInstExist(Institute institute) {
        boolean flag = true;
        int id = institute.getInst_id();
        Institute inst = queryInstInfoById(id);
        if (inst == null)
            flag = false;
        return flag;
    }

    /**
     * 添加学院信息
     * @param inst: 学院对象
     * @return flag
     */
    public boolean addInstInfo(Institute inst){
        boolean flag = false;
        if (checkInstExist(inst)) {
            System.out.println("存在重复的id");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");
        params.add("inst_name");

        List<Object> values = new ArrayList<Object>();
        values.add(inst.getInst_id());
        values.add(inst.getInst_name());

        if (dBUtils.add("institute_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        }
        else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 根据id修改学院信息
     * @param institute: 学院对象
     * @return flag
     */
    public boolean modifyInstInfo(Institute institute) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("inst_name");
        params.add("inst_id");

        List<Object> values = new ArrayList<Object>();
        values.add(institute.getInst_name());
        values.add(institute.getInst_id());


        if (dBUtils.update("institute_table", params, values)) {
            System.out.println("修改成功");
            flag = true;
        }
        else
            System.out.println("修改失败");
        return flag;
    }

    /**
     * 通过id删除学院信息
     * @param id：学院id号
     * @return flag
     */
    public boolean deleteInstInfoById(int id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");

        List<Object> values = new ArrayList<Object>();
        values.add(id);

        if (dBUtils.delete("institute_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }
}
