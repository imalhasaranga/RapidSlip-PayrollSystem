/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Gihan
 */
public class DbProperty implements Serializable{
    private String host;
    private String port;
    
    private String driver = "com.mysql.jdbc.Driver";
    private String username;
    private String password;
    private String mysqlpath = "C:/Program Files/MySQL/MySQL Server 4.1/bin";

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the mysqlpath
     */
    public String getMysqlpath() {
        return mysqlpath;
    }

    /**
     * @param mysqlpath the mysqlpath to set
     */
    public void setMysqlpath(String mysqlpath) {
        this.mysqlpath = mysqlpath;
    }
    
    
    
    
    
}
