package com.codemiser.e_commerce_app.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // usable on methods
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTimeTracker {
}
