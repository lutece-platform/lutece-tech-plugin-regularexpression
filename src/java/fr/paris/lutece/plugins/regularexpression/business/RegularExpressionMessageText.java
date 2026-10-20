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

import java.util.regex.Pattern;

import fr.paris.lutece.portal.service.editor.RichTextContentService;
import fr.paris.lutece.portal.service.editor.RichTextParsingException;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * The information and error messages of an expression are plain text (LUT-34103): the forms that validate a field with the
 * expression write its error message escaped, as text. The messages typed before with the rich text editor are stored as
 * HTML; they are read here as text, line breaks kept.
 */
public final class RegularExpressionMessageText
{
    private static final Pattern EDITOR_HTML = Pattern.compile(
            "(?is).*<\\s*/?\\s*(p|br|div|span|strong|em|b|i|u|s|ul|ol|li|a|h[1-6]|blockquote|pre|code|table|tbody|thead|tr|td|th|sub|sup|hr|img|font)\\b[^>]*>.*" );

    /**
     * Private constructor.
     */
    private RegularExpressionMessageText( )
    {
    }

    /**
     * Gives a message as plain text: a message holding the HTML of the rich text editor is converted, any other value is
     * returned as it is (a text may hold a &lt; or a &amp;).
     *
     * @param strMessage
     *            the stored message, may be null
     * @return the message as text
     */
    public static String toText( String strMessage )
    {
        if ( strMessage == null || !EDITOR_HTML.matcher( strMessage ).matches( ) )
        {
            return strMessage;
        }
        try
        {
            return RichTextContentService.getText( strMessage );
        }
        catch( RichTextParsingException e )
        {
            AppLogService.error( "Unable to read a regular expression message as text", e );
            return strMessage;
        }
    }
}
