package org.cfeclipse.ide.ui.text;

import org.cfeclipse.tooling.parser.lexer.CfmlTagNameRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.WordRule;

import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.core.text.LangPartitionScanner;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlStartTagScanner extends AbstractLangScanner {

	public CfmlStartTagScanner(TokenRegistry tokenRegistry) {
		super(tokenRegistry);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken tagToken = getToken(CfmlColorPreferences.CF_TAG);
		IToken stringToken = getToken(CfmlColorPreferences.CF_STRING);
		IToken keywordToken = getToken(CfmlColorPreferences.CFKEYWORD);
		
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("\'", "\'", stringToken));
		//rules.add(new SingleLineRule("cf", " ", tagToken, (char) -1, true));
		rules.add(new CfmlTagNameRule(tagToken));
		//rules.add(new SingleLineRule("=", "=", keywordToken));
		//rules.add(new CfmlTagNameRule(tagToken));
		//rules.add(new PredicateRule_Adapter(LangPartitionTypes.CF_START_TAG.getId(), new CfmlTagNameRule()));
		//rules.add(new PredicateRule_Adapter(LangPartitionTypes.CF_TAG_ATTRIBS.getId(), new CfmlAttributeNameRule()));
		
		
		//CfmlWordLexerRule<IToken> codeLexerRule = new CfmlWordLexerRule<IToken>(Token.WHITESPACE, keywordToken);
		//rules.add(new LexingRule_RuleAdapter(codeLexerRule));
		
		// For dev purposes consider only the cfset tag
		
		
		
	}

	
	
}
