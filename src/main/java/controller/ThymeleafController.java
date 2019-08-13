package controller;

import entity.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pojo.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafController {
    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("resource",resource);
        return "thymeleaf/index";
    }
    @RequestMapping("center")
    public String center() {
        return "thymeleaf/center/center";
    }

    @RequestMapping("test")
    public String test(ModelMap map){
        UserTest userTest1 =new UserTest();
        userTest1.setId("01");
        userTest1.setAge(18);
        userTest1.setBirthday(new Date());
        userTest1.setPassword("123");
        userTest1.setUsername("王淑芳");

        UserTest u2 = new UserTest();
        u2.setAge(17);
        u2.setUsername("李建军");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        UserTest u3 = new UserTest();
        u3.setAge(17);
        u3.setUsername("张铁柱");
        u3.setPassword("123456");
        u3.setBirthday(new Date());
        u3.setDesc("<font color='green'><b>hello imooc</b></font>");


        List<UserTest> userList = new ArrayList<>();
        userList.add(u3);
        userList.add(userTest1);
        userList.add(u2);
        map.addAttribute("user1",userTest1);
        map.addAttribute("u2",u2);
        map.addAttribute("u3",u3);
        map.addAttribute("userList",userList);
        return "thymeleaf/test";
    }
}
