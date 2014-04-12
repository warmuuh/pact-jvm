package au.com.dius.pact.consumer;

import au.com.dius.pact.model.*;

import java.util.Map;


public class ConsumerInteractionJavaDsl {
    public static PactVerification.VerificationResult pactVerified = PactVerification.PactVerified$.MODULE$;

    private String providerState;
    private String description;
    private Request request;
    private Response response;

    ConsumerInteractionJavaDsl(String providerState) {
        this.providerState = providerState;
    }

    public static ConsumerInteractionJavaDsl given(String providerState) {
        return new ConsumerInteractionJavaDsl(providerState);
    }

    public ConsumerInteractionJavaDsl uponReceiving(
        String description,
        String path,
        String method,
        Map<String, String> headers,
        String body) {

        this.description = description;
        request = Request$.MODULE$.apply(method, path, headers, body);
        return this;
    }

    public ConsumerInteractionJavaDsl willRespondWith(
        int status,
        Map<String, String> headers,
        String body) {

        response = Response$.MODULE$.apply(status, headers, body);
        return this;
    }

    public Interaction build() {
        return new Interaction(description, providerState, request, response);
    }
}
