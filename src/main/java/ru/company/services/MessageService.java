package ru.company.services;


import org.springframework.stereotype.Service;
import ru.company.models.Message;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    public Map<Character, Integer> calculateSymbols(Message message) {
        Map<Character, Integer> resultMap = new HashMap<>();
        for (char c : message.getMessage().toCharArray()) {
            resultMap.put(c, resultMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sortedEntries = resultMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .toList();

        Map<Character, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
