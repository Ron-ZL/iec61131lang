package edu.kit.iti.formal.automation.st;

/*-
 * #%L
 * iec61131lang
 * %%
 * Copyright (C) 2016 Alexander Weigl
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import edu.kit.iti.formal.automation.TestUtils;
import edu.kit.iti.formal.automation.parser.IEC61131Lexer;
import edu.kit.iti.formal.automation.parser.IEC61131Parser;
import edu.kit.iti.formal.automation.st.ast.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by weigl on 02.08.16.
 */
@RunWith(Parameterized.class)
public class ValidExpressionTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> setup() throws IOException {
        return TestUtils.loadLines("/edu/kit/iti/formal/automation/st/expressions/valid.txt");
    }

    @Parameterized.Parameter
    public String validExpression;

    @Test
    public void testValidExpression() {
        assertTrue(test(validExpression));

    }

    static boolean test(String s) {
        ANTLRInputStream stream = new ANTLRInputStream(s);
        IEC61131Lexer lexer = new IEC61131Lexer(stream);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        IEC61131Parser parser = new IEC61131Parser(cts);

        try {
            Expression e = parser.expression().ast;
           // System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return parser.getNumberOfSyntaxErrors() == 0;
    }

}
