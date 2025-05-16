package es.kpi.controllers;

import es.kpi.dto.request.CreateTargetDTO;
import es.kpi.services.KpiTargetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/target")
@Slf4j
@CrossOrigin("*")
/**
 * TargetController is responsible for handling requests related to target entities.
 * It provides endpoints for creating, updating, deleting, and retrieving target information.
 * <p>
 * The controller uses the TargetService to perform operations on target entities.
 * </p>
 */
public class TargetController {

    private final KpiTargetService kpiTargetService;

    // Create
    @PostMapping("/create")
    public ResponseEntity<?> createTarget(@Valid @RequestBody CreateTargetDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiTargetService.createTarget(dto);
            response.put("data", "KPI target created successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Fetch all by userId
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiTargetService.getAllTargetsByUserId(userId));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Fetch by userId and kpiId
    @GetMapping("/all/userId/{userId}/kpiId/{kpiId}")
    public ResponseEntity<?> getByUserIdAndKpiId(@PathVariable String userId, @PathVariable Long kpiId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiTargetService.getTargetByKpiIdAndUserId(kpiId, userId));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Fetch taget goal comparing with current KPI
    @GetMapping("/target/kpiId/{kpiId}")
    public ResponseEntity<?> getTargetGoalAndCurrent(@PathVariable Long kpiId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiTargetService.getTargetGoalByKPI(kpiId));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Delete
    @DeleteMapping("/delete/{targetId}")
    public ResponseEntity<?> deleteTarget(@PathVariable Long targetId) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiTargetService.deleteTarget(targetId);
            response.put("data", "KPI target deleted successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }
}
