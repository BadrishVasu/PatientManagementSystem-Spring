package com.pm.billingservice.grpc;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(
            BillingRequest billingRequest,
            StreamObserver<BillingResponse> responseObserver) {

        log.info("createBillingAccount request received: {}", billingRequest.toString());

        // Business logic - e.g. save to DB, perform calculations, etc.
        // Create BillingResponse object for business logic
        BillingResponse response = BillingResponse.newBuilder()
//                .setAccountId("69696969")
                .setAccountId(billingRequest.getPatientId())
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

//    @Override
//    public void createBillingAccounts(
//            BillingRequest billingRequest,
//            StreamObserver<BillingResponse> responseObserver) {
//
//        log.info("createBillingAccounts request received: {}", billingRequest.toString());
//
//        int count = 0;
//        while (count < 10) {
//            BillingResponse response = BillingResponse.newBuilder()
//                    .setAccountId(billingRequest.getPatientId())
//                    .setStatus("ACTIVE "+count)
//                    .build();
//            responseObserver.onNext(response);
//            count++;
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                responseObserver.onError(e);
//                return;
//            }
//        }
//
//        responseObserver.onCompleted();
//
//    }
}
