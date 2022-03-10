package com.example.androidlearn.json;

import static org.junit.Assert.*;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:52 2022/3/8
 */
public class GsonTestTest extends TestCase {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGson(){
        String json=GsonTest.generate();
        String json1 = GsonTest.generate1();
        GsonTest.main(null);
    }
}