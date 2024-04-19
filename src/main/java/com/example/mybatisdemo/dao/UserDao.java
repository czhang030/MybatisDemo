package com.example.mybatisdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisdemo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
