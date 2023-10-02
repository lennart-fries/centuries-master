package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserFormTest {

    @Test
    public void getUsername() {
        UserForm uf = new UserForm("a","a","a");
        assertTrue(uf.getUsername() == "a");
    }

    @Test
    public void setUsername() {
        UserForm uf = new UserForm("a","a","a");
        uf.setUsername("b");
        assertFalse(uf.getUsername() == "a");
        assertTrue(uf.getUsername() == "b");
    }

    @Test
    public void getPassword() {
        UserForm uf = new UserForm("a","a","a");
        assertTrue(uf.getPassword() == "a");
    }

    @Test
    public void setPassword() {
        UserForm uf = new UserForm("a","a","a");
        uf.setPassword("b");
        assertFalse(uf.getPassword() == "a");
        assertTrue(uf.getPassword() == "b");
    }

    @Test
    public void getEmail() {
        UserForm uf = new UserForm("a","a","a");
        assertTrue(uf.getEmail() == "a");
    }
}