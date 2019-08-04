package com.pixaven.support;

import static com.pixaven.Dynamic.dynamic;

import java.io.IOException;

import com.pixaven.Metadata;
import com.pixaven.Response;

public class ExceptionResponse implements Response {
    private final Exception e;

    public ExceptionResponse(Exception e) {
        this.e = e;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getMessage() {
        return e.getMessage();
    }

    @Override
    public Metadata getMetadata() {
        return DynamicMetadata.of(dynamic()
                                      .set("message", e.getMessage())
                                      .set("success", false));
    }

}
