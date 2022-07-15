package com.littlebean.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlebean.model.LeaveInfo;
import com.littlebean.model.MyTask;
import com.littlebean.model.User;
import com.littlebean.service.LeaveService;
import com.littlebean.util.JsonObject;
import com.littlebean.util.R;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    LeaveService leaveService;

    @RequestMapping("/listCommentList")
    public Map listCommentList(String taskId){
        Map<String, List<Comment>> map=new HashMap<>();
        List<Comment> commentList=new ArrayList<>();
        if(taskId!=null){
            commentList=taskService.getProcessInstanceComments(taskId);
        }
        map.put("list",commentList);
        return map;
    }




    /**
     * 根据登录用户获取任务列表
     */
    @RequestMapping("/queryTaskListByUserId")
    public JsonObject queryTaskListByUserId(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "15") Integer pageSize,
                                            HttpServletRequest request){
        PageHelper.startPage(page,pageSize);
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        List<Task> list=taskService.createTaskQuery()
                .processDefinitionKey("myLeave")
                .taskAssignee(user.getUsername()).list();
        List<MyTask> myList=new ArrayList<>();
        for(Task task:list){
            MyTask myTask=new MyTask();
            myTask.setProId(task.getProcessInstanceId());
            myTask.setName(task.getName());
            myTask.setCreateTime(task.getCreateTime());
            myTask.setId(task.getId());
            myList.add(myTask);
        }
        PageInfo<MyTask> pageInfo=new PageInfo<>(myList);
        JsonObject jsonObject=new JsonObject<>();
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }

    @RequestMapping("/queryLeaveInfoByProId")
    public LeaveInfo queryLeaveInfoByProId(String proId){
        return leaveService.getLeaveInfoTaskId(proId);
    }

    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody Map<String, String> map, HttpSession session){
        /*
          1 获取登录信息
          2 添加审核备注信息
          3 提交审核信息
          4 修改请假信息表状态
         */
        User user=(User) session.getAttribute("user");
        String taskId=map.get("taskId");
        String content=map.get("content");
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        Map<String, Object> variable=new HashMap<>();
        variable.put("msg","通过");
        Authentication.setAuthenticatedUserId(user.getUsername());
        String proId=task.getProcessInstanceId();
        taskService.addComment(taskId,proId,content);
        taskService.complete(taskId,variable);
        //修改请假状态
        LeaveInfo info=leaveService.getLeaveInfoTaskId(proId);
        if(info.getLeaveDays()>3&&user.getRoleName().equals("院长")){
            info.setState(2);
        }else if(info.getLeaveDays()<=3&&user.getRoleName().equals("系主任")){
            info.setState(2);
        }else {
            info.setState(1);
        }
        int num=leaveService.updateInfo(info);
        if(num>0){
            return R.ok();
        }
        return R.fail("不成功");
    }

    /**
     *  不同意请假审核（驳回申请）
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/saveInfo2")
    public R saveInfo2(@RequestBody Map<String,String> map,HttpSession session){

        /*
          1 获取登录信息
          2 添加审核备注信息
          3 提交审核信息
          4 修改请假信息表状态
          5、任务流向的处理
         */
        User user= (User) session.getAttribute("user");
        //接收前端传值信息
        String taskId=map.get("taskId");
        String content=map.get("content");
        //根据任务id查询任务信息
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        //组装参数
        Map<String,Object> variable=new HashMap<>();
        variable.put("msg","未通过");
        //获取流程实例
        String proID=task.getProcessInstanceId();
        //通过流程id获取请假信息
        LeaveInfo info=leaveService.getLeaveInfoTaskId(proID);
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        FlowNode flowNode= (FlowNode) bpmnModel.getMainProcess()
                .getFlowElement(task.getTaskDefinitionKey());
        //临时保存执行方向
        List flowList=new ArrayList();
        flowList.add(flowNode.getOutgoingFlows());
        //清理执行方向
        flowNode.getOutgoingFlows().clear();
        //需要设置是发起人的内容
        Authentication.setAuthenticatedUserId(user.getUsername());
        //添加备注
        taskService.addComment(taskId,proID,content);
        //提交任务
        taskService.complete(taskId,variable);
        //可以不恢复原始方向 不影响其他流程
        flowNode.setOutgoingFlows(flowList);
        //修改请假状态
        info.setState(3);
        int num=leaveService.updateInfo(info);
        if(num>0){
            return R.ok();
        }
        return R.fail("不成功");
    }

    /**
     * 已完成的历史信息
     */
    @RequestMapping("/hisTaskList")
    public JsonObject hisTaskList(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "15") Integer pageSize,
                                            HttpServletRequest request){
        PageHelper.startPage(page,pageSize);
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
//        List<Task> list=taskService.createTaskQuery()
//                .processDefinitionKey("myLeave")
//                .taskAssignee(user.getUsername()).list();
        //历史列表
        List<HistoricTaskInstance> list=historyService.createHistoricTaskInstanceQuery()
                .orderByHistoricTaskInstanceEndTime().asc()
                .taskAssignee(user.getUsername()).finished().list();
        List<MyTask> myList=new ArrayList<>();
        for(HistoricTaskInstance task:list){
            MyTask myTask=new MyTask();
            myTask.setProId(task.getProcessInstanceId());
            myTask.setName(task.getName());
            myTask.setCreateTime(task.getCreateTime());
            myTask.setId(task.getId());
            myList.add(myTask);
        }
        PageInfo<MyTask> pageInfo=new PageInfo<>(myList);
        JsonObject jsonObject=new JsonObject<>();
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }


}
