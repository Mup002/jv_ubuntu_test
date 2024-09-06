package tmdtdemo.tmdt.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnotherServiceResponse {
    private Long id;
    private String description;
    private List<String> details = new ArrayList<>();
    private Double price;
    private String url;
}
