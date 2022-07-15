package com.littlebean.controller;


import com.github.pagehelper.PageInfo;
import com.littlebean.model.LeaveInfo;
import com.littlebean.model.TongJi;
import com.littlebean.model.User;
import com.littlebean.service.LeaveService;
import com.littlebean.service.UserService;
import com.littlebean.util.JsonObject;
import com.littlebean.util.R;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserService userService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @RequestMapping("/leaveAll")
    public JsonObject queryLeaveAll(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "15") Integer pageSize,
                                    LeaveInfo leaveInfo,HttpSession session){
        JsonObject jsonObject=new JsonObject();
        User user= (User) session.getAttribute("user");
        if(user.getRoleName().equals("学生")){
            leaveInfo.setUserId(user.getId());
        }
        PageInfo<LeaveInfo> pageInfo=leaveService.queryLeaveAll(page,pageSize,leaveInfo);

        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setData(pageInfo.getList());
        jsonObject.setCount(pageInfo.getTotal());
        return jsonObject;
    }

    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody LeaveInfo leaveInfo, HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if(user!=null){
            leaveInfo.setUserId(user.getId());
            leaveInfo.setState(0);
        }
        boolean b=leaveService.saveInfo(leaveInfo);
        if(b==true){
            return R.ok();
        }
        return R.fail("添加失败");
    }

    @RequestMapping("/deleteByIds")
    public R deleteByIds(Integer id){
        int num=leaveService.deleteByIds(id);
        if(num>0){
            return R.ok();
        }
        return R.fail("失败");
    }

    @RequestMapping("/startApply")
    public R saveSubmit(HttpServletRequest request, String leaveId){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Integer userId=user.getId();
        //获取当前用户的班主任 辅导员，院长
        Map<String, Object> variables=new HashMap<>();
        variables.put("leaveId",leaveId);

        //根据Id查询请假天数
        LeaveInfo leaveInfo=leaveService.queryById(Integer.parseInt(leaveId));
        variables.put("day", leaveInfo.getLeaveDays());
        variables.put("student", user.getUsername());
        //根据当前登录账号获取办班主任
        User user1=userService.queryUserInfoByTypeAndRoleAndDeptId("3","班主任",user.getDeptId().toString());
        variables.put("teacher",user1.getUsername());
        //系主任
        User user2=userService.queryUserInfoByTypeAndRoleAndDeptId("2","系主任",user.getDeptId().toString());
        variables.put("manager",user2.getUsername());

        //院长
        User user3=userService.queryUserInfoByTypeAndRoleAndDeptId("1","院长",user.getDeptId().toString());
        variables.put("yz",user3.getUsername());
        //启动流程
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myLeave",variables);
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        taskService.complete(task.getId());
        //修改提交状态
        leaveInfo.setState(1);
        leaveInfo.setUserId(user.getId());
        leaveInfo.setProcessinstanceid(processInstance.getProcessInstanceId());
        int num=leaveService.updateInfo(leaveInfo);
        if(num>0){
            return R.ok();
        }
        return R.fail("提交失败");
    }
    @RequestMapping("/queryTongjiCounts")
    public List<TongJi> queryTongjiCounts(){
        return leaveService.queryTongjiCounts();
    }
}
