package com.gitittech.paygo.commons.exceptions;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;

public class ThirdPartyServiceProviderException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private FlutterError error;

    public ThirdPartyServiceProviderException(String message) {
        super(message);
    }

    public ThirdPartyServiceProviderException(FlutterError error) {
        this.error = error;
    }

    public static void throwUpstreamServerError(Response res) throws Exception {
        String stringError = res.errorBody() != null ? res.errorBody().string(): res.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(stringError);
        final var error = objectMapper.treeToValue(jsonNode, FlutterError.class);
        throw new ThirdPartyServiceProviderException(error);
    }

    public static void throwUpstreamServerError(int status, String message) throws Exception {
        final var error = new FlutterError();
        error.message = message;
        error.status = ""+status;
        throw new ThirdPartyServiceProviderException(error);
    }
    
    public FlutterError getFlutterError() {
        return error;
    }
}
