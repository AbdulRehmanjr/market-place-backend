package com.multivendor.marketplace.config.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.multivendor.marketplace.service.implement.UserDetailServiceImpl;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private UserDetailServiceImpl userDetails;
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
        final String RequestTokenHeader = request.getHeader("Authorization");
        // geting request type
        log.info("Request Type: {}",request.getMethod());
        log.info("Request URI: {}",request.getRequestURI());
        String username = null;
        String jwttoken = null;

        if(RequestTokenHeader != null && RequestTokenHeader.startsWith("Bearer ")){
            jwttoken = RequestTokenHeader.substring(7);
            try{
                username = this.jwtUtil.extractUsername(jwttoken);
            
            }catch(Exception e){
                log.error("Cannot extract username from token / expirerd token");
            }

        }else{
            log.error("Invalid token, not start with bearer string");
        }

        // validate token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetail = this.userDetails.loadUserByUsername(username);
            // log.info("userDetails: "+userDetails.toString());
            log.info("User is authenticated");
            if(this.jwtUtil.validateToken(jwttoken, userDetail)){
                // token is valid

                UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
                uPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("Authenticated user setting security context");
                SecurityContextHolder.getContext().setAuthentication(uPAT);
            }
        }else{
            log.error("Token not valide");
        }
           
    filterChain.doFilter(request, response);
    
    }
    
}
