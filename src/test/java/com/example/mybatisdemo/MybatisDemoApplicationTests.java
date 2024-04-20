package com.example.mybatisdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisdemo.dao.UserDao;
import com.example.mybatisdemo.pojo.User;
import com.example.mybatisdemo.pojo.UserQuery;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@MapperScan("com.example.mybatisdemo.dao")
@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        List<User> users = userDao.selectList(null);
        for (User b : users) {
            System.out.println(b);
        }
    }

    @Test
    void TestInsert() {
        User user = new User();
        user.setAge(23);
        user.setName("Seto");
        user.setTel("845051005");
        user.setPwd("MUSICIAN");
        userDao.insert(user);
    }

    @Test
    void testDelete(){
        userDao.deleteById(7L);
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setName("Amagi");
        userDao.updateById(user);
    }

    @Test
    void testSelectById(){
        User user = userDao.selectById(1L);
        System.out.println(user);
    }

    @Test
    void testSelectAll(){
        List<User> users = userDao.selectList(null);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    void testQueryWrapper(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.gt(User::getAge,10).lt( User::getAge, 30).between(User::getAge,16, 23);
        List<User> users = userDao.selectList(lqw);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
//    测试年龄在范围为10-30的条件查找
    void testQueryWrapper_2(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        UserQuery userQuery = new UserQuery();
        userQuery.setAge(10);
        userQuery.setAge2(30);

        lqw.gt(null!=userQuery.getAge(),User::getAge,10).lt(userQuery.getAge2()!=null,User::getAge, 30);

        List<User> users = userDao.selectList(lqw);
        for (User user:users
        ) {
            System.out.println(user);
        }
    }

//    测试投影操作，字段为表中存在的字段
    @Test
    void testQueryWrapper_3(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

        UserQuery userQuery = new UserQuery();
        userQuery.setAge(10);
        userQuery.setAge2(30);

        lqw.gt(null!=userQuery.getAge(), User::getAge, userQuery.getAge())
            .lt(userQuery.getAge2()!=null, User::getAge, userQuery.getAge2())
             .select(User::getName,User::getAge);

        List<User> users = userDao.selectList(lqw);

        for (User user:users
             ) {
            System.out.println(user);
        }
    }

//    测试聚和操作,select挑表中不存在的新字段（如下面的名为‘count(*)’的字段）
    @Test
    void testQueryWrapper_4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select( "count(*)","tel").groupBy("tel");
        List<Map<String, Object>> maps = userDao.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }


    //
    @Test
    void testQueryWrapper_5(){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();

//        模糊查询
//        lqw.likeRight(User::getName, "S");
//
//        List<User> users = userDao.selectList(lqw);
//
//        for (User user:users
//             ) {
//            System.out.println(user);
//        }

//        ---------------------------------
//        类似根据用户名和密码查询用户信息
//        lqw.eq(true, User::getName, "Manba")
//                .eq(true, User::getPassword, "out");
//        User user = userDao.selectOne(lqw);
//        System.out.println(user);

//        ---------------------------------

//      字段的值含有xx也是可看作一种模糊匹配
        lqw.like(true, User::getName, "e")
                .orderBy(true, false, User::getAge);
        List<User> users = userDao.selectList(lqw);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    void testInsert(){
        User user = new User();
        user.setName("Helsing");
        user.setAge(531);
        user.setPwd("HELL_SING");
        user.setTel("4006669999");
        userDao.insert(user);
    }

//    批量操作————batch
    @Test
    void testSelectByIds(){
        ArrayList<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);

        List<User> users = userDao.selectBatchIds(idList);


        for (User user :
                users) {
            System.out.println(user);
        }
    }

    //测试逻辑删除
    @Test
    void testlogicDelete(){
        userDao.deleteById(1L);
    }

//    乐观锁测试
    @Test
    void testUpdateWithLock(){
        User userA = userDao.selectById(3L);
        User userB = userDao.selectById(3L);

        userA.setName("Amagi");
        userDao.updateById(userA);

        userB.setName("蒸德食泥");
        userDao.updateById(userB);
    }

    @Test
    void TestCountByGroup(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> ageCountWrapper = userQueryWrapper.select(true, "count(*)", "age").groupBy(true, "age");
        List<Map<String, Object>> maps = userDao.selectMaps(ageCountWrapper);
        for (Map map :
                maps) {
            System.out.println(map);
        }
    }

    //测试分支合并

}
