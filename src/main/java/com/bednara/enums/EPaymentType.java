package com.bednara.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum EPaymentType {
        DEBITCARD("Bankomatkarte"),
        CREDITCARD("Kreditkarte"),
        PREPAYMENT("Vorauszahlung");

        private final String paymentType;

        private EPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        @Override
        public String toString() {
            return this.name();
        }

        public String getPaymentType() {
            return paymentType;
        }

        public static String getEPaymentTypeFromValue(String paymentType) {
            return Arrays.stream(EPaymentType.values())
                    .filter(f -> paymentType == f.getPaymentType()).map(EPaymentType::name)
                    .collect(Collectors.joining(","));
        }

    }

    

