package aiss.grupo6.vimeoMiner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO: añadir mejor manejo de errores
@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Id not found")
public class ChannelNotFoundException extends Exception{
}
