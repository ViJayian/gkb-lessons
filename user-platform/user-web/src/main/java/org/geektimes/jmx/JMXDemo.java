package org.geektimes.jmx;

import org.geektimes.projects.user.domain.User;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author ViJay
 * @date 2021/3/14 23:44
 */
public class JMXDemo {
    public static void main(String[] args) throws Exception {
        //获取平台MBean Server
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        //命名
        ObjectName objectName = new ObjectName("org.geektimes.jmx:type=User");

        User user = new User();
        server.registerMBean(createUserMBean(user),objectName);

        while (true) {
            Thread.sleep(2000);
            System.out.println(user);
        }
    }

    public static Object createUserMBean(User user) {
        return new UserManager(user);
    }
}
