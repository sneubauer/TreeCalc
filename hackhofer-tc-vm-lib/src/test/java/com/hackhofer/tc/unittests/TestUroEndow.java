package com.hackhofer.tc.unittests;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.hackhofer.tc.rt.S;
import com.hackhofer.tc.rt.V;
import com.hackhofer.tc.vm.TciMachine;
import com.hackhofer.tc.vm.asm.TciAsmReaderWriter;
import com.hackhofer.tc.vm.asm.TciAssembler.Asm;
import com.hackhofer.tc.vm.asm.TciDisassembler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUroEndow {
	public TestUroEndow() {
		try {
			Asm asm = TciAsmReaderWriter.read("./src/test/java/gen/uroliendow/gen.uroliendow.tcx");
			/* disassemble and write */
			PrintStream disout = new PrintStream("./src/test/java/gen/uroliendow/gen.uroliendow.tci.dis");
			TciDisassembler.print(asm, disout, false);
			disout.close();
			/* create instance of virtual machine */
			_s = new TciMachine(asm);
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
		List<String[]> list;
		_s.setInput("A_LI_Product", "6100");
		_s.setInput("A_LI_Language", "EN");
		list = _s.getInputList("A_LI_Language");
		assertArrayEquals(new String[][] { 
				{"EN", "English"}, 
				{"RO", "Romanian"}},
			list.toArray());
		list = _s.getInputList("A_LI_Product");
		assertArrayEquals(new String[][] { 
				{"6100", "Endowment UNIQA"}, 
				{"6200", "Endowment Raiffeisen"}},
			list.toArray());
		
		list = _s.getInputList("A_LI_PaymentFrequency");
		assertArrayEquals(new String[][] { 
				{"1", "yearly"}, 
				{"2", "half-yearly"},
				{"4", "quaterly"},
				{"12", "monthly"},
				},
			list.toArray());

		list = _s.getInputList("A_LI_Currency");
		assertArrayEquals(new String[][] { 
				{"EUR", "Euro"}, 
				{"RON", "Romanian leu"},
				},
			list.toArray());

		list = _s.getInputList("A_LI_Channel");
		assertArrayEquals(new String[][] { 
				{"1", "Bank"}, 
				{"2", "Agent"},
				{"3", "Broker"},
				},
			list.toArray());
		list = _s.getInputList("A_LI_Sex");
		assertArrayEquals(new String[][] { 
				{"1", "male"}, 
				{"2", "female"},
				},
			list.toArray());
		list = _s.getInputList("A_LI_DurationCode");
		assertArrayEquals(new String[][] { 
				{"1", "in years"}, 
				{"2", "age at end of policy"},
				},
			list.toArray());
		list = _s.getInputList("A_LI_PaymentPeriodCode");
		assertArrayEquals(new String[][] { 
				{"1", "in years"}, 
				{"2", "age at end of policy"},
				},
			list.toArray());
		list = _s.getInputList("A_LI_PaymentMethod");
		assertArrayEquals(new String[][] { 
				{"3", "Invoice"}, 
				{"2", "Direct Debit"},
				},
			list.toArray());
		list = _s.getInputList("A_LI_BaseCode");
		assertArrayEquals(new String[][] { 
				{"1", "sum insured"}, 
				{"2", "premium"},
				{"3", "sum insured plus profit (with indexation)"},
				{"4", "sum insured with indexation"},
				},
			list.toArray());

	}

	private static void listEquals(String[][] strings, List<String[]> list) {
		assertEquals(strings.length, list.size());
		for (int i=0; i<strings.length; i++) {
			String[] shouldbe = strings[i];
			String[] whatitis = list.get(i);
			assertArrayEquals(shouldbe, whatitis);
		}
	}

	@Test
	public void test2() {
		_s.setInput("A_LI_Check_YN", "0");
		_s.setInput("A_LI_CurrentDate", "3.12.2011");
		_s.setInput("A_LI_Language", "EN");
		_s.setInput("A_LI_PaymentFrequency", "1");
		_s.setInput("A_LI_Currency", "EUR");
		_s.setInput("A_LI_Product", "6100");
		_s.setInput("A_LI_Channel", "2");
		_s.setInput("A_LI_Sex", "1");
		_s.setInput("A_LI_DateOfBirth", "05.05.1974");
		_s.setInput("A_LI_Examination_YN", "0");
		_s.setInput("A_LI_AddRisk_Perc", "0");
		_s.setInput("A_LI_TariffBegin", "1.1.2012");
		_s.setInput("A_LI_DurationCode", "1");
		_s.setInput("A_LI_DurationValue", "10");
		_s.setInput("A_LI_PaymentPeriodCode", "1");
		_s.setInput("A_LI_PaymentPeriodValue", "10");
		_s.setInput("A_LI_PaymentMethod", "1");
		_s.setInput("A_LI_BaseCode", "1");
		_s.setInput("A_LI_BaseValue", "50000");
		_s.setInput("A_LI_Creationdate", "3.12.2011");
		_s.setInput("A_LI_Riders", "3");
		_s.setInput("A_LI_RiderProduct[0]", "6105");
		_s.setInput("A_LI_RiderProduct[1]", "6110");
		_s.setInput("A_LI_RiderProduct[2]", "6115");
		_s.setInput("A_LI_RiderSumCode[0]", "0");
		_s.setInput("A_LI_RiderSumCode[1]", "0");
		_s.setInput("A_LI_RiderSumCode[2]", "1");
		_s.setInput("A_LI_RiderSumValue[0]", "");
		_s.setInput("A_LI_RiderSumValue[1]", "");
		_s.setInput("A_LI_RiderSumValue[2]", "15");
		_s.setInput("A_LI_RiderAddRiskProm[0]", "0");
		_s.setInput("A_LI_RiderAddRiskPerc[0]", "0");
		_s.setInput("A_LI_RiderAddRiskProm[1]", "0");
		_s.setInput("A_LI_RiderAddRiskPerc[1]", "0");
		_s.setInput("A_LI_RiderAddRiskProm[2]", "0");
		_s.setInput("A_LI_RiderAddRiskPerc[2]", "0");
		
		assertEquals("1", _s.calculate("F_LI_InputOk"));
		
		assertEquals("100.56", _s.calculate("PX_LI_RiderPremiumYear(0)"));
		assertEquals("150.96", _s.calculate("PX_LI_RiderPremiumYear(1)"));
		assertEquals("22.68", _s.calculate("PX_LI_RiderPremiumYear(2)"));
		
		
		String ret;
		assertEquals("20111105", _s.calculate("PX_LI_Version"));
		assertEquals("10", _s.calculate("PX_LI_Duration"));
		assertEquals("10", _s.calculate("PX_LI_PaymentPeriod"));
		assertEquals("37", _s.calculate("PX_LI_AgeAtBegin"));
		assertEquals("47", _s.calculate("PX_LI_AgeAtEnd"));
		assertEquals("5163.36", _s.calculate("PX_LI_PremiumFreq"));
		assertEquals("5163.36", _s.calculate("PX_LI_PremiumFreqBase"));
		assertEquals("5163.36", _s.calculate("PX_LI_PremiumYear"));
		assertEquals("5437.56", _s.calculate("PX_LI_PremiumContractFreq"));
		assertEquals("5437.56", _s.calculate("PX_LI_PremiumContractYear"));
		assertEquals("32.63", _s.calculate("PX_LI_PremiumContractYearTax"));
		assertEquals("5445.56", _s.calculate("PX_LI_PremiumContractFreqFirst"));
		assertEquals("8", _s.calculate("PX_LI_PremiumPolicyFee"));
		assertEquals("50000", _s.calculate("PX_LI_Sum"));
		assertEquals("1997.44", _s.calculate("PX_LI_Profit"));
		assertEquals("51997.44", _s.calculate("PX_LI_SumPlusProfit"));
		assertEquals("10", ret=_s.calculate("PX_LI_Years"));
		int years = Integer.parseInt(ret);

		

		assertEquals("274.2", ret=_s.calculate("PX_LI_RiderPremiumFreqTotal"));
		assertEquals("3", ret = _s.calculate("PX_LI_Riders"));
		int riders = Integer.parseInt(ret);
		for (int indrider=0; indrider<riders; indrider++) {
			System.out.println("compute rider " + indrider);
			ret = _s.calculate("PX_LI_RiderProduct(" + indrider + ")");
			ret = _s.calculate("PX_LI_RiderCode(" + indrider + ")");
			ret = _s.calculate("PX_LI_RiderSparte(" + indrider + ")");
			ret = _s.calculate("PX_LI_RiderPremiumYear(" + indrider + ")");
			ret = _s.calculate("PX_LI_RiderSum(" + indrider + ")");
		}

		assertEquals("0", _s.calculate("F_LI_SurrenderValue(37, 1, 10, 10, 10, 10, 0.025, 0, 2, 201109, 0, 1)"));
		assertEquals("0.062534575556", _s.calculate("F_LI_ReserveTechnical(37, 1, 10, 10, 0.025, 0, 2, 201109, 0, 1)"));
		assertEquals("0", _s.calculate("F_LI_AccrualFact(10, 10, 1)"));
		
		double[] yearCounter= new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		double[] year = new double[] {2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};
		double[] premiumSummed = new double[] {5445.56, 10883.12, 16320.68, 21758.24, 27195.8, 32633.36, 38070.92, 43508.48, 48946.04, 54383.6};
		double[] surrenderValue = new double[] { 0, 0, 9191.1, 13446.2, 18247.24, 23531.58, 29321.96, 35644.56, 41896.64, 50000};
		double[] surrenderValuePlusProfit = new double[] {0, 0, 9260.18, 13629.15, 18592.16, 24090.41, 30149.68, 36799.35, 43440.12, 51997.44};
		double[] deathBenefit = new double[] {50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000, 50000};
		double[] deathBenefitPlusProfit = new double[] {50000, 50000, 50069.08, 50182.95, 50344.92, 50558.83, 50827.72, 51154.79, 51543.48, 51997.44};
		double[] profitYear = new double[] {0, 0, 69.08, 182.95, 344.92, 558.83, 827.72, 1154.79, 1543.48, 1997.44             };
		double[] reducedSum = new double[] {0, 0, 10653.75, 15267.01, 20291.99, 25626.98, 31267.72, 37211.8, 42812.41, 50000 };
		for (int indyear =0; indyear<years; indyear++) {
			System.out.println("compute values for year " + indyear);
			assertEquals(V.getInstance(yearCounter[indyear]).stringValue(), _s.calculate("PX_LI_YearCounter(" + indyear + ")"));
			assertEquals(V.getInstance(year[indyear]).stringValue(), _s.calculate("PX_LI_Year(" + indyear + ")"));
			assertEquals(V.getInstance(premiumSummed[indyear]).stringValue(), _s.calculate("PX_LI_PremiumSummed(" + indyear + ")"));

			assertEquals(V.getInstance(50000).stringValue(), _s.calculate("PX_LI_SumLayer(0)"));
			assertEquals(V.getInstance(0).stringValue(), _s.calculate("P_LI_SurrenderValue_(0, 1)"));
			
			assertEquals(V.getInstance(surrenderValue[indyear]).stringValue(), _s.calculate("PX_LI_SurrenderValueWithIndex(" + indyear + ")"));
			assertEquals(V.getInstance(surrenderValue[indyear]).stringValue(), _s.calculate("PX_LI_SurrenderValue(" + indyear + ")"));
			assertEquals(V.getInstance(surrenderValuePlusProfit[indyear]).stringValue(), _s.calculate("PX_LI_SurrenderValuePlusProfit(" + indyear + ")"));
			assertEquals(V.getInstance(deathBenefit[indyear]).stringValue(), _s.calculate("PX_LI_DeathBenefit(" + indyear + ")"));
			assertEquals(V.getInstance(deathBenefitPlusProfit[indyear]).stringValue(), _s.calculate("PX_LI_DeathBenefitPlusProfit(" + indyear + ")"));
			assertEquals(V.getInstance(profitYear[indyear]).stringValue(), _s.calculate("PX_LI_ProfitYear(" + indyear + ")"));
			assertEquals(V.getInstance(reducedSum[indyear]).stringValue(), _s.calculate("PX_LI_ReducedSum(" + indyear + ")"));
		}
	}
	
	@Test
	public void test3() {
		_s.setInput("A_LI_Check_YN", "0");
		_s.setInput("A_LI_CurrentDate", "3.12.2011");
		_s.setInput("A_LI_Language", "EN");
		_s.setInput("A_LI_PaymentFrequency", "12");
		_s.setInput("A_LI_Currency", "RON");
		_s.setInput("A_LI_Product", "6200");
		_s.setInput("A_LI_Channel", "1");
		_s.setInput("A_LI_Sex", "2");
		_s.setInput("A_LI_DateOfBirth", "01.10.1964");
		_s.setInput("A_LI_Examination_YN", "0");
		_s.setInput("A_LI_AddRisk_Perc", "0");
		_s.setInput("A_LI_TariffBegin", "6.12.2011");
		_s.setInput("A_LI_DurationCode", "1");
		_s.setInput("A_LI_DurationValue", "20");
		_s.setInput("A_LI_PaymentPeriodCode", "1");
		_s.setInput("A_LI_PaymentPeriodValue", "20");
		_s.setInput("A_LI_PaymentMethod", "1");
		_s.setInput("A_LI_BaseCode", "1");
		_s.setInput("A_LI_BaseValue", "100000");
		_s.setInput("A_LI_Creationdate", "3.12.2011");
		_s.setInput("A_LI_Riders", "0");
		
		assertEquals("12.11", _s.calculate("PX_LI_PremiumFreqLayer(1)")); 
		assertEquals("4840.8", _s.calculate("PX_LI_PremiumYearAllLayers(0)"));
		assertEquals("4986.12", _s.calculate("PX_LI_PremiumYearAllLayers(1)"));
		assertEquals("9856.92", _s.calculate("PX_LI_PremiumSummed(1)"));

	}
}
