package com.inspur.web.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.inspur.domain.User;
import com.inspur.exception.UserExistException;
import com.inspur.service.IUserService;
import com.inspur.service.impl.UserServiceImpl;
import com.inspur.util.WebUtils;
import com.inspur.web.formbean.RegisterFormBean;

/**
 * �����û�ע���Servlet
 * @author gacl
 *
 */
public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //���ͻ����ύ�ı����ݷ�װ��RegisterFormBean������
        RegisterFormBean formbean = WebUtils.request2Bean(request,RegisterFormBean.class);
        //У���û�ע����д�ı�����
        if (formbean.validate() == false) {//���У��ʧ��
            //����װ���û���д�ı����ݵ�formbean�����ͻ�register.jspҳ���form���н�����ʾ
            request.setAttribute("formbean", formbean);
            //У��ʧ�ܾ�˵�����û���д�ı����������⣬��ô����ת��register.jsp
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        try {
            // ע���ַ��������ڵ�ת����
            ConvertUtils.register(new DateLocaleConverter(), Date.class);
            BeanUtils.copyProperties(user, formbean);//�ѱ���������䵽javabean��
            user.setId(WebUtils.makeId());//�����û���Id����
            IUserService service = new UserServiceImpl();
            //����service���ṩ��ע���û�����ʵ���û�ע��
            service.registerUser(user);
            String message = String.format(
                    "ע��ɹ�����3���Ϊ���Զ�������¼ҳ�棡��<meta http-equiv='refresh' content='3;url=%s'/>", 
                    request.getContextPath()+"/servlet/LoginUIServlet");
            request.setAttribute("message",message);
            request.getRequestDispatcher("/message.jsp").forward(request,response);

        } catch (UserExistException e) {
            formbean.getErrors().put("userName", "ע���û��Ѵ��ڣ���");
            request.setAttribute("formbean", formbean);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // �ں�̨��¼�쳣
            request.setAttribute("message", "�Բ���ע��ʧ�ܣ���");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
