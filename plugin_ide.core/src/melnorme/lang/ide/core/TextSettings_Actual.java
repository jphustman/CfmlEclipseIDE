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
		LINE_COMMENT, 
		BLOCK_COMMENT, 
		DOC_LINE_COMMENT, 
		DOC_BLOCK_COMMENT, 
		STRING, 
		CHARACTER;
		
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