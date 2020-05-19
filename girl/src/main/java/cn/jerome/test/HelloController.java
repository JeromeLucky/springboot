package cn.jerome.test;

import cn.jerome.module.account.entity.AccountUser;
import cn.jerome.module.account.entity.AccoutQueryCons;
import cn.jerome.module.account.entity.BathResignReq;

import cn.jerome.module.account.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${selfParam}")
    private String selfParam;
    @Value("${age}")
    private int age;
    @Value("${content}")
    private String message;

    @Autowired
    private IUserService userService;

    @Autowired
    private  BoyProperties boyProperties;

    //restful url取参数

    @RequestMapping(value = "/test1/{name}",method = RequestMethod.GET)

    public  String say(@PathVariable("name") String aa){
        return "hello springBoot         "+selfParam+"message       "+message+"*******"+aa;
    }

    //测试从boy参数内取值 boyProperties 参数类
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String boyTest(){
        StringBuffer hibateSb = new StringBuffer();
        for (String aa:boyProperties.getHabit()){
            hibateSb.append(aa);
        }
        return boyProperties.getHeight()+"---------"+boyProperties.getWeight()+"habit"+hibateSb.toString();
    }
    //传递指定参数 newName userId ?newName="Jerome"&userId=101
    @RequestMapping(path = "/updateName",method = RequestMethod.POST)
    public int updateName (@RequestParam(name = "newName",required = true) String newName,@RequestParam(name = "userId",required = true) Long userId)
    {
        return  userService.updateName(newName,userId);
    }
    //将请求的参数存入model

    @RequestMapping(path = "/test4",method = RequestMethod.POST)
    public String test4(Model model,@RequestBody AccountUser req){
        model.addAttribute("acct2",req);
        return "thymeleaf";
    }

    @RequestMapping(path = "/test5",method = RequestMethod.POST)
    public String test4(@ModelAttribute("acct1") @RequestBody AccountUser req){

        return "success";
    }
    //post请求 参数为对象BathResignReq
    @RequestMapping(path = "/bathResigin",method = RequestMethod.POST)
    public List<AccountUser> test5(@RequestBody BathResignReq req){
        StringBuffer acctIdsb = new StringBuffer();
        for(AccountUser accountUser : req.getAcctArray()){
            acctIdsb.append(accountUser.getUserId()+"");
        }
        System.out.println(req.getOrderId()+"resign accid"+acctIdsb.toString());

        List<AccountUser> lists = userService.saveAll(req.getAcctArray());
        return lists;
    }

    //调用userService 进行dao操作
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public  AccountUser addUser (@RequestBody AccountUser accountUser){

       AccountUser user = (AccountUser) userService.saveAcctuser(accountUser);
       return  user;
    }

    //分页查询加排序
    @RequestMapping(value = "/findbypage",method = RequestMethod.POST)
    public  Page<AccountUser> findbypage (@RequestBody AccoutQueryCons queryCons){
        Page<AccountUser> users = userService.findByPage(queryCons);
        return  users;
    }

}
