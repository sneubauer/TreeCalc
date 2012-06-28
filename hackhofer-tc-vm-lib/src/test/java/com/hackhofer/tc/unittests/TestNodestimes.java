package com.hackhofer.tc.unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackhofer.tc.rt.S;
import com.hackhofer.tc.vm.TciMachine;
import com.hackhofer.tc.vm.asm.TciAsmReaderWriter;

public class TestNodestimes {
	public TestNodestimes() {
		try {
			_s = new TciMachine(TciAsmReaderWriter.read("./src/test/java/gen/testnodestimes/gen.testnodestimes.tcx"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private S _s;
	
	@Before 
	public void initialize() {
	}

	@After
	public void cleanup() {
		_s.reset();
	}
	
	@Test
	public void test1() {
		_s.setInput("I_counter", "0");
		assertEquals("1", _s.calculateMaintree("P_Test"));
		assertEquals("(1)", _s.calculateMaintree("P_Test_collx"));
	}
	
	@Test
	public void test2() {
		_s.setInput("I_counter", "1");
		assertEquals("2", _s.calculateMaintree("P_Test"));
		assertEquals("(1!1)", _s.calculateMaintree("P_Test_collx"));
	}
	
	@Test
	public void test3() {
		_s.setInput("I_counter", "100");
		assertEquals("101", _s.calculateMaintree("P_Test"));
	}
	
	@Test
	public void test4() {
		_s.setInput("I_counter", "1");
		_s.setInput("I_counter2", "0");
		assertEquals("2", _s.calculateMaintree("P_Test"));
		assertEquals("1", _s.calculateMaintree("P_Test2"));
		assertEquals("0", _s.calculateMaintree("P_Test2Index"));
		assertEquals("(0)", _s.calculateMaintree("P_Test2Index_collx"));
		assertEquals("0", _s.calculateMaintree("P_Test2Compute"));
	}
	@Test
	public void test5() {
		_s.setInput("I_counter", "1");
		_s.setInput("I_counter2", "1");
		assertEquals("2", _s.calculateMaintree("P_Test"));
		assertEquals("2", _s.calculateMaintree("P_Test2"));
		assertEquals("0", _s.calculateMaintree("P_Test2Index"));
		assertEquals("(0!0)", _s.calculateMaintree("P_Test2Index_collx"));
		assertEquals("1", _s.calculateMaintree("P_Test2Compute"));
	}
	@Test
	public void test6() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "1");
		assertEquals("3", _s.calculateMaintree("P_Test"));
		assertEquals("2", _s.calculateMaintree("P_Test2"));
		assertEquals("0", _s.calculateMaintree("P_Test2Index"));
		assertEquals("(0!0)", _s.calculateMaintree("P_Test2Index_collx"));
		assertEquals("1", _s.calculateMaintree("P_Test2Compute"));
	}
	@Test
	public void test7() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "3");
		assertEquals("3", _s.calculateMaintree("P_Test"));
		assertEquals("4", _s.calculateMaintree("P_Test2"));
		assertEquals("3", _s.calculateMaintree("P_Test2Index"));
		assertEquals("(0!0!1!2)", _s.calculateMaintree("P_Test2Index_collx"));
		assertEquals("1", _s.calculateMaintree("P_Test2Index_3rd"));
		assertEquals("7", _s.calculateMaintree("P_Test2Compute"));
	}
	@Test
	public void test8() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "3");
		_s.setInput("A_LI_Riders", "0");
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(0)"));
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(1)"));
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(2)"));
	}
	@Test
	public void test9() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "3");
		_s.setInput("A_LI_Riders", "1");
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(0)"));
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(1)"));
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(2)"));
	}
	@Test
	public void test10() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "3");
		_s.setInput("A_LI_Riders", "2");
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(0)"));
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(1)"));
		assertEquals("0", _s.calculateMaintree("PX_LI_RiderPremiumYear(2)"));
	}
	@Test
	public void test11() {
		_s.setInput("I_counter", "2");
		_s.setInput("I_counter2", "3");
		_s.setInput("A_LI_Riders", "3");
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(0)"));
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(1)"));
		assertEquals("70", _s.calculateMaintree("PX_LI_RiderPremiumYear(2)"));
	}
}
