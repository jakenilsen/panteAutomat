package com.jacob.panteautomat.service;

import com.jacob.panteautomat.utils.RequestLimit;
import io.github.bucket4j.Bucket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestLimitService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    private Bucket newBucket(String objectType) {
        RequestLimit.Type requestLimit = RequestLimit.Type.resolveLimitFromRequestHeader(objectType);
        return Bucket.builder()
                .addLimit(requestLimit.getLimit())
                .build();
    }
}
