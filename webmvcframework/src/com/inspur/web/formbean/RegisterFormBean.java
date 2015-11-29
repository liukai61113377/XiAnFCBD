package com.inspur.web.formbean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

/**
 * ��װ���û�ע���bean����������register.jsp�еı��������ֵ
 * RegisterFormBean�е�������register.jsp�еı��������nameһһ��Ӧ
 * RegisterFormBean��ְ����˸������register.jsp�еı��������ֵ֮�⻹������У����������ֵ�ĺϷ���
 * @author gacl
 *
 */
public class RegisterFormBean {

    //RegisterFormBean�е�������register.jsp�еı��������nameһһ��Ӧ
    //<input type="text" name="userName"/>
    private String userName;
    //<input type="password" name="userPwd"/>
    private String userPwd;
    //<input type="password" name="confirmPwd"/>
    private String confirmPwd;
    //<input type="text" name="email"/>
    private String email;
    //<input type="text" name="birthday"/>
    private String birthday;

    
    /**
     * �洢У�鲻ͨ��ʱ���û��Ĵ�����ʾ��Ϣ
     */
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /*
     * validate��������У���������
     * ��������У�����
     *         private String userName; �û�������Ϊ�գ�����Ҫ��3-8����ĸ abcdABcd 
     *         private String userPwd; ���벻��Ϊ�գ�����Ҫ��3-8������
     *         private String confirmPwd; ��������Ҫһ��
     *         private String email; ����Ϊ�գ���Ϊ��Ҫ��һ���Ϸ������� 
     *         private String birthday; ����Ϊ�գ���Ϊ��ʱ��Ҫ��һ���Ϸ�������
     */
    public boolean validate() {

        boolean isOk = true;

        if (this.userName == null || this.userName.trim().equals("")) {
            isOk = false;
            errors.put("userName", "�û�������Ϊ�գ���");
        } else {
            if (!this.userName.matches("[a-zA-Z]{3,8}")) {
                isOk = false;
                errors.put("userName", "�û���������3-8λ����ĸ����");
            }
        }

        if (this.userPwd == null || this.userPwd.trim().equals("")) {
            isOk = false;
            errors.put("userPwd", "���벻��Ϊ�գ���");
        } else {
            if (!this.userPwd.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("userPwd", "���������3-8λ�����֣���");
            }
        }

        // private String password2; ��������Ҫһ��
        if (this.confirmPwd != null) {
            if (!this.confirmPwd.equals(this.userPwd)) {
                isOk = false;
                errors.put("confirmPwd", "�������벻һ�£���");
            }
        }

        // private String email; ����Ϊ�գ���Ϊ��Ҫ��һ���Ϸ�������
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "���䲻��һ���Ϸ����䣡��");
            }
        }

        // private String birthday; ����Ϊ�գ���Ϊ��ʱ��Ҫ��һ���Ϸ�������
        if (this.birthday != null && !this.birthday.trim().equals("")) {
            try {
                DateLocaleConverter conver = new DateLocaleConverter();
                conver.convert(this.birthday);
            } catch (Exception e) {
                isOk = false;
                errors.put("birthday", "���ձ���Ҫ��һ�����ڣ���");
            }
        }

        return isOk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
