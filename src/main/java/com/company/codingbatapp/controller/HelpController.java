package com.company.codingbatapp.controller;

import com.company.codingbatapp.entity.Help;
import com.company.codingbatapp.payload.ApiResponse;
import com.company.codingbatapp.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/help")
public class HelpController {
    @Autowired
    HelpService service;

    @GetMapping("")
    public HttpEntity<?> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?> getAll(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getById(id));
    }


    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody Help help) {
        ApiResponse apiResponse = service.add(help);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@Valid @RequestBody Help help, @PathVariable Integer id) {
        ApiResponse apiResponse = service.update(id, help);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
