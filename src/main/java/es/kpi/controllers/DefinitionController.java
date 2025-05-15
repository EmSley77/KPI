package es.kpi.controllers;

import es.kpi.dto.request.CreateDefinitionDTO;
import es.kpi.services.KpiDefinitionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/definition")
/**
 * DefinitionController handles requests related to KPI definitions.
 * It provides endpoints for creating, retrieving, and deleting KPI definitions.
 */
public class DefinitionController {

    private final KpiDefinitionService kpiDefinitionService;

    // Create a new KPI definition
    @PostMapping("/create")
    public ResponseEntity<?> createDefinition(@RequestBody CreateDefinitionDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiDefinitionService.createDefinition(dto);
            response.put("data", "KPI Definition created successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Get all definitions by user ID
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiDefinitionService.getAllDefinitionsByUserId(userId));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Get a specific KPI definition by name and user ID
    @GetMapping("/all/userId/KPIDefinition/{userId}/{kpiDefinitionName}")
    public ResponseEntity<?> getAllByUserIdAndDefinitionName(
            @PathVariable String userId,
            @PathVariable String kpiDefinitionName) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", kpiDefinitionService.getKPIDefinitionByNameAndUserId(userId, kpiDefinitionName));
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }

    // Delete a KPI definition by ID
    @DeleteMapping("/delete/{definitionId}")
    public ResponseEntity<?> deleteDefinition(@PathVariable Long definitionId) {
        Map<String, Object> response = new HashMap<>();
        try {
            kpiDefinitionService.deleteCategory(definitionId);
            response.put("data", "KPI Definition deleted successfully");
            response.put("error", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
            return ResponseEntity.ok(response);
        }
    }
}
