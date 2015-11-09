package org.cfeclipse.ide.core.text;

import static melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes.CF_SET_STATEMENT;

import org.junit.Test;

import melnorme.lang.ide.core.text.LangPartitionScannerTest;

public class CfmlPartitionScannerTest extends LangPartitionScannerTest {
	
	@Test
	public void testBasic() throws Exception {
		testPartitions("<cfset var foo=\"bar\">", array(CF_SET_STATEMENT));
	}
	
	@Test
	public void testAttribute() throws Exception { testAttribute$(); }
	
	public void testAttribute$() throws Exception {
	/*
		testPartitions("foo = #[ blah ] ", array(ATTRIBUTE));
		testPartitions("foo = #![ \"--]--\" ] ", array(ATTRIBUTE));
		testPartitions(getClassResourceAsString(RustAttributeRule.class, "attribute_sample.rs") + "//other", 
			array(ATTRIBUTE, LangPartitionTypes.LINE_COMMENT));
			*/
	}

}
