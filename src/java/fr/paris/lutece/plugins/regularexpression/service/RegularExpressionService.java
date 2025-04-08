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
package fr.paris.lutece.plugins.regularexpression.service;

import fr.paris.lutece.plugins.regularexpression.business.RegularExpressionHome;
import fr.paris.lutece.portal.business.regularexpression.RegularExpression;
import fr.paris.lutece.portal.service.editor.RichTextContentService;
import fr.paris.lutece.portal.service.editor.RichTextParsingException;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.regularexpression.IRegularExpressionService;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * Regular expression service
 *
 */
public class RegularExpressionService implements IRegularExpressionService
{
    /**
     * return false if the pattern is invalid
     * 
     * @param strPattern
     *            the pattern to test
     * @return false if the pattern is invalid
     */
    public boolean isPatternValide( String strPattern )
    {
        try
        {
            Pattern.compile( strPattern );
        }
        catch( PatternSyntaxException exception )
        {
            return false;
        }

        return true;
    }

    /**
     * return false if the expression's syntax is invalid
     * 
     * @param regularExpression
     *            the regular expression object to test
     * @return false if the expression's syntax is invalid
     */
    public boolean isPatternValide( RegularExpression regularExpression )
    {
        try
        {
            Pattern.compile( regularExpression.getValue( ) );
        }
        catch( PatternSyntaxException exception )
        {
            return false;
        }

        return true;
    }

    /**
     * return true if the value in parameter verify the pattern
     * 
     * @param strValueToTest
     *            the value to test
     * @param strPattern
     *            the regular expression Pattern
     * @return true if the value in parameter verify the pattern
     */
    public boolean isMatches( String strValueToTest, String strPattern )
    {
        Pattern pattern = null;

        try
        {
            pattern = Pattern.compile( strPattern );
        }
        catch( PatternSyntaxException exception )
        {
            return false;
        }

        Matcher controler = pattern.matcher( strValueToTest );

        return controler.matches( );
    }

    /**
     * return true if the value in parameter verify the regular expression
     * 
     * @param strValueToTest
     *            the value to test
     * @param regularExpression
     *            the regular expression
     * @return true if the value verify the regular expression
     */
    public boolean isMatches( String strValueToTest, RegularExpression regularExpression )
    {
        Pattern pattern = null;

        try
        {
            pattern = Pattern.compile( regularExpression.getValue( ) );
        }
        catch( PatternSyntaxException exception )
        {
            return false;
        }

        Matcher controler = pattern.matcher( strValueToTest );

        return controler.matches( );
    }

    /**
     * return the regular expression object whose identifier is specified in parameter
     * 
     * @param nKey
     *            the regular expression key
     * @return the regular expression object whose identifier is specified in parameter
     */
    public RegularExpression getRegularExpressionByKey( int nKey )
    {
        return getRegularExpressionByKey( nKey, false );
    }

    /**
     * return the regular expression object whose identifier is specified in parameter
     * 
     * @param nKey
     *            the regular expression key
     * @param bForEditor
     *            True if the template is to be displayed in an editor
     * @return the regular expression object whose identifier is specified in parameter
     */
    public RegularExpression getRegularExpressionByKey( int nKey, boolean bForEditor )
    {
    	
    	Plugin plugin = PluginService.getPlugin( RegularExpressionPlugin.PLUGIN_NAME );
    	
    	RegularExpression regularExpression = RegularExpressionHome.findByPrimaryKey( nKey, plugin );
    	
    	//When attributes are generated in an textarea input with richtext option (from markdown editors or html editors), 
        //then attributes must be transformed in html by the RichTextContentService 
	    if ( !bForEditor )
	    {
	        try
	        {
	        	formatRichTextAttributes( regularExpression );
	        }
	        catch( RichTextParsingException e )
	        {
	            AppLogService.error( e.getMessage( ), e );
	        }
	    }
	    
	    return regularExpression;
    }
    

    /**
     * return a list of regular expression
     * 
     * @return all regular expression
     */
    public List<RegularExpression> getAllRegularExpression( )
    {
    	return getAllRegularExpression( false );
    }
    
    /**
     * return a list of regular expression
     * 
     * @param bForEditor
     *            True if the template is to be displayed in an editor
     * @return all regular expression
     */
    public List<RegularExpression> getAllRegularExpression( boolean bForEditor )
    {
        Plugin plugin = PluginService.getPlugin( RegularExpressionPlugin.PLUGIN_NAME );
        
        List<RegularExpression> regularExpressions = RegularExpressionHome.getList( plugin );
        
        //When attributes are generated in an textarea input with richtext option (from markdown editors or html editors), 
        //then attributes must be transformed in html by the RichTextContentService 
        if ( !bForEditor )
        {
            regularExpressions.forEach( regularExpression ->
            {
                try
                {
                	formatRichTextAttributes( regularExpression );
                }
                catch( RichTextParsingException e )
                {
                    AppLogService.error( e.getMessage( ), e );
                }
            } );
        }

        return regularExpressions;
    }
    
    /**
     * Format in HTML richText attributes of a regularExpression
     *
     * @param regularExpression
     *            The regularExpression to modify
     * @return the regularExpression with modification
     */
    public static void formatRichTextAttributes( RegularExpression regularExpression ) throws RichTextParsingException 
    {
    	regularExpression.setErrorMessage( RichTextContentService.getContent( regularExpression.getErrorMessage( ) ) );
        regularExpression.setInformationMessage( RichTextContentService.getContent( regularExpression.getInformationMessage( ) ) );
    }
}
