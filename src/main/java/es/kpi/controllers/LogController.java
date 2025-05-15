package es.kpi.controllers;

import es.kpi.dto.request.CreateLogDTO;
import es.kpi.services.KpiLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/log")
@Slf4j
/**
 * LogController is responsible for handling requests related to KPI logs.
 * It provides endpoints for creating, retrieving, and deleting KPI logs.
 */
public class LogController {

    private final KpiLogService kpiLogService;

    // Create a new KPI log
    @PostMapping("/create")
    public ResponseEntity<?> createLogs(@RequestBody CreateLogDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiLogService.createLog(dto);
            response.put("data", "KPI log created successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Get all logs by user ID
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiLogService.getAllLogsByUserId(userId));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Delete log by ID
    @DeleteMapping("/delete/{logId}")
    public ResponseEntity<?> deleteLog(@PathVariable Long logId) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiLogService.deleteLog(logId);
            response.put("data", "KPI log deleted successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }
}
