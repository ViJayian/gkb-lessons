package org.geektimes.jmx;


/**
 * jmx
 * 类名以MXBean为后缀
 *
 * @author ViJay
 * @date 2021/3/14 23:33
 */
public interface UserManagerMXBean {
    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getPassword();

    public void setPassword(String password);

    public String getEmail();

    public void setEmail(String email);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);
}
