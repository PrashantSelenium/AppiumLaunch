package com.android.qtpselenium.AppiumMavenTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.TestNG;

@Test
public class testclass {
	@Test
	public void testAdd(){
		String str = "TestNG is working fine";
		Assert.assertEquals("TestNG is working fine", str);
	}
}
