package com.inspur.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author gacl
 * Ϊ�û��ṩע����û������Servlet
 * RegisterUIServlet����Ϊ�û����ע�����
 * ���û�����RegisterUIServletʱ������ת��WEB-INF/pagesĿ¼�µ�register.jspҳ��
 */
public class RegisterUIServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
