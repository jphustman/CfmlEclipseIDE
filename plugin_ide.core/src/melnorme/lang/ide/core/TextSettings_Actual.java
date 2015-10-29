package melnorme.lang.ide.core;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

import org.cfeclipse.ide.core.text.CfmlDocumentSetupParticipant;
import org.cfeclipse.ide.core.text.CfmlPartitionScanner;
import melnorme.lang.ide.core.text.LangDocumentPartitionerSetup;
import melnorme.utilbox.misc.ArrayUtil;

public class TextSettings_Actual {
	
	public static final String PARTITIONING_ID = "org.cfeclipse.Partitioning";
	
	public static enum LangPartitionTypes {	
		CODE,
		//DOCTYPE,
		CF_COMMENT,
		CF_SCRIPT_COMMENT_BLOCK,
		CF_SCRIPT_COMMENT,
		JAVADOC_COMMENT,
		HTML_COMMENT,
//		CF_SCRIPT,
//		CF_START_TAG,
//		CF_START_TAG_BEGIN,
//		CF_START_TAG_END,
//		CF_TAG_ATTRIBS,
//		CF_SET_STATEMENT,
//		CF_RETURN_STATEMENT,
//		CF_BOOLEAN_STATEMENT,
//		CF_END_TAG,
//		HTML_START_TAG,
//		HTML_END_TAG,
//		HTML_START_TAG_BEGIN,
//		HTML_START_TAG_END,
//		HTML_TAG_ATTRIBS,
//		CF_EXPRESSION,
//		J_SCRIPT,
//		CSS,
//		SQL,
//		TAGLIB_TAG,
//		UNK_TAG,
		CHARACTER,
		STRING;		
		
		public String getId() {
			if(ordinal() == 0) {
				return IDocument.DEFAULT_CONTENT_TYPE;
			}
			return toString();
		}
		
	}
	
	public static IPartitionTokenScanner createPartitionScanner() {
		return new CfmlPartitionScanner();
	}
	
	public static LangDocumentPartitionerSetup createDocumentSetupHelper() {
		return new CfmlDocumentSetupParticipant();
	}
	
	/* ----------------- Common code ----------------- */
	
	public static final String[] PARTITION_TYPES = ArrayUtil.map(LangPartitionTypes.values(), 
		obj -> obj.getId(), String.class
	);
	
}