package com.allstate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class BankTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testBankConstructor() throws  Exception {
        Bank b = new Bank("Chase");
        assertNotNull(b.getId());
        assertEquals("Chase", b.getName());
        assertEquals(0, b.getClients().size());
    }

    @Test
    public void testAddClient() throws Exception {
        Bank b = new Bank("Chase");
        b.addClient("Bobby");
        assertEquals(1, b.getClients().size());

    }

    @Test
    public void testRemoveClient() throws Exception {
        Bank b = new Bank("Chase");
        b.addClient("Bobby");
        b.getClients().get(0).openAccount(AccountType.CHECKING);
        b.getClients().get(0).getAccounts().get(0).deposit(100f);
        b.getClients().get(0).getAccounts().get(0).withdrawal(50f);
        b.getClients().get(0).openAccount(AccountType.SAVINGS);
        b.getClients().get(0).getAccounts().get(1).deposit(100f);
        assertEquals(1, b.getClients().size());
        b.addClient("Joe");
        b.removeClient(b.getClients().get(0).getId());
        assertEquals(2, b.getClients().size());
        assertFalse(b.getClients().get(0).getIsActive());


    }

}