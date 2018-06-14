package com.rumian.demo1;

import com.rumian.demo1.base.ResponseCode;
import com.rumian.demo1.base.ResponseResult;
import com.rumian.demo1.exception.OperationException;
import com.rumian.demo1.model.Admin;
import com.rumian.demo1.redis.RedisPojoService;
import com.rumian.demo1.service.AuthHttpService;
import com.rumian.demo1.service.IAdminService;
import com.rumian.demo1.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    AuthHttpService authHttpService;

    @Autowired
    RedisPojoService redisPojoService;

    @Autowired
    IAdminService iAdminService;
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public ResponseResult getUserList(String name) throws OperationException {
        String url = "/user/getList";
        Map param = new HashMap();
        param.put("pageSize", 2);
        param.put("pageNo", 1);
        param.put("name", name);
        param.get("aaa").toString();
        if (1 == 1) {
            throw new OperationException(21, "test throw globle");
        }
        return authHttpService.get(url, param);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult updateUser(@RequestParam Map param) throws Exception{
        String url = "/user/update";
        return this.authHttpService.post(url,param);

    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public ResponseResult getCurrentUser(@RequestParam Map param) throws Exception{
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, iAdminService.getAdmin());

    }

    @RequestMapping(value = "/redis/set", method = RequestMethod.POST)
    public ResponseResult set(@RequestParam Map param) throws Exception{
        Admin currentUser = new Admin();
        currentUser.setUserName("zhangsan");
        //currentUser.setMobile("333333");
        redisPojoService.set("redisGetTest", currentUser,1800);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");

    }

    @RequestMapping(value = "/redis/get", method = RequestMethod.GET)
    public ResponseResult get(@RequestParam Map param) throws Exception{
        Admin user = DataUtils.getData(redisPojoService.get("redisGetTest"), Admin.class);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, user);
    }

    @RequestMapping(value = "/redis/setExpire", method = RequestMethod.GET)
    public ResponseResult setExpire(@RequestParam Map param) throws Exception{
        this.redisPojoService.setExpire("redisGetTest", 1800);
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
    }

    @RequestMapping(value = "/redis/delete", method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam Map param) throws Exception{
        redisPojoService.delete("redisGetTest");
        return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, "");
    }
}
