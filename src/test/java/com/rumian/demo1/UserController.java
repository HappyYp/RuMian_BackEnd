package com.rumian.demo1;

import com.rumian.demo1.base.ResponseCode;
import com.rumian.demo1.constants.AuthUserConstant;
import com.rumian.demo1.base.ResponseResult;
import com.rumian.demo1.constants.URL;
import com.rumian.demo1.exception.OperationException;
import com.rumian.demo1.model.Admin;
import com.rumian.demo1.service.AuthHttpService;
import com.rumian.demo1.service.IAdminService;
import com.rumian.demo1.util.DataUtils;
import com.rumian.demo1.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IAdminService iAdminService;
    @Autowired
    AuthHttpService authHttpService;
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseResult login(String userName, String password) throws OperationException {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return new ResponseResult(AuthUserConstant.LOGIN_PARAM_LACK, AuthUserConstant.LOGIN_PARAM_LACK_MSG);
        }

        //redis获取用户信息，存在直接登录
        Admin currentUser = iAdminService.getAdmin();
        if (currentUser != null) {
            return new ResponseResult(null, ResponseCode.OK, ResponseCode.OK_TEXT, currentUser);
        }

        //redis不存在，查询数据库
        Map param = new HashMap();
        param.put("userName", userName);
        param.put("password", EncryptUtils.encryptMD5(password));
        ResponseResult responseResult = this.authHttpService.post(URL.USER_LOGIN, param);
        if (responseResult.getCode() != 200){
            return responseResult;
        }
        currentUser = DataUtils.getData(responseResult.getData(), Admin.class);

        //写入redis
        iAdminService.putUserToRedis(currentUser);
        return new ResponseResult(ResponseCode.OK, ResponseCode.OK_TEXT);
    }

    /**
     * 用户未登录或超时
     * @return 未登录异常
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ResponseResult error() throws OperationException{
        throw new OperationException(AuthUserConstant.OVERTIME, AuthUserConstant.OVERTIME_MSG);
    }

}
