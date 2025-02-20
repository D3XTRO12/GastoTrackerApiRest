package com.d3xtro.GastoTracker.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d3xtro.GastoTracker.DTOs.IncomeDTO;
import com.d3xtro.GastoTracker.Services.IncomeService;

@RestController
@RequestMapping("/income")
public class IncomeResource {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchIncome(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) Long userId,
                                               @RequestParam(required = false) String category,
                                               @RequestParam(required = false) String query){
        try{
            if (query==null){
                return ResponseEntity.badRequest().body("Query parameter is required");
            }
            switch (query.toLowerCase()){
                case "all":
                    return ResponseEntity.ok(incomeService.getAllIncomes());
                case "by-id":
                    if (id==null){
                        return ResponseEntity.badRequest().body("Id parameter is required");
                    }
                    return ResponseEntity.ok(incomeService.getIncomeById(id));
                case "by-userid":
                    if (userId==null){
                        return ResponseEntity.badRequest().body("Name parameter is required");
                    }
                    return ResponseEntity.ok(incomeService.getIncomeByUserId(userId));
                case "by-category":
                    if (category==null){
                        return ResponseEntity.badRequest().body("Category parameter is required");
                    }
                    return ResponseEntity.ok(incomeService.getIncomeByCategory(category));  
                default:
                    return ResponseEntity.badRequest().body("Invalid query parameter");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    
    }

    // POST /income
    @PostMapping("/add")
    public ResponseEntity<IncomeDTO> createIncome(@RequestBody IncomeDTO incomeDTO) {
        IncomeDTO createdIncome = incomeService.createIncome(incomeDTO);
        return ResponseEntity.ok(createdIncome);
    }

    // PUT /income/{id}
    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        IncomeDTO updatedIncome = incomeService.updateIncome(id, incomeDTO);
        return ResponseEntity.ok(updatedIncome);
    }

    // DELETE /income/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
