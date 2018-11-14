package com.example.demo.service.impl;

import com.example.demo.dao.AyUserDao;
import com.example.demo.model.AyUser;
import com.example.demo.repository.AyUserRepository;
import com.example.demo.service.AyUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午4:04
 * @since JDK 1.8
 */
@Log4j2
@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    @Autowired
    AyUserRepository ayUserRepository;

    @Autowired
    RedisTemplate redisTemplate;

    @Resource
    AyUserDao ayUserDao;

    public static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public AyUser findById(Integer id) {
        //查询redis缓存找那个的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (ayUserList != null) {
            List<AyUser> ayUsers = ayUserList.stream().filter(ayUser -> id.equals(ayUser.getId())).collect(Collectors.toList());
            if (ayUsers.size() > 0) {
                return ayUsers.get(0);
            }
        }

        //查询数据库
        Optional optional = ayUserRepository.findById(id);
        if (optional.hashCode() != 0) {
            AyUser ayUser = ayUserRepository.findById(id).get();
            if (ayUser != null) {
                //step3:将数据插入到redis
                redisTemplate.opsForList().leftPush(ALL_USER, ayUser);
            }
            return ayUser;
        }
        return null;
    }

    @Override
    public List<AyUser> findAll() {
        return ayUserRepository.findAll();
    }

    @Override
    public AyUser save(AyUser ayUser) {
        AyUser ayUser_save = ayUserRepository.save(ayUser);
        //出现空指针异常
        String error = null;
        error.split("/");
        return ayUser_save;
    }

    @Override
    public void delete(Integer id) {
        ayUserRepository.deleteById(id);
        log.info("userId:" + id + "用户被删除了");
    }

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll(pageable);
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(Collection<Integer> ids) {
        return ayUserRepository.findByIdIn(ids);
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name, password);
    }
}
