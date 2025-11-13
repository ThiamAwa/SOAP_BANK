package com.groupeisi.m2gl.endpoint;

import com.groupeisi.m2gl.entities.GetSoldeRequest;
import com.groupeisi.m2gl.entities.SoldeResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BanqueEndpoint {

    private static final String NAMESPACE_URI = "http://www.esmt.com/banque";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSoldeRequest")
    public @ResponsePayload SoldeResponse getSolde(@RequestPayload GetSoldeRequest request) {
        SoldeResponse response = new SoldeResponse();

        long tel = request.getTel();
        long solde = 1000;

        response.setSolde(solde);
        return response;
    }
}
