package com.pm.patientservice.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import billing.BillingServiceGrpc.*;
import org.springframework.grpc.client.ImportGrpcClients;
import org.springframework.stereotype.Service;

@Service
@ImportGrpcClients(target = "billing_service", types = BillingServiceGrpc.BillingServiceBlockingStub.class)
public class BillingServiceGrpcClient {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub billingStub;

    public BillingServiceGrpcClient(BillingServiceBlockingStub billingStub) {
        this.billingStub = billingStub;
        log.info("Connecting to Billing Service GRPC service: {}", billingStub);
    }

    public BillingResponse createBillingAccount (String patientId, String name, String email) {
        BillingRequest billingRequest = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse billingResponse = billingStub.createBillingAccount(billingRequest);
        log.info("Received response from billing service via GRPC: {}", billingResponse);
        return billingResponse;
    }
}
