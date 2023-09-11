package ru.company.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class StringController {

    @PostMapping("/frequency")
    public Map<String, Integer> frequencyInString(@RequestParam(value = "str") String str){
        Map<Character, Integer> resultMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (c >= '0' && c <= '9' || c == '*' || c == '/' || c == '-' || c == '+' || c == '=') {
                continue;
            }
            resultMap.put(c, resultMap.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> sortedEntries = resultMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toList());
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : sortedEntries) {
            result.put(String.valueOf(entry.getKey()), entry.getValue());
        }

        return result;
    }
}
