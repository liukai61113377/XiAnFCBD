package com.inspur.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inspur.domain.User;
import com.inspur.service.IUserService;
import com.inspur.service.impl.UserServiceImpl;

/**
 * �����û���¼��servlet
 * @author gacl
 *
 */
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //��ȡ�û���д�ĵ�¼�û���
        String username = request.getParameter("username");
        //��ȡ�û���д�ĵ�¼����
        String password = request.getParameter("password");
        
        IUserService service = new UserServiceImpl();
        //�û���¼
        User user = service.loginUser(username, password);
        if(user==null){
            String message = String.format(
                    "�Բ����û������������󣡣������µ�¼��2���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv='refresh' content='2;url=%s'", 
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //��¼�ɹ��󣬾ͽ��û��洢��session��
        request.getSession().setAttribute("user", user);
        String message = String.format(
                "��ϲ��%s,��½�ɹ�����ҳ����3���������ҳ����<meta http-equiv='refresh' content='3;url=%s'", 
                user.getUserName(),
                request.getContextPath()+"/index.jsp");
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
