import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.company.controller.MessageController;
import ru.company.models.Message;
import ru.company.services.MessageService;
import ru.company.utils.exceptions.MessageCalculatedException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StringControllerTest {

    @Mock
    MessageService messageService;

    @InjectMocks
    MessageController messageController;

    @Mock
    BindingResult bindingResult;

    @Test
    void frequencyInStringTest() {
        final String inputString = "aaaaabcccc";
        final Message message = new Message(inputString);

        when(messageService.calculateSymbols(message)).thenReturn(Map.of('a', 5, 'c', 4, 'b', 1));

        final Map<Character, Integer> result = messageController.frequencyInString(message, bindingResult);

        Map<Character, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put('a', 5);
        expectedResult.put('c', 4);
        expectedResult.put('b', 1);

        assertEquals(result, expectedResult);
    }

    @Test
    void frequencyInStringWithLengthLimitTest() {
        final String inputString = "aaaaabccccaaaaabccccaaaaabcccc";
        final Message message = new Message(inputString);

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(
                new FieldError("message", "Size", "Size should be <25 symbols")));

        assertThrows(MessageCalculatedException.class, () ->
            messageController.frequencyInString(message, bindingResult));

        verify(messageService, never()).calculateSymbols(message);
    }
}