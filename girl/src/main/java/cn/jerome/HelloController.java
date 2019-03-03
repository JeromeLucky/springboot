package cn.jerome;

import cn.jerome.account.entity.BathResignReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cn.jerome.account.entity.User;

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
    private  BoyProperties boyProperties;
    @RequestMapping(value = "/test1/{name}",method = RequestMethod.GET)
    public  String say(@PathVariable("name") String aa){

        return "hello springBoot         "+selfParam+"message       "+message+"*******"+aa;
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String boyTest(){
        StringBuffer hibateSb = new StringBuffer();
        for (String aa:boyProperties.getHabit()){
            hibateSb.append(aa);
        }
        return boyProperties.getHeight()+"---------"+boyProperties.getWeight()+"habit"+hibateSb.toString();
    }
    @RequestMapping(path = "/test3",method = RequestMethod.POST)
    public String test3(@RequestParam(name = "account",required = true) String acct,@RequestParam(name = "password",required = true) String pwd)
    {
        return  "account**"+acct+"pwd###"+ pwd;
    }
    @ModelAttribute("acct1")
    @RequestMapping(path = "/test4",method = RequestMethod.POST)
    public String test4(Model model,@RequestBody User req){
        model.addAttribute("acct2",req);
        return "success";
    }
    @RequestMapping(path = "/test5",method = RequestMethod.POST)
    public String test4(@ModelAttribute("acct1") @RequestBody User req){

        return "success";
    }
    @RequestMapping(path = "/bathResigin",method = RequestMethod.POST)
    public String test5(@RequestBody BathResignReq req){
        StringBuffer acctIdsb = new StringBuffer();
        for(User user : req.getAcctArray()){
            acctIdsb.append(user.getUserId()+"");
        }
        return  req.getOrderId()+"resign accid"+acctIdsb.toString();
    }
 }
