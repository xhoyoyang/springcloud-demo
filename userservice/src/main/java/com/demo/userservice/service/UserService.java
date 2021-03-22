package com.demo.userservice.service;

import com.demo.userservice.dao.UserDao;
import com.demo.vo.user.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserService {

    private final ObjectMapper mapper = new ObjectMapper();

    private  ExecutorService service = Executors.newFixedThreadPool(2);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private UserDao userDao;

    @Transactional
    public String createUser()throws Exception{
        UserVo userVo = new UserVo();
        userVo.setName(UUID.randomUUID().toString().toLowerCase(Locale.ROOT).replaceAll("-",""));
        userVo.setAge(new Random().nextInt(100));
        this.userDao.createUser(userVo);
        //System.out.println(String.format("新增用户：%s 成功",mapper.writeValueAsString(userVo)));
        return mapper.writeValueAsString(userVo);
    }

    public void urerList() throws Exception{

        Integer id = 1;
        List<UserVo> list = new ArrayList<>(100);
        while (id>0){
            list = this.userDao.getAlluserList(id);
            //System.out.println(new Date()+":"+list.size());
            list.forEach(item->{
                try {
                    service.submit(new kafkaTask(kafkaTemplate,mapper.writeValueAsString(item)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
            if(list.size()>0){
                id = list.get(list.size()-1).getId();
                list.removeAll(list);
            }else{
                id = 0 ;
            }
        }
        System.out.println("查询结束");
    }
    
    class kafkaTask implements Runnable{

        private String msg;
        private KafkaTemplate<String,String> kafkaTemplate;
        
        public kafkaTask(KafkaTemplate<String,String> kafkaTemplate,String  msg){
            this.kafkaTemplate = kafkaTemplate;
            this.msg=msg;
        }
        
        @Override
        public void run() {
            System.out.println(new Date()+"\t"+Thread.currentThread().getName()+"\t"+Thread.currentThread().getId()+"\t"+msg);
            kafkaTemplate.send("topic1",msg);
        }
    }




}
