package com.rumian.demo1;

import com.rumian.demo1.base.ResponseCode;
import com.rumian.demo1.base.ResponseResult;
import com.rumian.demo1.exception.OperationException;
import com.rumian.demo1.model.Admin;
import com.rumian.demo1.model.User;
import com.rumian.demo1.redis.RedisPojoService;
import com.rumian.demo1.service.AuthHttpService;
import com.rumian.demo1.service.IAdminService;
import com.rumian.demo1.util.DataUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
//@RequestMapping("/test")
public class Demo1ApplicationTests {

    //    private JSONObject json = new JSONObject();
//
//    @Autowired
//    private RedisPojoService redisPojoService;
//    @Test
//    public void contextLoads() {
//    }
//    /**
//     * 插入字符串
//     */
//    @Test
//    public void setString() {
//        redisPojoService.set("redis_string_test", "springboot redis test");
//    }
//
//    /**
//     * 获取字符串
//     */
//    @Test
//    public void getString() {
//        String result = redisPojoService.get("redis_string_test");
//        System.out.println(result);
//    }
//
//    /**
//     * 插入对象
//     */
//    @Test
//    public void setObject() {
//        Person person = new Person("person", "male");
//        redisPojoService.set("redis_obj_test", json.toJSONString(person));
//    }
//
//    /**
//     * 获取对象
//     */
//    @Test
//    public void getObject() {
//        String result = redisPojoService.get("redis_obj_test");
//        Person person = json.parseObject(result, Person.class);
//        System.out.println(json.toJSONString(person));
//    }
//
//    /**
//     * 插入对象List
//     */
//    @Test
//    public void setList() {
//        Person person1 = new Person("person1", "male");
//        Person person2 = new Person("person2", "female");
//        Person person3 = new Person("person3", "male");
//        List<Person> list = new ArrayList<>();
//        list.add(person1);
//        list.add(person2);
//        list.add(person3);
//        redisPojoService.set("redis_list_test", json.toJSONString(list));
//    }
//
//    /**
//     * 获取list
//     */
//    @Test
//    public void getList() {
//        String result = redisPojoService.get("redis_list_test");
//        List<String> list = json.parseArray(result, String.class);
//        System.out.println(list);
//    }
//
//    @Test
//    public void remove() {
//        redisPojoService.remove("redis_test");
//    }
//
//}
//
//class Person {
//    private String name;
//    private String sex;
//
//    public Person() {
//
//    }
//
//    public Person(String name, String sex) {
//        this.name = name;
//        this.sex = sex;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//@Autowired
//AuthHttpService authHttpService;
//
//    @Autowired
//    RedisPojoService redisPojoService;
//
//    @Autowired
//    IAdminService iAdminService;
//    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
//    public ResponseResult getUserList(String name) throws OperationException {
//        String url = "/user/getList";
//        Map param = new HashMap();
//        param.put("pageSize", 2);
//        param.put("pageNo", 1);
//        param.put("name", name);
//        param.get("aaa").toString();
//        if (1 == 1) {
//            throw new OperationException(21, "test throw globle");
//        }
//        return authHttpService.get(url, param);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ResponseResult updateUser(@RequestParam Map param) throws Exception{
//        String url = "/user/update";
//        return this.authHttpService.post(url,param);
//
//    }
//
//    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
//    public ResponseResult getCurrentUser(@RequestParam Map param) throws Exception{
//        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, iAdminService.getAdmin());
//
//    }
//
//    @RequestMapping(value = "/redis/set", method = RequestMethod.POST)
//    public ResponseResult set(@RequestParam Map param) throws Exception{
//        Admin currentUser = new Admin();
//        currentUser.setUserName("zhangsan");
//        //currentUser.setMobile("333333");
//        redisPojoService.set("redisGetTest", currentUser,1800);
//        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
//
//    }
//
//    @RequestMapping(value = "/redis/get", method = RequestMethod.GET)
//    public ResponseResult get(@RequestParam Map param) throws Exception{
//        Admin user = DataUtils.getData(redisPojoService.get("redisGetTest"), Admin.class);
//        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, user);
//    }
//
//    @RequestMapping(value = "/redis/setExpire", method = RequestMethod.GET)
//    public ResponseResult setExpire(@RequestParam Map param) throws Exception{
//        this.redisPojoService.setExpire("redisGetTest", 1800);
//        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
//    }
//
//    @RequestMapping(value = "/redis/delete", method = RequestMethod.POST)
//    public ResponseResult delete(@RequestParam Map param) throws Exception{
//        redisPojoService.delete("redisGetTest");
//        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
//    }
}
