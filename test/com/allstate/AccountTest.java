package com.allstate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class AccountTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorWithType() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        assertEquals(AccountType.CHECKING, a1.getType());
        assertEquals(0f,a1.getBalance(), 0f);
        assertNotNull(a1.getId());
        assertFalse(a1.getIsClosed());
        assertEquals(0, a1.getTransactions().size());

    }

    @Test
    public void testOneWithdrawal() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.withdrawal(100);
        assertEquals(Account.overDraftFee, a1.getBalance(), 0f);
        assertFalse(a1.getIsClosed());
        assertEquals(1, a1.getTransactions().size());
        assertEquals(TransactionType.FEE, a1.getTransactions().get(0).getTransactionType());

    }

    @Test
    public void testWithdrawal3TimesCloseAccount() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.withdrawal(100);
        assertEquals(Account.overDraftFee, a1.getBalance(), 0f);
        assertFalse(a1.getIsClosed());
        a1.withdrawal(100);
        assertEquals(Account.overDraftFee * 2, a1.getBalance(), 0f);
        assertFalse(a1.getIsClosed());
        a1.withdrawal(100);
        assertEquals(Account.overDraftFee * 3, a1.getBalance(), 0f);
        assertTrue(a1.getIsClosed());
        assertEquals(3, a1.getTransactions().size());

    }

    @Test
    public void testDepositIfAccountNotClosed() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        assertEquals(100f, a1.getBalance(), 0f);
        assertFalse(a1.getIsClosed());
        assertEquals(1, a1.getTransactions().size());
        a1.withdrawal(25f);
        assertEquals(75f, a1.getBalance(), 0f);
        assertFalse(a1.getIsClosed());
        assertEquals(2, a1.getTransactions().size());

    }

    @Test
    public void testFilterTransactions() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.deposit(200f);
        a1.withdrawal(30f);
        a1.withdrawal(40f);
        assertEquals(230f, a1.getBalance(), 0f);
        assertEquals(2, a1.filterTransactions(TransactionType.DEPOSIT).size());
        assertEquals(2, a1.filterTransactions(TransactionType.WITHDRAWAL).size());
        assertEquals(0, a1.filterTransactions(TransactionType.FEE).size());

    }
    @Test
    public void testCloseAccount() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.closeAccount();
        assertEquals(0f, a1.getBalance(), 0f);
        assertTrue(a1.getIsClosed());
    }

}