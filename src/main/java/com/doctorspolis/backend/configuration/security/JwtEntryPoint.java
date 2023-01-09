package com.doctorspolis.backend.configuration.security;

import com.doctorspolis.backend.model.DTO.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class JwtEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .error("00001")
                .status(HttpStatus.BAD_REQUEST)
                .message(authException.getMessage())
                .details(authException.getMessage())
                .build();

        response.setStatus(HttpStatus.OK.value());

        try (PrintWriter writer = response.getWriter()) {
            String json = new ObjectMapper().writeValueAsString(errorDTO);

            writer.write(json);
            writer.flush();
        } catch (IOException ie) {
            System.out.println("vdqsdf");
        }
    }

}
