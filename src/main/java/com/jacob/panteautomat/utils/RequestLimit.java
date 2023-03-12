package com.jacob.panteautomat.utils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public class RequestLimit {

    public enum Type {

        BASICOBJECT {
            @Override
            public Bandwidth getLimit() {
                return Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(2)));
            }
        },
        BOTTLE {
            @Override
            public Bandwidth getLimit() {
                return Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(1)));
            }
        },
        CAN {
            @Override
            public Bandwidth getLimit() {
                return Bandwidth.classic(1, Refill.intervally(1, Duration.ofMillis(500)));
            }
        };
        public static Type resolveLimitFromRequestHeader(String objectType) {
            if (objectType == null || objectType.isEmpty()) {
                return BASICOBJECT;
            }else if (objectType.equals(RecyclableObjects.CAN.getString())) {
                return CAN;
            } else if (objectType.equals(RecyclableObjects.BOTTLE.getString())) {
                return BOTTLE;
            }
            return BASICOBJECT;
        }

        public abstract Bandwidth getLimit();
    }
}
