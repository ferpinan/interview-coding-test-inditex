package ga.ferpinan.pricesrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
	private LocalDateTime timeStamp;
	private String message;
}
