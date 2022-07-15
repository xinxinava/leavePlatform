package com.littlebean.controller;

import com.github.pagehelper.PageInfo;
import com.littlebean.model.User;
import com.littlebean.service.UserService;
import com.littlebean.util.JsonObject;
import com.littlebean.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserAll")
    public JsonObject queryUserAll(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "15") Integer pageSize,
                                   User user){
        PageInfo<User> pageInfo=userService.queryUserAll(page,pageSize,user);
        JsonObject jsonObject=new JsonObject();
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }

    @RequestMapping("/queryUserAll2")
    public JsonObject queryUserAll2(HttpSession session){
        User user=(User) session.getAttribute("user");
        PageInfo<User> pageInfo=userService.queryUserAll(1,1,user);
        JsonObject jsonObject=new JsonObject();
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }

    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody User user){
        user.setPassword("1");
        boolean b=userService.saveInfo(user);
        if(b==true){
            return R.ok();
        }
        return R.fail("添加失败");
    }

    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody User user){
        boolean b=userService.updateInfo(user);
        if(b==true) {
            return R.ok();
        }
        return R.fail("修改失败");
    }

    @RequestMapping("/deleteByIds")
    public R deleteByIds(String ids){
        List<String> strings= Arrays.asList(ids.split(","));
        for(String id:strings){
            userService.deleteById(Integer.parseInt(id));
        }
        return R.ok();
    }

    @RequestMapping("/updatePassword")
    public R updatePassword(@RequestBody User user){
        int num=userService.updatePassword(user);
        if(num>0){
            return R.ok();
        }
        return R.fail("修改密码失败");
    }


}
