package com.az.gitember.controller.lang;

import com.az.gitember.controller.lang.asm.MASMLexer;
import com.az.gitember.controller.lang.basic.jvmBasicLexer;
import com.az.gitember.controller.lang.c.CLexer;
import com.az.gitember.controller.lang.html.HTMLLexer;
import com.az.gitember.controller.lang.java.Java9Lexer;
import com.az.gitember.controller.lang.json.JSONLexer;
import com.az.gitember.controller.lang.txt.SimpleTextLexer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public class LangResolver {

    private final Lexer lexer;
    private final BaseTokenTypeAdapter tokenTypeAdapter;

    public LangResolver(String fileExtention, String content) {
        CharStream charStream = new ANTLRInputStream(content);
        if ("java".equalsIgnoreCase(fileExtention)) {
            this.lexer = new Java9Lexer(charStream );
            this.tokenTypeAdapter = new JavaTokenTypeAdapter(lexer);
        } else if ("json".equalsIgnoreCase(fileExtention)) {
            this.lexer = new JSONLexer(charStream );
            this.tokenTypeAdapter = new  JsonTokenTypeAdapter(lexer);
        } else if ("asm".equalsIgnoreCase(fileExtention)) {
            this.lexer = new MASMLexer(charStream );
            this.tokenTypeAdapter = new  AsmTokenTypeAdapter(lexer);
        } else if ("bas".equalsIgnoreCase(fileExtention)) {
            this.lexer = new jvmBasicLexer(charStream );
            this.tokenTypeAdapter = new  BasicTokenTypeAdapter(lexer);
        } else if ("c".equalsIgnoreCase(fileExtention) || "h".equalsIgnoreCase(fileExtention)) {
            this.lexer = new CLexer(charStream );
            this.tokenTypeAdapter = new  CTokenTypeAdapter(lexer);
/*
        } else if ("html".equalsIgnoreCase(fileExtention) || "htm".equalsIgnoreCase(fileExtention)) {
            this.lexer = new HTMLLexer(charStream );
            this.tokenTypeAdapter = new HtmlTokenTypeAdapter(lexer);
*/
        } else  {
            this.lexer = new SimpleTextLexer(charStream );
            this.tokenTypeAdapter = new SimpleTextTypeAdapter(lexer);
        }
    }

    public Lexer getLexer() {
        return  lexer;
    }

    public BaseTokenTypeAdapter getAdapter() {
        return tokenTypeAdapter;
    }

}
