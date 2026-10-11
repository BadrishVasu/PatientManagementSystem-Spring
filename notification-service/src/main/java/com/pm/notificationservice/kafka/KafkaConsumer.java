package com.pm.notificationservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "patient-events")
    public void consumeEvent(byte[] serializedPatientEvent) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(serializedPatientEvent);
            log.info("Patient Event arrived and parsed: {}", patientEvent);
        } catch (InvalidProtocolBufferException e) {
            log.warn("Issue occurred: {}", e);
            throw new RuntimeException(e);
        }
    }
}
