package com.testproject.testproject.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello")
    public String helloWorld(){
        return "hello world\n";
    }

    @GetMapping(path = "helloworldbean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");

    }

    @GetMapping(path = "helloworld/pathvar/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
