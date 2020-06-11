package com.mycompany.ejb.client;

import com.mycompany.ejb.UserBean;
import com.mycompany.ejb.entity.User;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Test {

    private static InitialContext context;

    static {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "remote+http://localhost:8080");

            context = new InitialContext(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            UserBean bean = (UserBean) context.lookup("ejb:ear-application/ejb-service/UserBean!com.mycompany.ejb.UserBean");

            User user = new User();

            user.setUsername("user");
            user.setPassword("123qwe");
            user.setRole(User.Role.ADMIN);

            user = bean.persist(user);

            System.out.println(user.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
