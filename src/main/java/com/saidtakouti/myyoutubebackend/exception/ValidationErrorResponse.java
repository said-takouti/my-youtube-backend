package com.saidtakouti.myyoutubebackend.exception;

import java.util.Map;

public record ValidationErrorResponse(Map<String, String> errors){}