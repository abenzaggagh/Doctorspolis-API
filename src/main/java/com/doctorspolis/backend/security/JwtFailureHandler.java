package com.doctorspolis.backend.security;

import com.doctorspolis.backend.model.DTO.ErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFailureHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

        ErrorDTO errorDTO = ErrorDTO
                .builder()
                .error("00001")
                .status("400")
                .message(accessDeniedException.getMessage())
                .details(accessDeniedException.getMessage())
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
