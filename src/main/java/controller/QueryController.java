package controller;

import entity.UserTest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.JSONResult;
import pojo.Resource;

@RestController
public class QueryController {

    @Autowired
    private Resource resource;

    @RequestMapping("/hello")
    public String toString() {

        return "Hello 小鸣";
    }

    @RequestMapping("/getUser")
    public JSONResult getUser() {
        UserTest userTest=new UserTest();
        userTest.setId("001");
        userTest.setAge(20);
        userTest.setUsername("张小鸣");
        userTest.setPassword("保密");
        return JSONResult.responseData(userTest);
    }

    @RequestMapping("/getResource")
    public JSONResult getResource(){
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource,bean);
        return JSONResult.responseData(bean);
    }

}
