package com.inspur.service.impl;

import com.inspur.dao.IUserDao;
import com.inspur.dao.impl.UserDaoImpl;
import com.inspur.domain.User;
import com.inspur.exception.UserExistException;
import com.inspur.service.IUserService;



public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();
    
    @Override
    public void registerUser(User user) throws UserExistException {
        if (userDao.find(user.getUserName())!=null) {
            //checked exception 
            //unchecked exception
            //�����ױ���ʱ�쳣��ԭ����������һ�����������쳣���Ը��û�һ���Ѻ���ʾ
            throw new UserExistException("ע����û����Ѵ��ڣ�����");
        }
        userDao.add(user);
    }

    @Override
    public User loginUser(String userName, String userPwd) {
        return userDao.find(userName, userPwd);
    }



}