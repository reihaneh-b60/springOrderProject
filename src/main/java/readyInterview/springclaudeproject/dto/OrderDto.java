package readyInterview.springclaudeproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDto {

    @NotNull
    @NotBlank
    private String orderCode;

    @NotBlank
    @NotNull
    private String orderName;

}
