package xiaolingUseJava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import xiaolingUseJava.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xiaolingUseJava.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/xiaolingUseJava-rabbitmq/")
public class RabbitMQWebController {

    private RabbitMQSender rabbitMQSender;

    @Autowired
   public RabbitMQWebController(RabbitMQSender rabbitMQSender){
       this.rabbitMQSender = rabbitMQSender;
   }

   @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId){
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);
        return "Message sent to the RabbitMQ XiaolingUseJava Successfully";
    }


}