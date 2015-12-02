package org.cfeclipse.ide.core.text;

import static melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes.CF_SET_STATEMENT;

import org.junit.Ignore;
import org.junit.Test;

import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.core.text.LangPartitionScannerTest;

public class CfmlPartitionScannerTest extends LangPartitionScannerTest {
	
	@Test
	@Ignore
	public void testBasic() throws Exception {
		testPartitions("<cfoutput>", array(LangPartitionTypes.CF_START_TAG_BEGIN, CF_SET_STATEMENT, LangPartitionTypes.CF_START_TAG_END));
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
