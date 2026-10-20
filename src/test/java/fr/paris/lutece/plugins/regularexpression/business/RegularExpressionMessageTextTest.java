/*
 * Copyright (c) 2002-2021, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.regularexpression.business;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.test.LuteceTestCase;

/**
 * Tests that the messages of an expression are read as text (LUT-34103).
 */
public class RegularExpressionMessageTextTest extends LuteceTestCase
{
    /**
     * A message typed with the rich text editor is read as text, its lines kept.
     */
    @Test
    void testEditorHtmlIsReadAsText( )
    {
        assertEquals( "The e-mail is not valid", RegularExpressionMessageText.toText( "<p>The e-mail is <strong>not valid</strong></p>" ) );
        assertEquals( "First line\nSecond & last", RegularExpressionMessageText.toText( "<p>First line</p><p>Second &amp; last</p>" ) );
    }

    /**
     * A plain text is kept as it is, characters a sanitizer would change included.
     */
    @Test
    void testTextIsKept( )
    {
        assertEquals( "Value a<b \"c\" & d", RegularExpressionMessageText.toText( "Value a<b \"c\" & d" ) );
        assertEquals( "x < 5", RegularExpressionMessageText.toText( "x < 5" ) );
        assertNull( RegularExpressionMessageText.toText( null ) );
    }
}
