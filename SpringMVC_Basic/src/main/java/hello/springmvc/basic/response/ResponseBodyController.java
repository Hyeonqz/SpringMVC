package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ResponseBodyController {

    //방법1
    @GetMapping("/response-body-stirng-v1")
    public void responseBody(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    //방법2
    @GetMapping("/response-body-stirng-v2")
    public ResponseEntity<String> responseBody2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //방벙3
    @ResponseBody
    @GetMapping("/response-body-stirng-v3")
    public String responseBody3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("A");
        helloData.setAge(23);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("A");
        helloData.setAge(23);
        return helloData;
    }
}
