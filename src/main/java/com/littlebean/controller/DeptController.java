package com.littlebean.controller;

import com.github.pagehelper.PageInfo;
import com.littlebean.model.DeptInfo;
import com.littlebean.model.Node;
import com.littlebean.service.DeptService;
import com.littlebean.util.JsonObject;
import com.littlebean.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/queryDeptAll")
    public JsonObject queryDeptAll(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "15") Integer pageSize,
                                   DeptInfo deptInfo){
      //  debugger;
        JsonObject jsonObject=new JsonObject();
        PageInfo<DeptInfo> pageInfo=deptService.queryDeptAll(page, pageSize, deptInfo);
        jsonObject.setMsg("ok");
        jsonObject.setCode(0);
        jsonObject.setCount(pageInfo.getTotal());
        jsonObject.setData(pageInfo.getList());
        return jsonObject;
    }

    /**
     * 添加实现
     * @param deptInfo
     * @return
     */
    @RequestMapping("/saveInfo")
    public R saveInfo(@RequestBody DeptInfo deptInfo){
        boolean b=deptService.saveInfo(deptInfo);
        if(b==true){
            return R.ok();
        }
        return R.fail("添加失败");
    }

    /**
     * 删除实现
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public R deleteById(Integer id){
        boolean b=deptService.deleteById(id);
        if(b==true){
            return R.ok();
        }
        return R.fail("删除失败");
    }
    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody DeptInfo deptInfo){
        boolean b=deptService.updateInfo(deptInfo);
        if(b==true){
            return R.ok();
        }
        return R.fail("修改失败");
    }

    @RequestMapping("/queryDeptTree")
    public List queryDeptTree(){
        return deptService.queryDeptTree();
    }
}
