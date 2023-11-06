package cl.impac.diagnostico.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ResponsesBuilder {
	 public Map<String, Object> createSuccessResponse(String message) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "success");
	        response.put("message", message);
	        return response;
	    }

	    public Map<String, Object> createErrorResponse(Throwable e) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("status", "error");

	        if (e instanceof ResponseStatusException) {
	            response.put("message", ((ResponseStatusException) e).getReason());
	        } else {
	            response.put("message", e.getMessage());
	        }

	        return response;
	    }
}
