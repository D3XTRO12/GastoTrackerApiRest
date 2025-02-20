package com.d3xtro.GastoTracker.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.d3xtro.GastoTracker.DTOs.IncomeDTO;
import com.d3xtro.GastoTracker.Models.Income;
import com.d3xtro.GastoTracker.Models.User;
import com.d3xtro.GastoTracker.Repositories.IncomeRepo;
import com.d3xtro.GastoTracker.Repositories.UserRepo;

import java.util.List;
@Service
public class IncomeService {

    @Autowired
    private IncomeRepo incomeRepo;

    @Autowired
    private UserRepo userRepo; // Necesario para manejar la relación con User

    // Obtener todos los ingresos
    public List<IncomeDTO> getAllIncomes() {
        return incomeRepo.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Obtener un ingreso por ID
    public IncomeDTO getIncomeById(Long id) {
        return incomeRepo.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));
    }

    // Obtener ingresos por ID de usuario
    public List<IncomeDTO> getIncomeByUserId(Long userId) {
        return incomeRepo.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Obtener ingresos por categoría
    public List<IncomeDTO> getIncomeByCategory(String category) {
        return incomeRepo.findByCategory(category).stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Crear un nuevo ingreso
    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        Income income = convertToEntity(incomeDTO);
        Income savedIncome = incomeRepo.save(income);
        return convertToDTO(savedIncome);
    }

    // Actualizar un ingreso existente
    public IncomeDTO updateIncome(Long id, IncomeDTO incomeDTO) {
        Income existingIncome = incomeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found with id: " + id));
        existingIncome.setName(incomeDTO.getName());
        existingIncome.setAmount(incomeDTO.getAmount());
        existingIncome.setCategory(incomeDTO.getCategory());

        // Actualizar la relación con User
        User user = userRepo.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + incomeDTO.getUserId()));
        existingIncome.setUser(user);

        Income updatedIncome = incomeRepo.save(existingIncome);
        return convertToDTO(updatedIncome);
    }

    // Eliminar un ingreso por ID
    public void deleteIncome(Long id) {
        incomeRepo.deleteById(id);
    }

    // Métodos de conversión entre Entidad y DTO
    private IncomeDTO convertToDTO(Income income) {
        return IncomeDTO.builder()
                .id(income.getId())
                .name(income.getName())
                .amount(income.getAmount())
                .category(income.getCategory())
                .userId(income.getUser().getId()) // Obtener el ID del usuario relacionado
                .build();
    }

    private Income convertToEntity(IncomeDTO incomeDTO) {
        // Obtener el usuario relacionado
        User user = userRepo.findById(incomeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + incomeDTO.getUserId()));

        return Income.builder()
                .id(incomeDTO.getId())
                .name(incomeDTO.getName())
                .amount(incomeDTO.getAmount())
                .category(incomeDTO.getCategory())
                .user(user) // Establecer la relación con User
                .build();
    }
}