/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcd.data;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Garry Huang
 */
public class DataManagerTest {
    
    public DataManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getClassList method, of class DataManager.
     */
    @Test
    public void testGetClassList() {
        System.out.println("getClassList");
        DataManager instance = null;
        ArrayList<UMLClass> expResult = null;
        ArrayList<UMLClass> result = instance.getClassList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelected method, of class DataManager.
     */
    @Test
    public void testGetSelected() {
        System.out.println("getSelected");
        DataManager instance = null;
        UMLClass expResult = null;
        UMLClass result = instance.getSelected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUMLClass method, of class DataManager.
     */
    @Test
    public void testCreateUMLClass() {
        System.out.println("createUMLClass");
        double x = 0.0;
        double y = 0.0;
        DataManager instance = null;
        instance.createUMLClass(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeClass method, of class DataManager.
     */
    @Test
    public void testMakeClass() {
        System.out.println("makeClass");
        String cName = "";
        String pName = "";
        String cNameText = "";
        String vNameText = "";
        String mNameText = "";
        double initX = 0.0;
        double initY = 0.0;
        DataManager instance = null;
        instance.makeClass(cName, pName, cNameText, vNameText, mNameText, initX, initY);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class DataManager.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        DataManager instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
