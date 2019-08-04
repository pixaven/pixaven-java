package com.pixaven;

public interface Operations<T extends Operations> {
    T resize(OperationConfiguration configuration);
    T scale(OperationConfiguration configuration);
    T crop(OperationConfiguration configuration);
    T watermark(OperationConfiguration configuration);
    T mask(OperationConfiguration configuration);
    T stylize(OperationConfiguration configuration);
    T adjust(OperationConfiguration configuration);
    T auto(OperationConfiguration configuration);
    T border(OperationConfiguration configuration);
    T padding(OperationConfiguration configuration);
    T store(OperationConfiguration configuration);
    T output(OperationConfiguration configuration);
    T webhook(OperationConfiguration configuration);
    T cdn(OperationConfiguration configuration);
}