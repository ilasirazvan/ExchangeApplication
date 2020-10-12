package com.exchange.rest;

import com.exchange.dao.UserDAO;
import com.exchange.entities.Credentials;
import com.exchange.entities.User;
import com.exchange.entities.UserSecurity;
import com.exchange.exception.UserExistingException;
import com.exchange.exception.UserNotFoundException;
import com.exchange.security.PasswordSecurity;
import com.exchange.security.TokenSecurity;
import com.exchange.utils.BuildResponse;
import com.exchange.utils.filter.AuthenticationFilter;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@DeclareRoles({"admin", "user", "guest"})
@Path("/user")
public class UserRestService extends ResourceConfig {

    final static Logger logger = Logger.getLogger(UserRestService.class);

    @POST
    @Path("/create")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserSecurity userSecurity) {
        UserDAO userDao = new UserDAO();
        try {
            if (userDao.getUserIdByEmail(userSecurity.getEmail()) != null) {
                throw new UserExistingException(userSecurity.getEmail());
            } else {
                userSecurity.setRole("user");
                String plainPassword = userSecurity.getPassword();
                userSecurity.setPassword(PasswordSecurity.generateHash(userSecurity.getPassword()));
                userDao.createUser(userSecurity);
                return authenticate(new Credentials(userSecurity.getEmail(), plainPassword));
            }
        } catch (UserExistingException e) {
            logger.debug(e.getMessage());
            return BuildResponse.buildResponseWithError(Response.Status.CONFLICT, e.getMessage());
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return BuildResponse.buildResponseWithError(Response.Status.INTERNAL_SERVER_ERROR, BuildResponse.ERR_500_MSG);
        }
    }

    @POST
    @Path("/authenticate")
    @PermitAll
    @Produces("application/json")
    @Consumes("application/json")
    public Response authenticate(Credentials credentials) {
        UserDAO userDao = new UserDAO();

        try {
            String id = userDao.getUserIdByEmail(credentials.getEmail());
            UserSecurity userSecurity = userDao.getUserAuthentication(id);

            if (!PasswordSecurity.validatePassword(credentials.getPassword(), userSecurity.getPassword())) {
                return BuildResponse.buildResponseWithError(Response.Status.UNAUTHORIZED, BuildResponse.ERR_500_MSG);
            }

            // generate a token for the user
            String token = TokenSecurity.generateJwtToken(id);

            // write the token to the database
            UserSecurity sec = new UserSecurity(null, token);
            sec.setId(id);
            userDao.setUserAuthentication(sec);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(AuthenticationFilter.AUTHORIZATION_PROPERTY, token);

            // Return the token on the response
            return BuildResponse.buildResponseWithObject(Response.Status.OK, "ok");
        } catch (UserNotFoundException e) {
            logger.debug(e.getMessage());
            return BuildResponse.buildResponseWithError(Response.Status.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return BuildResponse.buildResponseWithError(Response.Status.UNAUTHORIZED, BuildResponse.ERR_500_MSG);
        }

    }

    @GET
    @Path("/get")
    @RolesAllowed({"admin", "user"})
    @Produces("application/json")
    public Response get(@Context HttpHeaders headers) {
        UserDAO userDao = new UserDAO();

        try {
            String id = getId(headers);

            // use decoded email from jwt in header
            User user = userDao.getUser(id);

            // Return the user on the response
            return BuildResponse.buildResponseWithObject(Response.Status.OK, user.toString());
        } catch (UserNotFoundException e) {
            return BuildResponse.buildResponseWithError(Response.Status.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return BuildResponse.buildResponseWithError(Response.Status.UNAUTHORIZED, BuildResponse.ERR_500_MSG);
        }
    }

    private String getId(HttpHeaders headers) {
        // get the email we set in AuthenticationFilter
        List<String> id = headers.getRequestHeader(AuthenticationFilter.HEADER_PROPERTY_ID);

        if (id == null || id.size() != 1)
            throw new NotAuthorizedException("Unauthorized!");

        return id.get(0);
    }

}
