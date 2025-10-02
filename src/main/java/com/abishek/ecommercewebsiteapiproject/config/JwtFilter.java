package com.abishek.ecommercewebsiteapiproject.config;

import com.abishek.ecommercewebsiteapiproject.service.JwtService;
import com.abishek.ecommercewebsiteapiproject.users.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtService jwtService;
    ApplicationContext applicationContext;

    public JwtFilter(JwtService jwtService,    ApplicationContext applicationContext){
        this.jwtService = jwtService;
        this.applicationContext = applicationContext;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authheader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authheader!= null && authheader.startsWith("Bearer")) // checking weather the user is sending the token so we use Bearer
        {
            token = authheader.substring(7); // the token will be sent like Bearer asdasdasd
            username = jwtService.extractUserName(token); // we extract username from the token in this method.

        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) // This validation is used only when the autherntication object is not available
        {
            //Here we validate and generate the authentication object

            //We get the object of UserDetails from loaduserbyusername method in UserDetails Service implementtaion class
            UserDetails userDetails = applicationContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails))
            {

                //Since ww are adding a jwt filter before the UsernamePasswordAuthenticationFilter we need to create a authentication object for that.

                UsernamePasswordAuthenticationToken authenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); // This class expects userprincipal,credentials and authorities

                //Now we have to set this token in the Security context

                //We are setting the request details in the token
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //We are setting the authentication token in security context here
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }
        //This moves to the next filter. here We are continuing the filter chain
        filterChain.doFilter(request,response);

    }
}
