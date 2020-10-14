package com.jsw.masterslaverdb.service;

import com.jsw.masterslaverdb.annotations.DynamicDB;
import com.jsw.masterslaverdb.constant.DataSources;
import com.jsw.masterslaverdb.dao.UserMapper;
import com.jsw.masterslaverdb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @DynamicDB(value = DataSources.MASTER_DB)
    public User queryById(String id){
        return userMapper.selectByPrimaryKey(id);
    }

    @DynamicDB(value = DataSources.SLAVE_DB)
    public User findById(String id){
        return userMapper.selectByPrimaryKey(id);
    }
}
