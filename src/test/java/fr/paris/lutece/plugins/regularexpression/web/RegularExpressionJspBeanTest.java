/*
 * Copyright (c) 2002-2026, City of Paris
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
package fr.paris.lutece.plugins.regularexpression.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.admin.AccessDeniedException;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.test.AdminUserUtils;
import fr.paris.lutece.test.LuteceTestCase;
import fr.paris.lutece.test.mocks.MockHttpServletRequest;
import jakarta.inject.Inject;

/**
 * RegularExpressionJspBean Test: the screen opened by the button of an error message
 */
public class RegularExpressionJspBeanTest extends LuteceTestCase
{
    @Inject
    private RegularExpressionJspBean _bean;

    @Test
    public void testDoCreateRegularExpressionWithoutTitleReturnsToTheCreationScreen( ) throws AccessDeniedException
    {
        MockHttpServletRequest request = getUserRequest( );
        _bean.init( request, RegularExpressionJspBean.RIGHT_REGULAR_EXPRESSION_MANAGEMENT );

        String strUrl = _bean.doCreateRegularExpression( request );

        assertTrue( strUrl.endsWith( AdminMessageService.getMessageRelativeUrl( ) ), strUrl );
        String strBackUrl = AdminMessageService.getMessage( request ).getBackUrl( );
        assertTrue( strBackUrl.endsWith( "jsp/admin/plugins/regularexpression/CreateRegularExpression.jsp" ), strBackUrl );
    }

    @Test
    public void testDoModifyRegularExpressionWithInvalidPatternReturnsToTheModificationScreen( ) throws AccessDeniedException
    {
        MockHttpServletRequest request = getUserRequest( );
        _bean.init( request, RegularExpressionJspBean.RIGHT_REGULAR_EXPRESSION_MANAGEMENT );
        request.setParameter( "id_expression", "42" );
        request.setParameter( "title", "title" );
        request.setParameter( "value", "(" );
        request.setParameter( "valid_exemple", "example" );
        request.setParameter( "information_message", "information" );
        request.setParameter( "error_message", "error" );

        String strUrl = _bean.doModifyRegularExpression( request );

        assertTrue( strUrl.endsWith( AdminMessageService.getMessageRelativeUrl( ) ), strUrl );
        String strBackUrl = AdminMessageService.getMessage( request ).getBackUrl( );
        assertTrue( strBackUrl.endsWith( "jsp/admin/plugins/regularexpression/ModifyRegularExpression.jsp?id_expression=42" ), strBackUrl );
    }

    private MockHttpServletRequest getUserRequest( )
    {
        MockHttpServletRequest request = new MockHttpServletRequest( );
        request.setMethod( "POST" );
        AdminUserUtils.registerAdminUserWithRight( request, new AdminUser( ), RegularExpressionJspBean.RIGHT_REGULAR_EXPRESSION_MANAGEMENT );

        return request;
    }
}
