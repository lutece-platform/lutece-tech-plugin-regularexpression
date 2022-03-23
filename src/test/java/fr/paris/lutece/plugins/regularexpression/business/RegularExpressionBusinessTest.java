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

import fr.paris.lutece.plugins.regularexpression.service.RegularExpressionPlugin;
import fr.paris.lutece.portal.business.regularexpression.RegularExpression;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.test.LuteceTestCase;

public class RegularExpressionBusinessTest extends LuteceTestCase
{

    private Plugin _plugin = PluginService.getPlugin( RegularExpressionPlugin.PLUGIN_NAME );

    public void testCRUD( )
    {
        RegularExpression reg = new RegularExpression( );
        reg.setTitle( "title" );
        reg.setValue( "value" );
        reg.setInformationMessage( "info" );
        reg.setValidExemple( "example" );
        reg.setErrorMessage( "error" );

        RegularExpressionHome.create( reg, _plugin );

        RegularExpression loaded = RegularExpressionHome.findByPrimaryKey( reg.getIdExpression( ), _plugin );
        assertEquals( reg.getTitle( ), loaded.getTitle( ) );
        assertEquals( reg.getValue( ), loaded.getValue( ) );
        assertEquals( reg.getValidExemple( ), loaded.getValidExemple( ) );
        assertEquals( reg.getInformationMessage( ), loaded.getInformationMessage( ) );
        assertEquals( reg.getErrorMessage( ), loaded.getErrorMessage( ) );

        reg.setTitle( "title2" );
        reg.setValue( "value2" );
        reg.setInformationMessage( "info2" );
        reg.setValidExemple( "example2" );
        reg.setErrorMessage( "error2" );

        RegularExpressionHome.update( reg, _plugin );
        loaded = RegularExpressionHome.findByPrimaryKey( reg.getIdExpression( ), _plugin );
        assertEquals( reg.getTitle( ), loaded.getTitle( ) );
        assertEquals( reg.getValue( ), loaded.getValue( ) );
        assertEquals( reg.getValidExemple( ), loaded.getValidExemple( ) );
        assertEquals( reg.getInformationMessage( ), loaded.getInformationMessage( ) );
        assertEquals( reg.getErrorMessage( ), loaded.getErrorMessage( ) );

        RegularExpressionHome.remove( reg.getIdExpression( ), _plugin );

        loaded = RegularExpressionHome.findByPrimaryKey( reg.getIdExpression( ), _plugin );
        assertNull( loaded );
    }
}
