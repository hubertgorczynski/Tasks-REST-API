package com.crud.tasks.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Mail {
    @NotNull
    private String mailTo;

    private String toCc;

    @NotNull
    private String subject;

    @NotNull
    private String message;
}
