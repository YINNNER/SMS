package dao;

import utils.DBUtils;
import entity.Major;

import java.util.ArrayList;
import java.util.List;

public class MajorDAO {
    private DBUtils dBUtils;

    public MajorDAO() {
        dBUtils = new DBUtils();
    }

    /**
     * 通过id查找专业
     * @param id：id号
     * @return 专业对象
     */
    public Major queryMajInfoById(int id){
        Major major = new Major();
        List<Object> params = new ArrayList<Object>();
        params.add("maj_id");
        List<Object> values = new ArrayList<>();
        values.add(id);
        try {
            major = dBUtils.queryOneRef("major_table", params, values, Major.class);
            System.out.print(major);
            System.out.println(id + " 通过id查找专业成功");
        } catch (Exception e) {
            System.out.println(id + " 通过id查找专业失败");
            e.printStackTrace();
        }

        return major;
    }

    /**
     * 通过学院id专业名查找专业
     * @param inst_id：id号
     * @return 专业对象
     */
    public Major queryMajInfoByInstIdMajName(int inst_id, String maj_name){
        Major major = new Major();
        List<Object> params = new ArrayList<Object>();
        params.add("inst_id");
        params.add("maj_name");
        List<Object> values = new ArrayList<>();
        values.add(inst_id);
        values.add(maj_name);
        try {
            major = dBUtils.queryOneRef("major_table", params, values, Major.class);
            System.out.print(major);
            System.out.println(inst_id + " 通过学院id专业名查找专业");
        } catch (Exception e) {
            System.out.println(inst_id + " 通过学院id专业名查找专业");
            e.printStackTrace();
        }

        return major;
    }

    /**
     * 获得某学院下所有专业
     * @param inst_id：学生id
     * @return List<Score>
     */
    public List<Major> queryAllMajByInstId(int inst_id){
        List<Major> majorList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add("inst_id");
        List<Object> values = new ArrayList<>();
        values.add(inst_id);

        try {
            majorList = dBUtils.querySubsetRef("major_table", params, values, Major.class);
            System.out.print(majorList);
            System.out.println("获得某学院下所有专业成功");
        } catch (Exception e) {
            System.out.println("获得某学院下所有专业失败");
            e.printStackTrace();
        }

        return majorList;
    }

    /**
     * 判断是否已经存在相同的专业
     * @param major: 学院对象
     * @return flag
     */
    public boolean checkInstExist(Major major) {
        boolean flag = true;
        int id = major.getMaj_id();
        String majName = major.getMaj_name();
        Major maj = queryMajInfoById(id);
        Major query_maj = queryMajInfoByInstIdMajName(id, majName);
        if (maj == null && query_maj == null)
            flag = false;
        return flag;
    }

    /**
     * 添加专业信息
     * @param major: 学院对象
     * @return flag
     */
    public boolean addMajInfo(Major major){
        boolean flag = false;
        if (checkInstExist(major)) {
            System.out.println("存在重复的id");
            return false;
        }
        List<Object> params = new ArrayList<Object>();
        params.add("maj_id");
        params.add("maj_name");
        params.add("inst_id");

        List<Object> values = new ArrayList<Object>();
        values.add(major.getMaj_id());
        values.add(major.getMaj_name());
        values.add(major.getInst_id());

        if (dBUtils.add("major_table", params, values)) {
            System.out.println("添加成功");
            flag = true;
        }
        else
            System.out.println("添加失败");

        return flag;
    }

    /**
     * 根据id修改专业信息
     * @param major: 专业对象
     * @return flag
     */
    public boolean modifyMajorInfo(Major major) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("maj_name");
        params.add("inst_id");
        params.add("maj_id");

        List<Object> values = new ArrayList<Object>();
        values.add(major.getMaj_name());
        values.add(major.getInst_id());
        values.add(major.getMaj_id());

        if (dBUtils.update("major_table", params, values)) {
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
    public boolean deleteMajInfoById(int id) {
        boolean flag = false;
        List<Object> params = new ArrayList<Object>();
        params.add("maj_id");

        List<Object> values = new ArrayList<Object>();
        values.add(id);

        if (dBUtils.delete("major_table", params, values)) {
            System.out.println("删除成功");
            flag = true;
        }
        else
            System.out.println("删除失败");
        return flag;
    }
}
