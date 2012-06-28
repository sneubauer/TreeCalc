package com.hackhofer.tc.unittests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackhofer.tc.rt.S;
import com.hackhofer.tc.vm.TciMachine;
import com.hackhofer.tc.vm.asm.TciAsmReaderWriter;

public class TestNodes {
	public TestNodes() {
		try {
			_s = new TciMachine(TciAsmReaderWriter.read("./src/test/java/gen/testnodes/gen.testnodes.tcx"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
/*
	TABLE tab01 (key, value) {
		1, a;
		2, b;
		3, c;
	}
	=> direct access
*/
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
		assertEquals("01", _s.calculateMaintree("P_Single"));
	}
	@Test
	public void test2() {
		assertEquals("1", _s.calculateMaintree("P_Sum1"));
	}
	@Test
	public void test3() {
		assertEquals("7", _s.calculateMaintree("P_Sum123"));
	}
	@Test
	public void test4() {
		assertEquals("27", _s.calculateMaintree("P_Sum123_"));
	}
	@Test
	public void test5() {
		assertEquals("3", _s.calculateMaintree("P_Sum12"));
	}
	@Test
	public void test6() {
		assertEquals("4", _s.calculateMaintree("P_Sum3"));
	}
	@Test
	public void test7() {
		assertEquals("24", _s.calculateMaintree("P_Sum3_"));
	}
	@Test
	public void test8() {
		_s.setInput("I_INCLUDE_LEVEL1", "0");
		_s.setInput("I_include_level2", "0");
		assertEquals("0", _s.calculateMaintree("P_optional"));
	}
	@Test
	public void test9() {
		_s.setInput("I_include_level1", "1");
		_s.setInput("I_include_level2", "0");
		assertEquals("1", _s.calculateMaintree("P_optional"));
	}
	@Test
	public void test10() {
		_s.setInput("I_include_level1", "0");
		_s.setInput("I_include_level2", "1");
		assertEquals("0", _s.calculateMaintree("P_optional"));
	}
	@Test
	public void test11() {
		_s.setInput("I_include_level1", "1");
		_s.setInput("I_include_level2", "1");
		assertEquals("3", _s.calculateMaintree("P_optional"));
	}
	@Test
	public void test12() {
		_s.setInput("I_include_level1", "1");
		_s.setInput("I_include_level2", "1");
		assertEquals("(01)", _s.calculateMaintree("P_Single_collx"));
		assertEquals("(1)", _s.calculateMaintree("P_Sub1_collx"));
		assertEquals("(2)", _s.calculateMaintree("P_Sub2_collx"));
		assertEquals("(1!2!4)", _s.calculateMaintree("P_Sub123_collx"));
		assertEquals("1", _s.calculateMaintree("P_Sub123_first"));
		assertEquals("2", _s.calculateMaintree("P_Sub123_second"));
		assertEquals("(1!2!8!16)", _s.calculateMaintree("P_Sub123__collx"));
		assertEquals("(1!2)", _s.calculateMaintree("P_Sub12_collx"));
		assertEquals("(4)", _s.calculateMaintree("P_Sub3_collx"));
		assertEquals("(8!16)", _s.calculateMaintree("P_Sub3__collx"));
		assertEquals("01", _s.calculateMaintree("P_Single_first"));
		assertEquals("1", _s.calculateMaintree("P_TestFunny(1)"));
		assertEquals("5", _s.calculateMaintree("P_TestFunny(5)"));
	}
	@Test
	public void test13() {
		_s.setInput("I_include_level1", "1");
		_s.setInput("I_include_level2", "1");
		assertEquals("n", _s.calculate("P_n"));
		assertEquals("z", _s.calculate("P_z"));
		assertEquals("120", _s.calculate("P_Fac(5)"));
		assertEquals("1", _s.calculate("P_zsub"));
	}
}
