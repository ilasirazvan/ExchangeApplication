package com.exchange.utils.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.exchange.dao.UserDAO;
import com.exchange.entities.User;
import com.exchange.entities.UserSecurity;
import com.exchange.exception.UserNotFoundException;
import com.exchange.security.TokenSecurity;
import com.exchange.utils.BuildResponse;
import org.apache.log4j.Logger;

@Provider
@Priority( Priorities.AUTHENTICATION )
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
    final static Logger logger = Logger.getLogger( AuthenticationFilter.class );

    @Context
    private ResourceInfo resourceInfo;

    public static final String HEADER_PROPERTY_ID = "id";
    public static final String AUTHORIZATION_PROPERTY = "x-access-token";

    private static final String ACCESS_REFRESH = "Token expired. Please authenticate again!";
    private static final String ACCESS_INVALID_TOKEN = "Token invalid. Please authenticate again!";
    private static final String ACCESS_DENIED = "Not allowed to access this resource!";
    private static final String ACCESS_FORBIDDEN = "Access forbidden!";

    @Override
    public void filter( ContainerRequestContext requestContext )
    {
        Method method = resourceInfo.getResourceMethod();
        if( !method.isAnnotationPresent( PermitAll.class ) )
        {
            if( method.isAnnotationPresent( DenyAll.class ) )
            {
                requestContext.abortWith(
                        BuildResponse.buildResponseWithError( Response.Status.FORBIDDEN, ACCESS_FORBIDDEN )
                );
                return;
            }


            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authProperty = headers.get( AUTHORIZATION_PROPERTY );

            if( authProperty == null || authProperty.isEmpty() )
            {
                logger.warn("No token provided!");
                requestContext.abortWith(
                        BuildResponse.buildResponseWithError( Response.Status.UNAUTHORIZED, ACCESS_DENIED )
                );
                return;
            }

            String id = null ;
            String jwt = authProperty.get(0);

            try {
                id = TokenSecurity.validateJwtToken( jwt );
            } catch ( Exception ex ) {
                logger.warn("Invalid token provided!");
                requestContext.abortWith(
                        BuildResponse.buildResponseWithError( Response.Status.UNAUTHORIZED, ACCESS_INVALID_TOKEN )
                );
                return;
            }

            UserDAO userDao = new UserDAO();
            User user = null;
            try {
                user = userDao.getUser( id );
            }
            catch ( UserNotFoundException e ) {
                logger.warn("Token missmatch!");
                requestContext.abortWith(
                        BuildResponse.buildResponseWithError( Response.Status.UNAUTHORIZED, ACCESS_DENIED )
                );
                return;
            }

            UserSecurity userSecurity = userDao.getUserAuthentication( user.getId() );

            if( !userSecurity.getToken().equals( jwt ) ) {
                logger.warn("Token expired!");
                requestContext.abortWith(
                        BuildResponse.buildResponseWithError( Response.Status.UNAUTHORIZED, ACCESS_REFRESH )
                );
                return;
            }

            if( method.isAnnotationPresent( RolesAllowed.class ) )
            {

                RolesAllowed rolesAnnotation = method.getAnnotation( RolesAllowed.class );
                Set<String> rolesSet = new HashSet<String>( Arrays.asList( rolesAnnotation.value() ) );

                if( !isUserAllowed( userSecurity.getRole(), rolesSet ) )
                {
                    logger.warn("User does not have the rights to acces this resource!");
                    requestContext.abortWith(
                            BuildResponse.buildResponseWithError( Response.Status.UNAUTHORIZED, ACCESS_DENIED )
                    );
                    return;
                }
            }

            List<String> idList = new ArrayList<String>();
            idList.add( id );
            headers.put( HEADER_PROPERTY_ID, idList );
        }
    }

    private boolean isUserAllowed( final String userRole, final Set<String> rolesSet )
    {
        boolean isAllowed = false;

        if( rolesSet.contains( userRole ) )
        {
            isAllowed = true;
        }

        return isAllowed;
    }
}

