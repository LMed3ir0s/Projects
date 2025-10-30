package med3ir0s.userService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // => Captura Erro Genérico (exceções não tratadas)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneralException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage(),
                extractPath(request),
                "GEN_500"
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    // => Captura Erro de Usuário não encontrado
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Usuário não encontrado",
                ex.getMessage(),
                extractPath(request),
                "USR_404"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // => Captura Erro de Credenciais inválidas
    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ApiError> handleInvalidCredentials(InvalidCredentialException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.UNAUTHORIZED.value(),
                "Credenciais inválidas",
                ex.getMessage(),
                extractPath(request),
                "AUTH_401"
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
    }

    // => Captura Erro de Username já existente
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUsernameExists(UsernameAlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Username já existe",
                ex.getMessage(),
                extractPath(request),
                "USR_400_DUP"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    // => Captura Erro de Role não encontrada
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiError> handleRoleNotFound(RoleNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Role não encontrada",
                ex.getMessage(),
                extractPath(request),
                "ROLE_400"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    // => Captura Erros de validação (DTOs com @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErros(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fieldErros.put(error.getField(), error.getDefaultMessage())
        );

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                "Um ou mais campos estão inválidos.",
                extractPath(request),
                "VAL_400"
        );

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", apiError);
        responseBody.put("fields", fieldErros);
        return ResponseEntity.badRequest().body(responseBody);
    }

    // *** => Metodo auxiliar extrai o caminho da requisição ***
    private String extractPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}
