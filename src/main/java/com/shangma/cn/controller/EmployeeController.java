package com.shangma.cn.controller;

import com.github.pagehelper.PageHelper;
import com.shangma.cn.entity.Employee;
import com.shangma.cn.http.AxiosResult;
import com.shangma.cn.http.PageVo;
import com.shangma.cn.mapper.EmployeeMapper;
import com.shangma.cn.service.AsyncMailSender;
import com.shangma.cn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AsyncMailSender asyncMailSender;
    @GetMapping
    public AxiosResult getAll(@RequestParam(defaultValue = "1") int currentPage,
                         @RequestParam(defaultValue = "5") int pageSize){
        PageHelper.startPage(currentPage,pageSize);
        return AxiosResult.success(employeeService.getAllEmployee());
    }

    @GetMapping("{id}")
    public AxiosResult getOne(@PathVariable long id){
        return AxiosResult.success(employeeService.getEmpBID(id));
    }

    @PostMapping
    public AxiosResult addEmp(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return AxiosResult.success();
    }

    @PutMapping
    public AxiosResult updEmp(@RequestBody Employee employee){
        employeeService.updEmp(employee);
        return AxiosResult.success();
    }

    @DeleteMapping("{id}")
    public AxiosResult delEmp(@PathVariable long id){
        employeeService.delEmpBID(id);
        return AxiosResult.success();
    }

    @GetMapping("login")
    public AxiosResult doLogin(String email, String password, HttpSession session){
        String s = stringRedisTemplate.opsForValue().get(email);
        if(password.equalsIgnoreCase(s)){
            session.setAttribute("user","user");
            Employee empBID = employeeService.getEmpBID(12);
            return AxiosResult.success(empBID);
        }
        return AxiosResult.error();
    }

    /**
     * ssm发邮件步骤
     * 1.开通smtp pop3
     * 2.导入spring suppose和javaxmail
     * 3.注入bean javaMailSender（配置服务器、用户名、密码）
     * 在springboot中只需要导入起步依赖，然后在spring.mail中配置服务器用户名密码即可 最后引入javaMailSenderImpl
     *
     * ssm redis使用步骤
     * 1.导入spring-data-Redis  jedis slf4j
     * 2.注入bean（配置JedisConnectionFactory然后在StringRedisTemplate中注入)
     * springboot中导入起步依赖就可以直接使用stringRedisTemplate
     *
     *
     * ssm中的异步任务
     * 1.在service层创建异步工具类,并加入到容器中
     * 2.配置异步任务<task:annotation-driven/>
     * 3.在方法上加上@Async
     * springboot的异步任务，直接在MyApplication中添加注解@EnableAsync即可
     */
    @GetMapping("sendEmail")
    public AxiosResult sendEmail(String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("阿里嘎巴麻花疼<a1293233730@163.com>");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("登入验证码");
        //获得100000到999999的随机数
        int sixNum = (int) (Math.random()*(999999-100000+1)+100000);
        simpleMailMessage.setText("您的验证码(2分钟失效):"+String.valueOf(sixNum));
        log.info(Thread.currentThread().getName());
        asyncMailSender.senderEmail(simpleMailMessage);
        //往redis设值，2分钟失效
        stringRedisTemplate.opsForValue().set(email,String.valueOf(sixNum),2, TimeUnit.MINUTES);
        return AxiosResult.success();
    }
}
