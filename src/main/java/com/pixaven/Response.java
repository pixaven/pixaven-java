package com.pixaven;

public interface Response  {
    boolean isSuccessful();
    String getMessage();
    Metadata getMetadata();
}
