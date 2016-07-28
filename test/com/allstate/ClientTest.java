package com.allstate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testClientConstructor() throws Exception {
        Client c = new Client("John");
        assertNotNull(c.getId());
        assertEquals("John", c.getName());
        assertTrue(c.getIsActive());
        assertEquals(0, c.getAccounts().size());

    }

    @Test
    public void testClientOpenAccount() throws Exception {
        Client c = new Client("John");
        c.openAccount(AccountType.CHECKING);
        assertEquals(1, c.getAccounts().size());

    }

    @Test
    public void testClientCloseAccount() throws Exception {
        Client c = new Client("John");
        c.openAccount(AccountType.CHECKING);
        c.getAccounts().get(0).deposit(100f);
        c.openAccount(AccountType.SAVINGS);
        assertEquals(2, c.getAccounts().size());
        c.closeAccount(c.getAccounts().get(0).getId());
        assertTrue(c.getAccounts().get(0).getIsClosed());
        assertEquals(0f, c.getAccounts().get(0).getBalance(), 0f);
    }

}