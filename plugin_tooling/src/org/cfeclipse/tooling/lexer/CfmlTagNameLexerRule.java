package org.cfeclipse.tooling.lexer;

import melnorme.lang.tooling.parser.lexer.WordLexerRule;
import melnorme.lang.utils.parse.ICharacterReader;

public class CfmlTagNameLexerRule<TOKEN> extends WordLexerRule<TOKEN> {
	
	public static final String[] tag_names = { 
			"cfargument",
			"cfcase",
			"cfcatch",
			"cfcomponent",
			"cfcookie",
			"cfdirectory",
			"cfdump",
			"cfelse",
			"cfelseif",
			"cfexecute",
			"cffile",
			"cffunction",
			"cfif",
			"cfinclude",
			"cfinvoke",
			"cfinvokeargument",
			"cflocation",
			"cflock",
			"cfloop",
			"cfmail",			
			"cfoutput",
			"cfparam",
			"cfprocessingdirective",
			"cfquery",
			"cfqueryparam",
			"cfreturn",
			"cftry",
			"cfscript",
			"cfset",
			"cfsetting",			
			"cfswitch"
	};	
		
	public CfmlTagNameLexerRule(
			TOKEN whitespaceToken, 
			TOKEN defaultWordToken,
			TOKEN cfTagToken
			) {
		super(whitespaceToken, defaultWordToken);
		
		addKeywords(cfTagToken, CfmlTagNameLexerRule.tag_names);
	}
	
	@Override
	public TOKEN doEvaluateToken(ICharacterReader reader) {
		TOKEN result = super.doEvaluateToken(reader);
		return result;
	}
	
}