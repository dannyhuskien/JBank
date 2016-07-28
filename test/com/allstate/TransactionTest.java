package com.allstate;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class TransactionTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorWithAmountAndType() throws Exception {
        Transaction t1 = new Transaction(50f, TransactionType.DEPOSIT);
        assertNotNull(t1.getId());
        assertNotNull(t1.getDate());
        System.out.println("data: " + t1.getDate());
        assertEquals(50f, t1.getAmount(),0f);
        assertEquals(TransactionType.DEPOSIT, t1.getTransactionType());

    }

    @Test
    public void testToString() throws Exception{
        Transaction t1 = new Transaction(50f, TransactionType.DEPOSIT);
        assertNotNull(t1.toString());
        assertTrue(t1.toString().contains("TransactionId: "));
        assertThat(t1.toString(), CoreMatchers.containsString("Type: DEPOSIT amount: 50.0"));
    }


}
