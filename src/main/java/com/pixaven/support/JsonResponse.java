package com.pixaven.support;

import com.pixaven.Dynamic;
import com.pixaven.Metadata;
import com.pixaven.Response;

public class JsonResponse implements Response {
    private final Dynamic dynamic;

    public JsonResponse(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    @Override
    public String getMessage() {
        return dynamic.get("message");
    }

    @Override
    public boolean isSuccessful() {
        return dynamic.get("success");
    }

    @Override
    public Metadata getMetadata() {
        return DynamicMetadata.of(dynamic);
    }

}
